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
 * Classe en charge de gÃ©rer les utilisateurs du site
 * 
 */
public class UtilisateurManagerImplAngélo implements UtilisateurManager {

	private static class UtilisateurManagerHolder {
		private static UtilisateurManagerImplAngélo instance = new UtilisateurManagerImplAngélo();
	}
	
	private UtilisateurManagerImplAngélo() {}
	
	public static UtilisateurManagerImplAngélo getInstance() {
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
		// je présupose que l'identifiant sera un mail
		String identifiant = utilisateur.getEmail();
		String motDePasse = utilisateur.getMotDePasse();
		
		Boolean combinaisonValide = false;
		Utilisateur compteAssocier = utilisateur;
		
		//récupérer liste des comptes 
		
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
		// retourne le compte et les informations liées au compte si une association a été trouvé; 
		// Sinon retourne les saisies de l'utilisateur et lève une exception 
	}
	
	
	
	
	
}
