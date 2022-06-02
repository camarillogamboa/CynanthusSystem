class InstrucctionsSummaryViewController extends ControllerService {

    constructor() {
        super();
    }

}

class InstructionSetViewController extends ControllerService {

    constructor(setId) {
        super();
        this._setId = setId;
    }

    get setId() {
        return this._setId;
    }

    deleteThis() {
        doDelete(`/sets/${this._setId}`)
            .then(response => {
                if (processResponse(response)) {
                    appController.delegate.loadSummaryView()
                        .then(() => appController.delegate.loadSetListView())
                        .catch(() => console.log("Error al recargar la vista"));
                }
            })
            .catch(error => console.log(`Error al eliminar ${this._setId}. Error: ${error}`));
    }

    deleteInstruction(instructionId) {
        doDelete(`/sets/instruction/${instructionId}`)
            .then(response => {
                if (processResponse(response)) {
                    reloadInstructionSet(this._setId);
                }
            }).catch(error => console.log(`Error al eliminar la instrucción ${instructionId}. Error: ${error}`));
    }

    updateInfo(set) {
        $('#currentSet-title').html(`${set.name}`);
        $('#currentSet-info').html(set.info !== null ? set.info : "...");
        $(`#setLink-${set.id}`).text(set.name);
    }

    updateThis(form) {
        doPostForm("/sets", form)
            .then(response => {
                if (processResponse(response)) {
                    response.json()
                        .then(set => this.updateInfo(set))
                        .catch(error => console.log(`Se produjo un error al cargar la vista del nuevo conjunto. Error: ${error}`));
                }
            })
            .catch(error => console.log(error));
    }

    saveInstruction(form) {
        console.log("añadiendo nueva instrucción");
        doPostForm(`/sets/${this._setId}`, form)
            .then(response => {
                if (processResponse(response)) {
                    reloadInstructionSet(this._setId);
                }
            })
            .catch(error => console.log(error));
    }

    start() {
        super.start();
        loadDialogForm('#addInstructionDialog', form => this.saveInstruction(form), commonRejected);
        loadDialogForm('#updateCurrentSetDialog', form => this.updateThis(form), commonRejected);
        loadDialogForm('#deleteCurrentSetDialog', () => this.deleteThis());

        let currentSetContainer = $('#currentSetContainer')[0];

        let updateInstructionDialogs = currentSetContainer.getElementsByClassName('update-list');
        let deleteInstructionDialogs = currentSetContainer.getElementsByClassName('delete-list');

        for (let i = 0; i < updateInstructionDialogs.length; i++) {
            let dialog = updateInstructionDialogs[i];
            loadDialogForm(`#${dialog.id}`, form => this.saveInstruction(form), commonRejected);
        }

        for (let i = 0; i < deleteInstructionDialogs.length; i++) {
            let dialog = deleteInstructionDialogs[i];
            let elementId = dialog.getAttribute('data-element-id');
            loadDialogForm(`#${dialog.id}`, () => this.deleteInstruction(elementId));
        }

    }

    toString() {
        return `${super.toString()}:${this._setId}`;
    }

}

class InstructionsViewController extends NavegationAndLoadController {

    constructor(viewLoader) {
        super(viewLoader, "#currentSetContainer");

        this._delegateWrapper.service = new InstrucctionsSummaryViewController();
    }

    async loadSetListView() {
        return new Promise((resolve, reject) => {
            this._viewLoader.loadAndPlaceTo("/sets/list", $("#instructionSetListContainer"))
                .then(() => console.log("Se cargó la lista de conjuntos"))
                .then(resolve)
                .catch(error => {
                    console.log(`Error al cargar la lista de conjuntos. Error ${error}`);
                    reject();
                })
        });
    }

    async loadSummaryView() {
        return super.loadSummaryView("/sets/summary", () => new InstrucctionsSummaryViewController());
    }

    async loadInstructionSetView(setId, selectable) {
        return this.loadView(
            `/sets/${setId}`,
            selectable,
            () => new InstructionSetViewController(setId)
        );
    }

    addSet(form) {
        doPostForm("/sets", form)
            .then(response => {
                if (processResponse(response)) {
                    this.loadSetListView()
                        .then(() => response.json())
                        .then(set => reloadInstructionSet(set.id))
                        .catch(error => console.log(`Se produjo un error al cargar la vista del nuevo conjunto. Error: ${error}`));
                }
            })
            .catch(error => console.log(error));
    }

    start() {
        super.start();
        loadDialogForm('#addSetDialog', form => this.addSet(form), commonRejected);
    }

}

function reloadInstructionSet(setId) {
    appController.delegate.loadInstructionSetView(setId, `#setLink-${setId}`);
}