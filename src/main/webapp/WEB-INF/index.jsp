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
    <title>Login and Registration</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div>
			<form:form action="/register" method="post" modelAttribute="user" class="col-sm">
			<h2>Register here</h2>
				<div>
					<form:label path="userName">Name:</form:label>
					<form:input path="userName"/>
					<form:errors path="userName" class="text-danger"></form:errors>
				</div>
				<div>
					<form:label path="userLast">Last Name:</form:label>
					<form:input path="userLast"/>
					<form:errors path="userLast" class="text-danger"></form:errors>
				</div>
				<div>
					<form:label path="email">Email:</form:label>
					<form:input path="email"/>
					<form:errors path="email" class="text-danger"></form:errors>
				</div>
				<div>
					<form:label path="password">Password:</form:label>
					<form:input path="password" type="password" />
					<form:errors path="password" class="text-danger"></form:errors>
				</div>
				<div>
					<form:label path="confirmPassowrd">Confirm Password:</form:label>
					<form:input path="confirmPassowrd" type="password" />
					<form:errors path="confirmPassowrd" class="text-danger"></form:errors>
				</div>
			<button>Register</button>
			</form:form>
			<div>
			<form:form action="/login" method="post" modelAttribute="loginUser">
				<h2>Login Here</h2>
				<div>
					<form:label path="email">Email:</form:label>
					<form:input path="email"/>
					<form:errors path="email" class="text-danger"></form:errors>
				</div>
				<div>
					<form:label path="password">Password:</form:label>
					<form:input path="password" type="password"/>
					<form:errors path="password" class="text-danger"></form:errors>
				</div>
				<button>Login</button>
			</form:form>
			</div>
		</div>
	</div>


</body>
</html>