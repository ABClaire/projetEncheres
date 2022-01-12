package fr.eni.encheres.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.UtilisateurManagerImpl;
import fr.eni.encheres.bo.Utilisateur;

/**
 * En tant qu�utilisateur, je peux afficher le profil d�un utilisateur. Le
 * pseudo, nom, pr�nom, email, t�l�phone, rue, code postal, ville sont affich�s.
 */
@WebServlet("/AfficherPofilServlet")
public class AfficherPofilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AfficherPofilServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UtilisateurManagerImpl.getInstance().
		
		Utilisateur utilisateur = new Utilisateur();
		
		request.setAttribute("pseudo", utilisateur.getPseudo());
		request.setAttribute("nom", utilisateur.getNom());
		request.setAttribute("prenom", utilisateur.getPrenom());
		request.setAttribute("email", utilisateur.getEmail());
		request.setAttribute("telephone", utilisateur.getTelephone());
		request.setAttribute("rue", utilisateur.getRue());
		request.setAttribute("cp", utilisateur.getCodePostal());
		request.setAttribute("ville", utilisateur.getVille());
		
		request.getRequestDispatcher("WEB-INF/Profil.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
