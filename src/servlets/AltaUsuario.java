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
 * Servlet implementation class AltaUsuario
 */
@WebServlet("/altausuario")
public class AltaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = "";
		String pss = "";
		if(request.getParameter("usuario")!=null) {
			nombre = request.getParameter("usuario");
		}
		if(request.getParameter("pss")!=null) {
			pss = request.getParameter("pss");
		}
		Usuario usu = new Usuario (nombre, Utilidades.MD5(pss));
		
		boolean check = UsuarioManager.comprobarUsuarioExiste(usu);
		
		if(!check) {
			UsuarioManager.crearUsuario(usu);
			request.getSession().setAttribute("usuario", usu);
			RequestDispatcher rd = request.getRequestDispatcher("sesion.jsp");
			rd.forward(request, response);
			
		} else {
			request.getSession().setAttribute("check", check);
			RequestDispatcher rd = request.getRequestDispatcher("alta.jsp");
			rd.forward(request, response);
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
