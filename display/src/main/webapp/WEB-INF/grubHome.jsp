<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome to GrubHub bootleg</h1>
	<div>Welcome to First JSP Web Page from Java!</div>
	<p>Here is first Dynamic element:  <c:out value="${2+2}"/></p> 
	<p>Here is second Dynamic element: ${10 + 10}</p>
	
	<p>Here is data coming from GrabController Class: <c:out value="${nameVariable}"/></p>
	<p>Here is data coming from GrabController Class: <c:out value="${numbBelts}"/></p>
	<h3>Here is the same without 'c:out value=... </h3>
	${nameVariable}
	${numbBelts}

	<a href="/newOrder">Grub some food!</a>
	
</body>
</html>