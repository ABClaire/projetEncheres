/**
 * 
 */
package fr.eni.encheres.test;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bll.UtilisateurManagerImpl;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Classe en charge de tester la couche BLL
 * 
 */
public class TestBLL {
	public static void main(String[] args) {
		UtilisateurManager mger = UtilisateurManagerImpl.getInstance();
		
	/*
	 * =========================================================
	 * 				TEST INSCRIPTION UTILISATEUR
	 * =========================================================
	*/
		// Vérification erreur pseudo avec caractères spéciaux
		Utilisateur utilisateurErreurPseudo = new Utilisateur("Pseudo!", "Erreur", "Pseudo", "erreur.pseudo@outlook.fr", "15 rue de l'impasse", "35000", "Rennes", "monErreur", 100, false);
		try {
			mger.ajouterNouvelUtilisateur(utilisateurErreurPseudo);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		// Vérification erreur de doublon de pseudo
		Utilisateur utilisateurDoublonPseudo = new Utilisateur("mamie", "Doublon", "Pseudo", "erreur.pseudo@outlook.fr", "16 rue de l'impasse", "35000", "Rennes", "monErreur", 100, false);
		try {
			mger.ajouterNouvelUtilisateur(utilisateurDoublonPseudo);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		// Vérification erreur de doublon d'email
		Utilisateur utilisateurDoublonEmail = new Utilisateur("Alfred", "Doublon", "Email", "clairegoarnisson@gmail.com", "17 rue de l'impasse", "35000", "Rennes", "monErreur", 100, false);
		try {
			mger.ajouterNouvelUtilisateur(utilisateurDoublonEmail);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		// Vérification insertion utilisateur sans anomalie
		Utilisateur utilisateurSansAnomalies = new Utilisateur("JeSaisPas", "Cérien", "Jean", "jeancerien@gmail.com", "13 la voie est libre", "35000", "Rennes", "monErreur", 100, false);
		try {
			mger.ajouterNouvelUtilisateur(utilisateurSansAnomalies);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
	
		/*
		 * =========================================================
		 * 				TEST CONNEXION UTILISATEUR
		 * =========================================================
		*/
		
		// test identifiant 1
		// Utilisateur claire 
				
		Utilisateur test = new Utilisateur("clairegoarnisson@gmail.com", "mdp5");
		Utilisateur recuperer = new Utilisateur();
		try {
			recuperer = UtilisateurManagerImpl.getInstance().verificationLogin(test);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		// constat en cas de combinaison juste je fait bien remonter l'objet utilisateur et toute les donn�es corespondante en base de donn�e
		// TODO message d'exception non remont�
		// en cas d'erreur l'exception est bien lev�e mais le message pr�d�finie n'est pas pr�sent� (ni les donn�e saisie en entr�e)
		System.out.println(recuperer);

		

		
	}

}
