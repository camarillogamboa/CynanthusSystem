console.log("Iniciando Cynanthus Dayi Client Application...");

/**
 * Controlador general de la aplicación
 */
class GeneralViewController extends DelegationAndNavegationController {

    constructor(viewLoader) {
        super(viewLoader, "#mainArea");
        this.delegateWrapper.controller = new SummaryViewController();
    }

    async loadServerListView() {
        return new Promise((resolve, reject) => {
            this.viewLoader.loadAndPlaceTo("/server/list", $("#serverListContainer"))
                .then(() => {
                    console.log("Se cargó la lista de servidores");
                    if (this.delegate instanceof ServerViewController)
                        this.navGroup.select($(`#serverLink-${this.delegate.serverId}`)[0]);
                })
                .then(resolve)
                .catch(error => {
                    console.log(`Error al cargar lista de servidores. Error: ${error}`);
                    reject();
                });
        });
    }

    async loadSummaryView() {
        return super.loadSummaryView("/summary", () => new SummaryViewController());
    }

    async loadServerView(serverId, serverType, selectable) {
        return this.loadView(
            `/server/${serverId}`,
            () => createServerViewController(this.viewLoader, serverId, serverType),
            selectable
        );
    }

    async loadInstructionsView(selectable) {
        return this.loadView(
            `set`,
            () => new InstructionsViewController(this.viewLoader),
            selectable
        );
    }

    async loadUsersView(selectable) {
        return this.loadView(
            `/user`,
            () => new UsersViewController(this.viewLoader),
            selectable
        );
    }

    addServer(form) {
        httpClient.POST("/server", serializeForm(form))
            .then(response => {
                resetForm(form);
                return this.loadServerListView().then(() => response.json());
            })
            .then(serverInfo => this.loadServerView(serverInfo.id, serverInfo.serverType, `#serverLink-${serverInfo.id}`))
            .catch(error => console.log(`Se produjo un error al cargar la vista del nuevo servidor. Error: ${error}`));
    }

    start() {
        super.start();
        loadDialogForm('#addServerDialog', form => this.addServer(form), commonRejected);
    }

}

var httpClient = new HttpClientRedirector()

var stompClient = new StompClient('/dayi-socket');

var appController = new GeneralViewController(
    new ViewLoader(httpClient, null)
);

$(document).ready(() => {
    stompClient.connect()
        .then(() => console.log(`Cliente Stomp Conectado`))
        .catch(error => console.log(`Error al conectar el cliente stomp. Error ${error}`));
    appController.start();
});
