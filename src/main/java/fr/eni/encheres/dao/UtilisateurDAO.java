/**
 * 
 */
package fr.eni.encheres.dao;

import java.sql.SQLException;

import fr.eni.encheres.bo.Utilisateur;

/**
 * Interface Utilisateur DAO
 */
public interface UtilisateurDAO {
	void ajouterUtilisateur(Utilisateur utilisateur) throws SQLException;

}
