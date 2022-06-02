class ViewLoader {

    constructor(waitContent, failContent) {
        this._waitContent = waitContent;
        this._failContent = failContent;
    }

    async loadAndPlaceTo(url, area) {
        if (typeof area === 'string') area = $(area);

        if (this._waitContent != null)
            area.html(this._waitContent);

        return new Promise((resolve, reject) => {
            doGet(url)
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

class ControllerService {

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

    toString() {
        return `${this.constructor.name}`;
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

    constructor(...selectedClasess) {
        this._selected = null;
        this._selectedClasess = selectedClasess;
    }

    unselect() {
        if (this._selected != null) {
            this._selectedClasess.forEach(clazz => this._selected.classList.remove(clazz));
            this._selected = null;
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

class DelegatorController extends ControllerService {

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

    async loadView(url) {
        return this._viewLoader.loadAndPlaceTo(url, $(this._viewAreaId));
    }

    loadDelegate(delegate) {
        this._delegateWrapper.finalize();
        this._delegateWrapper.service = delegate;
        this._delegateWrapper.start();
    }

    start() {
        super.start();
        this._delegateWrapper.start();
    }

    finalize() {
        this._delegateWrapper.finalize();
        super.finalize();
    }

}

class DelegationAndNavegationController extends DelegatorController {

    constructor(viewLoader, viewAreaId, selectionGroup) {
        super(viewLoader, viewAreaId);
        this._navGroup = selectionGroup || new SelectionGroup('active', 'bg-info');
    }

    async loadView(url, selectable) {
        if (selectable != null) this._navGroup.select(selectable);
        return super.loadView(url);
    }

}

class NavegationAndLoadController extends DelegationAndNavegationController {

    constructor(viewLoader, viewAreaId, selectionGroup) {
        super(viewLoader, viewAreaId, selectionGroup);
    }

    async loadSummaryView(url, controllerFactory) {
        return new Promise((resolve, reject) => {
            super.loadView(url)
                .then(() => this.loadDelegate(controllerFactory()))
                .then(resolve)
                .catch(error => {
                    console.log(`Error al cargar la vista de bienvenida. Error: ${error}`);
                    reject();
                })
        });
    }

    async loadView(url, selectable, controllerFactory) {
        if (controllerFactory != null) {
            return new Promise((resolve, reject) => {
                super.loadView(url, selectable)
                    .then(() => this.loadDelegate(controllerFactory(this._viewLoader)))
                    .then(resolve)
                    .catch(error => {
                        console.log(`Error al cargar la vista ${url}. Error: ${error}`);
                        reject();
                    })
            });
        } else return super.loadView(url, selectable);
    }

}

var loadSpinner = `<div class="spinner-border m-auto text-info"></div>`;
var failLoadMessage = `<h6>No se logró cargar el elemento</h6>`;

var loadingIndicator = `
    <div class="row h-100 m-0">
        <div class="col text-center m-auto">
            <div class="spinner-grow text-info" role="status"></div>
            <div class="spinner-grow text-info" role="status"></div>
            <div class="spinner-grow text-info" role="status"></div>
        </div>
    </div>
`;

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