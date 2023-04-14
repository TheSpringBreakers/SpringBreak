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
<title>Edit Help Ticket</title>
</head>
<body>
	<header>
		<h1>Edit Help Ticket</h1>
	</header>
    <main>
    <div>
		<form:form action="/tickets/${theTicket.id}/update" method="post" modelAttribute="editTicketForm">
			<input type="hidden" name="_method" value="put">
			<p>
				<form:label path="title">Title: </form:label>
				<form:input path="title" value="${theTicket.title}"/>
				<form:errors path="title" />
			</p>
			
				<form:label path="type">Type: </form:label>
				<form:select path="type">
					<form:option value="${theTicket.type}">${theTicket.type}</form:option>
					<form:option value="Java">Java</form:option>
					<form:option value="cSharp">cSharp</form:option>
					<form:option value="JavaScript">JavaScript</form:option>
					<form:option value="CSS/HTML">CSS/HTML</form:option>
					<form:option value="Other">Other</form:option>
				</form:select>
			<p>
				<form:label path="description">Description: </form:label>
				<textarea name="description" rows="3" cols="20">${theTicket.description}</textarea>
				<form:errors path="description" />
			</p>
			<section>
    			<input type="hidden" name="user" value="${ user_id }" />
    		</section>
			<button>Submit</button>
		</form:form>
	</div>
    </main>
    <footer>
    
    </footer>
</body>
</html>