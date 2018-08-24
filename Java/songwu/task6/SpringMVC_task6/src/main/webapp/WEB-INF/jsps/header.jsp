<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2018/6/25
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
<head>
    <link rel="stylesheet" href="../css/header.css">
    <title>页眉</title>
</head>
<body>
<header>
    <div class="container">
        <div class="row">
            <div class="col-xs-6 hidden-xs">
                <span class="">客服热线：<a href="tel:010-594-78634">010-594-78634</a></span>
            </div>
            <a href=""><img src="../images/wechat.png" alt=""></a>
            <a href=""><img src="../images/qq.png" alt=""></a>
            <a href=""><img src="../images/sina.png" alt=""></a>
        </div>
    </div>
</header>
<!--content-->
<nav class="navbar navbar-default" role="navigation">
    <div class="container">
        <div class="row">
            <div class="navbar-header">
                <a class="navbar-brand" href=""><img src="../images/home-logo.png" alt="" class="img-re"></a>
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#navbar-collapse">
                    <i class="glyphicon glyphicon-menu-hamburger"></i>
                </button>
            </div> <!-- navbar-header -->
            <div class="collapse navbar-collapse navbar-right" id="navbar-collapse">
                <ul class="nav navbar-nav navbar-right text-center">
                    <li><a href="${pageContext.request.contextPath}/main" >首&nbsp;页</a></li>
                    <li><a href="${pageContext.request.contextPath}/u/profession">职&nbsp;业</a></li>
                    <li><a href="${pageContext.request.contextPath}/u/recommend">推&nbsp;荐</a></li>
                    <li><a href="${pageContext.request.contextPath}/logout">登&nbsp;录</a></li>
                    <li><a href="${pageContext.request.contextPath}/off">注&nbsp;销</a></li>
                </ul>
            </div>
        </div>  <!-- nav-row -->
    </div>  <!-- nav-container -->
</nav>
</body>
</html>