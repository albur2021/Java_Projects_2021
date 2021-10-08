<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>Details about menu item ${menuObj.id}</h1>
    <h3>${menuObj.itemName}</h3>
    <p>Uploaded by: ${menuObj.uploader.userName}</p>
    <p>Description: ${menuObj.description}</p>
    <a href="/menu/${menuObj.id}/edit">Edit</a>
</body>
</html>