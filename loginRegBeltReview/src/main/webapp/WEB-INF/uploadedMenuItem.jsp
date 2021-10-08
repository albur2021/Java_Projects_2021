<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" integrity="undefined" crossorigin="anonymous">
</head>
<body>
    <div>
      
        <form:form action="/menu/create" method="post" modelAttribute="menu">
            <p>
                <form:label path="itemName">Menu Item Name:</form:label>
                <form:errors class="text-danger" path="itemName"/>
                <form:input path="itemName"/>
            </p>
            <p>
                <form:label path="description">Description:</form:label>
                <form:errors class="text-danger" path="description"/>
                <form:textarea path="description"/>
            </p>
          
            <input type="submit" value="Create">
        </form:form>    

    </div>
</body>
</html>