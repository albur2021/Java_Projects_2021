<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>Details about menu item ${ideaObj.id}</h1>
    <h3>${ideaObj.itemName}</h3>
    <p>Uploaded by: ${ideaObj.uploader.userName}</p>
    <p>Description: ${ideaObj.description}</p>
    <a href="/ideas/${ideaObj.id}/edit">Edit</a>
</body>
</html>