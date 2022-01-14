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
	
	ArticleVendu selectByNoArticle(Integer noArticle) throws BLLException;

}
