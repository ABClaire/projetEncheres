<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Page de Connexion</title>
</head>
<body>
<form action="ConnectionServlet" method="POST">
	<input type="text" name="identifiant" value="${identifiant}"/>
	<input type="text" name="motDePasse" value="${motDePasse}"/>
	<p>${message}</p>
	<input type="submit" name="Connexion" value="Connexion"/>
	
</form>

</body>
</html>