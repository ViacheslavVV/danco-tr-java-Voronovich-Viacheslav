<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create lection</title>
</head>
<body>
	<c:if test="${result == -1}">
		<H4 class="error">Something wrong!</H4>
	</c:if>

	<form method="post" action="/Lection">

		<table>
			<tr>
				<td><label>Name</label> <input type="text" id="name"
					name="name" /></td>
			</tr>
			<tr>
				<td><label>Date</label> <input type="text" id="date"
					name="date" /></td>
			</tr>
		</table>
		<input type="hidden" name="type" value="CreateLection">
		<p>
			<input type="submit" value="Create">
		</p>
	</form>
	<a href="/lection/lectionMenu.jsp">Back to Lection menu</a>
</body>
</html>