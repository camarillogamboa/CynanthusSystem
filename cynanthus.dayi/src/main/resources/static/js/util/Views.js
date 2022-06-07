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

var autoCenteredLoadSpinner = centeredWrapper(loadSpinner);
var autoCetenredFailLoadMessage = centeredWrapper(failLoadMessage);

function centeredWrapper(html) {
    return `<div class="d-flex w-100 h-100 text-center"><div class="m-auto">${html}</div></div>`;
}