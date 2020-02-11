<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="objetos.Usuario" %>
    <%@page import="objetos.Estado" %>
    <%@page import="managers.EstadoManager" %>
    <%@page import="managers.CiudadManager" %>
    <%@page import="objetos.Ciudad" %>
    <%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/estilos.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="logo"><img id="logoimg" src="img/logo.png"></div>
	<%Usuario usu = null;
	if (request.getSession().getAttribute("usuario")!=null){
		usu=(Usuario)request.getSession().getAttribute("usuario");
	%>
	<h2>Bienvenido, <%=usu.getNombre() %></h2>
	<h3>Agregar Ciudad</h4>
	<Form method = "GET" action = "altaciudad" id="formciudad" class="formulario">
		<Input type = "hidden" id="opcion" name="opcion">
		<Label for = "ciudad" > Ciudad </label>
		<Input id = "ciudad" type = "text" name = "ciudad"/><br><br>
		<Input class = "boton" type = "submit" value="Aceptar">
	</Form >
	<%int alta = 2;
	if(request.getSession().getAttribute("alta")!=null){
		alta = (int)request.getSession().getAttribute("alta");
	}
	if(alta == 0){%>
		<h3 class="error">La ciudad ya existe</h3>
	<%request.getSession().setAttribute("alta", 2);
	} %>
	<%if(alta == 1){ %>
		<h3 id="correcto">La ciudad ha sido creada</h3>
	<%request.getSession().setAttribute("alta", 2);
	} %>
	<h3>Agregar Tiempo</h4>
	<Form method = "GET" action = "altatiempo" id = "formTiempo" class="formulario">
		<Label for = "ciudad" > Ciudad </label>
		<select id="ciudad" name="ciudad" form="formTiempo">
			<%ArrayList<Ciudad> ciudades = CiudadManager.consultarCiudades(); 
			for(int i = 0; i < ciudades.size(); i++) {%>
			<option value="<%=ciudades.get(i).getNumero()%>"><%=ciudades.get(i).getNombre()%></option>
			<%} %>
		</select><br><br>
		<Label for = "fechaIni" >Fecha inicio</label>
		<Input id = "fechaIni" type = "date" name = "fechaIni" min="2000-01-01" max="2019-12-31"><br><br>
		<Label for = "fechaFin" >Fecha fin</label>
		<Input id = "fechaFin" type = "date" name = "fechaFin" min="2000-01-01" max="2019-12-31"><br><br>
		<Label for = "tempMax" >Temp. máxima </label>
		<Input id = "tempMax" type = "number" name = "tempMax" min="-50" max="60"/><br><br>
		<Label for = "tempMin" >Temp. mínima </label>
		<Input id = "tempMin" type = "number" name = "tempMin" min="-50" max="60"/><br><br>
		<select id="estado" name="estado" form="formTiempo">			
			<%ArrayList<Estado> estados = EstadoManager.consultarEstados(); 
			for(int i = 0; i < estados.size(); i++) {%>
			<option value="<%=estados.get(i).getNumero()%>"><%=estados.get(i).getNombre()%></option>
			<%} %>
		</select><br><br>
		<Input type = "button" value="Aceptar" onClick="tiempo()">
	</Form >
	<div style="margin-top: 20px" class="cajaBoton"><Input class = "boton" type = "button" value="Salir" onClick="salir()"></div>
	<%int creado = 3;
	if(request.getSession().getAttribute("creado")!=null){
		creado = (int)request.getSession().getAttribute("creado");
	}
	if(creado == 0){
	%>
	<h3 class="error">No has introducido todos los datos, o la fecha es incorrecta</h3>
	<%
	request.getSession().setAttribute("creado", 3);
	} else if(creado == 1) {%>
	<h3 id="correcto">Creación correcta</h3>
	<%request.getSession().setAttribute("creado", 3);
	} else if(creado == 2) {%>
	<h3 class="error">Ya existe tiempo creado para alguno de los días indicados</h3>
	<%} %>
	<%request.getSession().setAttribute("creado", 3);
	} %>	
	<div id="tempError">
		<h3 class="error">La temperatura máxima no puede ser inferior a la mínima</h3>
	</div>
	
	<script src="js/inicio.js"></script>
</body>
</html>