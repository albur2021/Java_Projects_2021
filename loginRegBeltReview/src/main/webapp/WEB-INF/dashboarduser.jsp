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
                <th scope="col">MenuItem</th>
                <th scope="col">Uploaded By</th>
                <th scope="col">Actions</th>
              </tr>
            </thead>
            <tbody>
                <c:forEach items="${ allMenuItems }" var="menuItem">
                    <tr>
                        <th scope="row">${menuItem.id}</th>
                        <td><a href="/menu/${menuItem.id}/info">${menuItem.itemName}</a></td>
                        <td>${menuItem.uploader.userName}</td>
                        <td><a href="/menu/${menuItem.id}/edit">Edit</a> || <a href="/menu/${menuItem.id}/delete">Delete</a></td>
                      </tr>
                </c:forEach>
            </tbody>
          </table>
          <a href="/menu/new">Add a new Menu Item</a>
    </div>
</body>
</html>