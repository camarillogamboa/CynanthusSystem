console.log("Cargando script de registro de servidor");

function registerSubscription(idServerInfo) {
    console.log("ServerLog: haciendo subscripción con el id " + idServerInfo);
    enqueueSubscription(idServerInfo, "/properties");
}

function updateAvailableContent(content) {

}

function updateUnavailableContent(content) {
    let message = "No se encontraron registros. " + content["serverInfo"]["name"] + " no disponible";
    setDefaultUnavailableContent(message);
}