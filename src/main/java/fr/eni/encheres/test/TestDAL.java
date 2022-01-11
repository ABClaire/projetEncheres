/**
 * 
 */
package fr.eni.encheres.test;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.DALException;
import fr.eni.encheres.dao.DAOFactory;

/**
 * Classe en charge de 
 * @author cgoarnisson2021
 * @date 10 janv. 2022 - 16:51:37
 * @version ProjetEncheres - V0.1  
 */
public class TestDAL {

	/**
	 * Méthode en charge de tester la couche DAL
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {

		// Insertion d'un nouvel utilisateur
		try {
			DAOFactory.getUtilisateurDAO().ajouterUtilisateur(new Utilisateur("pépé", "arien", "fernand", "fernandesangelo@gmail.com", "0606060606", "2 rue de la chapelle", "35000", "RENNES", "mdp", 100, false));
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		// Liste de tous les utilisateurs
		try {
			List<Utilisateur> lstUtilisateurs = DAOFactory.getUtilisateurDAO().getAllUtilisateurs();
			lstUtilisateurs.forEach(System.out::println);
		} catch (DALException e) {
			e.printStackTrace();
		}
		
	}

}
