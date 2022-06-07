/**
 *
 */
class InstructionsViewController extends DelegationAndNavegationController {

    constructor(viewLoader) {
        super(viewLoader, "#currentSetContainer");

        this.delegateWrapper.controller = new InstrucctionsSummaryViewController();
    }

    async loadSetListView() {
        return new Promise((resolve, reject) => {
            this.viewLoader.loadAndPlaceTo("/set/list", $("#instructionSetListContainer"))
                .then(() => console.log("Se cargó la lista de conjuntos"))
                .then(resolve)
                .catch(error => {
                    console.log(`Error al cargar la lista de conjuntos. Error ${error}`);
                    reject();
                })
        });
    }

    async loadSummaryView() {
        return super.loadSummaryView("/set/summary", () => new InstrucctionsSummaryViewController());
    }

    async loadInstructionSetView(setId, selectable) {
        return this.loadView(
            `/set/${setId}`,
            () => new InstructionSetViewController(this, setId),
            selectable
        );
    }

    addSet(form) {
        httpClient.POST("/set", serializeForm(form))
            .then(response => {
                resetForm(form);
                return this.loadSetListView().then(() => response.json())
            })
            .then(set => this.loadInstructionSetView(set.id, `#setLink-${set.id}`))
            .catch(error => console.log(`Se produjo un error al cargar la vista del nuevo conjunto. Error: ${error}`));
    }

    start() {
        super.start();
        loadDialogForm('#addSetDialog', form => this.addSet(form), commonRejected);
    }

}