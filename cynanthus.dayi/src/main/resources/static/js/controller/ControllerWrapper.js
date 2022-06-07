class ControllerWrapper extends Controller {

    #controller;

    constructor() {
        super();
        this.#controller = null;
    }

    start() {
        if (this.#controller != null) this.#controller.start();
    }

    finalize() {
        if (this.#controller != null) this.#controller.finalize();
    }

    get controller() {
        return this.#controller;
    }

    set controller(controller) {
        this.#controller = controller;
    }

}