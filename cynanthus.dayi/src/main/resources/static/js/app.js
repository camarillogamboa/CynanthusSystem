console.log("Iniciando Cynanthus Dayi Client Application...");

class GeneralViewController extends DelegateAndSelectorController {

    constructor(viewLoader) {
        super(viewLoader, "#mainArea");
    }

    async loadServerListView() {
        return new Promise((resolve, reject) => {
            this._viewLoader.loadAndPlaceTo("/servers", $("#serverListContainer"))
                .then(() => {
                        console.log("Se cargó la lista de servidores");
                        if(this.delegate != null && this.delegate instanceof ServerViewController){
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

    async loadWelcomeView() {
        return new Promise((resolve, reject) => {
            this.loadView("/welcome")
                .then(() => this.loadDelegate(null))
                .then(() => this._navGroup.unselect())
                .then(resolve)
                .catch(error => {
                    console.log(`Error al cargar la vista de bienvenida. Error: ${error}`);
                    reject();
                })
        });
    }

    async loadServer(serverId, selectable) {
        return new Promise((resolve, reject) => {
            this.loadAndSelectView(`/server/${serverId}`, selectable)
                .then(() => this.loadDelegate(new ServerViewController(this._viewLoader, serverId)))
                .then(resolve)
                .catch(error => {
                    console.log(`Error al cargar la vista de servidor: ${serverId}. Error: ${error}`);
                    reject();
                });
        });
    }

    async loadInstructions(selectable) {
        return new Promise((resolve, reject) => {
            this.loadAndSelectView("/sets", selectable)
                .then(() => this.loadDelegate(new InstructionsViewController(this._viewLoader)))
                .then(resolve)
                .catch(error => {
                    console.log(`Error al cargar la vista de instrucciones. Error: ${error}`);
                    reject();
                });
        });
    }

    async loadUsers(selectable) {
        return new Promise((resolve, reject) => {
            this.loadAndSelectView("/users", selectable)
                .then(() => this.loadDelegate(new UsersViewController()))
                .then(resolve)
                .catch(error => {
                    console.log(`Error al cargar la vista de usuarios. Error: ${error}`);
                    reject();
                });
        });
    }

    addServer(form) {
        console.log("GUARDANDO");

        doPostForm("/server", form)
            .then(response => {
                if (processResponse(response)) {
                    this.loadServerListView()
                        .then(() => response.json())
                        .then(serverInfo => this.loadServer(serverInfo.id, `#serverLink-${serverInfo.id}`))
                        .catch(error => console.log(error));
                }
            })
            .catch(error => console.log(error));
    }

    start() {
        super.start();
        this.loadServerListView();
        loadDialogForm('#addServerDialog', form => this.addServer(form), commonRejected);
    }

    toString() {
        return "GeneralViewController";
    }

}

let appController = new GeneralViewController(new ViewLoader(null, autoCetenredFailLoadMessage));

$(document).ready(() => appController.start());
