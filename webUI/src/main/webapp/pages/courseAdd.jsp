<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New course</title>
<style>
.resOk {
	color: green;
}

.resFail {
	color: red;
}

.notif {
	color: blue;
}
</style>
</head>
<body>
	<%
		if (request.getAttribute("isOk") != null) {
			if ((Boolean) request.getAttribute("isOk")) {
	%>
		<H5 class="resOk">Course has been added.</H5>
	<%
		} else {
	%>
		<H5 class="resFail">Course hasn't been added.</H5>
	<%
		}
		}
	%>
	<H3>Create new course</H3>
	<div>
		<p class="notif">Date format : (yyyy-mm-dd hh:mm)</p>
		<br />
		<form method="post" action="/webUI/course/create/">
				<table>
				<tr>
					<td><label>Name</label> <input type="text" id="name" name="name" /></td>
				</tr>
				<tr>
					<td><label>Start Date</label> <input type="text" id="startDate" name="startDate" /></td>
				</tr>
				<tr>
					<td><label>Final Date</label> <input type="text" id="finalDate" name="finalDate" /></td>
				</tr>  
				<tr>
					<td><label>Max Students</label> <input type="text" id="maxStudents" name="maxStudents" /></td>
				</tr>
				<tr>
					<td><label>Max Lections</label> <input type="text" id="maxLections"	name="maxLections" /></td>
				</tr>
				</table>
			<button type="submit">Create</button>
		</form>
	</div>
	<a href="course">Back</a>
</body>
</html>