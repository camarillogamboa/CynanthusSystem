class ServerViewControllerChild extends ControllerService {

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

}

class ServerLogViewController extends ControllerService {

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

        this._delegateWrapper.service = new ServerSummaryViewController(this);

        this._webSocket = new WebSocketConnector('/dayi-socket');
    }

    get isAvailable() {
        return this._available;
    }

    get webSocket() {
        return this._webSocket;
    }

    async loadSummaryView() {
        return super.loadSummaryView(
            `/servers/${this._serverId}/summary`,
            () => new ServerSummaryViewController(this)
        );
    }

    get serverId() {
        return this._serverId;
    }

    loading() {
        $(`#serverMainContainer`).html(loadingIndicator);
    }

    setToAvailable() {
        $("#availableIndicatorContainer").html(`<i class="fas fa-2x fa-check-circle available-indicator"></i>`);
        $("#unavailableMessageContainer")[0].classList.add('d-none');

        let previusClass = this.findPreviusClass(this._available);

        $(`#serverLink-${this._serverId}`)[0].classList.replace(previusClass, "bg-success");
        this._available = true;
    }

    setToUnavailable() {
        $("#availableIndicatorContainer").html(`<i class="fas fa-2x fa-times-circle unavailable-indicator"></i>`);
        $("#unavailableMessageContainer")[0].classList.remove('d-none');

        let previusClass = this.findPreviusClass(this._available);

        $("#server-" + this._serverId)[0].classList.replace(previusClass, "bg-success");
        this._available = false;
    }

    findPreviusClass(available) {
        if (available === undefined) return "bg-info";
        else if (available === false) return "bg-danger";
        else return "succes";
    }

    updateAvailable(available) {
        if (available) this.setToAvailable();
        else this.setToUnavailable();
    }

    setServerMainContent(innerHtml) {
        $("#serverMainContentE").html(innerHtml);
    }

    loadPropertiesView(selectable) {
        this._navGroup.select(selectable);
        this.loading();
    }

    loadLogView(selectable) {
        this._navGroup.select(selectable);
        this.loading();
    }

    updateInfo(serverInfo) {
        $("#serverTitle").html(`${serverInfo.name}`);
        $("#serverAddress").html(`${serverInfo.address}:${serverInfo.port}`);
        $("#serverInfoText").html(serverInfo.info !== null ? serverInfo.info : "");
        $(`#serverLink-${serverInfo.id}`).text(serverInfo.name);
    }

    updateThis(form) {
        doPostForm("/servers", form)
            .then(response => {
                if (processResponse(response)) {
                    response.json().then(serverInfo => this.updateInfo(serverInfo)).catch(error => console.log(error));
                }
            })
            .catch(error => console.log(error));
    }

    deleteThis() {
        doDelete(`/servers/${this._serverId}`)
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
    }

    toString() {
        return `${super.toString()}:${this._serverId}`;
    }

}

class StorageServerViewController extends ServerViewController {

    constructor(viewLoader, serverId) {
        super(viewLoader, serverId);
    }

}

class StreamDataServerViewController extends ServerViewController {

    constructor(viewLoader, serverId) {
        super(viewLoader, serverId);
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