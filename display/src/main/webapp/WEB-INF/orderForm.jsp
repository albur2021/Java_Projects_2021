<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title><link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" integrity="undefined" crossorigin="anonymous">

</head>
<body onload="warnAboutCurry()">
	<h1>Order some food today, the food the something of something on Current Day is: ${dateInfo}</h1>
    <form action="/submitOrder" method="post">
        <div class="form-group">
            <label for="">Your Name</label>
            <input class="form-control" type="text" name="nameVariable" id="">
        </div>
        <div>
            <label for="">Select Spice Level</label>
            <select class="form-control" name="spiceLevel" id="">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4(extraa hot)</option>
            </select>
        </div>
        <div>
            <label for="">Credit Car Number</label>
            <input class="form-control" type="text" name="ccNum" id="">
        </div>
        <input type="submit" value="Order this Curry!">
    </form>
    <script src="/js/grubstuff.js"></script>
</body>
</html>