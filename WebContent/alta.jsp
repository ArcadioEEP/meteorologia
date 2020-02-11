<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/estilos.css">
<title>Alta Usuario</title>
</head>
<body>
	<div id="logo"><img id="logoimg" src="img/logo.png"></div>
	<%boolean check = false;
	if (request.getSession().getAttribute("check")!=null){
		check = (boolean)request.getSession().getAttribute("check");
	}%>
	<Form method = "GET" action = "altausuario" id="formAlta" class="formulario">
		<Label for = "usuario" > Usuario </label>
		<Input id = "usuario" type = "text" name = "usuario"/><br><br>
		<Label for = "pss" > Contraseña </label>
		<Input id = "pss" type = "password" name = "pss" ><br><br>
		<Label for = "pss2" > Confirmar </label>
		<Input id = "pss2" type = "password" name = "pss2" ><br><br>
		<Input class = "boton" type = "button" value="Aceptar" onClick="agregarUsu()">
	</Form >
	<div style="margin-top: 20px" class="cajaBoton"><Input class = "boton" type = "button" value="Salir" onClick="salir()"></div>
	<%if(check){%>
	<div>
		<h3 class="error">El usuario ya existe</h3>
	</div>
	<% request.getSession().setAttribute("check", false);}%>
	<div id="repetir">
		<h3 class="error">Las contraseñas no coinciden</h3>
	</div>
	
	
	<script src="js/inicio.js"></script>
</body>
</html>