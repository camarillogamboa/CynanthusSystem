class InstructionSetViewController extends Service {

    constructor(idSet) {
        super();
        this._idSet = idSet;
    }

    deleteInstructionSet(){
        console.log("Delete Set "+this._idSet);
    }

    saveInstruction(dialogId){
        console.log("Salvando instruccion desde el diálogo :"+dialogId);
    }

    deleteInstruction(idInstruction){
        console.log("Delete instruction "+idInstruction);
    }


    toString() {
        return "InstructionSetViewController: " + this._idSet;
    }

}

class InstructionsViewController extends DelegateAndSelectorController {

    constructor(viewLoader) {
        super(viewLoader, "#currentSetContainer");
    }

    loadInstructionSet(idSet, selectable) {
        this.loadAndSelectView(`/sets/${idSet}`, selectable);
        this.loadDelegate(new InstructionSetViewController(idSet));
    }

    saveInstructionSet(dialogId){
        console.log("Salvando conjunto");
        let form = $("#addSet-form");
        hideModal('#addSet');
        sendJson("/sets", form, data => console.log(data))
    }

    toString() {
        return "InstructionsViewController";
    }

}