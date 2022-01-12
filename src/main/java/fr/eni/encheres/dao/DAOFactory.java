/**
 * 
 */
package fr.eni.encheres.dao;

import fr.eni.encheres.dao.jdbc.ArticleVenduDAOImpl;
import fr.eni.encheres.dao.jdbc.UtilisateurDAOImpl;

/**
 * Classe en charge de 
 */
public class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOImpl();
	}
	
	public static ArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduDAOImpl();
	}

}
