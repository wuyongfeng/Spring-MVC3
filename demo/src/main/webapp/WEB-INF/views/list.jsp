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
			{display: 'ISO', name : 'iso', width : 40, sortable : true, align: 'center'},
			{display: 'Name', name : 'name', width : 180, sortable : true, align: 'left'},
			{display: 'Printable Name', name : 'printable_name', width : 120, sortable : true, align: 'left'},
			{display: 'ISO3', name : 'iso3', width : 130, sortable : true, align: 'left', hide: true},
			{display: 'Number Code', name : 'numcode', width : 80, sortable : true, align: 'right'}
			],
		searchitems : [
			{display: 'ISO', name : 'iso'},
			{display: 'Name', name : 'name', isdefault: true}
			],
		sortname: "iso",
		sortorder: "asc",
		usepager: true,
		title: 'Countries',
		useRp: true,
		rp: 15,
		showTableToggleBtn: true,
		width: 700,
		height: 200
	});   
})

</script>
</html>