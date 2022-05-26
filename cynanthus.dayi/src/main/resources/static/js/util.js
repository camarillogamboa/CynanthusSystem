class SelectionGroup {

    constructor(eventName,...selectedClasess) {
        this.eventName=eventName;
        this.selected = null;
        this.selectedClasess= selectedClasess;
    }

    select(element) {
        if (this.selected !== null){
            this.selectedClasess.forEach(clazz => this.selected.classList.remove(clazz))
        }
        this.selected = element;

        this.selectedClasess.forEach(clazz => this.selected.classList.add(clazz));
    }

    addToGroup(element){
        element.addEventListener(this.eventName,()=> this.select(element))
    }

}