/**
 * 
 */
function alta(){
	document.getElementById("opcion").value="alta";
	document.getElementById("formusuario").submit();
} 
function sesion(){
	document.getElementById("opcion").value="sesion";
	document.getElementById("formusuario").submit();
}
function agregarUsu(){
	if (document.getElementById("pss").value == document.getElementById("pss2").value){
		document.getElementById("formAlta").submit();
	}else{
		document.getElementById("repetir").style.visibility = "visible";
	}
}
function salir(){
	window.location.href = "inicio.html";
}
function tiempo(){
	if(Math.floor(document.getElementById("tempMax").value) < Math.floor(document.getElementById("tempMin").value)){
		document.getElementById("tempError").style.visibility = "visible";
	} else {
		document.getElementById("formTiempo").submit();
	}
}