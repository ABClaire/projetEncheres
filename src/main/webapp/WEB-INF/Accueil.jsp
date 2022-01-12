<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="ISO-8859-1" />

<title></title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet" href="Style.css" />

</head>
<body>

	<header>
		<!-- Titre pages + menu-->
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-6">
					<h1>ENI-Enchères</h1>
				</div>
				<div class="col-md-6">
					<ul class="nav">
						<li class="nav-item"><a class="nav-link active"
							href="http://localhost:8080/ProjetEncheres/Connexion">S'inscrire
								- Se connecter</a></li>
					</ul>
				</div>
			</div>
			<!--Sous titre-->
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-12">
						<h2>Liste des enchères</h2>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!--Recherche-->
	<section class="search-sec">
		<div class="container-fluid">

			<form action="AccueilServlet" method="post" novalidate="novalidate">
				<dv class="row">
				<div class="col-md-6">
					<p>Filtres</p>
					<!-- Formulaire de recherche-->
					<input type="text" class="form-control search-slt"
						placeholder="Le nom de l'article contient" name="nomArticle">
					<!-- Choix des catégories-->
					<div class="row">
						<div class="col-md-6">
							<p>Catégories</p>
						</div>
						<div class="col-md-6">
							<select class="form-control search-slt">
								<option>
									<!-- Toutes les catégories--> <a><p>Toutes</p></a>
								</option>
								<!-- Informatique -->
								<option><a><p>Informatique</p></a>
								</option>
								<!--Ameublement -->
								<option><a><p>Ameublement</p></a>
								</option>
								<!--Vêtement-->
								<option><a><p>Vêtement</p></a>
								</option>
								<!--Sport & Loisirs-->
								<option><a><p>Sports et loisirs</p></a>
								</option>
							</select>
						</div>
					</div>
				</div>
				<!--Boutons Rechercher-->
				<div class="col-md-6 text-center">
					<button type="button" class="btn btn-primary search-btn"
						name="rechercher" value="rechercher">Rechercher</button>
				</div>
			</form>
		</div>
	</section>
	<!-- Fin du formulaire de recherche-->

	<!-- Expérience Angélo -->

	<form action="AccueilServlet" method="POST">


		<input type="submit" name="recherche2" value="recherche2" />
		<select name="listeDeroulante" value="listeDeroulante" >
								<option name="Toutes" value="Toutes">
									<!-- Toutes les catégories--> <a><p>Toutes</p></a>
								</option>
								<!-- Informatique -->
								<option name="Informatique" value="Informatique"><a><p>Informatique</p></a>
								</option>
								<!--Ameublement -->
								<option name="Ameublement" value="Ameublement"><a><p>Ameublement</p></a>
								</option>
								<!--Vêtement-->
								<option name="Vêtement" value="Vêtement"><a><p>Vêtement</p></a>
								</option>
								<!--Sport & Loisirs-->
								<option name="Sports et loisirs" value="Sports et loisirs"><a><p>Sports et loisirs</p></a>
								</option>
		</select>
		

	</form>



	<!--  fin Expérience Angélo -->

	<c:forEach items="${Liste}" var="sc">
		<p>-------------------------------------------------------</p>
		<p>${sc.nomArticle}</p>
		<p>Prix :${sc.miseAPrix}</p>
		<p>Fin de l'enchère : ${sc.dateFinEncheres}</p>
		<p>Vendeur : ${sc.utilisateur.pseudo}</p>
		<p>-------------------------------------------------------</p>
	</c:forEach>

</body>
</html>