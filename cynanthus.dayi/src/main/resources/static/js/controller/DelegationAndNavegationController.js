/**
 *
 */
class DelegationAndNavegationController extends ViewDelegatorController {

    #navGroup;

    constructor(viewLoader, viewAreaId, selectionGroup) {
        super(viewLoader, viewAreaId);
        this.#navGroup = selectionGroup || new SelectionGroup('active', 'bg-info');
    }

    get navGroup() {
        return this.#navGroup;
    }

    async loadSummaryView(url, controllerFactory) {
        return new Promise((resolve, reject) => {
            super.loadView(url, controllerFactory)
                .then(resolve)
                .catch(error => {
                    console.log(`Error al cargar la vista de bienvenida. Error: ${error}`);
                    reject();
                })
        });
    }

    async loadView(url, controllerFactory, selectable) {
        if (selectable != null) this.#navGroup.select(selectable);
        return super.loadView(url, controllerFactory);
    }

}