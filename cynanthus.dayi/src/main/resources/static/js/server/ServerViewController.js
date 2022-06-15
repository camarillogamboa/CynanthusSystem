class ServerViewControllerChild extends Controller {

    #parent

    constructor(parent) {
        super();
        this.#parent = parent;
    }

    get parent() {
        return this.#parent;
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
        let serverAlias = this.parent.serverType.alias;

        httpClient.POST(`/${serverAlias}/${this.parent.serverId}`, serializeForm(form))
            .then(() => this.parent.loadPropertiesView($(`#propertiesLink`)[0]))
            .catch(error => console.log(error));
    }

    start() {
        super.start();
        loadDialogForm("#editPropertiesDialog", form => this.updateProperties(form), commonRejected);
    }

}

class ServerLogViewController extends ServerViewControllerChild {

    constructor(parent) {
        super(parent);
    }

}

class ServerViewController extends DelegationAndNavegationController {

    #serverId;
    #available;

    #onavailable;
    #onunavailable;

    #subscription;

    constructor(viewLoader, serverId) {
        super(viewLoader, "#serverMainContainer", new SelectionGroup('click', 'active'));
        this.#serverId = serverId;

        this.#available = undefined;

        this.#onavailable = [];
        this.#onunavailable = [];

        this.#onavailable.push(() => {
            $("#availableIndicatorContainer").html(`<i class="fas fa-2x fa-check-circle available-indicator"></i>`);
            $("#unavailableMessageContainer")[0].classList.add('d-none');
            let previusClass = this.findPreviusClass();
            $(`#serverLink-${this.#serverId}`)[0].classList.replace(previusClass, "bg-success");
        });

        this.#onunavailable.push(() => {
            $("#availableIndicatorContainer").html(`<i class="fas fa-2x fa-times-circle unavailable-indicator"></i>`);
            $("#unavailableMessageContainer")[0].classList.remove('d-none');
            let previusClass = this.findPreviusClass();
            $("#serverLink-" + this.#serverId)[0].classList.replace(previusClass, "bg-danger");
        });

        this.#subscription = null;

        this.delegateWrapper.controller = new ServerSummaryViewController(this);
    }

    get serverId() {
        return this.#serverId;
    }

    get available() {
        return this.#available;
    }

    get serverType() {
        return {
            type: "NONE",
            alias: "none"
        };
    }

    set available(available) {
        if (available) this.#onavailable.forEach(action => action());
        else this.#onunavailable.forEach(action => action());

        this.#available = available;
    }

    set onavailable(onavailable) {
        this.#onavailable.push(onavailable);
    }

    set onunavailable(onunavailable) {
        this.#onunavailable.push(onunavailable);
    }

    findPreviusClass() {
        if (this.#available === undefined) return "bg-info";
        else if (this.#available === false) return "bg-danger";
        else return "bg-success";
    }

    loadingView() {
        $(`#serverMainContainer`).html(loadingIndicator);
    }

    async loadSummaryView() {
        return super.loadSummaryView(
            `/server/${this.#serverId}/summary`,
            () => new ServerSummaryViewController(this)
        );
    }

    async loadPropertiesView(selectable) {
        this.loadingView();
        return this.loadView(
            `/server/${this.#serverId}/properties`,
            () => new PropertiesViewController(this),
            selectable
        );
    }

    async loadLogView(selectable) {
        this.navGroup.select(selectable);
        this.loadingView();
    }

    updateInfo(serverInfo) {
        $("#serverTitle").html(`${serverInfo.name}`);
        $("#serverAddress").html(`${serverInfo.address}:${serverInfo.port}`);
        $("#serverInfoText").html(serverInfo.info !== null ? serverInfo.info : "---");
        $(`#serverLink-${serverInfo.id}`).text(serverInfo.name);
    }

    updateThis(form) {
        httpClient.POST("/server", serializeForm(form))
            .then(response => response.json())
            .then(serverInfo => this.updateInfo(serverInfo))
            .catch(error => console.log(`Error al actualizar la información del servidor ${this.#serverId}. Error ${error}`));
    }

    deleteThis() {
        httpClient.DELETE(`/server/${this.#serverId}`)
            .then(() => appController.loadSummaryView())
            .then(() => appController.loadServerListView())
            .catch(error => console.log(`Error al eliminar ${this.#serverId}. Error: ${error}`))
    }

    searchAvailability() {
        stompClient.sendMessageTo(`/app/server/${this.#serverId}/state`, '');
    }

    start() {
        super.start();
        loadDialogForm('#updateServerDialog', form => this.updateThis(form));
        loadDialogForm('#deleteServerDialog', () => this.deleteThis());

        if (stompClient.connected) {
            this.#subscription = stompClient.subscribeTo(
                `/topic/server/${this.#serverId}/state`,
                message => this.available = JSON.parse(message.body)
            )
            this.searchAvailability();
        }
    }

    finalize() {
        $(`#serverLink-${this.#serverId}`)[0].classList.remove(this.findPreviusClass());

        if (this.#subscription != null) this.#subscription.unsubscribe();
        super.finalize();
    }

    toString() {
        return `${super.toString()}:${this.#serverId}`;
    }

}

class StorageServerViewController extends ServerViewController {

    #serverType;

    constructor(viewLoader, serverId) {
        super(viewLoader, serverId);

        this.#serverType = {
            type: "STORAGE",
            alias: "sordidus"
        };
    }

    get serverType() {
        return 'STORAGE';
    }

}

class StreamDataServerViewController extends ServerViewController {

    #serverType;

    constructor(viewLoader, serverId) {
        super(viewLoader, serverId);

        this.#serverType = {
            type: "STREAM_DATA",
            alias: "latiro"
        }
    }

    get serverType() {
        return this.#serverType;
    }

    async loadSensingNodesView(selectable) {
        return this.loadView(
            `/sensing`,
            () => new SensingNodesViewController(this),
            selectable
        );
    }
}

class ControlServerViewController extends ServerViewController {

    #serverType;

    constructor(viewLoader, serverId) {
        super(viewLoader, serverId);
        this.#serverType = {
            type: "CONTROL",
            alias: "stris"
        }
    }

    get serverType() {
        return this.#serverType;
    }

    async loadControlNodesView(selectable) {
        return this.loadView(
            `/control`,
            () => new SensingNodesViewController(this),
            selectable
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