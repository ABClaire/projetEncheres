/**
 * 
 */
package fr.eni.encheres.test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.DALException;
import fr.eni.encheres.dao.DAOFactory;

public class TestDAL {

	
	/**
	 * Méthode en charge de tester la couche DAL
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {

//		
//		/*
//		 * =========================================================
//		 * 				TEST INSCRIPTION UTILISATEUR
//		 * =========================================================
//		*/
//		
//		// Insertion d'un nouvel utilisateur
//		try {
//			DAOFactory.getUtilisateurDAO().ajouterUtilisateur(new Utilisateur("pépé", "arien", "fernand", "fernandesangelo@gmail.com", "0606060606", "2 rue de la chapelle", "35000", "RENNES", "mdp", 100, false));
//		} catch (DALException e) {
//			e.printStackTrace();
//		}
//		
//		
//		/*
//		 * =========================================================
//		 * 				TEST LISTE UTILISATEURS
//		 * =========================================================
//		*/
//		
//		// Liste de tous les utilisateurs
//		try {
//			List<Utilisateur> lstUtilisateurs = DAOFactory.getUtilisateurDAO().getAllUtilisateurs();
//			lstUtilisateurs.forEach(System.out::println);
//		} catch (DALException e) {
//			e.printStackTrace();
//		}

		
		/*
		 * =========================================================
		 * 			TEST AJOUT ARTICLE A VENDRE
		 * =========================================================
		*/
//		List<Utilisateur> lstAllUtilisateurs = null;
//		try {
//			lstAllUtilisateurs = DAOFactory.getUtilisateurDAO().getAllUtilisateurs();
//		} catch (DALException e) {
//			e.printStackTrace();
//		}
		Utilisateur utilisateur = null;
		try {
			utilisateur = DAOFactory.getUtilisateurDAO().selectUtilisateurById(3);
		} catch (DALException e1) {
			e1.printStackTrace();
		}
		
		Categorie categorieArticle = new Categorie(3);
		Retrait retraitArticle = new Retrait("11 rue des lilas", "44000","Nanntes");
		ArticleVendu articleAVendre = new ArticleVendu("Bol pour tricot", "Céramique. 15 x 15 x 10 cm", LocalDate.now(), LocalDate.now().plusDays(15), 100,utilisateur, categorieArticle, retraitArticle);
		
		try {
			DAOFactory.getArticleVenduDAO().ajouterArticleAVendre(articleAVendre, utilisateur);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

}
