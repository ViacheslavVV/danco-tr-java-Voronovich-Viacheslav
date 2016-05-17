<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All lecturers</title>
</head>
<body>
	<c:if test="${result != null}">
		<c:if test="${result.getClass().equals(java.util.Long)}">
			<c:if test="${result == -1}">
				<H4 class="error">Something wrong!</H4>
			</c:if>
		</c:if>
	</c:if>

	<form method="get" action="/Lecturer">
	<label>Sorting param</label>
    <div>
    <input type="radio" name="sortingParam" value="ID" checked>ID<br>
    <input type="radio" name="sortingParam" value="NAME">Name<br>
    </div>
    <input
			type="hidden" name="type" value="GetAllLecturers">
    <p><input type="submit" value="Get"></p>
	</form>
	<a href="/lecturer/lecturerMenu.jsp">Back to Lecturer menu</a>
	<c:if test="${result != null}">
		<c:if test="${!result.getClass().equals(java.util.Long)}">
			<div>
				<table>
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Age</th>
						</tr>
					</thead>
					<c:forEach var="lecturer" items="${result}">
						<tbody>
							<tr>
								<td>${lecturer.getId()}</td>
								<td>${lecturer.getName()}</td>
								<td>${lecturer.getAge()}</td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</div>
		</c:if>
	</c:if>
</body>
</html>