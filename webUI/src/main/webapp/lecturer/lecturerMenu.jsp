<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lecturer menu</title>
</head>
<body>

	<c:if test="${error != null}">
		<H4 class="error">Something wrong!</H4>
	</c:if>

	<H3>Lecturer menu</H3>
	<table>
		<tr>
			<td><a href="/lecturer/getLecturer.jsp">Get lecturer by id</a></td>
		</tr>
		<tr>
			<td><a href="/lecturer/getAllLecturers.jsp">Get all lecturers</a></td>
		</tr>
		<tr>
			<td><a href="/lecturer/deleteLecturer.jsp">Delete lecturer</a></td>
		</tr>
		<tr>
			<td><a href="/lecturer/createLecturer.jsp">Create lecturer</a></td>
		</tr>
	</table>

	<a href="/index.html">Back to Main menu</a>
</body>
</html>