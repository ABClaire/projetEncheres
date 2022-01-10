/**
 * 
 */
package fr.eni.encheres.dao;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;

/**
 * Interface Utilisateur DAO
 */
public interface UtilisateurDAO {
	void ajouterUtilisateur(Utilisateur utilisateur) throws DALException;
	List<Utilisateur> getAllUtilisateurs() throws DALException;

}
