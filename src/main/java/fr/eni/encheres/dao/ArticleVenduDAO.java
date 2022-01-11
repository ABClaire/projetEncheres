package fr.eni.encheres.dao;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;

public interface ArticleVenduDAO {
	
	void ajouterArticleVendu(ArticleVendu articleVendu) throws DALException;
	List<ArticleVendu> getAllArticleVendu() throws DALException;

}
