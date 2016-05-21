<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student page</title>
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
				<td><a href="/welcome.jsp">Main page</a></td>
			</tr>
		</table>
	</div>
	<c:if test="${error != null}"><H4 class="red_s">${error}</H4></c:if>
	<form action="/Student" method="post">
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
	<div class="sort">
		<a href="/Student"><input id="refresh" class="rad" type="button"
			value="Refresh"></a>
	</div>
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
				<c:forEach var="student" items="${students}">
				<tr>
					<td><a href="/Student?id=${student.getId()}">${student.getName()}</a></td>
					<td>${student.getAge()}</td>
					<td><a href="/EditStudent?id=${student.getId()}"><input class="rad"
							type="button" value="edit"></a></td>
					<td><a href="/DeleteStudent?id=${student.getId()}"><input class="rad"
							type="button" value="delete"></a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>