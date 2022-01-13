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
 * Classe en charge de gérer les utilisateurs du site
 * 
 */
public class UtilisateurManagerImplAngelo  {
	
	

	private static class UtilisateurManagerHolder {
		private static UtilisateurManagerImplAngelo instance = new UtilisateurManagerImplAngelo();
	}
	
	private UtilisateurManagerImplAngelo() {}
	
	public static UtilisateurManagerImplAngelo getInstance() {
		return UtilisateurManagerHolder.instance;
		
	}
	
	
	
	public String RecuperationMotDePasse (String email) throws BLLException {
		
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
	
	//passe plat 
	public List<ArticleVendu> RecuperationArticleEtUtilisateur () throws BLLException{
	
		
		try {
			return DAOFactory.getArticleVenduDAO().selectJointArticleUtilisateur();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BLLException(e);
		}
		
		
		
	}
	
	//TODO: faire remonter en premier les plus r�cente 
	
	//Filtre la liste global des Articles pour ramener seulement les �l�ments de la cat�gorie choisie 
	public List<ArticleVendu> FiltreSuivantCategorie (String categorie) throws BLLException{
		List<ArticleVendu> lstAReturn = new ArrayList<>();
		List<ArticleVendu> lstFinal = new ArrayList<>();
		try {
			List<ArticleVendu> lstGlobal = DAOFactory.getArticleVenduDAO().selectJointArticleUtilisateur();
			
			if (!categorie.equals("Toutes")) {
				
				for (ArticleVendu articleVendu : lstGlobal) {
					//TODO : risque de probl�me entre la cat�gorie de l'ihm Sport et loisir et de la base Sport&Loisirs
					if (articleVendu.getCategorieArticle().getLibelle().equals(categorie)) {
						//si la categorie de l'objet est la m�me que celle passer en param�tre on ajoute l'article a la liste
						lstAReturn.add(articleVendu);
					}
				}
			}else {
				lstAReturn =lstGlobal;
			}
			
				
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BLLException(e);
		}
		
		//R�cup�re les ench�res en cours (date d'ench�re inf�rieur � la date du jour) 
		for (ArticleVendu articleVendu : lstAReturn) {
			
			if (articleVendu.getDateFinEncheres().isAfter(LocalDate.now())) {		
				lstFinal.add(articleVendu);
			}
		}

		return lstFinal;
	}
	
	//M�thode pour faire une recherche par mot cl�e prend en entr�e la liste issue du chois de la cat�gorie et retourne une list 
	// tri� par les mots cl�e choisie
	
	
	public List<ArticleVendu> RechercheDansLeNomDelArticle (List<ArticleVendu> lstEntree , String motClef){
		List<ArticleVendu> LstIssueDeLaRecherche = new ArrayList<ArticleVendu>();
		
		for (ArticleVendu articleVendu : lstEntree) {
			
			if (articleVendu.getNomArticle().toUpperCase().contains(motClef.toUpperCase())) {
				 LstIssueDeLaRecherche.add(articleVendu);
			}
		}
		return LstIssueDeLaRecherche;
		// si retourne listVide mettre un message "Aucun n'article correspondant � votre Recherche"
	}
	
	
	
}
