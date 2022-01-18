/**
 * 
 */
package fr.eni.encheres.bll;

import java.time.LocalDate;
import java.util.List;

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
	
	/**
	 * Méthode en charge de 
	 * @param noArticleEnchere
	 * @param proposition
	 * @throws BLLException 
	 */
	@Override
	public void miseAJourPrixVente(Integer noArticleEnchere, Integer proposition) throws BLLException {
		try {
			DAOFactory.getArticleVenduDAO().updatePrixVente(noArticleEnchere, proposition);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
		
	}

	/**
	 * Méthode en charge de mettre à jour l'état des enchères en fonction de la date du jour
	 * @param dateDuJour
	 */
	public void actualisationEtatEnchereBDD(LocalDate dateDuJour) {
		List<ArticleVendu> lstArticles = null;
		List<ArticleVendu> lstArticlesUpdate = null;
		try {
			lstArticles = DAOFactory.getArticleVenduDAO().getAllArticleVendu();
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		
		for (ArticleVendu articleVendu : lstArticlesUpdate) {
			if("CREE".equals(articleVendu.getEtatVente()) && 
					(dateDuJour.isAfter(articleVendu.getDateDebutEncheres()) || 
							dateDuJour.isEqual(articleVendu.getDateDebutEncheres()))) {
				articleVendu.setEtatVente("ENCOURS");
				lstArticlesUpdate.add(articleVendu);
			}
			if("ENCOURS".equals(articleVendu.getEtatVente()) && dateDuJour.isBefore(articleVendu.getDateFinEncheres())) {
				articleVendu.setEtatVente("VENDU");
				lstArticlesUpdate.add(articleVendu);
			}
		}
			/*
			 * TODO 
			 * 		Récup toute la liste des articles (noArticle, date debut enchère, date fin enchère, enchère max, etat)
			 * 		
			 * 	Filtre lstarticles sur etat CREE
			 * 		si dateDuJour >= date début enchère -> etat articleVendu = "ENCOURS"
			 * 
			 * Filtre lstarticles sur etat ENCOURS
			 * 		si dateDuJour < date fin enchère -> 
			 * 				etat articleVendu = "VENDU" 
			 * 				et crédit vendeur 
			 * 				puis etat articleVendu = "CLOTURE"
			 * 
			 * 	lstArticles = pour chaque article, update
			 * 
			 */
			
		
	}
	

}
