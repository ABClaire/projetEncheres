package fr.eni.encheres.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.modeler.modules.ModelerSource;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateurManagerImpl;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.UtilisateurDAO;
import fr.eni.encheres.dao.jdbc.UtilisateurDAOImpl;

/**
 * Servlet implementation class ModifierUtilisateurServlet
 */
@WebServlet("/ModifierUtilisateur")
public class ModifierUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierUtilisateurServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProfilModel model = new ProfilModel();
		
		Utilisateur utilisateur =(Utilisateur) request.getSession().getAttribute("utilisateur");
		model.setUtilisateur(utilisateur);
		request.setAttribute("model", model);

		
		request.getRequestDispatcher("WEB-INF/ModifierProfil.jsp").forward(request, response);

	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProfilModel model = new ProfilModel();
		
		Utilisateur utilisateur =(Utilisateur) request.getSession().getAttribute("utilisateur");
		model.setUtilisateur(utilisateur);
		request.setAttribute("model", model);
		
		if(request.getParameter("modifierUtilisateur") != null) {
			System.out.println("Clic sur le bouton");
			//VÈrifier si l'utilisateur a le bon mot de passe avant d'appliquer les modifications :
			if(request.getParameter("motDePasse").equals(utilisateur.getMotDePasse())) {
				String pseudo = request.getParameter("pseudo");
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				String email = request.getParameter("email");
				String telephone = request.getParameter("telephone");
				String rue = request.getParameter("rue");
				String codePostal = request.getParameter("codePostal");
				String ville = request.getParameter("ville");
				String motDePasse = request.getParameter("motDePasse");
				String nouveauMotDePasse = request.getParameter("nouveauMotDePasse");
				System.out.println(codePostal);
				//Verifier si il y a une modification de mot de passe :
				if(((request.getParameter("nouveauMotDePasse").equals(request.getParameter("confirmationMotDePasse"))) || ((request.getParameter("nouveauMotDePasse").isEmpty() && request.getParameter("confirmationMotDePasse").isEmpty())))) {
					System.out.println(request.getParameter("confirmationMotDePasse"));
					
					Utilisateur utilisateurModif = new Utilisateur(utilisateur.getNoUtilisateur(),pseudo, nom, prenom, email, telephone, rue, codePostal, ville, nouveauMotDePasse, 100, false);
									
					try {
					UtilisateurManagerImpl.getInstance().modifierUtilisateur(model.getUtilisateur(),utilisateurModif);
					utilisateur = utilisateurModif;
				} catch (BLLException e) {
					e.printStackTrace();
					request.setAttribute("message", e.toString());
				}
			
				}
			} else {
				request.setAttribute("message", "Attention, vous avez saisi deux mots de passes diff√©rents. Veuillez recommancer s'il vous plait");
			}
		}
		doGet(request, response);
	}

}
