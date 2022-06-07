/**
 *
 */
class InstructionSetViewController extends Controller {

    #parent;
    #setId;

    constructor(parent, setId) {
        super();
        this.#parent = parent;
        this.#setId = setId;
    }

    deleteThis() {
        httpClient.DELETE(`/set/${this.#setId}`)
            .then(() => this.#parent.loadSetListView())
            .then(() => this.#parent.loadSummaryView())
            .catch(error => console.log(`Error al eliminar ${this.#setId}. Error: ${error}`));
    }

    deleteInstruction(instructionId) {
        console.log("ELIMINANDO " + instructionId);
        httpClient.DELETE(`/set/instruction/${instructionId}`)
            .then(() => this.#parent.loadInstructionSetView(this.#setId, `#setLink-${this.#setId}`))
            .catch(error => console.log(`Error al eliminar la instrucción ${instructionId}. Error: ${error}`));
    }

    updateInfo(set) {
        $('#currentSet-title').html(`${set.name}`);
        $('#currentSet-info').html(set.info !== null ? set.info : "---");
        $(`#setLink-${set.id}`).text(set.name);
    }

    updateThis(form) {
        httpClient.POST("/set", serializeForm(form))
            .then(response => response.json())
            .then(set => this.updateInfo(set))
            .catch(error => console.log(`Se produjo un error al cargar la vista del nuevo conjunto. Error: ${error}`));
    }

    saveInstruction(form) {
        console.log("GUARDANDO");
        httpClient.POST(`/set/${this.#setId}`, serializeForm(form))
            .then(() => this.#parent.loadInstructionSetView(this.#setId, `#setLink-${this.#setId}`))
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
            loadDialogForm(`#${dialog.id}`, form => this.saveInstruction(form), form => commonRejected(form));
        }

        for (let i = 0; i < deleteInstructionDialogs.length; i++) {
            let dialog = deleteInstructionDialogs[i];
            let elementId = dialog.getAttribute('data-element-id');
            loadDialogForm(`#${dialog.id}`, () => this.deleteInstruction(elementId));
        }

    }

    toString() {
        return `${super.toString()}:${this.#setId}`;
    }

}