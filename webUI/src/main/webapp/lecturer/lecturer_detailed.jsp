<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>
Lecturer detailed
</title>
<head>
	 <link href="/style/detailed.css" rel="stylesheet">
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
	<div id="table_main" class="lecturer_table">
		<table>
			<tr>
					<th>Lecturer's name</th>
					<th>Age</th>
			</tr>
			<tr>
					<td>LecturerName</td>
					<td>LecturerAge</td>
			</tr>
		</table>
	</div>
	<div id="table_main" class="course_table">
	<H3>Courses</H3>
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
					<td><a href="/Course?id=1">Name</a></td>
					<td>start date</td>
					<td>final date</td>
					<td>max lections</td>
					<td>max students</td>
				</tr>
				
			</tbody>
		</table>
	</div>
</body>
</html>