<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<form action="user/login" method="POST">
		<table>
			<tr>
				<td>账号</td>
				<td><input type="text" name="username" placeholder="账号" /></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="password" name="password" placeholder="密码" />
				</td>
			</tr>
			<tr>
				<td>记住我</td>
				<td><input type="checkbox" name="remberMe" value="1" />
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="立即登陆" /></td>
			</tr>
		</table>
	</form>
</body>
</html>