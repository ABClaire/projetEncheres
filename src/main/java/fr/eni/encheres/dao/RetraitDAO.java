/**
 * 
 */
package fr.eni.encheres.dao;

import java.sql.SQLException;

import fr.eni.encheres.bo.Retrait;

public interface RetraitDAO {
	
	void ajouterRetrait(Retrait retrait) throws DALException;

}
