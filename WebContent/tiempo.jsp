<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="managers.CiudadManager" %>
    <%@page import="objetos.Ciudad" %>
    <%@page import="objetos.Tiempo" %>
    <%@page import="objetos.Utilidades" %>
    <%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/estilos.css">
<meta charset="ISO-8859-1">
<title>Consultar Tiempo</title>
</head>
<body>
	<div id="logo"><img id="logoimg" src="img/logo.png"></div>
	<Form method = "GET" action = "mostrartiempo" id = "formMeteo" class="formulario">
		<Label for = "ciudad" > Ciudad </label>
		<select id="ciudad" name="ciudad" form="formMeteo">
			<%ArrayList<Ciudad> ciudades = CiudadManager.consultarCiudades(); 
			for(int i = 0; i < ciudades.size(); i++) {%>
			<option value="<%=ciudades.get(i).getNumero()%>"><%=ciudades.get(i).getNombre()%></option>
			
			<%} %>
		</select><br><br>
		<Label for = "fecha" > Fecha </label>
		<Input id = "fecha" type = "date" name = "fecha" min="2000-01-01" max="2019-12-31"><br><br>
		<Input type = "submit" value="Aceptar">
	</Form >
	<%Tiempo t = null;
	String c = "";
	int mostrado = 3;
	if (request.getSession().getAttribute("mostrado")!=null){
		mostrado = (int)request.getSession().getAttribute("mostrado");
	}
	if (request.getSession().getAttribute("tiempo")!=null){
		t = (Tiempo)request.getSession().getAttribute("tiempo");
	}
	if (request.getSession().getAttribute("ciudadNombre")!=null){
		c = (String)request.getSession().getAttribute("ciudadNombre");
	}
	if(mostrado == 0){%>
		<h3 class="error">No has introducido todos los datos, o la fecha es incorrecta</h3>
	<%request.getSession().setAttribute("mostrado", 3);
	} else if(mostrado == 1){%>
		<h3 class="error">No hay registro para esa fecha</h3>
	<%request.getSession().setAttribute("mostrado", 3);
	} else if(mostrado == 2){	%>
		<br><br>
		<table id="tabla">
			<tr>
				<td><b><%=c%></b></td>
				<td><b><%=(Utilidades.fechaEnString(t.getClave().getFecha()))%></b></td>
			</tr>
			<tr>
				<td><img src="img/<%=t.getEstado().getNumero() %>.png" width="100" height="100"></td>
				<td>Mínimo: <%=t.getTempMin()%>ºC<br>Máximo: <%=t.getTempMax()%>ºC</td>
			</tr>
		</table>
	<%request.getSession().setAttribute("mostrado", 3);
	} %>
	<div style="margin-top: 20px" class="cajaBoton"><Input class = "boton" type = "button" value="Salir" onClick="salir()"></div>
	
	
	<script src="js/inicio.js"></script>
</body>
</html>