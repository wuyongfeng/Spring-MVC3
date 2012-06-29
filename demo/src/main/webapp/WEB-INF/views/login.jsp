<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form"  uri="http://www.springframework.org/tags/form"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="js/jquery.js"></script>
<title>欢迎登陆</title>
<style type="text/css">
body{width:100%; margin:0px;}
.header-logo{ background:#063263; height:115px;}
.main{ background:url(img/main-bg.png) repeat-x; height:409px;}
.main-table{ background:url(img/main-bg1.png) no-repeat center; height:409px; padding-top:47px;}
.main-kuang td{ font-size:12px; line-height:40px; padding-right:10px;}
.bottom{background:url(img/bottom.png) repeat-x; height:50px; text-align:center; font-size:12px; font-family:Arial, Helvetica, sans-serif}
.main-title-l{background:url(img/title-left.png) no-repeat; height:43px; width:47px;}
.main-title-m{background:url(img/title-mid.png) repeat-x; height:43px; font-size:12px; color:#FFFFFF; line-height:43px;}
.main-title-r{background:url(img/title-right.png) no-repeat; height:43px; width:11px;}
.main-table-td01{background:#f3f8fb; height:200px; border-right:1px solid #000; border-left:1px solid #000;}
.Sign-l{background:url(img/main-btm-l.png) no-repeat; height:76px; width:20px;}
.Sign-m{background:url(img/main-btm-m.png) repeat-x; height:76px;}
.Sign-r{background:url(img/main-btm-r.png) no-repeat; height:66px; width:252px; font-size:30px; padding-left:60px; padding-bottom:10px; font-family:'微软雅黑'; text-align:center; color:#FFFFFF;cursor:pointer;}
a{ color:#FFFFFF; text-decoration:none;}
</style>
</head>

<body>
<form:form class = "loginForm" modelAttribute="user" action="loginto" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td class="header-logo"><img src="img/login.png" /></td>
  </tr>
  <tr>
    <td class="main">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="main-table">
		  <tr>
		  	<td>&nbsp;</td>
			<td width="600" height="43">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<td class="main-title-l" height="43">&nbsp;</td>
					<td class="main-title-m">
						请输入您的用户名和密码进行登录
					</td>
					<td id="regit" class="main-title-m" style="cursor:pointer">
						注册
					</td>
					<td id="setpsw" class="main-title-m" style="cursor:pointer">
						忘记密码
					</td>
					<td class="main-title-r">&nbsp;</td>
				  </tr>
				</table>
			</td>
			<td>&nbsp;</td>
		  </tr>
		  <tr>
			<td>&nbsp;</td>
			<td class="main-table-td01">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="main-kuang" style="padding-left:100px;">
				  <tr>
					<td align="right">用户名</td>
					<td><form:input path="name"   style="width:260px; height:25px; border-radius: 5px;"/></td>
				  </tr>
				  <tr>
					<td align="right">密码</td>
					<td><form:password path="password" style="width:260px; height:25px; border-radius: 5px;"/></td>
				  </tr>
				  <tr id="regitinfo">
					<td align="right">重复密码</td>
					<td><input id="repassword" type="password" style="width:260px; height:25px; border-radius: 5px;"/></td>
				  </tr>
				</table>
			</td>
			<td>&nbsp;</td>
		  </tr>
		  <tr>
			<td>&nbsp;</td>
			<td width="600">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<td class="Sign-l" height="43">&nbsp;</td>
					<td class="Sign-m">&nbsp;</td>
					<td class="Sign-r">登陆</td>
				  </tr>
				</table>
			</td>
			<td style="background:url(img/main-btm-r-r.png) no-repeat; height:76px;">&nbsp;</td>
		  </tr>
		  <tr>
			<td>&nbsp;</td>
			<td valign="top"><img src="img/main-bottom.png" /></td>
			<td>&nbsp;</td>
		  </tr>
		</table>
		</form:form>
	</td>
  </tr>
  <tr>
    <td class="bottom">Copyright &copy  1998-2012, Oracle. All rights reserved. </td>
  </tr>
</table>
</body>
<script>
	$(document).ready(function() {
		$("#name").focus();
		$("tr#regitinfo").hide();
		});
	$("td.Sign-r").click(function(){
		if(validate ())
			$("form").submit();
	})
	$("td#regit").click(function(){
		if(	$("form").attr("action") == "loginto"){
			$("tr#regitinfo").show("normal",function(){
				$("td.Sign-r").html("注册");
				$("form").attr("action","regit");
				$("td#regit").html("登陆");
				
			});
		}else{
			$("tr#regitinfo").hide("normal",function(){
				$("td.Sign-r").html("登陆");
				$("form").attr("action","loginto");
				$("td#regit").html("注册");
				
			});
		}
	})
	function validate (){
		if($("#name").attr("value")==""){
			alert("用户名不能为空！");
			return false;
		}
		if(	$("form").attr("action") == "regit"){
			var psw = $("#password").attr("value");
			var repsw = $("#repassword").attr("value");
			if(psw != repsw){
				alert("两次密码不一致！");
				return false;
			}
		}
		return true;
		
	}
</script>
</html>
