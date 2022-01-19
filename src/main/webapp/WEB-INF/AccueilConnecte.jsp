<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <title>Accueil</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&family=Pacifico&display=swap" rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="lib/animate/animate.min.css" rel="stylesheet">
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
    <div class="container-xxl bg-white p-0">
        <!-- Spinner Start -->
        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->
        
        <!-- Navbar & Hero Start -->
        <div class="container-xxl bg-white py-5">
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark px-4 px-lg-5 py-3 py-lg-0">

                <a href="accueilConnecte.html" class="navbar-brand p-0">
                    <h1 class="text-primary m-0">Grandma's Store</h1>
                    <img src="img/logo.gif" alt="logo">
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                    <span class="fa fa-bars"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse">
                    <div class="navbar-nav ms-auto py-0 pe-4">
                        <a href="http://localhost:8080/ProjetEncheres/AccueilConnecte" class="nav-item nav-link">Encheres</a>
                        <a href="vendreArticle.html" class="nav-item nav-link">Vendre un article</a>
                        <a href="http://localhost:8080/ProjetEncheres/AfficherPofilServlet" class="nav-item nav-link">Mon profil</a>
                    </div>
                   <form action="AccueilConnecte" method="POST"><input type="submit" name="deconnexion" value="Deconnexion" class="btn btn-primary py-2 px-4"></form>
                </div>
            </nav>
        </div>
        <!-- Navbar & Hero End -->
        
        
        <!-- Recherche -->
        <div class="container-xxl py-5">
            <div class="container">
                <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                    <h5 class="section-title ff-secondary text-center text-primary fw-normal">Encheres & Ventes</h5>
                    <h1 class="mb-5">Recherche</h1>
                </div>

                <div class="tab-class wow fadeInUp" data-wow-delay="0.1s">
                    <div class="row">
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
                                                    <p>Categories</p>
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
                                                        <input class="form-check-input" type="radio" id="achats" onclick="onClickAchats()">
                                                        <label class="form-check-label" for="flexRadioDefault1">
                                                            Achats
                                                        </label>
                                                        <!-- Choix types achats-->
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox" value="Enchères ouvertes" id="enchOuvertes">
                                                            <label class="form-check-label" for="flexCheckDefault">
                                                                Encheres ouvertes
                                                            </label>
                                                        </div>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox" value="Mes enchères" id="mesEnch">
                                                            <label class="form-check-label" for="flexCheckChecked">
                                                                Mes encheres
                                                            </label>
                                                        </div>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox" value="Mes enchères remportées" id="enchRemportees">
                                                            <label class="form-check-label" for="flexCheckChecked">
                                                                Mes encheres remportees
                                                            </label>
                                                        </div>
                                                        <!--Fin choix type achats-->
                                                    </div>
                                                </div>

                                                <!--Mes ventes-->
                                                <div class="col-md-6">
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" id="ventes" onclick="onClickVentes()">
                                                        <label class="form-check-label" for="flexRadioDefault2">
                                                            Mes ventes
                                                        </label>
                                                        <!-- Choix types achats-->
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox" value="Ventes en cours" id="ventesEnCours">
                                                            <label class="form-check-label" for="flexCheckDefault">
                                                                Mes ventes en cours
                                                            </label>
                                                        </div>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox" value="Ventes non débutées" id="ventesNonDebut">
                                                            <label class="form-check-label" for="flexCheckChecked">
                                                                Ventes non debutees
                                                            </label>
                                                        </div>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox" value="Ventes terminées" id="ventesTerminees">
                                                            <label class="form-check-label" for="flexCheckChecked">
                                                                Ventes terminees
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
                    </div> <!-- /row  -->
                </div>
			</div>

            </div>
            <!-- Fin de la recherche -->
            <!-- Liste des résultats-->
            <div class="container-xxl py-5">
                <div class="container">
                    <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                        <h5 class="section-title ff-secondary text-center text-primary fw-normal">Encheres & Ventes</h5>
                        <h1 class="mb-5">Liste des resultats</h1>
                    </div>
                    <div class="tab-class wow fadeInUp" data-wow-delay="0.1s">

                        <div class="row">
                            <c:forEach items="${Liste}" var="sc">
                                <p>-------------------------------------------------------</p>

                                <p><a href="<c:url value="/DetailEnchere?noArticle=${sc.noArticle}"/>">${sc.nomArticle}</a></p>
                                <p>Prix :${sc.miseAPrix}</p>
                                <p>Fin de l'enchère : ${sc.dateFinEncheres}</p>
                                <label name="${sc.utilisateur.pseudo}"></label>
                                <!-- Envoi un paramètre par l'URL  -->
                                <p>Vendeur : <a href="<c:url value="/AfficherProfilVendeurServlet?pseudoVendeur=${sc.utilisateur.pseudo}" />" >${sc.utilisateur.pseudo} </a></p>

                                <p>-------------------------------------------------------</p>
                            </c:forEach>
                        </div> <!-- /row -->




                    </div>
                </div>
            </div>
            <!--Fin liste des résultats -->
            <!-- Footer Start -->
            <div class="container-fluid bg-dark text-light footer pt-5 mt-5 wow fadeIn" data-wow-delay="0.1s">
                <div class="container">
                    <div class="copyright">
                        <div class="row">
                            <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
                                 <a class="border-bottom" href="#">Your Site Name</a>, All Right Reserved.

                                <!--/*** This template is free as long as you keep the footer author’s credit link/attribution link/backlink. If you'd like to use the template without the footer author’s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/-->
                                Designed By <a class="border-bottom" href="https://htmlcodex.com">HTML Codex</a><br><br>
                                Distributed By <a class="border-bottom" href="https://themewagon.com" target="_blank">ThemeWagon</a>
                            </div>
                            <div class="col-md-6 text-center text-md-end">
                                <div class="footer-menu">
                                    <a href="">Home</a>
                                    <a href="">Cookies</a>
                                    <a href="">Help</a>
                                    <a href="">FQAs</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Footer End -->
            <!-- Back to Top -->
            <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
        </div>


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
        <script src="lib/eni/checkDisabled.js"></script>


        <!-- Template Javascript -->
        <script src="js/main.js"></script>
</body>
</html>