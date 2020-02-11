package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.CiudadManager;
import objetos.Ciudad;

/**
 * Servlet implementation class AltaCiudad
 */
@WebServlet("/altaciudad")
public class AltaCiudad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaCiudad() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ciudad = "";
		if(request.getParameter("ciudad")!=null) {
			ciudad = request.getParameter("ciudad");
		}
		int alta = CiudadManager.comprobarCiudadExiste(ciudad);
		if(alta == 1) {
			Ciudad c = new Ciudad (ciudad);
			CiudadManager.crearCiudad(c);
		} 
		request.getSession().setAttribute("alta", alta);
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
