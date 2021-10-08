
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div>
        <form:form action="/team/${team.id}/update" method="post" modelAttribute="team">
            <p>
                <form:label path="name">Team Name</form:label>
                <form:errors path="name"/>
                <form:input path="name"/>
            </p>
            <p>
                <form:label path="city">City</form:label>
                <form:errors path="city"/>
                <form:input  path="city"/>
            </p>
            
            <input type="submit" value="Submit"/>
        </form:form>   
      </div>
</body>
</html>