<%@page import="com.training.danco.model.Course"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course page</title>
</head>
<body>

	<h3>Select operation:</h3>
	<div style="border: 2px">
		<ul>
			<li><a href="course/create" class="link">Create course</a></li>
			<li><a href="course/getById" class="link">Get course by id</a></li>
			<li><a href="course/delete" class="link">Delete course</a></li>
			<li><a href="course/clone" class="link">Clone course</a></li>
			<li><a href="course/all" class="link">Get all courses</a></li>
		</ul>
	</div>

	<%
		String type = (String) request.getAttribute("type");
		if (type != null) {
			if (type.equals("all")) {
	%>
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
			<%
				if (request.getAttribute("data") != null) {
							List<Course> courses = (List<Course>) request.getAttribute("data");
							for (Course course : courses) {
			%>
			<tbody>
				<tr style="width: 1500px">
					<td><%=course.getId()%></td>
					<td><%=course.getName()%></td>
					<td><%=course.getStartDate()%></td>
					<td><%=course.getFinalDate()%></td>
					<td><%=course.getMaxLections()%></td>
					<td><%=course.getMaxStudents()%></td>
				</tr>
			</tbody>
			<%
				}
						}
			%>
		</table>
	</div>
	<%
		}
		}
	%>
</body>
</html>