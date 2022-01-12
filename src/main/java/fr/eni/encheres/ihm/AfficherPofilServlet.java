"upackage fr.eni.encheres.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bll.UtilisateurManagerImpl;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.UtilisateurDAO;
import fr.eni.encheres.dao.jdbc.UtilisateurDAOImpl;

/**
 * En tant qu’utilisateur, je peux afficher le profil d’un utilisateur. 
 * Le pseudo, nom, prénom, email, téléphone, rue, code postal, ville sont affichés.
 */
@WebServlet("/AfficherPofilServlet")
public class AfficherPofilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficherPofilServlet() {
        super();
}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ProfilModel model = new ProfilModel();
		
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		model.setUtilisateur(utilisateur);
		request.setAttribute("model", model);
		
		request.getRequestDispatcher("WEB-INF/Profil.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
