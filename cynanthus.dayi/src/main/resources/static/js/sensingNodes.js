console.log("Cargando script de nodos de sensado");

function registerSubscription(idServerInfo) {
    console.log("ServerNodes: haciendo subscripción con el id " + idServerInfo);
    enqueueSubscription(idServerInfo, "/sensing");
}

function updateAvailableContent(content) {

}

function updateUnavailableContent(content) {

}