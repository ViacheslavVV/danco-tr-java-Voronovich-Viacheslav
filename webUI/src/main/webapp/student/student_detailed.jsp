<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>
Student detailed
</title>
<head>
	 <link href="/style/detailed.css" rel="stylesheet">
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
	<div id="table_main" class="student_table">
		<table>
			<tr>
					<th>Student's name</th>
					<th>Age</th>
			</tr>
			<tr>
					<td>${student.getName()}</td>
					<td>${student.getAge()}</td>
			</tr>
		</table>
	</div>
	<div id="table_main" class="course_table">
		<H3>Course</H3>
		<table>
			<thead>
				<tr>
						<th>Name</th>
						<th>Start Date</th>
						<th>Final Date</th>
						<th>Max Lections</th>
						<th>Max Students</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><a href="/Course?id=${course.getId()}">${course.getName()}</a></td>
					<td>${course.getStartDate()}</td>
					<td>${course.getFinalDate() }</td>
					<td>${course.getMaxLections() }</td>
					<td>${course.getMaxStudents() }</td>
				</tr>
				
			</tbody>
		</table>
	</div>
</body>
</html>