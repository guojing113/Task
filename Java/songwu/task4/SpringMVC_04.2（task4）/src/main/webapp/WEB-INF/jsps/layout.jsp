<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2018/6/25
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://code.jquery.com/jquery-3.2.1.slim.min.js">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>Title</title>
</head>

<body>
<div id="header">
    <tiles:insertAttribute name="header" />
</div>
<div id="main">
    <tiles:insertAttribute name="main" />
</div>
<div id="recommend">
    <tiles:insertAttribute name="recommend" />
</div>
<div id="profession">
    <tiles:insertAttribute name="profession"/>
</div>
<div id="footer">
    <tiles:insertAttribute name="footer" />
</div>
</body>
</html>