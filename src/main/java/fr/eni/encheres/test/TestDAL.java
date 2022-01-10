/**
 * 
 */
package fr.eni.encheres.test;

import java.sql.SQLException;

import fr.eni.encheres.bo.Utilisateur;
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
		DAOFactory.getUtilisateurDAO().ajouterUtilisateur(new Utilisateur("mamie", "goarnisson", "claire", "clairegoarnisson@gmail.com", "0606060606", "2 rue de la chapelle", "35000", "RENNES", "mdp", 100, false));
	}

}
