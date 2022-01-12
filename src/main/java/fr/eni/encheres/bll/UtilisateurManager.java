/**
 * 
 */
package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Utilisateur;

/**
 * Interface de la BLL  
 */
public interface UtilisateurManager {
	
	Utilisateur ajouterNouvelUtilisateur(Utilisateur utilisateur) throws BLLException;
	void modifierUtilisateur (Utilisateur utilisateur) throws BLLException;
}
