console.log("Iniciando Cynanthus Dayi Client Application...");

/*--------------------------------------------------GENERAL-VIEW------------------------------------------------------*/
class GeneralViewController extends DelegateAndSelectorController {

    constructor(viewLoader) {
        super(viewLoader, "#mainArea");
    }

    loadServerListView() {
        this._viewLoader.loadAndPlaceTo("/servers", $("#serverListContainer"));
    }

    loadServer(idServer, selectable) {
        this.loadAndSelectView(`/server/${idServer}`, selectable);
        this.loadDelegate(new ServerViewController(this._viewLoader, idServer, "/dayi-socket"));
    }

    loadInstructions(selectable) {
        this.loadAndSelectView("/sets", selectable);
        this.loadDelegate(new InstructionsViewController(this._viewLoader));
    }

    loadUsers(selectable) {
        this.loadAndSelectView("/users", selectable);
        this.loadDelegate(new UsersViewController());
    }

    start() {
        super.start();
        this.loadServerListView();
    }

    toString() {
        return "GeneralViewController";
    }

}

let appController = new GeneralViewController(new ViewLoader(null, autoCetenredFailLoadMessage));

$(document).ready(() => appController.start());
