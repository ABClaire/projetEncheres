/**
 * 
 */
package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.DALException;
import fr.eni.encheres.dao.DAOFactory;

/**
 * Classe en charge de gérer les utilisateurs du site
 * 
 */
public class UtilisateurManagerImpl implements UtilisateurManager {
	
	private Boolean pseudoIsValid = false;
	private Boolean pseudoIsDoublon = false;
	private Boolean emailIsDoublon = false;

	private static class UtilisateurManagerHolder {
		private static UtilisateurManagerImpl instance = new UtilisateurManagerImpl();
	}
	
	private UtilisateurManagerImpl() {}
	
	public static UtilisateurManagerImpl getInstance() {
		return UtilisateurManagerHolder.instance;
		
	}
	
	@Override
	public Utilisateur ajouterNouvelUtilisateur(Utilisateur nouvelUtilisateur) throws BLLException {
		
		BLLException be = new BLLException();
		
		verificationCaracteresPseudo(nouvelUtilisateur.getPseudo(), be);
		verificationPseudoUnique(nouvelUtilisateur.getPseudo(), be);
		verificationEMailUnique(nouvelUtilisateur.getEmail(), be);
		
		if(be.hasErreur()) {
			throw be;
		}
		
		Utilisateur utilisateur = mapUtilisateur(nouvelUtilisateur);
		
		try {
			DAOFactory.getUtilisateurDAO().ajouterUtilisateur(utilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
		
		return utilisateur;
	}


	/**
	 * Méthode en charge de vérfiier que le pseudo ne possède que des caractères alphanumériques
	 * @param pseudo
	 * @param be
	 */
	private void verificationCaracteresPseudo(String pseudo, BLLException be) {
		for (int i = 0; i < pseudo.length(); i++) {
			pseudoIsValid =	Character.isLetterOrDigit(pseudo.charAt(i));
			if(!pseudoIsValid) {
				be.ajouterErreur(new ParameterException("Le pseudo doit contenir des caractères alphanumériques uniquement. Les caractères spéciaux sont interdits"));
				break;
			}
		}		
	}

	/**
	 * Méthode en charge de vérifer l'unicité du pseudo
	 * @param pseudo
	 * @param exception couche BLL
	 */
	private void verificationPseudoUnique(String pseudo, BLLException be) {
		try {
			List<Utilisateur> lstUtilisateurs = DAOFactory.getUtilisateurDAO().getAllUtilisateurs();
			lstUtilisateurs.stream().forEach(
						utilisateur -> {
							if(utilisateur.getPseudo().equals(pseudo)) {
								pseudoIsDoublon = true;
							}
						});
			if(pseudoIsDoublon) {
				be.ajouterErreur(new ParameterException("Le pseudo renseigné existe déjà"));
				pseudoIsDoublon = false;
			}
			
		} catch (DALException e) {
			e.printStackTrace();
		}
		
	}
	

	/**
	 * Méthode en charge de vérifier l'unicité de l'email
	 * @param email
	 * @param be
	 */
	private void verificationEMailUnique(String email, BLLException be) {
		try {
			List<Utilisateur> lstUtilisateurs = DAOFactory.getUtilisateurDAO().getAllUtilisateurs();
			lstUtilisateurs.stream().forEach(
						utilisateur -> {
							if(utilisateur.getEmail().equals(email)) {
								emailIsDoublon = true;
							}
						});
			if(emailIsDoublon) {
				be.ajouterErreur(new ParameterException("L'email renseigné existe déjà."));
				emailIsDoublon = false;
			}
			
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * Méthode en charge de créer un nouvel utilisateur en fonction des données saisies
	 * @param nouvelUtilisateur
	 * @return
	 */
	private Utilisateur mapUtilisateur(Utilisateur nouvelUtilisateur) {
		Utilisateur utilisateur;
		String pseudo = nouvelUtilisateur.getPseudo();
		String nom = nouvelUtilisateur.getNom();
		String prenom = nouvelUtilisateur.getPrenom();
		String email = nouvelUtilisateur.getEmail();
		String rue = nouvelUtilisateur.getRue();
		String cp = nouvelUtilisateur.getCodePostal();
		String ville = nouvelUtilisateur.getVille();
		String mdp = nouvelUtilisateur.getMotDePasse();
		
		
		if(nouvelUtilisateur.getTelephone() != null) {
			String telephone = nouvelUtilisateur.getTelephone();
			utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, mdp, ville, telephone, 100, false);
			
		} else {
			utilisateur = new Utilisateur(pseudo, nom, prenom, email, rue, cp, ville, mdp, 100, false);	
		}
		
		return utilisateur;
	}
	
	
	public Utilisateur verificationIdentifiantMotDePasse (Utilisateur utilisateur) throws BLLException {
		Boolean combinaisonValide = false;
		
		// récupération de la saisie utilisateur
		String identifiant = utilisateur.getEmail();
		String motDePasse = utilisateur.getMotDePasse();
		String pseudo = utilisateur.getPseudo();
		Utilisateur compteAAssocier = utilisateur;
		
		//récupération de la liste des comptes
		List<Utilisateur> LstCompteUtilisateur;
		
		// DAl exception passer en BLL exception
		try {
			LstCompteUtilisateur = DAOFactory.getUtilisateurDAO().getAllUtilisateurs();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
		
		// Vérification de l'existance d'un compte utilisateur
		for (Utilisateur compte : LstCompteUtilisateur) {	
			if ((identifiant.equals(compte.getEmail()) && motDePasse.equals(compte.getMotDePasse())) || (pseudo.equals(compte.getPseudo()) && motDePasse.equals(compte.getMotDePasse()))) {
				// le compte existe
				combinaisonValide = true;
				compteAAssocier = compte;
				break;
			}
			
			
		}
		
		// si le compte n'exite pas: exception
		if (combinaisonValide == false) {
			BLLException be = new BLLException();

			be.ajouterErreur(new ParameterException("Erreur Mdp identifiant"));
			
			throw be;
		}

		return compteAAssocier;
	}
	
}
