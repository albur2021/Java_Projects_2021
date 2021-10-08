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
    <h1>Hello World. Let's put this wonderful pets into a home</h1>
    <!-- <h3>All the Pets: ${allPets}</h3> -->


    <table class="table table-dark">
        <thead>
          <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Creator</th>
            <th scope="col">Version</th>
            <th scope="col">Actions</th>
          </tr>
        </thead>

        <tbody>

            <c:forEach items="${allLang}" var="lang">
                <tr>
                    <td>${lang.id}</td>
                    <td>${lang.name}</td>
                    <td>${lang.creator}</td>
                    <td>${lang.version}</td>
                    <td><a href="/lang/${lang.id}">View</a> || <a href="/lang/${lang.id}/edit">Edit</a> || <a href="/lang/${lang.id}/delete">Delete</a></td>
                  </tr>
            </c:forEach>
         
        </tbody>
      </table>

      <hr>
      <div>
        <h1>Choose One Favoriable Language</h1>

                <form:form action="/lang/create" method="post" modelAttribute="lang">
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