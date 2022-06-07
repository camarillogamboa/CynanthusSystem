class HttpError extends Error {

    #status

    constructor(message, statusCode) {
        super(message);
        this.name = this.constructor.name;
        this.#status = statusCode;
    }

    get status() {
        return this.#status;
    }

}