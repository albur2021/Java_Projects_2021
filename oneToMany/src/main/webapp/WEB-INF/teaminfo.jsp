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
    <h3>Team Name: ${teamtoshow.name}</h1>
    <h3>City: ${teamtoshow.city}</h3>
    <h3>Players List:${teamtoshow.players}</h3>
    


    <table class="table table-dark">
        <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">FullName</th>
            <th scope="col">Age</th>
            
          </tr>
        </thead>
        <tbody>
            <c:forEach items='${teamtoshow.players}' var="player">
                <tr>
                    <th scope="row">${player.id}</th>
                    <td>${player.first_name} ${player.last_name}</td>
                    <td>${player.age}</td>
                </tr>
               
            </c:forEach>
        </tbody>
      </table>

        
    
</body>
</html>