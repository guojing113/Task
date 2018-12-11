<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style1.css"/>

    <title>上传图片</title>
    <%--下面得到的是项目名，base意思就是在路径前加上href的信息--%>
    <base href="${pageContext.request.contextPath}/">
</head>
<body>

<form action="osspicture" method="post" enctype="multipart/form-data">
    <%--这里从session中获取用户名--%>
    <input type="hidden" name="name" value="${sessionScope.name}"/>
    图片： <input type="file" name="pictureFile"/><br>
    <input type="submit" value="提交">
</form>
</body>
</html>