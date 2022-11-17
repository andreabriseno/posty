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
    <title>Dashboard</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div>
		<h1> Welcome to Posty <c:out value="${loggedUser.userName}"></c:out></h1>
		<a href="/logout"> Logout </a>
		<form:form action="/post/create" method="post" modelAttribute="post">
			<h2>Create post</h2>
			<div>
				<form:label path="title">Title:</form:label>
				<form:errors path="title"/>
				<form:input path="title" type="text"/>
			</div>
			<div>
				<form:label path="message">Message:</form:label>
				<form:errors path="message"></form:errors>
				<form:textarea path="message"></form:textarea>
			</div>
			<button>Post it </button>
		</form:form>
		
		<h2>Your most recent posts!</h2>
		<ul>
				<c:forEach var="post" items="${loggedUser.allPosts}">
					<li> <a href="/post/${post.id}"><c:out value="${post.title}"></c:out> </a></li>
				</c:forEach>
		</ul>
	</div>
	<div class="col-sm">
	<h2>3 Recent Post</h2>
		<ul>
			<c:forEach var="p" items="${allPosts}">
				<li><a href="/post/${p.id}"><c:out value="${p.title}"/></a></li>
			</c:forEach>
		</ul>
	</div>
		
</body>
</html>