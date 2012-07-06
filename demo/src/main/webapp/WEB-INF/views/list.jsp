<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/flexigrid.pack.js"></script>
<link rel="stylesheet" type="text/css" href="css/flexigrid.pack.css" />
<title>用户列表</title>
</head>
<body>
<c:forEach items="${userList}" var="user">
${user.name}<br>
</c:forEach>
<table id="flex1" ></table>
</body>
<script>
$(document).ready(function(){
	$("#flex1").flexigrid({
		method:'GET',
		url: 'jsonList',
		dataType: 'json',
		colModel : [
			{display: '用户ID', name : 'id', width : 100, sortable : true, align: 'center'},
			{display: '注册时间', name : 'date', width : 130, sortable : true, align: 'left'},
			{display: '用户名', name : 'name', width : 180, sortable : true, align: 'left'},
			{display: '密码', name : 'password', width : 120, sortable : true, align: 'left'}
			],
		buttons : [
		   		{name: 'Add', bclass: 'add', onpress : test},
		   		{name: 'Delete', bclass: 'delete', onpress : test},
		   		{separator: true}
		   		],
		searchitems : [
			{display: '用户名', name : 'name', isdefault: true}
			],
		usepager: true,
		title: '用户列表',
		useRp: true,
		rp: 15,
		showTableToggleBtn: true,
		width: 700,
		height: 200
	});   
})
function test(com, grid) {
			if (com == 'Delete') {
				var gridp = grid;
				confirm('Delete ' + $('.trSelected', grid).length + ' items?')
			} else if (com == 'Add') {
				alert('Add New Item');
			}
		}
</script>
</html>