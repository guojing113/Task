<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>Cookie</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
</head>

<body>
    <h1>用户登陆</h1>
    <hr>
    <form action="<%=basePath%>/dologin" method="post" name="loginFrom">
        <table>
            <tr>
                <td>用户名:</td>
                <td><input type="text" name="name" value="" /></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input type="password" name="passwordString" value="" /></td>
            </tr>

            <tr>
                <td  colspan="2" align="center"><input type="submit" value="登陆" /></td>
            </tr>

        </table>
    </form>
</body>
</html>