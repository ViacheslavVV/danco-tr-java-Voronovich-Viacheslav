<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete lection</title>
</head>
<body>
	<c:if test="${result == false}">
		<H4 class="error">Something wrong!</H4>
	</c:if>

	<form method="post" action="/Lection">
		<label>Lection id</label> <input type="text" name="lectionId" /> <input
			type="hidden" name="type" value="DeleteLection">
		<p>
			<input type="submit" value="Delete">
		</p>
	</form>
	<a href="/lection/lectionMenu.jsp">Back to Lection menu</a>
	</body>
</html>