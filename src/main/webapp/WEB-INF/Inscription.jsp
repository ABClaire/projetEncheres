<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inscription</title>
</head>
<body>
	<h1> ENI Ench�res </h1>

	<h2> Mon profil </h2>

	<form action="Inscription" method="POST" class="dialog-form" >
		<div class = "formulaire">
			<label for="pseudo" class="form-label"> Pseudo:</label>
				<input class="form-field" type="text" id="pseudo" name="pseudo" required
				pattern="[a-zA-Z0-9]{1,30}" title="Le pseudo ne doit pas comporter de caract�res sp�ciaux"/>

			<label for="nom" class="form-label"> Nom: </label>
				<input class="form-field" type="text" id="nom" name="nom" required
				pattern="{1,30}" title="Le nom ne doit pas d�passer 30 caract�res"/>			
		</div>
				
		<div class = "formulaire">
			<label for="prenom" class="form-label"> Pr�nom: </label>
			<input class="form-field" type="text" id="prenom" name="prenom" required
			pattern="{1,30}" title="Le pr�nom ne doit pas d�passer 30 caract�res"/> 

			<label for="email" class="form-label"> Email:  </label>
				<input class="form-field" type="email" id="email" name="email" required
				pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"/>
		</div>
		
		<div class = "formulaire">
			<label for="telephone" class="form-label"> T�l�phone: </label>
				<input class="form-field" type="text" id="telephone" name="telephone" 
				pattern="^((\+\d{1,3}(-| )?\(?\d\)?(-| )?\d{1,5})|(\(?\d{2,6}\)?))(-| )?(\d{3,4})(-| )?(\d{4})(( x| ext)\d{1,5}){0,1}$"
				title="Le t�l�phone ne doit pas d�passer 15 caract�res"/>
			
			<label for="rue" class="form-label"> Rue: </label>
				<input class="form-field" type="text" id="rue" name="rue" required
				pattern="{1,30}" title="La rue ne doit pas d�passer 30 caract�res"/>
		</div>

		<div class = "formulaire">
			<label for="codePostal" class="form-label"> Code postal: </label>
			<input class="form-field" type="text" id="codePostal" name="codePostal" required
			pattern="{1,10}" title="Le code postal ne doit pas d�passer 10 caract�res"/> 

			<label for="ville" class="form-label"> Ville:  </label>
				<input class="form-field" type="ville" id="ville" name="ville" required
				pattern="{1,50}" title="La ville ne doit pas d�passer 50 caract�res"/>
		</div>

		<div class = "formulaire">
			<label for="motDePasse" class="form-label"> Mot de passe: </label>
			<input class="form-field" type="password" id="motDePasse" name="motDePasse" required
			pattern="{1,10}" title="Le mot de passe ne doit pas d�passer 10 caract�res"/> 

			<label for="confirmationMotDePasse" class="form-label"> Confirmation: </label>
			<input class="form-field" type="password" id="confirmationMotDePasse" name="confirmationMotDePasse" required
			pattern="{1,10}" title="Le mot de passe ne doit pas d�passer 10 caract�res"/> 
		</div>

		<input type="submit" name="creerUtilisateur" value="Cr�er"/>
		</form>
		
		<!--  N�cessaire d'avoir deux formulaires pour g�rer l'annulation de l'inscription -->
		<form action="Inscription" method="POST" class="dialog-form" >
			<input type="submit" name="annulerUtilisateur" value="Annuler"/>
		</form>
		
		<p> ${message} </p>

	</form>


</body>
</html>