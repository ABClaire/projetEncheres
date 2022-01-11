/**
 * 
 */
package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.DALException;
import fr.eni.encheres.dao.DAOFactory;

/**
 * Classe en charge de gérer les utilisateurs du site
 * 
 */
public class UtilisateurManagerImpl implements UtilisateurManager {

	private static class UtilisateurManagerHolder {
		private static UtilisateurManagerImpl instance = new UtilisateurManagerImpl();
	}
	
	private UtilisateurManagerImpl() {}
	
	public static UtilisateurManagerImpl getInstance() {
		return UtilisateurManagerHolder.instance;
		
	}
	
	@Override
	public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) throws BLLException {
		try {
			DAOFactory.getUtilisateurDAO().getAllUtilisateurs().add(utilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
		
		return utilisateur;
	}
}
