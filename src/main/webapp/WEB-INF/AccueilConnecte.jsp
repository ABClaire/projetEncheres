<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />

    <title></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="Style.css" />

</head>
<body>

    <header>
    <!-- Titre pages + menu-->
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-6">
                <h1>
                    ENI-Enchères
                </h1>
            </div>
            <div class="col-md-6">
                <ul class="nav">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">Enchères</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/ProjetEncheres/NouvelleVente">Vendre un article</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="http://localhost:8080/ProjetEncheres/AfficherPofilServlet">Mon profil</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Déconnexion</a>
                    </li>
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

     </header>

        <!--Recherche-->
        <section class="search-sec">
            <div class="container-fluid">
                <form action="AccueilConnecte" method="post" novalidate="novalidate">
                    <dv class="row">
                        <div class="col-md-6">
                            <p>Filtres</p>
                            <!-- Formulaire de recherche-->
                            <input type="text" class="form-control search-slt" placeholder="Le nom de l'article contient" name="nomArticle">
                            <!-- Choix des catégories-->
                            <div class="row">
                                <div class="col-md-6">
                                    <p>Catégories</p>
                                </div>
                                <div class="col-md-6">
                                    <select class="form-control search-slt" name="listeDeroulante" value="listeDeroulante">
                                        <option name="Toutes" value="Toutes">
                                            <!-- Toutes les catégories-->
                                            <a><p>Toutes</p></a>
                                        </option>
                                        <!-- Informatique -->
                                        <option name="Informatique" value="Informatique">
                                            <a><p>Informatique</p></a>
                                        </option>
                                        <!--Ameublement -->
                                        <option name="Ameublement" value="Ameublement">
                                            <a><p>Ameublement</p></a>
                                        </option>
                                        <!--Vêtement-->
                                        <option name="Vêtement" value="Vêtement">
                                            <a><p>Vêtement</p></a>
                                        </option>
                                        <!--Sport & Loisirs-->
                                        <option name="Sports&Loisirs" value="Sports&Loisirs">
                                            <a><p>Sports et loisirs</p></a>
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <!-- Filtre Achat ou Mes ventes-->
                            <!--Achats-->
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
                                        <label class="form-check-label" for="flexRadioDefault1">
                                            Achats
                                        </label>
                                        <!-- Choix types achats-->
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                            <label class="form-check-label" for="flexCheckDefault">
                                                Enchères ouvertes
                                            </label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked">
                                            <label class="form-check-label" for="flexCheckChecked">
                                                Mes enchères
                                            </label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked">
                                            <label class="form-check-label" for="flexCheckChecked">
                                                Mes enchères remportées
                                            </label>
                                        </div>
                                        <!--Fin choix type achats-->
                                    </div>
                                </div>

                                <!--Mes ventes-->
                                <div class="col-md-6">
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2">
                                        <label class="form-check-label" for="flexRadioDefault2">
                                            Mes ventes
                                        </label>
                                        <!-- Choix types achats-->
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                            <label class="form-check-label" for="flexCheckDefault">
                                                Mes ventes en cours
                                            </label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked">
                                            <label class="form-check-label" for="flexCheckChecked">
                                                Ventes non débutées
                                            </label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked">
                                            <label class="form-check-label" for="flexCheckChecked">
                                                Ventes terminées
                                            </label>
                                        </div>
                                        <!--Fin choix type achats-->
                                    </div>
                                </div>
                            </div>
                            <!--Fin filtre Achats ou Mes ventes-->
                        </div>
                        <!--Boutons Rechercher-->
                        <div class="col-md-6 text-center">
                            <button type="submit" class="btn btn-primary search-btn" name="recherche" value="recherche">Rechercher</button>
                        </div>
                </form>
            </div>
        </section>
        <!-- Fin du formulaire de recherche-->
    </div>


<!-- Début Affichage des Articles -->
	<c:forEach items="${Liste}" var="sc">
		<p>-------------------------------------------------------</p>
		<p>${sc.nomArticle}</p>
		<p>Prix :${sc.prixVente}</p>
		<p>Fin de l'enchère : ${sc.dateFinEncheres}</p>
		<p>Vendeur : ${sc.utilisateur.pseudo}</p>
		<p>-------------------------------------------------------</p>
	</c:forEach>
	<!-- Fin Affichage des Articles-->


</body>
</html>