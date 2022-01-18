<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page de Connexion</title>

<!--  BOOTSTRAP ET  CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet" href="Style.css" />
<!-- FIN BOOTSTRAP ET CSS -->

</head>
<body>

<!-- HEADER -->
	<header>
		<!-- Titre pages + menu-->
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-6">
					<h1>ENI-Enchères</h1>
				</div>
				<div class="col-md-6">					
				</div>
			</div>			
		</div>
	</header>

	<!-- FIN DU HEADER -->	

<p> ${messageInscriptionOk} </p>



<div class="container-fluid">
<form action="Connexion" method="POST">
	<div class="row">
		<div class="col-md-4">
		</div>
		<div class="col-md-4">
			<div class="row">
				<div class="col-md-6">			
				<label>Identifiant :</label>			
				</div>
				<div class="col-md-6">
				<input type="text" name="identifiant" value="${identifiant}"/>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
				<label>Mot de passe :</label>							
				</div>
				<div class="col-md-6">			
				<input type="text" name="motDePasse" value="${motDePasse}"/>									
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
				
				<input type="submit" name="Connexion" value="Connexion" class="btn btn-primary"/>
				
				</div>
				<div class="col-md-6">		
				<a href="http://localhost:8080/ProjetEncheres/MotDePasseOublie">Mot de passe oublié ?</a>
				</div>				
			</div>
							<input type="submit" name="Creation Compte" value="Creation Compte" class="btn btn-secondary"/>		
		</div>
		<div class="col-md-4">		
		</div>
	</div>
	</form>
</div>
</body>
</html>