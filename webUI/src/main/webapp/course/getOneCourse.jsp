<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>One course</title>
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
		<label>Course ID</label> <input type="text" name="courseId"><br>
		<input type="hidden" name="type" value="GetOneCourse">
		<p>
			<input type="submit" value="Get">
		</p>
	</form>
	<a href="/course/courseMenu.jsp">Back to Course menu</a>
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
					<tbody>
						<tr>
							<td>${result.getId()}</td>
							<td>${result.getName()}</td>
							<td>${result.getStartDate()}</td>
							<td>${result.getFinalDate()}</td>
							<td>${result.getMaxLections()}</td>
							<td>${result.getMaxStudents()}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</c:if>
	</c:if>
</body>
</html>