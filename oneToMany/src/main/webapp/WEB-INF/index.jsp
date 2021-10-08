
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
    <h1>Hello Team and Players</h1>
    <h3>${allTheTeams}</h3>

    

    <table class="table table-dark">
        <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">City</th>
            <th scope="col">Roster(Players)</th>
          </tr>
        </thead>
        <tbody>
            <c:forEach items='${allTeams}' var="team">
                <tr>
                    <td>${team.name}</td>
                    <td><a href="/teams/${team.id}/info">${team.name}</a></td>
                    <td>${team.city}</td>
                    <td><a href="/teams/{team.id}/info">View</a> || <a href="/teams/${team.id}/edit">Edit</a> || <a href="/teams/${team.id}/delete">Delete</a> </td>
                    <td>
                    <c:forEach items='${ team.players}' var='p'>
                        <ul>
                            <li>${p.first_name} ${p.last_name}</li>
                        </ul>
                        
                    </c:forEach>
                    
                    </td>
                  </tr>
                  <tr>
            </c:forEach>
        </tbody>
      </table>
      
      <hr>
      <div>
        <h1>Create a new Team</h1>

                <form:form action="/teams/create" method="post" modelAttribute="team">
            <p>
                <form:label path="name">Name</form:label>
                <form:errors class="text-danger" path="name"/>
                <form:input path="name"/>
            </p>
            <p>
                <form:label path="city">City</form:label>
                <form:errors  class="text-danger" path="city"/>
                <form:input  path="city"/>
            </p>
           
            <input type="submit" value="Submit"/>
        </form:form>   
      </div>

      <div>
          <a href="/players/new">Create a new Player</a>
      </div>
      
   
</body>
</html>