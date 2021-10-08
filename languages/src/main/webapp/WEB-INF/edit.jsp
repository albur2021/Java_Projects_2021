
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
        <form:form action="/lang/${lang.id}/update" method="post" modelAttribute="lang">
            <p>
                <form:label path="name">Name</form:label>
                <form:errors path="name"/>
                <form:input path="name"/>
            </p>
            <p>
                <form:label path="creator">Creator</form:label>
                <form:errors path="creator"/>
                <form:input  path="creator"/>
            </p>
            <p>
                <form:label path="version">Version</form:label>
                <form:errors path="version"/>
                <form:input type="number" path="version"/>
            </p>
            <input type="submit" value="Submit"/>
        </form:form>   
      </div>
</body>
</html>