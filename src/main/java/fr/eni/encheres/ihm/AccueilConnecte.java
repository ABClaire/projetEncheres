package fr.eni.encheres.ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateurManagerImplAngelo;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class AccueilConnecte
 */
@WebServlet("/AccueilConnecte")
public class AccueilConnecte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccueilConnecte() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		Integer noUtilisateur = utilisateur.getNoUtilisateur();
	
		System.out.println(noUtilisateur);
		//----------------------------------------- Copie fonction recherche--------------------------------------------------
		String option = "Toutes";
		String motClef = null;
		List<ArticleVendu> lstARetouner = new ArrayList<ArticleVendu>();
 		
		//Au clique sur rechercher on r�cup�re la cat�gorie et un possible motClef
		if (request.getParameter("recherche")!= null) {
			option = request.getParameter("listeDeroulante");
			motClef = request.getParameter("nomArticle");
		}
		
		
		// si il n'y a pas de mot cl�e on retourne la Liste global filtrer par le choix de cat�gorie 
		if (motClef == null || motClef.isBlank()) {
			
			try {
				request.setAttribute("Liste", UtilisateurManagerImplAngelo.getInstance().FiltreSuivantCategorie(option));
			} catch (BLLException e) {
				e.printStackTrace();
			}
			//si il y a un mot cl�e je r�cup�re la liste tri� par cat�gorie puis je la trie par recherche par mot cl� avant de la renvoyer 
		}else {
			
			try {
				lstARetouner = UtilisateurManagerImplAngelo.getInstance().FiltreSuivantCategorie(option);
			} catch (BLLException e) {
				
				e.printStackTrace();
			}
			
			request.setAttribute("Liste", UtilisateurManagerImplAngelo.getInstance().RechercheDansLeNomDelArticle(lstARetouner, motClef));
		}
		//-----------------------------------------------------fin copie fonction recherche---------------------------------------------------
		
		request.getRequestDispatcher("WEB-INF/AccueilConnecte.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
