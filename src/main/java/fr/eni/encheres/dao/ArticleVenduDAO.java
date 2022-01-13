package fr.eni.encheres.dao;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;

public interface ArticleVenduDAO {
	
	ArticleVendu ajouterArticleAVendre(ArticleVendu articleVendu, Utilisateur utilisateur) throws DALException;
	List<ArticleVendu> getAllArticleVendu() throws DALException;
	List<ArticleVendu> selectJointArticleUtilisateur() throws DALException;
	
}
