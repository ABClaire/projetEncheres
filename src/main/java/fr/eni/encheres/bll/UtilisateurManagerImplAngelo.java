/**
 * 
 */
package fr.eni.encheres.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.DALException;
import fr.eni.encheres.dao.DAOFactory;

/**
 * Classe en charge de gÃ©rer les utilisateurs du site
 * 
 */
public class UtilisateurManagerImplAngelo {

	private static class UtilisateurManagerHolder {
		private static UtilisateurManagerImplAngelo instance = new UtilisateurManagerImplAngelo();
	}

	private UtilisateurManagerImplAngelo() {
	}

	public static UtilisateurManagerImplAngelo getInstance() {
		return UtilisateurManagerHolder.instance;

	}

	public String RecuperationMotDePasse(String email) throws BLLException {

		List<Utilisateur> lstUtilisateur = new ArrayList<>();
		String message = "Aucun compte correspondant ï¿½ cette adresse mail";
		try {
			lstUtilisateur = DAOFactory.getUtilisateurDAO().getAllUtilisateurs();

		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}

		for (Utilisateur utilisateur : lstUtilisateur) {

			if (email.equals(utilisateur.getEmail())) {
				message = "Votre mot de passe vous ï¿½ ï¿½tï¿½ renvoyï¿½ par mail";
			}
		}

		return message;

	}

	// passe plat
	public List<ArticleVendu> RecuperationArticleEtUtilisateur() throws BLLException {

		try {
			return DAOFactory.getArticleVenduDAO().selectJointArticleUtilisateur();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BLLException(e);
		}

	}

	// TODO: faire remonter en premier les plus rï¿½cente

	// Filtre la liste global des Articles pour ramener seulement les ï¿½lï¿½ments
	// de la catï¿½gorie choisie
	public List<ArticleVendu> FiltreSuivantCategorie(String categorie) throws BLLException {
		List<ArticleVendu> lstAReturn = new ArrayList<>();
		List<ArticleVendu> lstFinal = new ArrayList<>();
		try {
			List<ArticleVendu> lstGlobal = DAOFactory.getArticleVenduDAO().selectJointArticleUtilisateur();

			if (!categorie.equals("Toutes")) {

				for (ArticleVendu articleVendu : lstGlobal) {
					// TODO : risque de problï¿½me entre la catï¿½gorie de l'ihm Sport et loisir et
					// de la base Sport&Loisirs
					if (articleVendu.getCategorieArticle().getLibelle().equals(categorie)) {
						// si la categorie de l'objet est la mï¿½me que celle passer en paramï¿½tre on
						// ajoute l'article a la liste
						lstAReturn.add(articleVendu);
					}
				}
			} else {
				lstAReturn = lstGlobal;
			}

		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}

		// Rï¿½cupï¿½re les enchï¿½res en cours (date d'enchï¿½re infï¿½rieur ï¿½ la
		// date du jour)
		for (ArticleVendu articleVendu : lstAReturn) {

			if (articleVendu.getDateFinEncheres().isAfter(LocalDate.now())) {
				lstFinal.add(articleVendu);
			}
		}

		return lstFinal;
	}

	// Mï¿½thode pour faire une recherche par mot clï¿½e prend en entrï¿½e la liste
	// issue du chois de la catï¿½gorie et retourne une list
	// triï¿½ par les mots clï¿½e choisie

	public List<ArticleVendu> RechercheDansLeNomDelArticle(List<ArticleVendu> lstEntree, String motClef) {
		List<ArticleVendu> LstIssueDeLaRecherche = new ArrayList<ArticleVendu>();

		for (ArticleVendu articleVendu : lstEntree) {

			if (articleVendu.getNomArticle().toUpperCase().contains(motClef.toUpperCase())) {
				LstIssueDeLaRecherche.add(articleVendu);
			}
		}
		return LstIssueDeLaRecherche;
		// si retourne listVide mettre un message "Aucun n'article correspondant ï¿½
		// votre Recherche"
	}

	// Methode pour filtrer par Achat-------------------------------------------
	public List<ArticleVendu> FiltreListeParArticleAchetable(List<ArticleVendu> lstEntree, String pseudoSession) {
		List<ArticleVendu> lstAretourner = new ArrayList<ArticleVendu>();

		for (ArticleVendu article : lstEntree) {
			if (article.getUtilisateur().getPseudo() != pseudoSession) {
				lstAretourner.add(article);
			}
		}

		return lstAretourner;
	}
	
	//-------Methode pour EnchèreOuverte/MesEnchèrs/MesEnchèreRemporter----------
	
		public List<ArticleVendu> AchatSwitchPourCheckbox (List<ArticleVendu> lstEntree, String pseudoSession, Integer choix){
			
			List<ArticleVendu> lstRetourner = new ArrayList<ArticleVendu>();
			
			for (ArticleVendu articleVendu : lstEntree) {
				
				switch (choix) {
				case 1://Enchère ouverte
					System.out.println("Enchère ouverte");
					if (articleVendu.getDateFinEncheres().isBefore(LocalDate.now())) {
						lstRetourner.add(articleVendu);
						System.out.println("dans le if");
					}
					break;
					
				case 2://Mes Enchères
					System.out.println("Enchère ouverte");
					
				
					break;
					
				case 3://Mes Enchère rapporter
					
					System.out.println("Mes Enchère rapporter");
					break;
					
				default://Aucun choix
					
					System.out.println("Aucun choix");
					break;
				}
			}
			
			return lstRetourner;
			}
	
	
	
	//Methode pour filtrer par Mes Ventes ---------------------------------------
	public List<ArticleVendu> FiltreListeParArticleDeLAcheteur(List<ArticleVendu> lstEntree, String pseudoSession){
		List<ArticleVendu> lstAretourner = new ArrayList<ArticleVendu>();
		
		for (ArticleVendu article : lstEntree) {
			if (article.getUtilisateur().getPseudo().equals(pseudoSession)) {
				lstAretourner.add(article);
			}
		}
		
		return lstAretourner;	
	}

}
