console.log("Cargando script de propiedades del servidor");

class TableBuilder {

    build(propertyInfos) {
        return "<table class='table table-striped table-borderless'>" +
            this.buildHead() +
            this.buildBody(propertyInfos) +
            "</table>";
    }

    buildHead() {
        return "<thead class='dayi-bg text-white sticky-top'>" +
            "<tr>" +
            "<th class='p-2 text-center align-middle' scope='col'><h6>Clave</h6></th>" +
            "<th class='p-2 text-center align-middle' scope='col'><h6>Valor</h6></th>" +
            "<th class='p-2' scope='col'>" +
            "<div class='container-fluid p-0'>" +
            "<div class='row m-0'>" +
            "<div class='col m-auto text-center'><h6>Información</h6></div>" +
            "<div class='col-xs ml-auto'>" +
            "<button type='button' class='btn btn-light m-0' data-toggle='modal' data-target='#editPropertiesModal'>" +
            "<span><i class='fas fa-pen'></i></span>" +
            "</button>" +
            "</div>" +
            "</div>" +
            "</div>" +
            "</th>" +
            "</tr>" +
            "</thead>";
    }

    buildBody(propertyInfos) {
        return "<tbody>" +
            this.buildBodyItems(propertyInfos) +
            "</tbody>";
    }

    buildBodyItems(propertyInfos) {
        let innerHtml = "";

        propertyInfos.forEach(propertyInfo => {
            innerHtml +=
                "<tr class='border-top'>" +
                "<td class='font-weight-bold border-right'>" + propertyInfo.key + "</td>" +
                "<td class='border-right'>" + propertyInfo.value + "</td>" +
                "<td>" + propertyInfo.info + "</td>" +
                "</tr>";
        });

        return innerHtml;
    }

}

function registerSubscription(idServerInfo) {
    console.log("ServerProperties: registrando subscripción con el id " + idServerInfo);
    enqueueSubscription(idServerInfo, "/properties");
}

function updateAvailableContent(content) {
    let tableBuidler = new TableBuilder();

    let innerHtml = tableBuidler.build(content['propertyInfos']);
    setMainContentE(innerHtml);
}

function updateUnavailableContent(content) {
    let message = "No se encontraron propiedades. " + content["serverInfo"]["name"] + " no disponible";
    setDefaultUnavailableContent(message);
}