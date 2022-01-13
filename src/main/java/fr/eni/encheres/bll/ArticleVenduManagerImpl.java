/**
 * 
 */
package fr.eni.encheres.bll;

import java.util.List;
import java.util.stream.Collectors;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.DALException;
import fr.eni.encheres.dao.DAOFactory;

/**
 * Classe en charge de gérer les articles vendus
 */
public class ArticleVenduManagerImpl implements ArticleVenduManager{

	private static class ArticleVenduManagerHolder {
		private static ArticleVenduManagerImpl instance = new ArticleVenduManagerImpl();
	}
	
	private ArticleVenduManagerImpl() {}
	
	public static ArticleVenduManagerImpl getInstance() {
		return ArticleVenduManagerHolder.instance;
		
	}
	
	@Override
	public ArticleVendu ajouterUnArticle(ArticleVendu articleAVendre, Utilisateur utilisateur) throws BLLException {
		ArticleVendu nouvelArticle;
		try {
			nouvelArticle = DAOFactory.getArticleVenduDAO().ajouterArticleAVendre(articleAVendre, utilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
		return nouvelArticle;
	}
	
	public List<String> listeDesCategories(){
		List<String> lstCategories = null;
		try {
			List<ArticleVendu> lstArticles = DAOFactory.getArticleVenduDAO().selectJointArticleUtilisateur();
			lstCategories = lstArticles.stream().map(a -> a.getCategorieArticle().getLibelle()).collect(Collectors.toList());
		} catch (DALException e) {
			e.printStackTrace();
		}
		return lstCategories;
		
	}

}
