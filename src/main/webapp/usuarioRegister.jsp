<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="register" method="post">
		<table>
			<tr>
				<td>Nombre</td>
				<td><input type="text" name="uname"></td>
			</tr>
			<tr>
				<td>Contraseña</td>
				<td><input type="text" name="password"></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td>Tlf</td>
				<td><input type="text" name="phone"></td>
			</tr>
			<tr>
				<td>Direccion</td>
				<td><input type="text" name="direction"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="register"></td>
			</tr>
		</table>

	</form>
</body>
</html>