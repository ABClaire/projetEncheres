/**
 * 
 */
package fr.eni.encheres.bll;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Interface de l'implémentation article vendu
 */
public interface ArticleVenduManager {
	
	ArticleVendu ajouterUnArticle(ArticleVendu articleAVendre) throws BLLException;
	
	ArticleVendu selectBestEnchereByNoArticle(Integer noArticle) throws BLLException;

	void miseAJourPrixVente(Integer noArticleEnchere, Integer proposition) throws BLLException;
	
}
