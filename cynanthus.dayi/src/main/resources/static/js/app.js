console.log("Iniciando Cynanthus Dayi JS...");

let startActions = [];

let finishActions = [];

function start() {
    startActions.forEach(startAction => startAction())
}

function finalize() {
    finishActions.forEach(finishAction => finishAction())
}
