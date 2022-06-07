/**
 *
 */
class StompClient {

    #socketPath;
    #stompClient;

    #connected;

    constructor(socketPath) {
        this.#socketPath = socketPath;
        this.#stompClient = null;

        this.#connected = false;
    }

    get connected() {
        return this.#connected;
    }

    connect() {
        let socket = new SockJS(this.#socketPath);
        this.#stompClient = Stomp.over(socket);

        return new Promise((resolve, reject) => {
            this.#stompClient.connect(
                {},
                frame => {
                    this.#connected = true;
                    resolve(frame);
                },
                error => {
                    this.#connected = false;
                    reject(error);
                }
            );
        });
    }

    subscribeTo(subscriptionPath, messageConsumer) {
        return this.#stompClient.subscribe(subscriptionPath, messageConsumer);
    }

    unsubscribe(id) {
        this.#stompClient.unsubscribe(id);
    }

    connectAndSubscribeTo(subscriptionPath, messageConsumer) {
        return this.connect().then(() => this.subscribeTo(subscriptionPath, messageConsumer));
    }

    sendMessageTo(path, message) {
        this.#stompClient.send(path, {}, JSON.stringify(message));
    }

    disconnect() {
        return new Promise((resolve, reject) => {
            try {
                if (this.#stompClient != null) this.#stompClient.disconnect(resolve);
                else resolve();
                this.#stompClient = null;
            } catch (error) {
                reject(error);
            }
        });
    }

}