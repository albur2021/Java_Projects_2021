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
  

  
        <table class="table table-dark">
            <thead>
              <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Ninja(ninjas)</th>
              </tr>
            </thead>
    
            <tbody>
    
                <c:forEach items="${allDojos}" var="dojo">
                    <tr>
                        <td>${dojo.id}</td>
                        <td><a href="/dojos/${dojo.id}/info">${dojo.name}</a></td>
                        <td>
                            <ul>
                                <c:forEach items='${dojo.ninjas }' var ='n'>
                                    <li>${n.first_name} ${n.last_name}</li>
                                </c:forEach>
                            </ul>
                        </td>
                       
                        
                      </tr>
                </c:forEach>
             
            </tbody>
          </table>
 


        <h1>Create a new Team</h1>

        <form:form action="/dojos/create" method="post" modelAttribute="dojo">
            <p>
                <form:label path="name">Name</form:label>
                <form:errors class="text-danger" path="name"/>
                <form:input path="name"/>
            </p>
           
           
            <input type="submit" value="Submit"/>
        </form:form> 
        <a href="/create/newNinja">Create a new Ninja</a>  
  
</body>
</html>