/**
 * 
 */
package fr.eni.encheres.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.dao.DALException;
import fr.eni.encheres.dao.DAOFactory;

/**
 * Classe en charge de gérer les articles vendus
 */
public class ArticleVenduManagerImpl implements ArticleVenduManager {

	private static class ArticleVenduManagerHolder {
		private static ArticleVenduManagerImpl instance = new ArticleVenduManagerImpl();
	}

	private ArticleVenduManagerImpl() {
	}

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

	@Override
	public ArticleVendu selectBestEnchereByNoArticle(Integer noArticle) throws BLLException {
		try {
			return DAOFactory.getArticleVenduDAO().selectArticleByIdBestEnchere(noArticle);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
	}

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
	 * Méthode en charge de mettre à jour l'état des enchères en fonction de la date
	 * du jour
	 * 
	 * @param dateDuJour
	 * @throws BLLException
	 */
	public void actualisationEtatEnchereBDD(LocalDate dateDuJour) throws BLLException {
		List<ArticleVendu> lstArticles = new ArrayList<ArticleVendu>();
		List<ArticleVendu> lstArticlesUpdate = new ArrayList<ArticleVendu>();

		// Récuparation de tous les articles de la BDD
		try {
			lstArticles = DAOFactory.getArticleVenduDAO().getAllArticleVendu();
		} catch (DALException e) {
			e.printStackTrace();
		}

		// Mise à jour des états vente et insertion dans une liste des articles qui
		// devront être mis à jour en BDD
		for (ArticleVendu articleVendu : lstArticles) {
			if ("CREE".equals(articleVendu.getEtatVente()) && (dateDuJour.isAfter(articleVendu.getDateDebutEncheres())
					|| dateDuJour.isEqual(articleVendu.getDateDebutEncheres()))) {
				articleVendu.setEtatVente("ENCOURS");
				lstArticlesUpdate.add(articleVendu);
			}

			if ("ENCOURS".equals(articleVendu.getEtatVente())
					&& dateDuJour.isAfter(articleVendu.getDateFinEncheres())) {
				articleVendu.setEtatVente("CLOTURE");
				lstArticlesUpdate.add(articleVendu);
				ajoutCreditVendeur(articleVendu, articleVendu.getPrixVente());
			}
		}

		// Mise à jour de l'état de vente des articles en BDD

		lstArticlesUpdate.forEach(a -> {

				try {
					DAOFactory.getArticleVenduDAO().updateEtatVente(a);
				} catch (DALException e) {
					e.printStackTrace();
				}
		});
	}


	

	/**
	 * Méthode en charge de créditer le vendeur à la clôture de l'enchère
	 * 
	 * @param prixVente
	 * @throws BLLException
	 */
	private void ajoutCreditVendeur(ArticleVendu article, Integer prixVente) throws BLLException {
		try {
			DAOFactory.getUtilisateurDAO().modifierCreditUtilisateur(article.getUtilisateur().getNoUtilisateur(),
					prixVente);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}

	}

}
