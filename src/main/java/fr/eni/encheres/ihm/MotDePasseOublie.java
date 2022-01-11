package fr.eni.encheres.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.UtilisateurManagerImplAngelo;

/**
 * Servlet implementation class MotDePasseOublie
 */
@WebServlet("/MotDePasseOublie")
public class MotDePasseOublie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MotDePasseOublie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("Recuperation Mot de passe") != null) {
			String identifiant = request.getParameter("identifiant");
			String message = UtilisateurManagerImplAngelo.getInstance().RecuperationMotDePasse(identifiant);
			
			request.setAttribute("message", message);
			
			
		}
		request.getRequestDispatcher("WEB-INF/MotDePasseOublie.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
