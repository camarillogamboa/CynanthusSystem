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
        if (this._selected != null) {
            this._selectedClasess.forEach(clazz => this._selected.classList.remove(clazz))
        }
    }

    select(selectable) {
        this.unselect();

        if (typeof selectable == 'string') selectable = $(selectable)[0];

        console.log("Seleccionando " + selectable);
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
            console("WebSocketConnector, connected: " + frame);
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

    async loadAndPlaceTo(urlView, area) {
        if (typeof area === 'string') area = $(area);

        if (this._waitContent != null)
            area.html(this._waitContent);

        return new Promise((resolve, reject) => {
            doGet(urlView)
                .then(response => response.text())
                .then(text => area.html(text))
                .then(resolve)
                .catch(error => {
                    console(error);
                    if (this._failContent != null) area.html(this._failContent);
                    reject();
                });
        });
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

    async loadView(urlView) {
        return this._viewLoader.loadAndPlaceTo(urlView, $(this._viewAreaId));
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

    async loadAndSelectView(urlView, selectable) {
        this._navGroup.select(selectable);
        return this.loadView(urlView);
    }

}

var loadSpinner = `<div class="spinner-border m-auto text-info"></div>`;
var failLoadMessage = `<h6>No se logró cargar el elemento</h6>`;

function centeredWrapper(html) {
    return `<div class="d-flex w-100 h-100 text-center"><div class="m-auto">${html}</div></div>`;
}

var autoCenteredLoadSpinner = centeredWrapper(loadSpinner);
var autoCetenredFailLoadMessage = centeredWrapper(failLoadMessage);

function serializeForm(form) {
    let serialized = form.serializeArray();
    let s = '';
    let data = {};
    for (s in serialized) {
        let value = serialized[s]['value'];

        if (value === '') value = null;

        data[serialized[s]['name']] = value;
    }
    return JSON.stringify(data);
}

function showModal(idModal) {
    $(idModal).modal('show');
}

function hideDialog(dialogId) {
    $(dialogId).modal('hide');
    $('body').removeClass('modal-open');
    $('.modal-backdrop').remove();
}

function loadForm(formId, resolve, reject) {
    let form = $(formId);

    if (form != null && form[0] != null) {
        let eventListerner;

        if (reject != null) {
            eventListerner = event => {
                event.preventDefault();
                event.stopPropagation();

                console.log("Validando formulario " + formId);

                if (form[0].checkValidity() === true) {
                    console.log(`Formulario ${formId} válido`);
                    form[0].classList.add('was-validated');
                    resolve(form);
                } else {
                    console.log(`Formulario ${formId} inválido`);
                    form[0].classList.add('was-validated');
                    reject(form);
                }
            }
        } else eventListerner = event => {
            event.preventDefault();
            event.stopPropagation();
            resolve(form);
        }

        form[0].addEventListener('submit', eventListerner);

    } else console("No se encontró el formulario con identificador " + formId);
}

function loadDialogForm(dialogId, resolve, reject) {
    let dialog = $(dialogId);

    if (dialog != null && dialog[0] != null) {
        loadForm(
            `${dialogId}-form`,
            form => {
                dialog.modal('hide');
                $('body').removeClass('modal-open');
                $('.modal-backdrop').remove();
                resolve(form);
            },
            reject
        );
    } else console.log("No se encontró el diálogo con identificador " + dialogId);
}

function commonRejected(form) {
    console.log("FORMULARIO RECHAZADO");
}

function processResponse(response) {
    let status = response.status;
    console.log(response);
    return true;
}

async function doGet(url) {
    return fetch(
        url, {
            credentials: "include",
            method: "GET",
            redirect: "manual"
        }
    )
}

async function doPostData(url, json) {
    return fetch(url, {
        credentials: "include",
        headers: {'Content-Type': 'application/json'},
        method: "POST",
        redirect: "manual",
        body: json
    });
}

async function doPostForm(url, form) {
    let json = serializeForm(form);
    return doPostData(url, json);
}

async function doDelete(url) {
    return fetch(
        url, {
            headers: {'Content-Type': 'application/json'},
            method: "DELETE",
            redirect: "manual"
        }
    );
}