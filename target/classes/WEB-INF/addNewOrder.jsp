<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<form action="/add-new-order" method="POST">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <label>Id</label>
    <input type="text" name="id" required>
    <label>Name</label>
    <input type="text" name="title" required>
    <label>Date</label>
    <input type="text" name="price" required>
    <input type="submit" value="Add new order">

</form>


</body>
</html>