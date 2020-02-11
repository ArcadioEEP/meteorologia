package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import managers.UsuarioManager;
import objetos.Usuario;
import objetos.Utilidades;

/**
 * Servlet implementation class SeccionUsuario
 */
@WebServlet("/seccionusuario")
public class SeccionUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeccionUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcion="";
		if(request.getParameter("opcion")!=null) {
			opcion = request.getParameter("opcion");
		}
		if(opcion.equals("alta")) {
			RequestDispatcher rd = request.getRequestDispatcher("alta.jsp");
			rd.forward(request, response);
		} else if (opcion.equals("sesion")) {
			String nombre = "";
			String pss = "";
			if(request.getParameter("usuario")!=null) {
				nombre = request.getParameter("usuario");
			}
			if(request.getParameter("pss")!=null) {
				pss = request.getParameter("pss");
			}
			Usuario usu = new Usuario(nombre, Utilidades.MD5(pss));
			if(UsuarioManager.comprobarPss(usu)) {
				request.getSession().setAttribute("usuario", usu);
				RequestDispatcher rd = request.getRequestDispatcher("sesion.jsp");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("password.jsp");
				rd.forward(request, response);
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
