/**
 * 
 */
package fr.eni.encheres.bo;

import java.util.List;

/**
 * Classe en charge de créer un objet catégorie
 * 
 */
public class Categorie {
	Integer noCategorie;
	String libelle;
	List<ArticleVendu> lstArticlesVendus;
	/**
	 * Constructeur.
	 */
	public Categorie() {
	}
	/**
	 * Constructeur.
	 * @param noCategorie
	 * @param libelle
	 * @param lstArticlesVendus
	 */
	public Categorie(Integer noCategorie, String libelle, List<ArticleVendu> lstArticlesVendus) {
		this.noCategorie = noCategorie;
		this.libelle = libelle;
		this.lstArticlesVendus = lstArticlesVendus;
	}
	/**
	 * Getter pour noCategorie.
	 * @return the noCategorie
	 */
	public Integer getNoCategorie() {
		return noCategorie;
	}
	/**
	 * Setter pour noCategorie.
	 * @param noCategorie the noCategorie to set
	 */
	public void setNoCategorie(Integer noCategorie) {
		this.noCategorie = noCategorie;
	}
	/**
	 * Getter pour libelle.
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}
	/**
	 * Setter pour libelle.
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	/**
	 * Getter pour lstArticlesVendus.
	 * @return the lstArticlesVendus
	 */
	public List<ArticleVendu> getLstArticlesVendus() {
		return lstArticlesVendus;
	}
	/**
	 * Setter pour lstArticlesVendus.
	 * @param lstArticlesVendus the lstArticlesVendus to set
	 */
	public void setLstArticlesVendus(List<ArticleVendu> lstArticlesVendus) {
		this.lstArticlesVendus = lstArticlesVendus;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Categorie [noCategorie=").append(noCategorie).append(", libelle=").append(libelle)
				.append(", lstArticlesVendus=").append(lstArticlesVendus).append("]");
		return builder.toString();
	}
	
}
