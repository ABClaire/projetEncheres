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
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.ArticleVenduDAO;
import fr.eni.encheres.dao.DALException;

/**
 * Classe en charge d'effectuer des traitements de base de donn�es
 *
 */

public class ArticleVenduDAOImpl implements ArticleVenduDAO {

	//TODO vérifier si le SELECT_ALL est toujours utile
	private final static String INSERT = "INSERT INTO ARTICLES_VENDUS (nom_article,description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?,?)";
	private final static String UPDATE_PRIX_VENTE = "UPDATE ARTICLES_VENDUS SET prix_vente=? WHERE no_article=?";
	private final static String SELECT_ALL = "SELECT no_article, nom_article, date_debut_encheres, date_fin_encheres, prix_vente, no_utilisateur,etat_article FROM ARTICLES_VENDUS";
	private final static String SELECT_ARTICLE_BY_USER = "SELECT \r\n"
			+ "    av.no_article,\r\n"
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
			+ "GROUP BY av.no_article, nom_article, description, libelle, prix_initial,date_fin_encheres,r.rue,r.code_postal,r.ville,pseudo";
	
	private final static String SELECT_BY_ID_BEST_ENCHERE = "SELECT TOP 1\r\n"
			+ "	   av.no_article,\r\n"
			+ "    nom_article,\r\n"
			+ "    description,\r\n"
			+ "    libelle AS categorie,\r\n"
			+ "	   prix_initial,\r\n"
			+ "    date_fin_encheres,\r\n"
			+ "    r.rue,\r\n"
			+ "    r.code_postal,\r\n"
			+ "    r.ville,\r\n"
			+ "    pseudo AS vendeur,\r\n"
			+ "    av.no_utilisateur AS no_vendeur,\r\n"
			+ "	ISNULL(MAX(montant_enchere), 0) AS \"meilleure offre\",\r\n"
			+ "	e.no_utilisateur AS encheriste\r\n"
			+ "FROM ARTICLES_VENDUS av\r\n"
			+ "INNER JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur\r\n"
			+ "LEFT JOIN ENCHERES e ON av.no_article = e.no_article\r\n"
			+ "INNER JOIN CATEGORIES c ON av.no_categorie = c.no_categorie\r\n"
			+ "INNER JOIN RETRAITS r ON av.no_article = r.no_article\r\n"
			+ "WHERE av.no_article = ?\r\n"
			+ "GROUP BY av.no_article, nom_article, description, libelle, prix_initial, date_fin_encheres,r.rue,r.code_postal,r.ville,pseudo, av.no_utilisateur, e.no_utilisateur\r\n"
			+ "ORDER BY no_article, \"meilleure offre\" DESC\r\n";
	
	
	/**
	 * Méthode en charge d'ajouter un nouvel article dans la BDD
	 */
	@Override
	public ArticleVendu ajouterArticleAVendre (ArticleVendu articleVendu) throws DALException {
		try (Connection cnx = JdbcTools.getConnection()) {
			PreparedStatement pStmt;
			pStmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, articleVendu.getNomArticle());
			pStmt.setString(2, articleVendu.getDescription());
			pStmt.setDate(3,Date.valueOf(articleVendu.getDateDebutEncheres()));
			pStmt.setDate(4, Date.valueOf(articleVendu.getDateFinEncheres()));
			pStmt.setInt(5, articleVendu.getMiseAPrix());
			pStmt.setInt(6, articleVendu.getUtilisateur().getNoUtilisateur());
			pStmt.setInt(7, articleVendu.getCategorieArticle().getNoCategorie());

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
		return articleVendu;
	}

	
	@Override
	public void updatePrixVente(Integer noArticleEnchere, Integer proposition) throws DALException {
		try (Connection cnx = JdbcTools.getConnection()){
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE_PRIX_VENTE);
			pStmt.setInt(1, proposition);
			pStmt.setInt(2, noArticleEnchere);
			pStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}		
	}
	
	@Override
	public ArticleVendu selectArticleByIdBestEnchere(Integer idArticle) throws DALException {		
		ArticleVendu article = null;

		try(Connection cnx = JdbcTools.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_ID_BEST_ENCHERE);
			pStmt.setInt(1, idArticle);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				article = map(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}
		
		return article;
	}
	


	@Override
	public List<ArticleVendu> getAllArticleVendu() throws DALException {
		List<ArticleVendu> articlesVendus = new ArrayList<ArticleVendu>();
		Utilisateur utilisateurVendeur;
		Categorie categorie;
		Retrait retrait;
		try(Connection cnx = JdbcTools.getConnection()) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while(rs.next()) {
				
				Integer noArticle = rs.getInt("no_article");
				String nom = rs.getString("nom_article");
				Integer prixVente = rs.getInt("prix_vente");
				LocalDate dateFinEncheres = (rs.getDate("date_fin_encheres")).toLocalDate();
				LocalDate dateDebutEncheres = (rs.getDate("date_debut_encheres")).toLocalDate();
				String etatVente = rs.getString("etat_article");
				utilisateurVendeur = new Utilisateur(rs.getInt("no_vendeur"), rs.getString("vendeur"));
					
				ArticleVendu articleVendu = new ArticleVendu(noArticle, nom, dateDebutEncheres, dateFinEncheres,
						prixVente, utilisateurVendeur, etatVente);
				
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
		Utilisateur utilisateur ;
		Categorie categorie ;
		Retrait retrait;
		
		
		try(Connection cnx = JdbcTools.getConnection()) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ARTICLE_BY_USER);
			while(rs.next()) {
			
				utilisateur = new Utilisateur(rs.getString("pseudo"));
			
				categorie = new Categorie(rs.getString("categorie"));
				
				retrait = new Retrait(rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"));
				
				ArticleVendu articleAvecUtilisateuretCategorie = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),  rs.getString("description"), (rs.getDate("date_fin_encheres")).toLocalDate(), rs.getInt("prix"), utilisateur, categorie, retrait);
				
		
				lstArticleVendus.add(articleAvecUtilisateuretCategorie);				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}
		
		return lstArticleVendus;
	}

	private ArticleVendu map(ResultSet rs) throws SQLException {
		Utilisateur utilisateurVendeur;
		Utilisateur utilisateurEncheriste;
		Categorie categorie;
		Retrait retrait;
		Enchere enchere;
		List<Enchere> lstEncheres = new ArrayList<Enchere>();
		ArticleVendu article;
		
		Integer noArticle = rs.getInt("no_article");
		String nom = rs.getString("nom_article");
		String description = rs.getString("description");
		Integer prixInitial = rs.getInt("prix_initial");
		LocalDate dateFinEncheres = (rs.getDate("date_fin_encheres")).toLocalDate();
		
		
		utilisateurVendeur = new Utilisateur(rs.getInt("no_vendeur"), rs.getString("vendeur"));
		utilisateurEncheriste = new Utilisateur(rs.getInt("encheriste"));
		
		categorie = new Categorie(rs.getString("categorie"));
		retrait = new Retrait(rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"));
		article = new ArticleVendu(noArticle, nom, description, dateFinEncheres, prixInitial, utilisateurVendeur, categorie, retrait);
		
		enchere = new Enchere(rs.getInt("meilleure offre"), article, utilisateurEncheriste);
				
		article.setEnchereMaximum(enchere);
		
		return article;
	}
	
}
