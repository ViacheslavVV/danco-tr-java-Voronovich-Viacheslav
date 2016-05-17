<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Remove lection from course</title>
</head>
<body>
	<c:if test="${result == false}">
		<H4 class="error">Something wrong!</H4>
	</c:if>
	
	<form method="post" action="/Course">
		<label>Lection ID</label> <input type="text" name="lectionId"><br>
		<label>Course ID</label> <input type="text" name="courseId"> <input
			type="hidden" name="type" value="RemoveLectionFromCourse">
		<p>
			<input type="submit" value="Remove">
		</p>
	</form>
	<a href="/course/courseMenu.jsp">Back to Course menu</a>
</body>
</html>