console.log("Cargando script de nodos de control");

function registerSubscription(idServerInfo) {
    console.log("ServerNodes: haciendo subscripción con el id " + idServerInfo);
    enqueueSubscription(idServerInfo, "/properties");
}

function updateAvailableContent(content) {

}

function updateUnavailableContent(content) {
    let message = "message";
    setDefaultUnavailableContent(message);
}