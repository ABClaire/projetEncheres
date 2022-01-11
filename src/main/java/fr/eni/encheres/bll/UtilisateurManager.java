/**
 * 
 */
package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Utilisateur;

/**
 * Interface de la BLL  
 */
public interface UtilisateurManager {
	
	Utilisateur ajouterUtilisateur(Utilisateur utilisateur) throws BLLException;

}
