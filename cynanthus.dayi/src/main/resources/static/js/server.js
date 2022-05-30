class ServerViewController extends DelegateAndSelectorController {

    constructor(viewLoader, serverId, socketPath) {
        super(viewLoader, "", new SelectionGroup('click', 'active'));
        this._serverId = serverId;
        this._available = undefined;
        this._webSocket = new WebSocketConnector("/dayi-socket");
    }

    get serverId(){
        return this._serverId;
    }

    setToAvailable() {
        $("#availableIndicatorE").html(`<i class="fas fa-2x fa-check-circle available-indicator"></i>`);

        let previusClass = this.findPreviusClass(this._available);

        $("#server-" + this._serverId)[0].classList.replace(previusClass, "bg-success");
        this._available = true;
    }

    setToUnavailable() {
        $("#availableIndicatorE").html(`<i class="fas fa-2x fa-times-circle unavailable-indicator"></i>`);
        $("#unavailableMessageE").html(
            `<div class="alert alert-warning rounded-0 border-left-0 border-right-0 p-2 w-100">
            <p class="w-100 m-auto text-center">
            No se logró establecer comunicación con el servidor.
            Verifique la dirección y puerto de referencia o asegurese que el microservicio correspondiente se esté ejecutando
            </p>
            </div>`
        );

        let previusClass = this.findPreviusClass(this._available);

        $("#server-" + this._serverId)[0].classList.replace(previusClass, "bg-success");
        this._available = false;
    }

    findPreviusClass(available) {
        if (available === undefined) return "bg-info";
        else if (available === false) return "bg-danger";
        else return "succes";
    }

    updateServerInfo(serverInfo) {
        $("#serverNameE").html(`${serverInfo.name}`);
        $("#addressE").html(`${serverInfo.address}:${serverInfo.port}`);
        $("#serverInfoE").html(serverInfo.info !== null ? serverInfo.info : "No hay información")
    }

    updateAvailable(available) {
        if (available) this.setToAvailable();
        else this.setToUnavailable();
    }

    setServerMainContent(innerHtml) {
        $("#serverMainContentE").html(innerHtml);
    }

    loadSensingNodes(serverId, selectable) {
        this.loadAndSelectView("", selectable);
    }

    loadControlNodes(serverId, selectable) {
        this.loadAndSelectView("", selectable);
    }

    loadProperties(serverId, selectable) {
        this.loadAndSelectView("", selectable);
    }

    loadLog(serverId, selectable) {
        this.loadAndSelectView("", selectable);
    }

    deleteThis() {
        doDelete(`/server/${this._serverId}`)
            .then(response => {
                if (processResponse(response)) {
                    appController.loadServerListView()
                        .then(() => appController.loadWelcomeView())
                        .catch(() => console.log("Error al recargar la vista"));
                }
            })
            .catch(error => console.log(`Error al eliminar ${this._serverId}. Error: ${error}`));
    }

    start() {
        super.start();
        loadDialogForm('#deleteServerDialog', () => this.deleteThis());
    }

    toString() {
        return "ServerViewController: " + this._serverId;
    }

}