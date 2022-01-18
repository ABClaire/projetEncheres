<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>D�tail ench�re</title>
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
					<p>Cat�gorie :  ${model.article.categorieArticle.libelle}</p>
					<p>Meilleure offre : ${model.article.enchereMaximum.montantEnchere} par ${model.encheriste.pseudo} </p>
					<p>Mise � prix : ${model.article.miseAPrix}</p>
					<p>Fin ench�re : ${model.article.dateFinEncheres}</p>
					<p>Retrait : ${model.article.lieuRetrait.lieu} <br>
								 ${model.article.lieuRetrait.codePostal} <br>
								  ${model.article.lieuRetrait.ville}	</p>
					<p>Vendeur : ${model.vendeur.pseudo}</p>		
					<p>Ma proposition : </p>
					<form action="DetailEnchere" method="POST">
					<!-- noArticle envoy� dans la requ�te en "hidden" pour conserver l'information -->
						<input type="hidden" name="noArticle" value="${model.article.noArticle}"/>
						<input type="number" name="proposition" value="${model.article.enchereMaximum.montantEnchere}"/>
						<input type="submit" name="nouvelleEnchere" value="Encherir"/>
					</form>
					<p><strong> ${model.message} </strong></p>
					<p><strong> ${messageErreur} </strong></p>
				</div>
				<div class="col-md-4">
				</div>
			</div>
		</div>
	</div>
</div>











</body>
</html>