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
import fr.eni.encheres.bll.CategorieManagerImpl;
import fr.eni.encheres.bll.UtilisateurManagerImpl;
import fr.eni.encheres.bll.UtilisateurManagerImplAngelo;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.DAOFactory;

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
		String nextScreen = "WEB-INF/AccueilConnecte.jsp";
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		Integer noUtilisateur = utilisateur.getNoUtilisateur();
	
		// Remonter la liste des categories disponibles
				try {
					List<Categorie> lstCategories = CategorieManagerImpl.getInstance().listeDesCategories();
					request.getServletContext().setAttribute("lstCategories", lstCategories);
				} catch (BLLException e1) {
					e1.printStackTrace();
				}
				
		//----------------------------------------- Copie fonction recherche--------------------------------------------------
		String option = "Toutes";
		String motClef = null;
		List<ArticleVendu> lstARetouner = new ArrayList<ArticleVendu>();
		String radioSelect = "rien" ;
		List<Integer> checkBoxSelect = new ArrayList<Integer>();
		Utilisateur utiliPourRecherche = new Utilisateur();
		
		System.out.println((Utilisateur) request.getSession().getAttribute("utilisateur"));
 		
		//Au clique sur rechercher on recupere la categorie et un possible motClef
		if (request.getParameter("recherche")!= null) {
			option = request.getParameter("listeDeroulante");
			motClef = request.getParameter("nomArticle");
			Utilisateur utiliSession = (Utilisateur) request.getSession().getAttribute("utilisateur");
			try {
				utiliPourRecherche =UtilisateurManagerImpl.getInstance().getByIdUtilisateur(utiliSession.getNoUtilisateur());
				System.out.println("numero utili" + utiliPourRecherche);
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

			// on récupère les choix utilisateurs dans les radio et checkbox
			if (request.getParameter("achatVente").equals("AchatRadio")) {
				System.out.println("radio a achat cliqué");
				radioSelect = "achat";
				if (request.getParameter("Enchères ouvertes")!=null) {
					checkBoxSelect.add(1);
				}
				if (request.getParameter("Mes enchères")!=null) {
					checkBoxSelect.add(2);
				}
				if (request.getParameter("Mes enchères remportées")!=null) {
					checkBoxSelect.add(3);
				}
			}
			

			if (request.getParameter("achatVente").equals("VenteRadio")) {
				System.out.println("radio a Vente cliqué");
				radioSelect = "mesventes";
				if (request.getParameter("Ventes en cours")!=null) {
					checkBoxSelect.add(1);
				}
				if (request.getParameter("Ventes non débutées")!=null) {
					checkBoxSelect.add(2);
				}
				if (request.getParameter("Ventes terminées")!=null) {
					checkBoxSelect.add(3);
				}
			}
			
			
		}
		
		// si il n'y a pas de mot clee on retourne la Liste global filtrer par le choix de cat�gorie 
		if (motClef == null || motClef.isBlank()) { 		
			List<ArticleVendu> listTemp = new ArrayList<ArticleVendu>();
			try {
				listTemp = UtilisateurManagerImplAngelo.getInstance().FiltreSuivantCategorie(option);
				
				if (radioSelect.equals("achat")) {
					System.out.println(listTemp);
					System.out.println("------------------------------------------------------------------------");
					List<ArticleVendu> laListeDeTrop= UtilisateurManagerImplAngelo.getInstance().filtreCheckboxAchat(checkBoxSelect, listTemp, utiliPourRecherche);
					System.out.println(laListeDeTrop);
					request.setAttribute("Liste",laListeDeTrop );
					
				}if (radioSelect.equals("mesventes")) {
					//TODO : rajouter cas mes ventes
					//cas ou on le radio mes ventes est select
				
					
				}else {
					//cas ou rien n'est select
					request.setAttribute("Liste", listTemp);
				}
				
				
			} catch (BLLException e) {
				e.printStackTrace();
			}
			//si il y a un mot clee je recupere la liste triee par categorie puis je la trie par recherche par mot clee avant de la renvoyer 
		}else {
			List<ArticleVendu> listPretePourLaRecherche = new ArrayList<ArticleVendu>();
			List<ArticleVendu> listTemp = new ArrayList<ArticleVendu>();
			try {
				listTemp = UtilisateurManagerImplAngelo.getInstance().FiltreSuivantCategorie(option);
				
				if (radioSelect.equals("achat")) {
					
					listPretePourLaRecherche = UtilisateurManagerImplAngelo.getInstance().filtreCheckboxAchat(checkBoxSelect, listTemp, (Utilisateur) request.getSession().getAttribute("utilisateur"));
					
				}if (radioSelect.equals("mesventes")) {
					//TODO : rajouter cas mes ventes
					//cas ou on le radio mes ventes est select
				
					
				}else {
					
					listPretePourLaRecherche = listTemp;
				}
				
			} catch (BLLException e) {
				
				e.printStackTrace();
			}
			
			request.setAttribute("Liste", UtilisateurManagerImplAngelo.getInstance().RechercheDansLeNomDelArticle(listPretePourLaRecherche, motClef));
		}
		
		//--Ajout m�thode recherche ----------------------------------------------------------------------------------------------------
		List<ArticleVendu> LstArticleRadio = new ArrayList<ArticleVendu>();
		Utilisateur login = (Utilisateur) ((HttpServletRequest) request).getSession().getAttribute("utilisateur");
			// si bouton radio Achat selectionne - retourner la liste des produits achetable (on ecarte donc les produits du proprietaire)
		
				UtilisateurManagerImplAngelo.getInstance().FiltreListeParArticleDeLAcheteur(lstARetouner,login.getPseudo());
				
			// si bouton radio Mes ventes selectionne - retourner la liste des produits de l'utilisateur 
		
				UtilisateurManagerImplAngelo.getInstance().FiltreListeParArticleDeLAcheteur(LstArticleRadio, login.getPseudo());
		
		
		//-----------------------------------------------------fin copie fonction recherche---------------------------------------------------
		
	
		if(request.getParameter("deconnexion") != null) {
			request.getSession().setAttribute("utilisateur", null);
			nextScreen = "AccueilServlet";
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
