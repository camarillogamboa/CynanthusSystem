/**
 *
 */
class DelegatorController extends Controller {

    #delegateWrapper;

    constructor() {
        super();
        this.#delegateWrapper = new ControllerWrapper();
    }

    get delegateWrapper() {
        return this.#delegateWrapper;
    }

    get delegate() {
        return this.#delegateWrapper.controller;
    }

    loadDelegate(delegate) {
        this.#delegateWrapper.finalize();
        this.#delegateWrapper.controller = delegate;
        this.#delegateWrapper.start();
    }

    start() {
        super.start();
        this.#delegateWrapper.start();
    }

    finalize() {
        this.#delegateWrapper.finalize();
        super.finalize();
    }

}