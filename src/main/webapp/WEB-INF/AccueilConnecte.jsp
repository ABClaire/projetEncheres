<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
                    ENI-Ench�res
                </h1>
            </div>
            <div class="col-md-6">
                <ul class="nav">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">Ench�res</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/ProjetEncheres/NouvelleVente">Vendre un article</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="http://localhost:8080/ProjetEncheres/AfficherPofilServlet">Mon profil</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">D�connexion</a>
                    </li>
                </ul>
            </div>
        </div>
        <!--Sous titre-->
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <h2>Liste des ench�res</h2>
                </div>
            </div>
        </div>

     </header>

        <!--Recherche-->
        <section class="search-sec">
            <div class="container-fluid">
                <form action="#" method="post" novalidate="novalidate">
                    <dv class="row">
                        <div class="col-md-6">
                            <p>Filtres</p>
                            <!-- Formulaire de recherche-->
                            <input type="text" class="form-control search-slt" placeholder="Le nom de l'article contient">
                            <!-- Choix des cat�gories-->
                            <div class="row">
                                <div class="col-md-6">
                                    <p>Cat�gories</p>
                                </div>
                                <div class="col-md-6">
                                    <select class="form-control search-slt">
                                        <option>
                                            <!-- Toutes les cat�gories-->
                                            <a><p>Toutes</p></a>
                                        </option>
                                        <!-- Informatique -->
                                        <option>
                                            <a><p>Informatique</p></a>
                                        </option>
                                        <!--Ameublement -->
                                        <option>
                                            <a><p>Ameublement</p></a>
                                        </option>
                                        <!--V�tement-->
                                        <option>
                                            <a><p>V�tement</p></a>
                                        </option>
                                        <!--Sport & Loisirs-->
                                        <option>
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
                                                Ench�res ouvertes
                                            </label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked">
                                            <label class="form-check-label" for="flexCheckChecked">
                                                Mes ench�res
                                            </label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked">
                                            <label class="form-check-label" for="flexCheckChecked">
                                                Mes ench�res remport�es
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
                                                Ventes non d�but�es
                                            </label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked">
                                            <label class="form-check-label" for="flexCheckChecked">
                                                Ventes termin�es
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
                            <button type="button" class="btn btn-primary search-btn">Rechercher</button>
                        </div>
                </form>
            </div>
        </section>
        <!-- Fin du formulaire de recherche-->
    </div>

</body>
</html>