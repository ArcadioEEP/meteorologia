package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.CiudadManager;
import managers.EstadoManager;
import managers.TiempoManager;
import objetos.Ciudad;
import objetos.Estado;
import objetos.Tiempo;
import objetos.TiempoPK;
import objetos.Utilidades;

/**
 * Servlet implementation class AltaTiempo
 */
@WebServlet("/altatiempo")
public class AltaTiempo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaTiempo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean datos = true;
		int c = 0;
		int ciudad = 0;
		Date fechaIni = new Date();
		Date fechaFin = new Date();
		int tempMax = 0;
		int tempMin = 0;
		int estado = 0;
		if(request.getParameter("ciudad")!=null) {
			ciudad = Integer.parseInt(request.getParameter("ciudad"));
			
		} else {
			datos = false;
		}
		if(request.getParameter("fechaIni")!=null && Utilidades.comprobarFormato(request.getParameter("fechaIni"))) {
			try {
				fechaIni = Utilidades.parsearFechaString(request.getParameter("fechaIni"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			datos = false;
		}
		if(request.getParameter("fechaFin")!=null && Utilidades.comprobarFormato(request.getParameter("fechaFin"))) {
			try {
				fechaFin = Utilidades.parsearFechaString(request.getParameter("fechaFin"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			datos = false;
		}
		if(request.getParameter("tempMax")!="") {
			tempMax = Integer.parseInt(request.getParameter("tempMax"));
		} else {
			datos = false;
		}
		if(request.getParameter("tempMin")!="") {
			tempMin = Integer.parseInt(request.getParameter("tempMin"));
		} else {
			datos = false;
		}
		if(request.getParameter("estado")!=null) {
			estado = Integer.parseInt(request.getParameter("estado"));
		} else {
			datos = false;
		}
		int creado = 0;
		if(datos) {
			if(Utilidades.fechaMayor(fechaIni, fechaFin) > 0) {
				Date aux = fechaIni;
				fechaIni = fechaFin;
				fechaFin = aux;
			}
			fechaFin.setDate(fechaFin.getDate()+1);
			TiempoPK tiempoClave = new TiempoPK(fechaIni, ciudad);
			ArrayList<Estado> estados = EstadoManager.consultarEstados();
			Estado e = estados.get(estado-1);
			Tiempo tiempo = new Tiempo(tiempoClave, tempMax, tempMin, e);
			boolean duplicado = false;
			while(Utilidades.fechaMayor(fechaFin, tiempo.getClave().getFecha()) > 0) {
				if(TiempoManager.coincide(tiempo)) {
					duplicado = true;
				}
				Date fecha = tiempo.getClave().getFecha();
				fecha.setDate(fecha.getDate()+1);
				tiempo.setClave(new TiempoPK(fecha, ciudad));
			}
			if(!duplicado) {
				creado = 1;
				try {
					fechaIni = Utilidades.parsearFechaString(request.getParameter("fechaIni"));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				tiempoClave = new TiempoPK(fechaIni, ciudad);
				tiempo = new Tiempo(tiempoClave, tempMin, tempMax, e);
				while(Utilidades.fechaMayor(fechaFin, tiempo.getClave().getFecha()) > 0) {
					TiempoManager.crearTiempo(tiempo);
					Date fecha = tiempo.getClave().getFecha();
					fecha.setDate(fecha.getDate()+1);
					tiempo.setClave(new TiempoPK(fecha, ciudad));
				}
			} else {
				creado = 2;
			}
		}
		request.getSession().setAttribute("creado", creado);
		RequestDispatcher rd = request.getRequestDispatcher("sesion.jsp");
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
