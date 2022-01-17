/**
 * 
 */
package fr.eni.encheres.dao;


public interface EnchereDAO {


	void ajouterNouvelleEnchere(Integer noArticle, Integer noEncheriste, Integer montantNouvelleEnchere) throws DALException;
}
