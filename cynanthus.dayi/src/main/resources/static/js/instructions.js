let setButtonGroup= new SelectionGroup('click','active','bg-info');

function loadInstructionSet(element,id){
 setButtonGroup.select(element);
 console.log("Cargando conjunto de instrucción: "+id);

 let request = $.ajax(`/sets/${id}`);

 request.done(data =>{
   $("#currentSetViewE").html(data);
 })

}