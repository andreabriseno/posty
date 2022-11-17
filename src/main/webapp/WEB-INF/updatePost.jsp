<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="UTF-8">
    <title>Update Post </title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
 </head>
<body>

<form:form action="/post/update" method="post" modelAttribute="post">
		<h2>Update post</h2>
		<input type="hidden" name=_method value="put">
		<form:hidden path="user"/>
		<form:hidden path="id"/>
		<div>
			<form:label path="title">Title:</form:label>
			<form:errors path="title"/>
			<form:input path="title" type="text" value="${post.title}"/>
		</div>
		<div>
			<form:label path="message">Message:</form:label>
			<form:errors path="message"></form:errors>
			<form:textarea path="message" value="${post.message}"></form:textarea>
		</div>
		<button>Update</button>
	</form:form>

</body>
</html>