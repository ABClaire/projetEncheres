package fr.eni.encheres.test;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateurManagerImplAngélo;
import fr.eni.encheres.bo.Utilisateur;

public class TestBLLAngélo {
	
	public static void main(String[] args) {
		
		// test identifiant 1
		// Utilisateur claire 
		
		Utilisateur test = new Utilisateur("clairegoarnisson@gmail.com", "mdp5");
		Utilisateur recuperer = new Utilisateur();
		try {
			recuperer = UtilisateurManagerImplAngélo.getInstance().ControleIdentifiantMotDePasse(test);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		// constat en cas de combinaison juste je fait bien remonter l'objet utilisateur et toute les données corespondante en base de donnée
		// TODO message d'exception non remonté
		// en cas d'erreur l'exception est bien levée mais le message prédéfinie n'est pas présenté (ni les donnée saisie en entrée)
		System.out.println(recuperer);
		
	}

}
