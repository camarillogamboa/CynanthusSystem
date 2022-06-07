/**
 *
 */
class SelectionGroup {

    #selected;
    #selectedClasess;

    constructor(...selectedClasess) {
        this.#selected = null;
        this.#selectedClasess = selectedClasess;
    }

    unselect() {
        if (this.#selected != null) {
            this.#selectedClasess.forEach(clazz => this.#selected.classList.remove(clazz));
            this.#selected = null;
        }
    }

    select(selectable) {
        this.unselect();

        if (typeof selectable == 'string') selectable = $(selectable)[0];

        console.log("Seleccionando " + selectable);

        this.#selected = selectable;
        this.#selectedClasess.forEach(clazz => this.#selected.classList.add(clazz));
        this.#selected.blur();
    }

}