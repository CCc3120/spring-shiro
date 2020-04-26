<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<base href="<%=basePath%>">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h5>List Page.</h5>
Welcome:<shiro:principal></shiro:principal>
<br/>

<a href="user/logout">退出</a>
<br/>
<shiro:hasRole name="admin">
<a href="admin.jsp">admin.jsp</a>
<br/>
</shiro:hasRole>
<shiro:hasRole name="user">
<a href="User.jsp">User.jspa</a>
<br/>
</shiro:hasRole>
<a href="user/testAnnotation">Annotation</a>
</body>
</html>