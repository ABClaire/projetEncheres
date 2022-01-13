/**
 * 
 */
package fr.eni.encheres.dao;

import java.util.List;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dao.DALException;

public interface CategorieDAO {
	
	List<Categorie> listeDesCategories() throws DALException;
	
	void ajouterCategorie(Categorie categorie) throws DALException;
}
