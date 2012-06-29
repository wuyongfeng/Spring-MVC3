<%@page import="java.io.PrintWriter"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<title>异常提示页面</title>
</head>
<body>
<h1>
	出错了!  
</h1>

<% Exception ex = (Exception)request.getAttribute("exception");%>
<% ex.printStackTrace(new java.io.PrintWriter(out)); %>
</body>
</html>
