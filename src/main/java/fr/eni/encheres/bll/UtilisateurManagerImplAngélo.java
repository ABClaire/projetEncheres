/**
 * 
 */
package fr.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.DALException;
import fr.eni.encheres.dao.DAOFactory;

/**
 * Classe en charge de gérer les utilisateurs du site
 * 
 */
public class UtilisateurManagerImplAng�lo implements UtilisateurManager {

	private static class UtilisateurManagerHolder {
		private static UtilisateurManagerImplAng�lo instance = new UtilisateurManagerImplAng�lo();
	}
	
	private UtilisateurManagerImplAng�lo() {}
	
	public static UtilisateurManagerImplAng�lo getInstance() {
		return UtilisateurManagerHolder.instance;
		
	}
	
	@Override
	public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) throws BLLException {
		try {
			DAOFactory.getUtilisateurDAO().getAllUtilisateurs().add(utilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
		
		return utilisateur;
	}
	
	
	
	public Utilisateur ControleIdentifiantMotDePasse (Utilisateur utilisateur) throws BLLException {
		// je pr�supose que l'identifiant sera un mail
		String identifiant = utilisateur.getEmail();
		String motDePasse = utilisateur.getMotDePasse();
		
		Boolean combinaisonValide = false;
		Utilisateur compteAssocier = utilisateur;
		
		//r�cup�rer liste des comptes 
		
		List<Utilisateur> LstCompteUtilisateur = new ArrayList<>();
		
		// DAl exception passer en BLL exception
		try {
			LstCompteUtilisateur = DAOFactory.getUtilisateurDAO().getAllUtilisateurs();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BLLException(e);
		}
		
		for (Utilisateur compte : LstCompteUtilisateur) {
			
			if (identifiant.equals(compte.getEmail()) && motDePasse.equals(compte.getMotDePasse())) {
				// si vrai alors ce compte existe !
				combinaisonValide= true;
				compteAssocier = compte;
			}
		}
		
		///////////////////////
		
		if (combinaisonValide == false) {
			BLLException be = new BLLException();

			be.ajouterErreur(new ParameterException("Erreur Mdp identifiant"));
			
			throw new BLLException(be);
			
			// je throw une exception pour signaler que le mot de passe ou identifiant est invalide
		}

		return compteAssocier;
		// retourne le compte et les informations li�es au compte si une association a �t� trouv�; 
		// Sinon retourne les saisies de l'utilisateur et l�ve une exception 
	}
	
	
	
	
	
}
