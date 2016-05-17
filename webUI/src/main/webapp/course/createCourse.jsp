<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create course</title>
</head>
<body>
	<c:if test="${result == -1}">
		<H4 class="error">Something wrong!</H4>
	</c:if>

	<form method="post" action="/Course">

		<table>
			<tr>
				<td><label>Name</label> <input type="text" id="name"
					name="name" /></td>
			</tr>
			<tr>
				<td><label>Start Date</label> <input type="text" id="startDate"
					name="startDate" /></td>
			</tr>
			<tr>
				<td><label>Final Date</label> <input type="text" id="finalDate"
					name="finalDate" /></td>
			</tr>
			<tr>
				<td><label>Max Students</label> <input type="text"
					id="maxStudents" name="maxStudents" /></td>
			</tr>
			<tr>
				<td><label>Max Lections</label> <input type="text"
					id="maxLections" name="maxLections" /></td>
			</tr>
		</table>
		<input type="hidden" name="type" value="CreateCourse">
		<p>
			<input type="submit" value="Create">
		</p>
	</form>
	<a href="/course/courseMenu.jsp">Back to Course menu</a>
</body>
</html>