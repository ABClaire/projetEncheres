package fr.eni.encheres.dao;

import fr.eni.encheres.dao.jdbc.ArticleVenduDAOImpl;

/**
 * Classe en charge de la DAOFactory d'Article Vendu
 */

public class DAOFactoryArticleVendu {

	public static ArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduDAOImpl();
	}
}
