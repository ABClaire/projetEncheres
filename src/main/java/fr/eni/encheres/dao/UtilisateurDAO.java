/**
 * 
 */
package fr.eni.encheres.dao;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bo.Utilisateur;

/**
 * Interface Utilisateur DAO
 */
public interface UtilisateurDAO {
	void ajouterUtilisateur(Utilisateur utilisateur) throws DALException;
	List<Utilisateur> getAllUtilisateurs() throws DALException;
	Utilisateur getById(int idUtilisateur) throws DALException;
	Utilisateur getByPseudo(String pseudoUtilisateur) throws DALException;
	void modifierUtilisateur(Utilisateur utilisateur) throws DALException;


}
