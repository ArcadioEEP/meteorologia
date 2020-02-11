package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.CiudadManager;
import managers.TiempoManager;
import objetos.Ciudad;
import objetos.Tiempo;
import objetos.TiempoPK;
import objetos.Utilidades;

/**
 * Servlet implementation class MostrarFecha
 */
@WebServlet("/mostrartiempo")
public class MostrarTiempo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarTiempo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean datos = true;
		int ciudad = 0;
		String ciudadNombre = "";
		Date fecha = new Date();
		Tiempo tiempo = null;
		int mostrado = 0;
		if(request.getParameter("ciudad")!=null) {
			ciudad = Integer.parseInt(request.getParameter("ciudad"));
			ArrayList<Ciudad> ciudades = CiudadManager.consultarCiudad(ciudad);
			if(!ciudades.isEmpty()) {
				ciudadNombre = ciudades.get(0).getNombre();
			}
		} else {
			datos = false;
		}
		if(request.getParameter("fecha")!=null && Utilidades.comprobarFormato(request.getParameter("fecha"))) {
			try {
				fecha = Utilidades.parsearFechaString(request.getParameter("fecha"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			datos = false;
		}
		if(datos) {
			TiempoPK tiempoClave = new TiempoPK (fecha, ciudad);
			ArrayList<Tiempo> tiempos = TiempoManager.mostrarTiempo(tiempoClave);
			if(tiempos.size() > 0) {
				tiempo = tiempos.get(0);
				mostrado = 2;
			} else {
				mostrado = 1;
			}
		}
		request.getSession().setAttribute("ciudadNombre", ciudadNombre);
		request.getSession().setAttribute("tiempo", tiempo);
		request.getSession().setAttribute("mostrado", mostrado);
		RequestDispatcher rd = request.getRequestDispatcher("tiempo.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
