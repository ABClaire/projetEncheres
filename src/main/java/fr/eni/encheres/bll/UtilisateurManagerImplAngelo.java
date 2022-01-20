/**
 * 
 */
package fr.eni.encheres.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.DALException;
import fr.eni.encheres.dao.DAOFactory;

/**
 * Classe en charge de gérer les utilisateurs du site
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
		String message = "Aucun compte correspondant � cette adresse mail";
		try {
			lstUtilisateur = DAOFactory.getUtilisateurDAO().getAllUtilisateurs();

		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}

		for (Utilisateur utilisateur : lstUtilisateur) {

			if (email.equals(utilisateur.getEmail())) {
				message = "Votre mot de passe vous � �t� renvoy� par mail";
			}
		}

		return message;

	}

	// passe plat
	public List<ArticleVendu> RecuperationArticleEtUtilisateur() throws BLLException {

		try {
			return DAOFactory.getArticleVenduDAO().selectJointArticleUtilisateur();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}

	}

	// TODO: faire remonter en premier les plus r�cente

	// Filtre la liste global des Articles pour ramener seulement les �l�ments
	// de la cat�gorie choisie
	public List<ArticleVendu> FiltreSuivantCategorie(String categorie) throws BLLException {
		List<ArticleVendu> lstAReturn = new ArrayList<>();
		List<ArticleVendu> lstFinal = new ArrayList<>();
		try {
			List<ArticleVendu> lstGlobal = DAOFactory.getArticleVenduDAO().selectJointArticleUtilisateur();

			if (!categorie.equals("Toutes")) {

				for (ArticleVendu articleVendu : lstGlobal) {
					// TODO : risque de probl�me entre la cat�gorie de l'ihm Sport et loisir et
					// de la base Sport&Loisirs
					if (articleVendu.getCategorieArticle().getLibelle().equals(categorie)) {
						// si la categorie de l'objet est la m�me que celle passer en param�tre on
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

		// R�cup�re les ench�res en cours (date d'ench�re inf�rieur � la
		// date du jour)
		

		return lstAReturn;
	}
	
	
	// Method qui retourne une liste d'article dans l'état Encours
	private List<ArticleVendu> filtreArticleEncoursParDate(List<ArticleVendu> lstEntree) {
		List<ArticleVendu> lstReturn = new ArrayList<ArticleVendu>();
		for (ArticleVendu articleVendu : lstEntree) {

			if (articleVendu.getDateFinEncheres().isAfter(LocalDate.now()) & (articleVendu.getDateDebutEncheres().isBefore(LocalDate.now())) | articleVendu.getDateDebutEncheres().equals(LocalDate.now())) {
				lstReturn.add(articleVendu);
			}
		}
		return lstReturn;
	}
	
	


	// M�thode pour faire une recherche par mot cl�e prend en entr�e la liste
	// issue du chois de la cat�gorie et retourne une list
	// tri� par les mots cl�e choisie

	public List<ArticleVendu> RechercheDansLeNomDelArticle(List<ArticleVendu> lstEntree, String motClef) {
		List<ArticleVendu> LstIssueDeLaRecherche = new ArrayList<ArticleVendu>();

		for (ArticleVendu articleVendu : lstEntree) {

			if (articleVendu.getNomArticle().toUpperCase().contains(motClef.toUpperCase())) {
				LstIssueDeLaRecherche.add(articleVendu);
			}
		}
		return LstIssueDeLaRecherche;
		// si retourne listVide mettre un message "Aucun n'article correspondant �
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

	// -------Methode pour
	// EncherereOuverte/MesEncheres/MesEnchereRemporter----------

	

	// Methode pour les checkbox D'Achat

	public List<ArticleVendu> filtreCheckboxAchat(List<Integer> lstCheck, List<ArticleVendu> lstIssuDeRecherche,
			Utilisateur utilisateurSession) {

		List<ArticleVendu> lstReturn = new ArrayList<ArticleVendu>();

		for (Integer integer : lstCheck) {
			switch (integer) {
			case 1: // Dans Achat -> Enchère Ouverte
				List<ArticleVendu> LstTemporaire = new ArrayList<ArticleVendu>();
				for (ArticleVendu articleVendu : lstIssuDeRecherche) {
					if (articleVendu.getUtilisateur()
							.getNoUtilisateur() != (utilisateurSession.getNoUtilisateur())) {
						// je récupère les articles avec le statut ENCOURS et j'exclue les articles que
						// l'utilisateur vend
						LstTemporaire.add(articleVendu);
					}
					lstReturn = filtreArticleEncoursParDate (LstTemporaire);
				}
				
				break;

			case 2: // Achat -> mes enchères

				for (ArticleVendu articleVendu : lstIssuDeRecherche) {

					for (Enchere enchere : articleVendu.getLstEncheres()) {

						if (enchere.getNoEncheriste() == utilisateurSession.getNoUtilisateur()) {
							// je récupère la liste des articles sur lesquels j'ai enchéris et je check si
							// l'id de l'utilisateur en session est présent si oui j'ajoute à la liste à
							// retourner
							lstReturn.add(articleVendu);
						}
					}
				}

				break;
			case 3:// mes enchères remportées
				for (ArticleVendu articleVendu : lstIssuDeRecherche) {
						// TODO : ESSAYER de récuperer la BestEnchere en itérant dans la liste puis de set l'articles avec 
					
					ArticleVendu articlestockage = new ArticleVendu();
					try {
						articlestockage = DAOFactory.getArticleVenduDAO().selectArticleByIdBestEnchere(articleVendu.getNoArticle());
					} catch (DALException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					articleVendu.setEnchereMaximum(articlestockage.getEnchereMaximum());
					
					
					
					if (articleVendu.getDateFinEncheres().isBefore(LocalDate.now()) & articleVendu.getEnchereMaximum().getUtilisateur().getNoUtilisateur() == utilisateurSession.getNoUtilisateur()) {
						// tu comprendras plus tard
						lstReturn.add(articleVendu);
					}

				}

				break;

			default:
				System.out.println("error tu es dans le default du switch de la boucle des choix");
				break;

			}

		}

		List<ArticleVendu> lstDesSurvivants = lstReturn.stream().distinct().collect(Collectors.toList());

		return lstDesSurvivants;

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
