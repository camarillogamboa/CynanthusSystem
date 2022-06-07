class Controller {

    start() {
        console.log(`Iniciando controlador ${this}`);
    }

    finalize() {
        console.log(`Finalizando controlador ${this}`);
    }

    toString() {
        return `${this.constructor.name}`;
    }

}