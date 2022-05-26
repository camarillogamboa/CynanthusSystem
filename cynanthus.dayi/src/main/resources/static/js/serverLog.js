console.log("Cargando script de registro de servidor");

let group = new SelectionGroup('click','active','bg-info');

class LogViewBuilder {

    build(logFileNames) {
        return `
            <div class="row h-100 m-0">
                ${this.buildListPane(logFileNames)}
                <div class="col-md-10 p-1">
                    <div>
                        
                    </div>
                </div>
            </div>
        `;
    }

    buildListPane(logFileNames) {
        return `
            <div class="col-md-2 p-0 border-right overflow-auto">
                <nav class="navbar navbar-light p-1 w-100 align-items-start">
                    <nav class="nav nav-pills flex-column w-100 m-0">
                        <ul class="row m-0 p-0">${this.buildList(logFileNames)}</ul>
                    </nav>
                </nav>
            </div>
        `;
    }

    buildList(logFileNames) {
        let html = "";


        logFileNames.forEach(fileName => {
            html +=
                `<li class='nav-item w-100 pb-1'>
                <a 
                    id="${fileName}"
                    class="nav-link section-bar text-center"
                    style="font-size: 11px;"
                   onclick="group.select(this)"
                >${fileName}</a>
                </li>`;
        })

        return html;
    }

}

function registerSubscription(idServerInfo) {
    console.log("ServerLog: haciendo subscripción con el id " + idServerInfo);
    enqueueSubscription(idServerInfo, "/log");
}

function updateAvailableContent(content) {
    let logfileNames = content.logFileNames;

    if (logfileNames.length > 0) {
        let viewBuilder = new LogViewBuilder();
        setMainContentE(viewBuilder.build(logfileNames));
    } else {
        let message = "No se encontraron registros asociados a " + content["serverInfo"]["name"] + ".";
        setDefaultUnavailableContent(message);
    }
}

function updateUnavailableContent(content) {
    let message = "No se encontraron registros. " + content["serverInfo"]["name"] + " no disponible";
    setDefaultUnavailableContent(message);
}