/**
 * Define el conjunto de interfaces y clases abstractas que exponen un API
 * de intercambio de información basada en POJOS
 */
open module cynanthus.auri.api {

    requires cynanthus.bean;
    requires cynanthus.domain;

    exports edu.cynanthus.auri.api;
    exports edu.cynanthus.auri.api.exception;

}