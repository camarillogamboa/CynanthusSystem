/**
 *
 */
class ViewDelegatorController extends DelegatorController {

    #viewLoader;
    #viewAreaId;

    constructor(viewLoader, viewAreaId) {
        super();
        this.#viewLoader = viewLoader;
        this.#viewAreaId = viewAreaId;
    }

    get viewLoader() {
        return this.#viewLoader;
    }

    async loadView(url, controllerFactory) {
        return this.#viewLoader
            .loadAndPlaceTo(url, $(this.#viewAreaId))
            .then(() => this.loadDelegate(controllerFactory()));
    }

}