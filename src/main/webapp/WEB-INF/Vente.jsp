<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ENI-Encheres - vente</title>
</head>
<body>

<h1> Nouvelle vente</h1>

	<form action="NouvelleVente" method="POST">
	
		<label for="nom_article"> Article: </label>
			<input type="text" name="nom_article" value="${model.article.nom}"/><br>
	
		<label for="description_article"> Description: </label>
			<input type="textarea" name="description_article" value="${model.article.description}"/><br>
			
		<label for="categorie"> Categorie: </label>
			<select name="categorie">
				<option></option>
				<option value="1"><a><p>Informatique</p></a></option>
				<option value="2"><a><p>Ameublement</p></a></option>
				<option value="3"><a><p>Vêtement</p></a></option>
				<option value="4"><a><p>Sports et loisirs</p></a></option>
			</select>
			<br>
		<label for="photo_article"> Photo de l'article: </label>
			<button type="button" name="photo_article" disabled>Télécharger </button><br>
			
			
		<label for="prix_article"> Mise à prix (en point): </label>
			<input type="number" name="prix_article" value="${model.article.miseAPrix}"/><br>
			
		<label for="debut_enchere"> Début de l'enchère: </label>
			<input type="date" name="debut_enchere" value="${model.article.dateDebutEncheres}"/><br>
			
		<label for="fin_enchere"> Fin de l'enchère: </label>
			<input type="date" name="fin_enchere" value="${model.article.dateFinEncheres}"/><br>
			
		<fieldset> 
            <legend>Retrait</legend> 
            <label for="rue"> Rue: </label>
				<input type="text" name="rue" value="${model.retrait.lieu}"/><br>
			<label for="cp"> Code postal: </label>
				<input type="text" name="cp" value="${model.retrait.codePostal}"/><br>
			<label for="ville"> Ville: </label>
				<input type="text" name="ville" value="${model.retrait.ville}"/><br>		
         </fieldset>
         
         <input type="submit" name="enregistrer" value="Enregistrer"/>
         <input type="submit" name="annuler" value="Annuler"/>	
	</form>
	
	<form action="NouvelleVente" method="POST">
	      <input type="submit" name="retour_accueil" value="Retourner aux enchères"/>
    </form>    
     
	<p> ${message}</p>

</body>
</html>