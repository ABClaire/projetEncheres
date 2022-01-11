package fr.eni.encheres.test;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateurManagerImplAng�lo;
import fr.eni.encheres.bo.Utilisateur;

public class TestBLLAng�lo {
	
	public static void main(String[] args) {
		
		// test identifiant 1
		// Utilisateur claire 
		
		Utilisateur test = new Utilisateur("clairegoarnisson@gmail.com", "mdp5");
		Utilisateur recuperer = new Utilisateur();
		try {
			recuperer = UtilisateurManagerImplAng�lo.getInstance().ControleIdentifiantMotDePasse(test);
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
