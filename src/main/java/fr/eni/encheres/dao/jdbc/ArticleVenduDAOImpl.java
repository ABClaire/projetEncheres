package fr.eni.encheres.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.ArticleVenduDAO;
import fr.eni.encheres.dao.DALException;

/**
 * Classe en charge d'effectuer des traitements de base de donnï¿½es
 *
 */

public class ArticleVenduDAOImpl implements ArticleVenduDAO {

	//TODO Rajouter categorie article et lieu retrait
	private final static String INSERT = "INSERT INTO ARTICLES_VENDUS (nom_article,description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?,?)";
	private final static String SELECT_ALL = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres,prix_initial, prix_vente, no_utilisateur,no_categorie FROM ARTICLES_VENDUS";
	private final static String SELECT_ARTICLE_JOINTED_UTILISATEUR = "SELECT \r\n"
			+ "	nom_article,\r\n"
			+ "	description,\r\n"
			+ "	prix_initial,\r\n"
			+ "	date_fin_encheres,\r\n"
			+ "	pseudo\r\n"
			+ "FROM ARTICLES_VENDUS av\r\n"
			+ "INNER JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur\r\n"
			+ "GROUP BY nom_article, description, prix_initial, date_fin_encheres, pseudo\r\n"
			+ "";
	//TODO : REmplacer par ce select la méthode qui remonte une liste article 
	private final static String SELECT_ARTICLE_BY_USER = "SELECT \r\n"
			+ "    nom_article,\r\n"
			+ "    description,\r\n"
			+ "    libelle AS categorie,\r\n"
			+ "    ISNULL(MAX(montant_enchere), prix_initial) AS prix,\r\n"
			+ "    date_fin_encheres,\r\n"
			+ "    r.rue,\r\n"
			+ "    r.code_postal,\r\n"
			+ "    r.ville,\r\n"
			+ "    pseudo\r\n"
			+ "FROM ARTICLES_VENDUS av\r\n"
			+ "INNER JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur\r\n"
			+ "LEFT JOIN ENCHERES e ON av.no_article = e.no_article\r\n"
			+ "INNER JOIN CATEGORIES c ON av.no_categorie = c.no_categorie\r\n"
			+ "INNER JOIN RETRAITS r ON av.no_article = r.no_article\r\n"
			+ "GROUP BY nom_article, description, libelle, prix_initial,date_fin_encheres,r.rue,r.code_postal,r.ville,pseudo";
	
	/**
	 * MÃ©thode en charge d'ajouter un nouvel article dans la BDD
	 */
	@Override
	public void ajouterArticleAVendre (ArticleVendu articleVendu,Utilisateur utilisateur) throws DALException {
		try (Connection cnx = JdbcTools.getConnection()) {
			PreparedStatement pStmt;
			pStmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, articleVendu.getNomArticle());
			pStmt.setString(2, articleVendu.getDescription());
			pStmt.setDate(3,Date.valueOf(articleVendu.getDateDebutEncheres()));
			pStmt.setDate(4, Date.valueOf(articleVendu.getDateFinEncheres()));
			pStmt.setInt(5, articleVendu.getMiseAPrix());
			pStmt.setInt(6, utilisateur.getNoUtilisateur());
			pStmt.setInt(7, articleVendu.getCategorieArticle().getNoCategorie());;

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
	
	
	@Override
	//methode qui permet de recuperer une map avec comme clef un objet articles et en valeur un objet Utilisateur;
	public List<ArticleVendu> selectJointArticleUtilisateur() throws DALException  {
		List<ArticleVendu> lstArticleVendus = new ArrayList<ArticleVendu>();
		Utilisateur utilisateur = null ;
		
		
		try(Connection cnx = JdbcTools.getConnection()) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ARTICLE_JOINTED_UTILISATEUR);
			while(rs.next()) {
			
				utilisateur = new Utilisateur(rs.getString("pseudo"));
				
				ArticleVendu articleAvecUtilisateur = new ArticleVendu(rs.getString("nom_article"), rs.getString("description"), (rs.getDate("date_fin_encheres")).toLocalDate(), rs.getInt("prix_initial"), utilisateur);
		
				lstArticleVendus.add(articleAvecUtilisateur);
				
						
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}
		
		return lstArticleVendus;
	}


	private ArticleVendu map(ResultSet rs) throws SQLException {
		Integer noArticle = rs.getInt("no_article");
		String nom = rs.getString("nom_article");
		String description = rs.getString("description");
		LocalDate dateDebutEncheres = (rs.getDate("date_debut_encheres")).toLocalDate();
		LocalDate dateFinEncheres = (rs.getDate("date_fin_encheres")).toLocalDate();
		Integer prixInitial = rs.getInt("prix_initial");
		Integer prixVente = rs.getInt("prix_vente");
						
		return null;
	}
	

	
	
	
	
	
	
}
