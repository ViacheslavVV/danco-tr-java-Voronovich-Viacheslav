<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="/style/style_menu.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>
	<div class="menu">
		<table>
			<tr>
				<td><a href="/Course">Course</a></td>
				<td><a href="/Lecturer">Lecturer</a></td>
				<td><a href="/Lection">Lection</a></td>
				<td><a href="/Student">Student</a></td>
			</tr>
		</table>
	</div>
	<h2>Welcome, ${sessionScope.user.login}!!!</h2>
	
	<form action="/LogOut">
			<input type="submit" value="Log Out">
	</form>
	
</body>
</html>