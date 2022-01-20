package fr.eni.encheres.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bll.UtilisateurManagerImpl;
import fr.eni.encheres.bll.UtilisateurManagerImplAngelo;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.DALException;
import fr.eni.encheres.dao.DAOFactory;

public class TestMain {

	public static void main(String[] args) {
		
		
		Set<ArticleVendu> setArticleVendu = new HashSet<ArticleVendu>();
	
		
		
		List<ArticleVendu> LstGlobal = null;
		try {
			LstGlobal = UtilisateurManagerImplAngelo.getInstance().RecuperationArticleEtUtilisateur();
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (ArticleVendu articleVendu : LstGlobal) {
			try {
				articleVendu.setLstEncheres(DAOFactory.getEnchereDAO().selectAllEncheresByArticle(articleVendu));
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		List<Integer> lstchoix = new ArrayList<Integer>();
		lstchoix.add(2);
		
		
		Utilisateur mamie = null;
		try {
			mamie = UtilisateurManagerImpl.getInstance().getByIdUtilisateur(1);
			try {
				mamie.setLstEncheres(DAOFactory.getEnchereDAO().selectAllEncheresByUser(mamie));
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("utilisateur :");
		
		System.out.println("----------------------------------------------------------------");
		
		List<ArticleVendu> lstTest = UtilisateurManagerImplAngelo.getInstance().filtreCheckboxAchat(lstchoix, LstGlobal, mamie);
		
		int compteur = 1;
		for (ArticleVendu articleVendu : lstTest) {
			System.out.println(compteur++);
			System.out.println(articleVendu);
		}
		
		
		
		// problème sur l'enchèreMax qui n'est pas présente dans l'objet Article
		
		
	}

	public static Set<ArticleVendu> findDuplicates(List<ArticleVendu> list) {
		Set<ArticleVendu> items = new HashSet<ArticleVendu>();
		Set<ArticleVendu> duplicates = new HashSet<ArticleVendu>();
		for (ArticleVendu item : list) {
			if (items.contains(item)) {
				duplicates.add(item);
			} else {
				items.add(item);
			}
		}
		return duplicates;
	}

}
