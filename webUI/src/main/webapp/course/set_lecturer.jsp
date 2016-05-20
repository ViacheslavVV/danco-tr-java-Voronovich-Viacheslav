<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>
Set Lecturer page
</title>
<head>
	 <link href="/style/style_lecturer.css" rel="stylesheet">
	 	 <link href="/style/style_menu.css" rel="stylesheet">
</head>
<body>
	<div class="menu">
		<table>
			<tr>
				<td><a href="/course/course.jsp">Course</a></td>
				<td><a href="/lecturer/lecturer.jsp">Lecturer</a></td>
				<td><a href="/lection/lection.jsp">Lection</a></td>
				<td><a href="/student/student.jsp">Student</a></td>
			</tr>
		</table>
	</div>
	<form action="/SetLecturer" method="post">
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
				<c:forEach var="lecturer" items="${lecturers}">
				<tr>
					<td><a href="/Lecturer?id=${lecturer.getId()}">${lecturer.getName()}</a></td>
					<td>${lecturer.getAge()}</td>
					<td><input class="rad" type="radio" name="lecturerId" value="${lecturer.getId()}"></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<input class="green_but" type="submit" value="Set">
	</div>
	</form>
</body>
</html>