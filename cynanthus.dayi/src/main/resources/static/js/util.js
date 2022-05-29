class Service {

    constructor() {
        this._startActions = [];
        this._finishActions = [];
    }

    start() {
        console.log(`Iniciando servicio controlador ${this}`);
        this._startActions.forEach(startAction => startAction());
    }

    finalize() {
        console.log(`Finalizando servicio controlador ${this}`);
        this._finishActions.forEach(startAction => startAction());
    }

    addStartAction(startAction) {
        this._startActions.push(startAction);
    }

    addFinishAction(finishAction) {
        this._finishActions.push(finishAction);
    }

}

class ServiceWrapper {

    constructor() {
        this._service = null;
    }

    start() {
        if (this._service != null) this._service.start();
    }

    finalize() {
        if (this._service != null) this._service.finalize();
    }

    get service() {
        return this._service;
    }

    set service(service) {
        this._service = service;
    }

}

class SelectionGroup {

    constructor(eventName, ...selectedClasess) {
        this._eventName = eventName;
        this._selected = null;
        this._selectedClasess = selectedClasess;
    }

    unselect() {
        if (this._selected !== null) {
            this._selectedClasess.forEach(clazz => this._selected.classList.remove(clazz))
        }
    }

    select(selectable) {
        this.unselect();
        this._selected = selectable;
        this._selectedClasess.forEach(clazz => this._selected.classList.add(clazz));
        this._selected.blur();
    }

    addToGroup(selectable) {
        selectable.addEventListener(this._eventName, () => this.select(selectable))
    }

}

class WebSocketConnector {

    constructor(socketPath) {
        this._socketPath = socketPath;
        this._stompClient = null;
    }

    connect(connectAction) {
        let socket = new SockJS(this._socketPath);
        this._stompClient = Stomp.over(socket);
        this._stompClient.connect({}, frame => {
            console.log("WebSocketConnector, connected: " + frame);
            connectAction();
        });
    }

    subscribeTo(subscriptionPath, eventConsumer) {
        return this._stompClient.subscribe(subscriptionPath, eventConsumer);
    }

    unsubscribe(id) {
        this._stompClient.unsubscribe(id);
    }

    connectAndSubscribeTo(subscriptionPath, eventConsumer) {
        this.connect(() => this.subscribeTo(subscriptionPath, eventConsumer));
    }

    sendMessageTo(path, message) {
        this._stompClient.send(path, {}, JSON.stringify(message));
    }

    disconnect() {
        if (this._stompClient !== null) {
            this._stompClient.disconnect();
        }
        this._stompClient = null;
    }

}

class ViewLoader {

    constructor(waitContent, failContent) {
        this._waitContent = waitContent;
        this._failContent = failContent;
    }

    loadAndPlaceTo(urlView, area) {
        if (this._waitContent != null)
            area.html(this._waitContent);

        fetch(
            urlView,
            {
                credentials: 'include',
            }
        ).then(data => data.text()).then(text => area.html(text)).catch(error => {
            console.log(error);
            if (this._failContent != null) {
                area.html(this._failContent);
            }
        });
    }

    loadAndPlaceToById(urlView, areaId) {
        this.loadAndPlaceTo(urlView, $(`#${areaId}`));
    }

}

class DelegatorController extends Service {

    constructor(viewLoader, viewAreaId) {
        super();
        this._viewLoader = viewLoader;
        this._viewAreaId = viewAreaId;

        this._delegateWrapper = new ServiceWrapper();
    }

    get delegateWrapper() {
        return this._delegateWrapper;
    }

    get delegate() {
        return this._delegateWrapper.service;
    }

    loadView(urlView) {
        this._viewLoader.loadAndPlaceTo(urlView, $(this._viewAreaId));
    }

    loadDelegate(delegate) {
        this._delegateWrapper.finalize();
        this._delegateWrapper.service = delegate;
        this._delegateWrapper.start();
    }

    finalize() {
        super.finalize();
        this._delegateWrapper.finalize();
    }

}

class DelegateAndSelectorController extends DelegatorController {

    constructor(viewLoader, viewAreaId, selectionGroup) {
        super(viewLoader, viewAreaId);
        this._navGroup = selectionGroup || new SelectionGroup('click', 'active', 'bg-info');
    }

    loadAndSelectView(urlView, selectable) {
        this.loadView(urlView);
        this._navGroup.select(selectable);
    }

}

let loadSpinner = `<div class="spinner-border m-auto text-info"></div>`;
let failLoadMessage = `<h6>No se logró cargar el elemento</h6>`;

function centeredWrapper(html) {
    return `<div class="d-flex w-100 h-100 text-center"><div class="m-auto">${html}</div></div>`;
}

let autoCenteredLoadSpinner = centeredWrapper(loadSpinner);
let autoCetenredFailLoadMessage = centeredWrapper(failLoadMessage);

function deleteResource(url,thenAction,catchAction){
    fetch(url,{method:"DELETE",credentials:"include"}).then(then).catch(catchAction);
}

function showModal(idModal) {
    $(idModal).modal('show');
}

function hideModal(idModal) {
    $(idModal).modal('hide');
    $('body').removeClass('modal-open');
    $('.modal-backdrop').remove();
}