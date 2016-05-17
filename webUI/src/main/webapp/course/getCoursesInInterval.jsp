<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Get courses in interval</title>
</head>
<body>
	<c:if test="${result != null}">
		<c:if test="${result.getClass().equals(java.util.Long)}">
			<c:if test="${result == -1}">
				<H4 class="error">Something wrong!</H4>
			</c:if>
		</c:if>
	</c:if>

	<form method="get" action="/Course">
		<label>Date from</label> <input type="text" name="dateFrom"><br>
		<label>Date to</label> <input type="text" name="dateTo"> <input
			type="hidden" name="type" value="GetCoursesInInterval">
		<p>
			<input type="submit" value="Get">
		</p>
	</form>
	<a href="/course/courseMenu.jsp">Back to Course menu</a>
	<br>

	<c:if test="${result != null}">
		<c:if test="${!result.getClass().equals(java.util.Long)}">
			<div>
				<table>
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Start Date</th>
							<th>Final Date</th>
							<th>Max Lections</th>
							<th>Max Students</th>
						</tr>
					</thead>
					<c:forEach var="course" items="${result}">
						<tbody>
							<tr>
								<td>${course.getId()}</td>
								<td>${course.getName()}</td>
								<td>${course.getStartDate()}</td>
								<td>${course.getFinalDate()}</td>
								<td>${course.getMaxLections()}</td>
								<td>${course.getMaxStudents()}</td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</div>
		</c:if>
	</c:if>
</body>
</html>