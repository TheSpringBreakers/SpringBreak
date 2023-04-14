<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- for forms -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!-- for validation -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" type="text/css" href="/css/style.css">
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Login and Register</title>
</head>
<body class="container">
	<div class="row">
		<div class="col">
			<h1>Register</h1>
			<form:form action="/register" method="post" modelAttribute="newUser">
				<p>
					<form:errors path="name" class="error" />
					<form:label path="name">Name:</form:label>
					<form:input path="name" type="text" />
				</p>
				<p>
					<form:errors path="email" class="error" />
					<form:label path="email">Email:</form:label>
					<form:input path="email" type="text" />
				</p>
				<p>
					<form:errors path="password" class="error" />
					<form:label path="password">Password:</form:label>
					<form:input path="password" type="password" />
				</p>
				<p>
					<form:errors path="confirmPassword" class="error" />
					<form:label path="confirmPassword">Confirm Password:</form:label>
					<form:input path="confirmPassword" type="password" />
				</p>
				<button>Submit</button>
			</form:form>
		</div>
		<div class="col">
			<h1>Login</h1>
			<form:form action="/login" method="post" modelAttribute="newLogin">
				<p>
					<form:errors path="email" class="error" />
					<form:label path="email">Email:</form:label>
					<form:input path="email" type="text" />
				</p>
				<p>
					<form:errors path="password" class="error" />
					<form:label path="password">Password:</form:label>
					<form:input path="password" type="password" />
				</p>
				<button>Submit</button>
			</form:form>
		</div>
	</div>
</body>
</html>