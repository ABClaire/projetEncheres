package fr.eni.encheres.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.ArticleVenduDAO;
import fr.eni.encheres.dao.DALException;

/**
 * Classe en charge d'effectuer des traitements de base de donn�es
 *
 */

public class ArticleVenduDAOImpl implements ArticleVenduDAO {

	//Rajouter categorie article et lieu retrait
	private final static String INSERT = "INSERT INTO ARTICLES_VENDUS (nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix) VALUES (?,?,?,?,?,?)";
	private final static String SELECT_ALL = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres,prix_initial, prix_vente, no_utilisateur,no_categorie FROM ARTICLES_VENDUS";

	/**
	 * M�thode en charge d'ajouter un nouvel article dans la BDD
	 */
	@Override
	public void ajouterArticleVendu (ArticleVendu articleVendu) throws DALException {
		try (Connection cnx = JdbcTools.getConnection()) {
			PreparedStatement pStmt;
				pStmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
				pStmt.setString(1, articleVendu.getNomArticle());
				pStmt.setString(2, articleVendu.getDescription());
				pStmt.setString(3, articleVendu.getDescription());
				pStmt.setDate(4,java.sql.Date.valueOf(articleVendu.getDateDebutEncheres()));
				pStmt.setDate(5, java.sql.Date.valueOf(articleVendu.getDateFinEncheres()));
				pStmt.setInt(6, articleVendu.getMiseAPrix());
				//pStmt.setInt(7, articleVendu.getCategorieArticle());
//				pStmt.set???(8, articleVendu.getLieuRetrait());

				pStmt.executeUpdate();

				ResultSet rs = pStmt.getGeneratedKeys();
				if (rs.next()) {
					Integer idGenere = rs.getInt(1);
					articleVendu.setNoArticle(idGenere);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DALException(e.getMessage());
			}
		
	}


	@Override
	public List<ArticleVendu> getAllArticleVendu() throws DALException {
		List<ArticleVendu> articlesVendus = new ArrayList<ArticleVendu>();
		try(Connection cnx = JdbcTools.getConnection()) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while(rs.next()) {
				ArticleVendu articleVendu = map(rs);
				articlesVendus.add(articleVendu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}
		
		return articlesVendus;
	}


	private ArticleVendu map(ResultSet rs) throws SQLException {
		Integer noArticle = rs.getInt("noArticle");

		
		return null;
	}
	

	
	
}
