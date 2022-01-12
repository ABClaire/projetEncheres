/**
 * 
 */
package fr.eni.encheres.bll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.DALException;
import fr.eni.encheres.dao.DAOFactory;
import fr.eni.encheres.dao.UtilisateurDAO;
import fr.eni.encheres.dao.jdbc.JdbcTools;
import fr.eni.encheres.dao.jdbc.UtilisateurDAOImpl;

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
	 * M√©thode en charge de cr√©er un nouvel utilisateur en fonction des donn√©es saisies
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
	/*
	 * MÈthode en charge de 
	 */
	
	public Utilisateur verificationIdentifiantMotDePasse (Utilisateur utilisateur) throws BLLException {
		Boolean combinaisonValide = false;
		
		// r√©cup√©ration de la saisie utilisateur
		String identifiant = utilisateur.getEmail();
		String motDePasse = utilisateur.getMotDePasse();
		String pseudo = utilisateur.getPseudo();
		Utilisateur compteAAssocier = utilisateur;
		
		//r√©cup√©ration de la liste des comptes
		List<Utilisateur> LstCompteUtilisateur;
		
		// DAl exception passer en BLL exception
		try {
			LstCompteUtilisateur = DAOFactory.getUtilisateurDAO().getAllUtilisateurs();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
		
		// V√©rification de l'existance d'un compte utilisateur
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

			be.ajouterErreur(new ParameterException("Identifiant ou mot de passe incorrect. Veuillez r√©essayer ou cliquez sur le bouton de cr√©ation d'un compte."));
			
			throw be;
		}

		return compteAAssocier;
	}

	/*
	 * RÈcuperer un utilisateur par son ID
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
	 * RÈcuperer un utilisateur par son Pseudo
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
	 * MÈthode en charge de mofidier un utilisateur existant par l'utilisateur
	 */
	@Override
	public void modifierUtilisateur(Utilisateur utilisateur) throws BLLException {
		BLLException be= new BLLException();
	
		verificationPseudo(utilisateur.getPseudo(), be);
		verificationNom(utilisateur.getNom(), be);
		verificationPrenom(utilisateur.getPrenom(), be);
		verificationEmail(utilisateur.getEmail(), be);
		verificationTelephone(utilisateur.getTelephone(), be);
		verificationRue(utilisateur.getRue(), be);
		verificationCp(utilisateur.getCodePostal(), be);	
		verificationVille(utilisateur.getVille(), be);
		verificationCaracteresPseudo(utilisateur.getPseudo(), be);
		verificationPseudoUnique(utilisateur.getPseudo(), be);
		verificationEMailUnique(utilisateur.getEmail(), be);
		
		if(be.hasErreur()) {
			throw be;
		}
			
		try {
			DAOFactory.getUtilisateurDAO().modifierUtilisateur(utilisateur);;
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
	}
	/********************CONTRAINTES ET VERIFICATIONS*********************
	 * 
	 *********************************************************************/
	
	/**
	 * M√©thode en charge de v√©rfiier que le pseudo ne poss√®de que des caract√®res alphanum√©riques
	 * @param pseudo
	 * @param be
	 */
	private void verificationCaracteresPseudo(String pseudo, BLLException be) {
		for (int i = 0; i < pseudo.length(); i++) {
			pseudoIsValid =	Character.isLetterOrDigit(pseudo.charAt(i));
			if(!pseudoIsValid) {
				be.ajouterErreur(new ParameterException("Le pseudo doit contenir des caract√®res alphanum√©riques uniquement. Les caract√®res sp√©ciaux sont interdits"));
				break;
			}
		}		
	}

	/**
	 * M√©thode en charge de v√©rifer l'unicit√© du pseudo
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
				be.ajouterErreur(new ParameterException("Le pseudo renseign√© existe d√©j√†"));
				pseudoIsDoublon = false;
			}
			
		} catch (DALException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * M√©thode en charge de v√©rifier l'unicit√© de l'email
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
				be.ajouterErreur(new ParameterException("L'email renseign√© existe d√©j√†."));
				emailIsDoublon = false;
			}
			
		} catch (DALException e) {
			e.printStackTrace();
		}

	}
	
	/*****VERIFICATIONS******/
	private void verificationPseudo(String pseudo, BLLException be) {
		if(pseudo == null || pseudo.isBlank() || pseudo.length()>30) {
			be.ajouterErreur(new ParameterException("Le pseudo est obligatoire et doit Ítre <=30 caractËres" ));
		}
	}
	private void verificationNom(String nom, BLLException be) {
		if(nom == null || nom.isBlank() || nom.length()>30) {
			be.ajouterErreur(new ParameterException("Le nom est obligatoire et doit Ítre <=30 caractËres" ));
		}
	}
	private void verificationPrenom(String prenom, BLLException be) {
		if(prenom == null || prenom.isBlank() || prenom.length()>30) {
			be.ajouterErreur(new ParameterException("Le prÈnom est obligatoire et doit Ítre <=30 caractËres" ));
		}
	}
	private void verificationEmail(String email, BLLException be) {
		if(email == null || email.isBlank() || email.length()>50) {
			be.ajouterErreur(new ParameterException("L'email est obligatoire et doit Ítre <=50 caractËres" ));
		}
	}
	private void verificationTelephone(String telephone, BLLException be) {
		if(telephone.length()>15) {
			be.ajouterErreur(new ParameterException("Le telephone doit Ítre <=15 caractËres" ));
		}
	}
	private void verificationRue(String rue, BLLException be) {
		if(rue == null || rue.isBlank() || rue.length()>30) {
			be.ajouterErreur(new ParameterException("La rue est obligatoire et doit Ítre <=30 caractËres" ));
		}
	}
	private void verificationCp(String cp, BLLException be) {
		if(cp == null || cp.isBlank() || cp.length()>10) {
			be.ajouterErreur(new ParameterException("Le code postal est obligatoire et doit Ítre <=10 caractËres" ));
		}
	}
	
	private void verificationVille(String ville, BLLException be) {
		if(ville == null || ville.isBlank() || ville.length()>10) {
			be.ajouterErreur(new ParameterException("La ville est obligatoire et doit Ítre <=50 caractËres" ));
		}
	}
	
}
