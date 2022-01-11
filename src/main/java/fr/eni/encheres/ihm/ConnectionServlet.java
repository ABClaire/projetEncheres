package fr.eni.encheres.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateurManagerImpl;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ConnectionServlet
 */
@WebServlet("/ConnectionServlet")
public class ConnectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectionServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextScreen = "WEB-INF/ConnexionPage.jsp";
		if (request.getParameter("Connexion") != null) {
			String identifiant = request.getParameter("identifiant");
			String motDePasse = request.getParameter("motDePasse");
			Utilisateur saisieUtilisateur = new Utilisateur(identifiant, identifiant, motDePasse);
			Utilisateur utilisateurRecuperer = new Utilisateur();
			String message = null ;
			try {
				utilisateurRecuperer = UtilisateurManagerImpl.getInstance().verificationIdentifiantMotDePasse(saisieUtilisateur);
				request.getSession().setAttribute("utilisateur", utilisateurRecuperer);
			} catch (BLLException e) {
				message = e.toString();
			
				
			}
			
			request.setAttribute("message", message);
			request.setAttribute("donneeUtilisateur", utilisateurRecuperer);

		}
		if (request.getParameter("Creation Compte") != null) {
			nextScreen = "WEB-INF/inscription.jsp";
			
		}
		request.getRequestDispatcher(nextScreen).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
