/**
 * 
 */
package fr.eni.encheres.bll;

import fr.eni.encheres.bo.ArticleVendu;
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
	public ArticleVendu ajouterUnArticle(ArticleVendu articleAVendre) throws BLLException {
		ArticleVendu nouvelArticle;
		try {
			nouvelArticle = DAOFactory.getArticleVenduDAO().ajouterArticleAVendre(articleAVendre);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
		return nouvelArticle;
	}
	
	//TODO: supprimer cette requête?

	@Override
	public ArticleVendu selectBestEnchereByNoArticle(Integer noArticle) throws BLLException {
		try {
			return DAOFactory.getArticleVenduDAO().selectArticleByIdBestEnchere(noArticle);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
	}
	

}
