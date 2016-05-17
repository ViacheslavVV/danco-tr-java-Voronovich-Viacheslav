<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course menu</title>
</head>
<body>

	<c:if test="${error != null}">
		<H4 class="error">Something wrong!</H4>
	</c:if>

	<H3>Course menu</H3>
	<table>
		<tr>
			<td><a href="/course/getOneCourse.jsp">Get course by id</a></td>
		</tr>
		<tr>
			<td><a href="/course/getManyCourses.jsp">Get courses</a></td>
		</tr>
		<tr>
			<td><a href="/course/deleteCourse.jsp">Delete course</a></td>
		</tr>
		<tr>
			<td><a href="/course/createCourse.jsp">Create course</a></td>
		</tr>
		<tr>
			<td><a href="/course/setLecturerToCourse.jsp">Set lecturer to course</a></td>
		</tr>
		<tr>
			<td><a href="/course/addLectionToCourse.jsp">Add lection to course</a></td>
		</tr>
		<tr>
			<td><a href="/course/removeLectionFromCourse.jsp">Remove lection
					from course</a></td>
		</tr>
		<tr>
			<td><a href="/course/addStudentToCourse.jsp">Add student to course</a></td>
		</tr>
		<tr>
			<td><a href="/course/removeStudentFromCourse.jsp">Remove student
					from course</a></td>
		</tr>
		<tr>
			<td><a href="/course/getCoursesInInterval.jsp">Get courses in
					interval</a></td>
		</tr>
		<tr>
			<td><a href="/course/addStudentToCourse.jsp">Add student to course</a></td>
		</tr>

	</table>

	<a href="/index.html">Back to Main menu</a>
</body>
</html>