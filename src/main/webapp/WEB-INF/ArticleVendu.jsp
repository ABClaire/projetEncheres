<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>D�tail vente</title>
</head>
<body>


<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
				<h1>
                    ENI-Ench�res
                </h1>
			<div class="row">
				<div class="col-md-4">
				</div>
				<!--  Affichage du d�tail de la vente -->
				<div class="col-md-4">
					<p>Nom Article : ${model.article.nomArticle}</p>
					<p>Description : ${model.article.description}</p>
					<p>Cat�gorie :  ${model.article.categorieArticle}</p>
					<p>Meilleure offre : !!!!meilleure offre!!!!!</p>
					<p>Mise � prix : ${model.article.miseAPrix}</p>
					<p>Fin ench�re : ${model.article.dateFinEncheres}</p>
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