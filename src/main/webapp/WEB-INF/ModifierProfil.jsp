.<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="Style.css" />
</head>
<body>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
				<h1>ENI ENCHERES</h1>
		</div>
	</div>
	<form action="ModifierUtilisateur" method="POST" class="dialog-form" >	
	<div class="row">
	<!-- COLONNE DE GAUCHE -->
	<div class="col-md-6">		
		<div class = "formulaire">
			<label for="pseudo" class="form-label"> Pseudo:</label>
				<input class="form-field" type="text" id="pseudo" name="pseudo" value="${model.utilisateur.pseudo}" required
				pattern="[a-zA-Z0-9]{1,30}" title="Le pseudo ne doit pas comporter de caractères spéciaux"/>
		</div>
		
		<div class = "formulaire">
			<label for="prenom" class="form-label"> Prénom: </label>
			<input class="form-field" type="text" id="prenom" name="prenom" value="${model.utilisateur.prenom}" required
			pattern="{1,30}" title="Le prénom ne doit pas dépasser 30 caractères"/> 
		</div>
		
		<div class = "formulaire">
			<label for="telephone" class="form-label"> Téléphone: </label>
				<input class="form-field" type="text" id="telephone" name="telephone" value="${model.utilisateur.telephone}" 
				pattern="^((\+\d{1,3}(-| )?\(?\d\)?(-| )?\d{1,5})|(\(?\d{2,6}\)?))(-| )?(\d{3,4})(-| )?(\d{4})(( x| ext)\d{1,5}){0,1}$"
				title="Le téléphone ne doit pas dépasser 15 caractères" />
		</div>
				<div class = "formulaire">
			<label for="codePostal" class="form-label"> Code postal: </label>
			<input class="form-field" type="text" id="codePostal" name="codePostal" value="${model.utilisateur.codePostal}" required
			pattern="{1,10}" title="Le code postal ne doit pas dépasser 10 caractères"/> 
		</div>
		
		<!-- MOT DE PASSE ACTUEL -->
		<div class = "formulaire">
			<label for="motDePasse" class="form-label"> Mot de passe actuel: </label>
			<input class="form-field" type="password" id="motDePasse" name="motDePasse" required
			pattern="{1,10}" title="Le mot de passe ne doit pas dépasser 10 caractères"/> 
		</div>
		
		<!-- NOUVEAU MOT DE PASSE -->
		<div class = "formulaire">
			<label for="nouveauMotDePasse" class="form-label"> Nouveau mot de passe: </label>
			<input class="form-field" type="password" id="nouveauMotDePasse" name="nouveauMotDePasse" required
			pattern="{1,10}" title="Le mot de passe ne doit pas dépasser 10 caractères"/> 
		</div>
		
		
		</div>
	
		<!-- COLONNE DE DROITE -->
		<div class="col-md-6">
		
		<div class = "formulaire">
			<label for="nom" class="form-label"> Nom: </label>
				<input class="form-field" type="text" id="nom" name="nom" value="${model.utilisateur.nom}" required
				pattern="{1,30}" title="Le nom ne doit pas dépasser 30 caractères"/>			
		</div>
		
		<div class = "formulaire">
			<label for="email" class="form-label"> Email:  </label>
				<input class="form-field" type="email" id="email" name="email" value="${model.utilisateur.email}" required
				pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"/>
		</div>
		
		<div class = "formulaire">			
			<label for="rue" class="form-label"> Rue: </label>
				<input class="form-field" type="text" id="rue" name="rue" required value="${model.utilisateur.rue}"
				pattern="{1,30}" title="La rue ne doit pas dépasser 30 caractères"/>
		</div>
		
		<div class = "formulaire">
			<label for="ville" class="form-label"> Ville:  </label>
				<input class="form-field" type="ville" id="ville" name="ville" value="${model.utilisateur.ville}"required
				pattern="{1,50}" title="La ville ne doit pas dépasser 50 caractères"/>
		</div>
		
		<div></div>
		
				<!-- CONFIRMATION NOUVEAU MOT DE PASSE -->		
		<div class = "formulaire">
			<label for="confirmationMotDePasse" class="form-label"> Confirmation: </label>
			<input class="form-field" type="password" id="confirmationMotDePasse" name="confirmationMotDePasse"  required
			pattern="{1,10}" title="Le mot de passe ne doit pas dépasser 10 caractères"/> 
		</div>
		
		
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
		</div>
		<div class="col-md-6">
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
		</div>
		<div class="col-md-4">
		
		<input type="submit" name="modifierUtilisateur" value="Modifier"/>
		<input type="button" name="supprimerUtilisateur" value="Supprimer"/>
		
		<p> ${message} </p>
		</div>
		<div class="col-md-4">		
			</div>
	</div>
	</form>
</div>

	











</body>
</html>