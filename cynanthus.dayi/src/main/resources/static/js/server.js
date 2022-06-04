class ServerViewControllerChild extends Service {

    constructor(parent) {
        super();
        this._parent = parent;
    }

}

class ServerSummaryViewController extends ServerViewControllerChild {

    constructor(parent) {
        super(parent);
    }

}

class SensingNodesViewController extends ServerViewControllerChild {

    constructor(parent) {
        super(parent);
    }

    start() {
        super.start();
    }

    finalize() {
        super.finalize();
    }

}

class ControlNodesViewController extends ServerViewControllerChild {

    constructor(parent) {
        super(parent);
    }

    start() {
        super.start();
    }

    finalize() {
        super.finalize();
    }

}

class PropertiesViewController extends ServerViewControllerChild {

    constructor(parent) {
        super(parent);
    }

    updateProperties(form) {
        console.log("actualizando propiedades");
    }

    start() {
        super.start();
        loadDialogForm("#editPropertiesDialog", form => this.updateProperties(form), commonRejected);
    }

}

class ServerLogViewController extends Service {

    constructor(serverId) {
        super();
        this._serverId = serverId;
    }

}

class ServerViewController extends NavegationAndLoadController {

    constructor(viewLoader, serverId) {
        super(viewLoader, "#serverMainContainer", new SelectionGroup('click', 'active'));
        this._serverId = serverId;
        this._available = undefined;

        this._onavailable = [];
        this._onunavailable = [];

        this._onavailable.push(() => {
            $("#availableIndicatorContainer").html(`<i class="fas fa-2x fa-check-circle available-indicator"></i>`);
            $("#unavailableMessageContainer")[0].classList.add('d-none');
            let previusClass = this.findPreviusClass();
            $(`#serverLink-${this._serverId}`)[0].classList.replace(previusClass, "bg-success");
        });

        this._onunavailable.push(() => {
            $("#availableIndicatorContainer").html(`<i class="fas fa-2x fa-times-circle unavailable-indicator"></i>`);
            $("#unavailableMessageContainer")[0].classList.remove('d-none');
            let previusClass = this.findPreviusClass();
            $("#serverLink-" + this._serverId)[0].classList.replace(previusClass, "bg-danger");
        })

        this._webSocket = new WebSocketConnector('/dayi-socket');

        this._subscription = null;

        this._delegateWrapper.service = new ServerSummaryViewController(this);
    }

    get serverId() {
        return this._serverId;
    }

    get serverType() {
        return 'NONE';
    }

    get isAvailable() {
        return this._available;
    }

    set available(available) {
        if (available) this._onavailable.forEach(action => action());
        else this._onunavailable.forEach(action => action());

        this._available = available;
    }

    set onavailable(onavailable) {
        this._onavailable.push(onavailable);
    }

    set onunavailable(onunavailable) {
        this._onunavailable.push(onunavailable);
    }

    get webSocket() {
        return this._webSocket;
    }

    loading() {
        $(`#serverMainContainer`).html(loadingIndicator);
    }

    async loadSummaryView() {
        return super.loadSummaryView(
            `/server/${this._serverId}/summary`,
            () => new ServerSummaryViewController(this)
        );
    }

    findPreviusClass() {
        if (this._available === undefined) return "bg-info";
        else if (this._available === false) return "bg-danger";
        else return "bg-success";
    }

    async loadPropertiesView(selectable) {
        this.loading();
        return this.loadView(
            `/server/${this._serverId}/properties`,
            selectable,
            () => new PropertiesViewController(this)
        );
    }

    loadLogView(selectable) {
        this._navGroup.select(selectable);
        this.loading();
    }

    updateInfo(serverInfo) {
        $("#serverTitle").html(`${serverInfo.name}`);
        $("#serverAddress").html(`${serverInfo.address}:${serverInfo.port}`);
        $("#serverInfoText").html(serverInfo.info !== null ? serverInfo.info : "---");
        $(`#serverLink-${serverInfo.id}`).text(serverInfo.name);
    }

    updateThis(form) {
        doPostForm("/server", form)
            .then(response => {
                if (processResponse(response)) {
                    response.json().then(serverInfo => this.updateInfo(serverInfo)).catch(error => console.log(error));
                }
            })
            .catch(error => console.log(error));
    }

    deleteThis() {
        doDelete(`/server/${this._serverId}`)
            .then(response => {
                if (processResponse(response)) {
                    appController.loadSummaryView()
                        .then(() => appController.loadServerListView())
                        .catch(() => console.log("Error al recargar la vista"));
                }
            })
            .catch(error => console.log(`Error al eliminar ${this._serverId}. Error: ${error}`));
    }

    start() {
        super.start();
        loadDialogForm('#updateServerDialog', form => this.updateThis(form));
        loadDialogForm('#deleteServerDialog', () => this.deleteThis());

        this._webSocket.connectAndSubscribeTo(
            `/topic/server/${this._serverId}/state`,
            message => this.available = JSON.parse(message.body)
        )
            .then(subscription => this._subscription = subscription)
            .then(() => this._webSocket.sendMessageTo(`/app/server/${this._serverId}/state`, ''))
            .catch(error => console.log(error));
    }

    finalize() {
        $(`#serverLink-${this._serverId}`)[0].classList.remove(this.findPreviusClass());

        if (this._subscription != null) this._subscription.unsubscribe();
        this._webSocket.disconnect();
        super.finalize();
    }

    toString() {
        return `${super.toString()}:${this._serverId}`;
    }

}

class StorageServerViewController extends ServerViewController {

    constructor(viewLoader, serverId) {
        super(viewLoader, serverId);
    }

    get serverType() {
        return 'STORAGE';
    }

}

class StreamDataServerViewController extends ServerViewController {

    constructor(viewLoader, serverId) {
        super(viewLoader, serverId);
    }

    get serverType() {
        return 'STREAM_DATA';
    }

    loadSensingNodesView(selectable) {
        return this.loadView(
            `/sensing`,
            selectable,
            () => new SensingNodesViewController(this)
        );
    }
}

class ControlServerViewController extends ServerViewController {

    constructor(viewLoader, serverId) {
        super(viewLoader, serverId);
    }

    get serverType() {
        return 'CONTROL';
    }

    async loadControlNodesView(selectable) {
        return this.loadView(
            `/control`,
            selectable,
            () => new SensingNodesViewController(this)
        );
    }

}

function createServerViewController(viewLoader, serverId, serverType) {
    switch (serverType) {
        case 'STORAGE':
            return new StorageServerViewController(viewLoader, serverId);
        case 'STREAM_DATA':
            return new StreamDataServerViewController(viewLoader, serverId);
        case 'CONTROL':
            return new ControlServerViewController(viewLoader, serverId);
        default:
            throw `Tipo de servidor ${serverType} no reconocido`;
    }
}