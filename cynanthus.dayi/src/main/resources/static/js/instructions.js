class InstructionSetViewController extends Service {

    constructor(setId) {
        super();
        this._setId = setId;
    }

    deleteThis() {
        console.log("Delete Set " + this._setId);
        doDelete(`/sets/${this._setId}`)
            .then(response => {
                if (processResponse(response)) {
                    appController.loadInstructions('#instructionsLink');
                }
            })
            .catch(error => console.log(`Error al eliminar ${this._setId}. Error: ${error}`));
    }

    deleteInstruction(instructionId) {
        doDelete(`/sets/instruction/${instructionId}`)
            .then(response => {
                if (processResponse(response)) {
                    reloadAndSelect(this._setId);
                }
            }).catch(error => console.log(`Error al eliminar la instrucción ${instructionId}. Error: ${error}`));
        console.log("Delete instruction " + instructionId);
    }

    updateThis(form) {
        appController.delegate.addSet(form);
    }

    saveInstruction(form) {
        console.log("añadiendo nueva instrucción");
        doPostForm(`/sets/${this._setId}`, form)
            .then(response => {
                if (processResponse(response)) {
                    reloadAndSelect(this._setId);
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
        return "InstructionSetViewController: " + this._setId;
    }

}

class InstructionsViewController extends DelegateAndSelectorController {

    constructor(viewLoader) {
        super(viewLoader, "#currentSetContainer");
    }

    async loadInstructionSet(setId, selectable) {
        return new Promise((resolve, reject) => {
            this.loadAndSelectView(`/sets/${setId}`, selectable)
                .then(() => this.loadDelegate(new InstructionSetViewController(setId)))
                .then(resolve)
                .catch(error => {
                    console.log(`Error al cargar vista de conjunto de instruccion: ${setId}. Error: ${error}`);
                    reject();
                });
        });
    }

    addSet(form) {
        console.log("GUARDANDO");

        doPostForm("/sets", form)
            .then(response => {
                if (processResponse(response)) {
                    response.json()
                        .then(setId => reloadAndSelect(setId))
                        .catch(error => console.log(`Se produjo un error al cargar la vista del nuevo conjunto. Error: ${error}`));
                }
            })
            .catch(error => console.log(error));
    }

    start() {
        super.start();
        loadDialogForm('#addSetDialog', form => this.addSet(form), commonRejected);
    }

    toString() {
        return "InstructionsViewController";
    }

}

async function reloadAndSelect(setId) {
    return appController.loadInstructions("#instructionsLink")
        .then(() => appController.delegate.loadInstructionSet(setId, `#setLink-${setId}`));
}