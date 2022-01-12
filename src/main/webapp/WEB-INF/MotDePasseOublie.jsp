<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mot de passe oublié</title>
</head>
<body>

<form action="MotDePasseOublie" method="POST">
	<p>Saisir votre E-mail :</p>
	<input type="text" name="identifiant" value="${identifiant}"/><br>
	
	<input type="submit" name="Recuperation Mot de passe" value="Recuperation Mot de passe"/>
	<p>${message}</p>
	
	
	
</form>

</body>
</html>