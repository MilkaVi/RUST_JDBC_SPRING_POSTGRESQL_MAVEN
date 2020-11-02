<%@ page import="javax.swing.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org"
      xmlns:sec="https://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>Spring Security Example </title>
</head>


<form action="/login" method="post">
    <div><label> User Name : <input type="text" name="login" required/> </label></div>
    <div><label> Password: <input type="password" name="password" required/> </label></div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>

    <div><input type="submit" value="Sign In"/></div>
</form>
<a href="/registration">add new user</a>
</body>
</html>