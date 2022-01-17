package fr.eni.encheres.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleVenduManagerImpl;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.EnchereManagerImpl;
import fr.eni.encheres.bll.UtilisateurManagerImpl;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class DetailVenteServlet
 */
@WebServlet("/DetailEnchere")
public class PageEnchereServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Integer noArticleEnchere;
	ArticleVendu detailArticle;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageEnchereServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageEnchereModel model = new PageEnchereModel();
		Utilisateur encheriste = null;

		noArticleEnchere = Integer.valueOf(request.getParameter("noArticle"));
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		Integer noEncheriste = utilisateur.getNoUtilisateur();
		
		try {
			detailArticle = ArticleVenduManagerImpl.getInstance().selectBestEnchereByNoArticle(noArticleEnchere);
			Utilisateur vendeur = UtilisateurManagerImpl.getInstance().getByIdUtilisateur(detailArticle.getUtilisateur().getNoUtilisateur());
			request.setAttribute("nomVendeur", vendeur.getPseudo());
			
			encheriste = UtilisateurManagerImpl.getInstance().getByIdUtilisateur(detailArticle.getEnchereMaximum().getUtilisateur().getNoUtilisateur());	
			if(encheriste == null) {
				request.setAttribute("nomEncheriste", "aucune enchère actuellement");
			} else {
				model.setEncheriste(encheriste);
			}
			model.setArticle(detailArticle);
			model.setVendeur(vendeur);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (BLLException e) {
			e.printStackTrace();
		}
				
		if(request.getParameter("nouvelleEnchere") != null) {
			try {
				EnchereManagerImpl.getInstance().ajouterNouvelleEnchere(noArticleEnchere, noEncheriste, Integer.parseInt(request.getParameter("proposition")));
				detailArticle.getEnchereMaximum().setMontantEnchere(Integer.parseInt(request.getParameter("proposition")));
				encheriste = UtilisateurManagerImpl.getInstance().getByIdUtilisateur(noEncheriste);
				detailArticle.getEnchereMaximum().setUtilisateur(encheriste);
				model.setArticle(detailArticle);
				model.setEncheriste(encheriste);
				model.setMessage("Votre enchère est bien enregistrée");
			} catch (BLLException e) {
				e.printStackTrace();
				request.setAttribute("messageErreur", e.toString());
			}
			
		}
		
		request.setAttribute("model", model);
		
		request.getRequestDispatcher("WEB-INF/PageEnchere.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
