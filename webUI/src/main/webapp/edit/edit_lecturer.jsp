<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit lecturer</title>
<head>
<link href="/style/style_edit.css" rel="stylesheet">
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
	<form action="/EditLecturer" method="post">
		<div id="table_create" class="data_table">
			<table>
				<tr>
					<td><input type="text" name="name" /></td>
					<td><label>Name</label></td>
				</tr>
				<tr>
					<td><input type="text" name="age" /></td>
					<td><label>Age</label></td>
				</tr>
				<tr>
					<td><input class="rad" type="submit" value="Save"></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>