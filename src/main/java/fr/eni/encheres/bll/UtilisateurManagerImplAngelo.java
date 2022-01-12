/**
 * 
 */
package fr.eni.encheres.bll;

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
		String message = "Aucun compte correspondant à cette adresse mail";
		try {
			lstUtilisateur = DAOFactory.getUtilisateurDAO().getAllUtilisateurs();
			
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
		
		for (Utilisateur utilisateur : lstUtilisateur) {
			
			if (email.equals(utilisateur.getEmail())) {
				message = "Votre mot de passe vous à été renvoyé par mail";
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
	
	
	public List<ArticleVendu> FiltreSuivantCategorie (String categorie) throws BLLException{
		List<ArticleVendu> lstAReturn = new ArrayList<>();
		try {
			List<ArticleVendu> lstGlobal = DAOFactory.getArticleVenduDAO().selectJointArticleUtilisateur();
			
			if (!categorie.equals("Toutes")) {
				
				for (ArticleVendu articleVendu : lstGlobal) {
					//TODO : risque de problème entre la catégorie de l'ihm Sport et loisir et de la base Sport&Loisirs
					if (articleVendu.getCategorieArticle().getLibelle().equals(categorie)) {
						//si la categorie de l'objet est la même que celle passer en paramêtre on ajoute l'article a la liste
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
		
		
		
		
		
		
		
		
		return lstAReturn;
	}
	
	
}
