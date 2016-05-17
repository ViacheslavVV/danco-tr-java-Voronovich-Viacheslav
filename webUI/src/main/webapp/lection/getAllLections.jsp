<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All lections</title>
</head>
<body>
	<c:if test="${result != null}">
		<c:if test="${result.getClass().equals(java.util.Long)}">
			<c:if test="${result == -1}">
				<H4 class="error">Something wrong!</H4>
			</c:if>
		</c:if>
	</c:if>

	<form method="get" action="/Lection">
	<label>Sorting param</label>
    <div>
    <input type="radio" name="sortingParam" value="DATE">Date<br>
    <input type="radio" name="sortingParam" value="ID" checked>ID<br>
    <input type="radio" name="sortingParam" value="NAME">Name<br>
    </div>
    <input
			type="hidden" name="type" value="GetAllLections">
    <p><input type="submit" value="Get"></p>
	</form>
	<a href="/lection/lectionMenu.jsp">Back to Lection menu</a>
	<c:if test="${result != null}">
		<c:if test="${!result.getClass().equals(java.util.Long)}">
			<div>
				<table>
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Date</th>
						</tr>
					</thead>
					<c:forEach var="lection" items="${result}">
						<tbody>
							<tr>
								<td>${lection.getId()}</td>
								<td>${lection.getName()}</td>
								<td>${lection.getDate()}</td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</div>
		</c:if>
	</c:if>
</body>
</html>