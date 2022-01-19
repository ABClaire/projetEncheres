<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Détail enchère</title>
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
				<h5 id="detailVente"> Detail vente</h5> 
				<h5 id="venteRemportee"><strong> Vous avez remporté la vente </strong> </h5>
				<div class="col-md-4">
					<p>Nom Article : ${model.article.nomArticle}</p>
					<p>Description : ${model.article.description}</p>
					<p>Catégorie :  ${model.article.categorieArticle.libelle}</p>
					<p id="pseudoEncheriste">Meilleure offre : ${model.article.enchereMaximum.montantEnchere} par ${model.encheriste.pseudo}${aucuneEnchere} </p>
					<p>Mise à prix : ${model.article.miseAPrix}</p>
					<p>Fin enchère : ${model.article.dateFinEncheres}</p>
					<p>Retrait : ${model.article.lieuRetrait.lieu} <br>
								 ${model.article.lieuRetrait.codePostal} <br>
								  ${model.article.lieuRetrait.ville}	</p>
					<p>Vendeur : ${model.vendeur.pseudo}</p>	
					<p id="telephoneVendeur"> Tel : ${model.vendeur.telephone}<p>
					<form action="DetailEnchere" method="POST" id="formulaireEncherir">
					<p>Ma proposition : </p>
					<!-- noArticle envoyé dans la requête en "hidden" pour conserver l'information -->
						<input type="hidden" name="noArticle" value="${model.article.noArticle}"/>
						<input type="number" name="proposition" min="${model.article.miseAPrix}" value="${model.article.enchereMaximum.montantEnchere}"/>
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

<input type="hidden" value="${model.article.etatVente}" id="etatVente"/>	
<input type="hidden" value="${pseudoUtilisateur}" id="pseudoUtilisateur"/>

 <!-- JavaScript Libraries -->
 <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
 <script src="lib/wow/wow.min.js"></script>
 <script src="lib/easing/easing.min.js"></script>
 <script src="lib/waypoints/waypoints.min.js"></script>
 <script src="lib/counterup/counterup.min.js"></script>
 <script src="lib/owlcarousel/owl.carousel.min.js"></script>
 <script src="lib/tempusdominus/js/moment.min.js"></script>
 <script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
 <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>
 <!--  SCRIPT POUR DISABLED LES BOUTONS -->
 <script src="lib/eni/pageEnchere.js"></script>


 <!-- Template Javascript -->
 <script src="js/main.js"></script>

</body>
</html>
