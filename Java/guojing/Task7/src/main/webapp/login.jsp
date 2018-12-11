<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<h1>登录</h1>
<form action="/student/login" name="student" method="POST">
    手机:<input type="text" name="tel" value="${student.tel}"><br>
    密码:<input type="text" name="password" value="${student.password}"><br>
    <input type="submit" value="登录"><br>
</form>
</body>
</html>
