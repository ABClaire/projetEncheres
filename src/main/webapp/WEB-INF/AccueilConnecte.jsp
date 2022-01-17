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
            <form action="AccueilConnecte" method="POST">
            <div class="col-md-6">
                <ul class="nav">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">Enchères</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/ProjetEncheres/NouvelleVente">Vendre un article</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/ProjetEncheres/AfficherPofilServlet">Mon profil</a>
                    </li>
                    <li class="nav-item">
                        <input class="nav-link" type="submit" name="deconnexion" value="Déconnexion"/>
                    </li>
                </ul>
            </div>
            </form>
      
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
											<!-- Toutes les catégories--> <a><p>Toutes</p></a>
										</option>
										 <c:forEach items="${lstCategories}" var="categorie">
								 			 <option value="${categorie.libelle}">${categorie.libelle}</option>
								 		</c:forEach>			
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
		<p><a href="<c:url value="/DetailEnchere?noArticle=${sc.noArticle}"/>" >${sc.nomArticle}</a></p>
		<p>Prix :${sc.miseAPrix}</p>
		<p>Fin de l'enchère : ${sc.dateFinEncheres}</p>
		<label name="${sc.utilisateur.pseudo}"></label>
		<!-- Envoi un paramètre par l'URL  -->
		<p >Vendeur : <a href="<c:url value="/AfficherProfilVendeurServlet?pseudoVendeur=${sc.utilisateur.pseudo}"/>" >${sc.utilisateur.pseudo} </a></p>
		<p>-------------------------------------------------------</p>
	</c:forEach>
	<!-- Fin Affichage des Articles-->


</body>
</html>