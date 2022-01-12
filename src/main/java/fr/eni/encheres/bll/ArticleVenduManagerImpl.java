/**
 * 
 */
package fr.eni.encheres.bll;

import fr.eni.encheres.bo.ArticleVendu;
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
	public void ajouterUnArticle(ArticleVendu articleAVendre, Utilisateur utilisateur) throws BLLException {
		try {
			DAOFactory.getArticleVenduDAO().ajouterArticleAVendre(articleAVendre, utilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
	}

}
