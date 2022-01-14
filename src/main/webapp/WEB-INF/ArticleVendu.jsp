<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Détail vente</title>
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
				<!--  Affichage du détail de la vente -->
				<div class="col-md-4">
					<p>Nom Article : ${model.article.nomArticle}</p>
					<p>Description : ${model.article.description}</p>
					<p>Catégorie :  ${model.article.categorieArticle}</p>
					<p>Meilleure offre : !!!!meilleure offre!!!!!</p>
					<p>Mise à prix : ${model.article.miseAPrix}</p>
					<p>Fin enchère : ${model.article.dateFinEncheres}</p>
					<p>Retrait : ${model.article.lieuRetrait}</p>
					<p>Vendeur : ${model.article.utilisateur}</p>		
					<p>Ma proposition !!!proposition!!!!</p>
				</div>
				<div class="col-md-4">
				</div>
			</div>
		</div>
	</div>
</div>











</body>
</html>