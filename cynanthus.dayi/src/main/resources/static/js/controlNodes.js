console.log("Cargando script de nodos de control");

function registerSubscription(idServerInfo) {
    console.log("ServerNodes: haciendo subscripción con el id " + idServerInfo);
    enqueueSubscription(idServerInfo, "/control");
}

function updateAvailableContent(content) {
    console.log("Ejecutando vista habilitada");
    let generalNodes = content.generalNodes;
}

function updateUnavailableContent(content) {
    console.log("Ejecutando vista deshabilitada");
    let message = "message";
    setDefaultUnavailableContent(message);
}