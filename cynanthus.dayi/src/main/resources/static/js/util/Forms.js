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

function loadForm(formId, resolve, reject) {
    let form = $(formId);

    if (form != null && form[0] != null) {
        //console.log("Registrando formulario " + formId);
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
        //console.log("Registrando diálogo " + dialogId);
        loadForm(
            `${dialogId}-form`,
            form => {
                dialog.modal('hide');
                $('body').removeClass('modal-open');
                $('.modal-backdrop').remove();
                resolve(form, dialog);
            },
            reject != null ? form => reject(form, dialog) : null
        );
    } else console.log("No se encontró el diálogo con identificador " + dialogId);
}

function commonRejected(form) {
    console.log("FORMULARIO RECHAZADO");
}

function resetForm(form) {
    form[0].reset();
    form[0].classList.remove('was-validated');
}