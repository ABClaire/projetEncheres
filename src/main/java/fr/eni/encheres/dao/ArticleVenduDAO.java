package fr.eni.encheres.dao;

import java.util.List;
import java.util.Map;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;

public interface ArticleVenduDAO {
	
	void ajouterArticleVendu(ArticleVendu articleVendu) throws DALException;
	List<ArticleVendu> getAllArticleVendu() throws DALException;
	List<ArticleVendu> selectJointArticleUtilisateur() throws DALException;
	
}
