<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>
Lecturer page
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
	<form action="/Lecturer" method="post">
	<div id="table_create" class="data_table">
		<table>
			<thead>
				<tr>
						<th>Name</th>
						<th>Age</th>
						<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="text" name="name" /></td>
					<td><input type="text" name="age" /></td>
					<td><input class="rad" type="submit" value="Create"></td>
				</tr>
			</tbody>
		</table>
	</div>
	</form>
	<form action="/Lecturer" method="get">
	<div class="sort">
		<table>
			<tr>
				<td>
					<label>Sorting param</label>
					<select>
						<option>Пункт 1</option>
						<option>Пункт 2</option>
					</select>
				</td>
				<td>
					<td><input id="refresh" class="rad" type="submit" value="Refresh">
				</td>
			</tr>
		</table>
	</div>
	</form>
	<div id="table_main" class="data_table">
		<table>
			<thead>
				<tr>
						<th>Name</th>
						<th>Age</th>
						<th></th>
						<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><a href="/Lecturer?id=1">Name</a></td>
					<td>Age</td>
					<td><a href="/EditLecturer?id=1"><input class="rad" type="button" value="edit"></a></td>
					<td><a href="/DeleteLecturer?id=1"><input class="rad" type="button" value="delete"></a></td>
				</tr>
				
			</tbody>
		</table>
	</div>
</body>
</html>