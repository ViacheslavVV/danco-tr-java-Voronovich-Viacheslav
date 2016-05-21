<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add students</title>
<head>
<link href="/style/style_student.css" rel="stylesheet">
<link href="/style/style_menu.css" rel="stylesheet">
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
	<form action="/AddStudent" method="post">
		<input type="hidden" name="courseId" value="${courseId}">
		<div id="table_main" class="data_table">
			<table>
				<thead>
					<tr>
						<th>Name</th>
						<th>Age</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="student" items="${students}">
					<tr>
						<td><a href="/Student?id=${student.getId()}">${student.getName()}</a></td>
						<td>${student.getAge()}</td>
						<td><input type="checkbox" name="studentId" value="${student.getId()}"></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<input class="green_but" type="submit" value="Add">
		</div>
	</form>
</body>
</html>