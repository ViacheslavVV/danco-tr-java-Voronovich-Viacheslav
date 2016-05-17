<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lection menu</title>
</head>
<body>

	<c:if test="${error != null}">
		<H4 class="error">Something wrong!</H4>
	</c:if>

	<H3>Lection menu</H3>
	<table>
		<tr>
			<td><a href="/lection/getLection.jsp">Get lection by id</a></td>
		</tr>
		<tr>
			<td><a href="/lection/getAllLections.jsp">Get lections</a></td>
		</tr>
		<tr>
			<td><a href="/lection/getLectionByDate.jsp">Get lections by date</a></td>
		</tr>
		<tr>
			<td><a href="/lection/deleteLection.jsp">Delete lection</a></td>
		</tr>
		<tr>
			<td><a href="/lection/createLection.jsp">Create lection</a></td>
		</tr>
	</table>

	<a href="/index.html">Back to Main menu</a>
</body>
</html>