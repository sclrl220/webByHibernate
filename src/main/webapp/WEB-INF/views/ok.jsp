<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
                      + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/jquery.js"></script>
</head>
<script type="text/javascript">
	$(function(){
	});
</script>
<body>
	<h1>这是一个页面缓存的练习</h1>
	<font style="color: green; font-weight: bold; font-size: 18px">${date}</font>
	<br>
	<br>
	<jsp:include page="/WEB-INF/views/ok1.jsp"></jsp:include>
</body>
</html>