/**
 * 
 */
package fr.eni.encheres.dao;

import fr.eni.encheres.dao.jdbc.UtilisateurDAOImpl;

/**
 * Classe en charge de 
 */
public class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOImpl();
	}

}
