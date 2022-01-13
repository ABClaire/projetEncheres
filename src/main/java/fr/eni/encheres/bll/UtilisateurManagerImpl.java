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
 * Classe en charge de gerer les utilisateurs du site
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
		
		verificationPseudo(nouvelUtilisateur.getPseudo(), be);
		verificationNom(nouvelUtilisateur.getNom(), be);
		verificationPrenom(nouvelUtilisateur.getPrenom(), be);
		verificationEmail(nouvelUtilisateur.getEmail(), be);
		verificationTelephone(nouvelUtilisateur.getTelephone(), be);
		verificationRue(nouvelUtilisateur.getRue(), be);
		verificationCp(nouvelUtilisateur.getCodePostal(), be);	
		verificationVille(nouvelUtilisateur.getVille(), be);
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

	/**
	 * Méthode en charge de vérifier la connexion de l'utilistaeur.
	 * @return l'utilisateur connecté
	 */
	public Utilisateur verificationLogin (Utilisateur utilisateur) throws BLLException {
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

			be.ajouterErreur(new ParameterException("Identifiant ou mot de passe incorrect. Veuillez réessayer ou cliquez sur le bouton de création d'un compte."));
			
			throw be;
		}

		return compteAAssocier;
	}
		
		/**
		 * Méthode en charge d'afficher un message de récupération de mot de passe à l'utilisateur
		 * @param email
		 * @return un message d'envoi de mail
		 * @throws BLLException
		 */
		public String RecuperationMotDePasse (String email) throws BLLException {
			
			List<Utilisateur> lstUtilisateur = new ArrayList<>();
			String message = "Aucun compte correspondant à cette adresse mail";
			try {
				lstUtilisateur = DAOFactory.getUtilisateurDAO().getAllUtilisateurs();
				
			} catch (DALException e) {
				e.printStackTrace();
				throw new BLLException(e);
			}
			
			for (Utilisateur utilisateur : lstUtilisateur) {
				
				if (email.equals(utilisateur.getEmail())) {
					 message = "Votre mot de passe vous a été envoyé par mail";
				}
			}
			
			
			return message;
		
		}
		
	/*
	 * R�cuperer un utilisateur par son ID
	 */
	public Utilisateur getByIdUtilisateur (int IdUtilisateur) throws BLLException {
		try {
			return DAOFactory.getUtilisateurDAO().getById(IdUtilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
	}
	
	/*
	 * R�cuperer un utilisateur par son Pseudo
	 */
	public Utilisateur getByPseudoUtilisateur (String pseudoUtilisateur) throws BLLException {
		try {
			return DAOFactory.getUtilisateurDAO().getByPseudo(pseudoUtilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
	}

	/*
	 * M�thode en charge de mofidier un utilisateur existant par l'utilisateur
	 */
	@Override
	public void modifierUtilisateur(Utilisateur utilisateur, Utilisateur utilisateurModif) throws BLLException {
		BLLException be= new BLLException();
	
		verificationPseudo(utilisateurModif.getPseudo(), be);
		verificationNom(utilisateurModif.getNom(), be);
		verificationPrenom(utilisateurModif.getPrenom(), be);
		verificationEmail(utilisateurModif.getEmail(), be);
		verificationTelephone(utilisateurModif.getTelephone(), be);
		verificationRue(utilisateurModif.getRue(), be);
		verificationCp(utilisateurModif.getCodePostal(), be);	
		verificationVille(utilisateurModif.getVille(), be);
		verificationCaracteresPseudo(utilisateurModif.getPseudo(), be);
		
		//Verificiation du Pseudo unique et du Mail unique
		
		if(utilisateur.getPseudo().equals(utilisateurModif.getPseudo())) {
			System.out.println("Le pseudo de l'utilisateur reste le m�me");	
		}
		else {
			verificationPseudoUnique(utilisateurModif.getPseudo(), be);
			System.out.println("V�rifier si le pseudo existe d�j�");
			System.out.println(utilisateurModif.getPseudo());
		}
		
		if(utilisateur.getEmail().equals(utilisateurModif.getEmail())){
			System.out.println("L'email de l'utilisateur reste le m�me");
		}
		else {
			verificationEMailUnique(utilisateurModif.getEmail(), be);
			System.out.println("V�rifier si l'email existe d�j�");
			System.out.println(utilisateurModif.getEmail());

		}
	
		if(be.hasErreur()) {
			throw be;
		}
			
		try {
			DAOFactory.getUtilisateurDAO().modifierUtilisateur(utilisateurModif);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
	}
	
	
	
	/*
	 * M�thode en charge de supprimier un profil utilisateur par l'utilisateur
	 */
	
	
	@Override
	public void supprimerUtilisateur(int idUtilisateur) throws BLLException, DALException {
			BLLException be = new BLLException();
			if(be.hasErreur()) {
				throw be;
			}
			DAOFactory.getUtilisateurDAO().supprimerUtilisateur(idUtilisateur);	
	}
	
	
	
	
	
	
	
	
	
	/********************CONTRAINTES ET VERIFICATIONS*********************
	 * 
	 *********************************************************************/
	
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
	
	/*****VERIFICATIONS******/
	private void verificationPseudo(String pseudo, BLLException be) {
		if(pseudo == null || pseudo.isBlank() || pseudo.length()>30) {
			be.ajouterErreur(new ParameterException("Le pseudo est obligatoire et doit �tre <=30 caract�res" ));
		}
	}
	private void verificationNom(String nom, BLLException be) {
		if(nom == null || nom.isBlank() || nom.length()>30) {
			be.ajouterErreur(new ParameterException("Le nom est obligatoire et doit �tre <=30 caract�res" ));
		}
	}
	private void verificationPrenom(String prenom, BLLException be) {
		if(prenom == null || prenom.isBlank() || prenom.length()>30) {
			be.ajouterErreur(new ParameterException("Le pr�nom est obligatoire et doit �tre <=30 caract�res" ));
		}
	}
	private void verificationEmail(String email, BLLException be) {
		if(email == null || email.isBlank() || email.length()>50) {
			be.ajouterErreur(new ParameterException("L'email est obligatoire et doit �tre <=50 caract�res" ));
		}
	}
	private void verificationTelephone(String telephone, BLLException be) {
		if(telephone.length()>15) {
			be.ajouterErreur(new ParameterException("Le telephone doit �tre <=15 caract�res" ));
		}
	}
	private void verificationRue(String rue, BLLException be) {
		if(rue == null || rue.isBlank() || rue.length()>30) {
			be.ajouterErreur(new ParameterException("La rue est obligatoire et doit �tre <=30 caract�res" ));
		}
	}
	private void verificationCp(String cp, BLLException be) {
		if(cp == null || cp.isBlank() || cp.length()>10) {
			be.ajouterErreur(new ParameterException("Le code postal est obligatoire et doit �tre <=10 caract�res" ));
		}
	}
	
	private void verificationVille(String ville, BLLException be) {
		if(ville == null || ville.isBlank() || ville.length()>10) {
			be.ajouterErreur(new ParameterException("La ville est obligatoire et doit �tre <=50 caract�res" ));
		}
	}
	

}
