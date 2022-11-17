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
<title>View Post</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<h1><c:out value="${post.title}"></c:out></h1>
	<a href="/dashboard"> Home</a>
	<p><c:out value="${post.message}"></c:out></p>
	<p>Posted by:<c:out value="${post.user.userName}"></c:out></p>
	<c:if test="${isAuthor}">
		<a href="/post/${post.id}/update">Update</a> |
		<a href="/post/${post.id}/delete">Delete</a>
	</c:if>
	<c:if test="${isAuthor}">
		<a href="/post/${post.id}/like">Like</a>
		<form:form action="/post/${id}/comment" method="post" modelAttribute="comment">
			<h3>Comment here</h3>
			<form:input path="message"/>
			<button>Submit</button>
		</form:form>
	</c:if>
	<%-- <h3>This post is liked by: </h3>
	<ul>
		<c:forEach var="u" items="${post.likedBy}">
			<li> <c:out value="${u.userName}"></c:out></li>
		</c:forEach>
	</ul> --%>

</body>
</html>