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
<title>Insert title here</title>
</head>
<body>
	<header>
		<h1>Browse Help Tickets</h1>
	</header>
    <main>
    <div class="cardContainer">
    <c:forEach items="${allTickets}" var="t">
		<div class="card" style="width: 18rem;">
		  <div class="card-body">
		    <h5 class="card-title"><span><a href="/tickets/${t.id}">${t.title}</a></span></h5>
		    <h6 class="card-subtitle mb-2 text-muted">${t.user.name}</h6>
		    <p class="card-text">${t.type}</p>
		    <p class="card-text">${t.description}</p>
		    <a href="/tickets/${t.id}/edit" class="card-link">Edit</a>
		    <a href="/ticket/${t.id}/delete" class="card-link">Delete</a>
		   	 <c:forEach items="${t.likedUsers}" var="u">
		   	 <c:choose>
				<c:when test="${u.id == userId}"></c:when>
					<c:otherwise>
						<form action="/tickets/${t.id}/bookmark" method="post">
							<input type="hidden" name="_method" value="put" />
							<button class="btn">Bookmark</button>
						</form>
					</c:otherwise>
			</c:choose>
		   	 </c:forEach>
			${t.likedUsers[0].name}
		  </div>
		</div>
    </c:forEach>
</div>
    </main>
    <footer>
    
    </footer>
</body>
</html>