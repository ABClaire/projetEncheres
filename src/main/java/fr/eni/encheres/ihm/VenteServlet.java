package fr.eni.encheres.ihm;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bll.ArticleVenduManagerImpl;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.ArticleVenduDAO;
import fr.eni.encheres.dao.jdbc.ArticleVenduDAOImpl;

/**
 * Servlet implementation class VenteServlet
 */
@WebServlet("/NouvelleVente")
public class VenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VenteServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VenteModel model = new VenteModel();
		final Integer PRIX_INITIAL = 100;
		
		if(request.getParameter("enregistrer") != null) {
			
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
			
			Categorie categorie = new Categorie(Integer.parseInt(request.getParameter("categorie")));
			model.setCategorie(categorie);
			
			/*
			 * TODO: récupérer l'adresse de l'utilisateur comme adresse par défaut
			 * model.setRetrait(retrait) à envoyer à l'ouverture de la page
			 * puis gérer la saisie utilisateur s'il veut modifier l'adresse de retrait
			 */
			
			Retrait retrait = new Retrait(request.getParameter("rue"), request.getParameter("cp"), request.getParameter("ville"));
			model.setRetrait(retrait);
			
			String nom_article = request.getParameter("nom_article");
			String description = request.getParameter("description_article");
			LocalDate dateDebutEnchere = LocalDate.parse(request.getParameter("debut_enchere"));
			LocalDate dateFinEnchere = LocalDate.parse(request.getParameter("fin_enchere"));
			
			ArticleVendu articleAVendre = new ArticleVendu(nom_article, description, dateDebutEnchere, dateFinEnchere, PRIX_INITIAL, utilisateur,categorie,retrait);
			
			try {
				ArticleVenduManagerImpl.getInstance().ajouterUnArticle(articleAVendre, utilisateur);
				request.setAttribute("message", "Vente ajoutée");
			} catch (BLLException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("model", model);
		request.getRequestDispatcher("WEB-INF/Vente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
