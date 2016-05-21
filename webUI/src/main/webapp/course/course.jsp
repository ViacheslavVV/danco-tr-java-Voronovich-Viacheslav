<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>
Course page
</title>
<head>
	 <link href="/style/style_course.css" rel="stylesheet">
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
	<form action="/Course" method="post">
	<div id="table_create" class="data_table">
		<table>
			<thead>
				<tr>
						<th>Name</th>
						<th>Start Date</th>
						<th>Final Date</th>
						<th>Max Lections</th>
						<th>Max Students</th>
						<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="text" name="name" /></td>
					<td><input type="text" name="startDate" /></td>
					<td><input type="text" name="finalDate" /></td>
					<td><input type="text" name="maxLections" /></td>
					<td><input type="text" name="maxStudents" /></td>
					<td><input class="rad" type="submit" value="Create"></td>
				</tr>
			</tbody>
		</table>
	</div>
	</form>
	<form action="/Course" method="get">
	<div class="sort">
		<table>
			<tr>
				<td>
					<label>Sorting param</label>
					<select name="sortingParam">
						<option>ID</option>
						<option>LECTURER</option>
						<option>STUDENTS_COUNT</option>
						<option>NAME</option>
						<option>START_DATE</option>
					</select>
				</td>
				<td>
					<label>Type param</label>
					<select name="courseDateParam">
						<option>NONE</option>
						<option>AFTER_DATE</option>
						<option>CURRENT</option>
					</select>
				</td>
				<td>
					<label>Date from (for after_date)</label>
					<input type="text" name="dateFrom">
				</td>
				<td>
					<label>Date to</label>
					<input type="text" name="dateTo">
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
						<th>Start Date</th>
						<th>Final Date</th>
						<th>Max Lections</th>
						<th>Max Students</th>
						<th></th>
						<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach  var="course" items="${courses}">
				<tr>
					<td><a href="/Course?id=${course.getId()}">${course.getName()}</a></td>
					<td>${course.getStartDate()}</td>
					<td>${course.getFinalDate()}</td>
					<td>${course.getMaxLections()}</td>
					<td>${course.getMaxStudents()}</td>
					<td><a href="/EditCourse?id=${course.getId()}"><input class="rad" type="button" value="edit"></a></td>
					<td><a href="/DeleteCourse?id=${course.getId()}"><input class="rad" type="button" value="delete"></a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>