console.log("Iniciando Cynanthus Dayi Client Application...");

class GeneralViewController extends NavegationAndLoadController {

    constructor(viewLoader) {
        super(viewLoader, "#mainArea");
        this._delegateWrapper.service = new SummaryViewController();
    }

    async loadServerListView() {
        return new Promise((resolve, reject) => {
            this._viewLoader.loadAndPlaceTo("/server/list", $("#serverListContainer"))
                .then(() => {
                    console.log("Se cargó la lista de servidores");
                    if (this.delegate instanceof ServerViewController) {
                        this._navGroup.select($(`#serverLink-${this.delegate.serverId}`)[0]);
                    }
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
            selectable,
            viewLoader => createServerViewController(viewLoader, serverId, serverType)
        );
    }

    async loadInstructionsView(selectable) {
        return this.loadView(
            `set`,
            selectable,
            viewLoader => new InstructionsViewController(viewLoader)
        );
    }

    async loadUsersView(selectable) {
        return this.loadView(
            `/user`,
            selectable,
            viewLoader => new UsersViewController(viewLoader)
        );
    }

    addServer(form) {
        doPostForm("/server", form)
            .then(response => {
                if (processResponse(response)) {
                    resetForm(form);
                    this.loadServerListView()
                        .then(() => response.json())
                        .then(serverInfo => this.loadServerView(serverInfo.id, serverInfo.serverType, `#serverLink-${serverInfo.id}`))
                        .catch(error => console.log(`Se produjo un error al cargar la vista del nuevo servidor. Error: ${error}`));
                }
            })
            .catch(error => console.log(error));
    }

    start() {
        super.start();
        loadDialogForm('#addServerDialog', form => this.addServer(form), commonRejected);
    }

}

let appController = new GeneralViewController(new ViewLoader(null, autoCetenredFailLoadMessage));

$(document).ready(() => appController.start());
