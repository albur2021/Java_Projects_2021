<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create a new Ninja</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" integrity="undefined" crossorigin="anonymous">

</head>
<body>
    <h1>Create a new Ninja</h1>
    <h3>${allDojos}</h3>

         <form:form action="/ninjas/create" method="post" modelAttribute="ninja">
    <p>
        <form:label path="first_name">First Name</form:label>
        <form:errors class="text-danger" path="first_name"/>
        <form:input path="first_name"/>
    </p>
    <p>
        <form:label path="last_name">Last Name</form:label>
        <form:errors  class="text-danger" path="last_name"/>
        <form:input  path="last_name"/>
    </p>
    <p>
      <form:label path="age">Age</form:label>
      <form:errors  class="text-danger" path="age"/>
      <form:input type="number" path="age"/>
  </p>
      <p>
          <form:label path="dojo">Select Dojo</form:label>
          <form:select path="dojo" id="">
              <c:forEach items='${allDojos}' var='n'>
                  <option value="${n.id}">${n.name}</option>
              </c:forEach>
          </form:select>
       </p>
   
    <input type="submit" value="Submit"/>
</form:form>  
</body>
</html>