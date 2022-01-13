/**
 * 
 */
package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.DALException;

/**
 * Interface de la BLL  
 */
public interface UtilisateurManager {
	
	Utilisateur ajouterNouvelUtilisateur(Utilisateur utilisateur) throws BLLException;
	void modifierUtilisateur(Utilisateur utilisateur, Utilisateur utilisateurModif) throws BLLException;
	void supprimerUtilisateur(int idUtilisateur) throws BLLException, DALException;
}
