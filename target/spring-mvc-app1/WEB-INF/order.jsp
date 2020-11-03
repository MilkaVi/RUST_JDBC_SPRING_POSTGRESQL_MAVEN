
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn"
           uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<form action="/" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input type="submit" value="Sign Out"/>
</form>
<table>
    <tr>
        <th>Files id</th>
        <th>Files name</th>
        <th>Date</th>
        <th>Delete</th>
        <th>Update</th>
    </tr>
    <c:forEach items="${orderList}" var="order">
        <tr>
            <td>${order.id}</td>
            <td>${order.name}</td>
            <td>${order.date}</td>
            <td><a href="/delete/${order.id}">Delete this file</a></td>
            <td><a href="/update/${order.id}">Update this file</a></td>
            <td></td>
        </tr>
    </c:forEach>
</table>
<a href="/add-new-order">Add new file</a>

<form action="/select">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <p>selecting files</p>
    <label>Id</label>
    <input type="text" name="id">
    <label>Name</label>
    <input type="text" name="title" >
    <label>Date</label>
    <input type="text" name="date" >
    <p><input type="submit" value="search"></p>
</form>

</body>
</html>