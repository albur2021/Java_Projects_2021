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
    <h1>Welcome to the dashboard ${loggedInUser.userName} !</h1>
    <div>
        <a href="/logout">Logout</a>
       
    </div>

    <div>
        <table class="table">
            <thead>
              <tr>
                <th scope="col">ID#</th>
                <th scope="col">Idea</th>
                <th scope="col">Create By</th>
                <th scope="col">Actions</th>
              </tr>
            </thead>
            <tbody>
                <c:forEach items="${ allIdeasItems }" var="ideaItem">
                    <tr>
                        <th scope="row">${ideaItem.id}</th>
                        <td><a href="/ideas/${ideaItem.id}/info">${ideaItem.itemName}</a></td>
                        <td>${ideaItem.uploader.userName}</td>
                        <td><a href="/ideas/${ideaItem.id}/edit">Edit</a> || <a href="/ideas/${ideaItem.id}/delete">Delete</a></td>
                      </tr>
                </c:forEach>
            </tbody>
          </table>
          <a href="/ideas/new">Add a new Menu Item</a>
    </div>
</body>
</html>