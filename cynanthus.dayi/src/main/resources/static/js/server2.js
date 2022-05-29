console.log("Cargando script de vista de servidor");

let tabGroup = new SelectionGroup('click', 'active');

class WebSocketConnector {

    constructor(socketName, messagePath, subscriptionPath) {
        this._socketName = socketName;
        this._messagePath = messagePath;
        this._subscriptionPath = subscriptionPath;
        this._stompClient = null;
    }

    connect(callback) {
        let socket = new SockJS(this._socketName);
        let stompClient = Stomp.over(socket);
        let subscriptionPath = this._subscriptionPath;
        stompClient.connect({}, frame => {
            console.log("Connected: " + frame);
            stompClient.subscribe(subscriptionPath, callback);
        });
        this._stompClient = stompClient;
    }

    sendMessage(obj) {
        this._stompClient.send(this._messagePath, {}, JSON.stringify(obj));
    }

    disconnect() {
        if (this._stompClient !== null) {
            this._stompClient.disconnect();
        }
        this._stompClient = null;
    }

}

let webSocket = null;

function selectTab(element) {
    tabGroup.select(element);
}

function init(subPath, callback) {
    let socketname = "/dayi-socket";

    webSocket = new WebSocketConnector(
        socketname,
        "/app/server" + subPath,
        "/topic/server" + subPath
    );

    startActions.push(() => webSocket.connect(callback));
    finishActions.push(() => {
        if (webSocket !== null) {
            webSocket.disconnect();
        }
        webSocket = null;
    })
}

function consumePublication(message) {
    let body = message.body;
    let obj = JSON.parse(body);
    console.log(obj);
    updateServerView(obj);
}

function requestPublication() {
    console.log("Realizando peticion de publicación");
    let obj = {};

    webSocket.sendMessage(obj);
}

function enqueueSubscription(idServerInfo, option) {
    init("/" + idServerInfo + option, consumePublication);
    startActions.push(() => setTimeout(() => requestPublication(), 500));
}

function updateServerView(content) {

    let available = content.available;
    let serverInfo = content.serverInfo;

    $("#serverNameE").html(`${serverInfo.name}`);
    $("#addressE").html(`${serverInfo.address}:${serverInfo.port}`);
    $("#serverInfoE").html(serverInfo.info !== null ? serverInfo.info : "No hay información")

    if (available) {
        $("#availableIndicatorE").html(`<i class="fas fa-2x fa-check-circle available-indicator"></i>`);

        $("#server" + serverInfo.id)[0].classList.replace("bg-info", "bg-success");
        updateAvailableContent(content);
    } else {
        $("#availableIndicatorE").html(`<i class="fas fa-2x fa-times-circle unavailable-indicator"></i>`);
        $("#unavailableMessageE").html(
            `<div class="alert alert-warning rounded-0 border-left-0 border-right-0 p-2 w-100">
            <p class="w-100 m-auto text-center">
            No se logró establecer comunicación con el servidor.
            Verifique la dirección y puerto de referencia o asegurese que el microservicio correspondiente se esté ejecutando
            </p>
            </div>`
        );

        $("#server" + serverInfo.id)[0].classList.replace("bg-info", "bg-danger");
        updateUnavailableContent(content);
    }

}

function setDefaultUnavailableContent(message) {
    setMainContentE(
        `<div class="row h-100 m-0">
        <div class="col text-center m-auto">
        <h6>${message}</h6>
        </div>
        </div>`
    );
}

function setMainContentE(innerHTML) {
    $("#serverMainContentE").html(innerHTML);
}


