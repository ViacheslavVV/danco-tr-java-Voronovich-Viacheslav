<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course detailed</title>
<head>
<link href="/style/detailed.css" rel="stylesheet">
<link href="/style/style_menu.css" rel="stylesheet">
</head>
<body>
	<c:if test="${message != null}"><H4 class="red_s">${message}</H4></c:if>
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
	<div id="table_main" class="course_table">
		<table>
			<tr>
				<th>Name</th>
				<th>Start Date</th>
				<th>Final Date</th>
				<th>Max Lections</th>
				<th>Max Students</th>
			</tr>
			<tr>
				<td>${course.getId()}</td>
				<td>${course.getStartDate()}</td>
				<td>${course.getFinalDate()}</td>
				<td>${course.getMaxLections()}</td>
				<td>${course.getMaxStudents()}</td>
			</tr>
		</table>
	</div>
	<div id="table_main" class="lecturer_course_table">
		<h3>
			Lecturer
			<c:if test="${lecturer == null}">
				<a href="/SetLecturer?courseId=${course.getId()}"><input
					class="rad_add" type="button" value="set"></a>
			</c:if>
		</h3>
		<table>
			<tr>
				<th>Name</th>
				<th>Age</th>
				<th></th>
			</tr>
			<c:if test="${lecturer != null}">
				<tr>
					<td><a href="/Lecturer?id=${lecturer.getId()}">${lecturer.getName() }</a></td>
					<td>${lecturer.getAge() }</td>
					<td><a
						href="/RemoveLecturer?courseId=${course.getId()}&lecturerId=${lecturer.getId()}"><input
							class="rad" type="button" value="remove"></a></td>
				</tr>
			</c:if>
		</table>
	</div>
	<div id="table_main" class="student_course_table">
		<H3>
			Students<a href="/AddStudent?courseId=${course.getId()}"><input
				class="rad_add" type="button" value="add"></a>
		</H3>
		<table>
			<thead>
				<tr>
					<th>Name</th>
					<th>Age</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="student" items="${students}">
					<tr>
						<td><a href="/Student?id=${student.getId() }">${student.getName()}</a></td>
						<td>${student.getAge()}</td>
						<td><a href="/RemoveStudent?courseId=${course.getId() }&studentId=${student.getId() }"><input
								class="rad" type="button" value="remove"></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div id="table_main" class="lection_course_table">
		<H3>
			Lections<a href="/AddLection?courseId=${course.getId()}"><input class="rad_add"
				type="button" value="add"></a>
		</H3>
		<table>
			<thead>
				<tr>
					<th>Name</th>
					<th>Date</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="lection" items="${lections}">
				<tr>
					<td>${lection.getName()}</td>
					<td>${lection.getDate()}</td>
					<td><a href="/RemoveLection?courseId=${course.getId()}&lectionId=${lection.getId() }"><input
							class="rad" type="button" value="remove"></a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>