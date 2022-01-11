<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Page de Connexion</title>
</head>
<body>

<p> ${messageInscriptionOk} </p>

	<form action="Connexion" method="POST">
		<p>Identifiant :</p><input type="text" name="identifiant" value="${identifiant}"/><br>
		<p>Mot de passe :</p><input type="text" name="motDePasse" value="${motDePasse}"/>
	
		<p>${message}</p>
		<p>${donneeUtilisateur}</p>
		<input type="submit" name="Connexion" value="Connexion"/>
		
		
	</form>

	<form action="Connexion" method="POST">
		
		<input type="submit" name="Creation Compte" value="Creation Compte"/>
		
	</form>

</body>
</html>