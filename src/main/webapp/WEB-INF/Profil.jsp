<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profil de ${model.utilisateur.pseudo}]</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="Style.css" />

</head>
<body>


<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
				<h1>
                    ENI-Enchères
                </h1>
			<div class="row">
				<div class="col-md-4">
				</div>
				<!--  Affichage du profil de l'utilisateur -->
				<div class="col-md-4">
					<p>Pseudo : ${model.utilisateur.pseudo}</p>
					<p>Nom : ${model.utilisateur.nom}</p>
					<p>Prénom :  ${model.utilisateur.prenom}</p>
					<p>Email :  ${model.utilisateur.email}</p>
					<p>Téléphone : ${model.utilisateur.telephone}</p>
					<p>Rue : ${model.utilisateur.rue}</p>
					<p>Code Postal : ${model.utilisateur.codePostal}</p>
					<p>Ville : ${model.utilisateur.ville}</p>		
				<!-- Bouton Modifier utilisateur -->
				<input type="button" name="modifierUtilisateur" value="Modifier"/>
				
				</div>
				<div class="col-md-4">
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>