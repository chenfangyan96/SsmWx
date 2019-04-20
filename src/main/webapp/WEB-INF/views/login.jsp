
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="common/jstl.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${ctx}/resource/css/login.css">
	<script type="text/javascript" src="${ctx}/resource/js/jquery.min.js"></script>
	<title>后台管理平台</title>
</head>
<body>
<div id="login_center">
	<div id="login_area">
		<div id="login_form">
			<form action="/test/login" method="post">
				<div id="login_tip">
					用户登录&nbsp;&nbsp;UserLogin
				</div>
				<div><input type="text" name="username" class="username" value="" maxlength="20" required="required" placeholder="请输入账号"></div>
				<div><input type="password" name="password" class="pwd" value="" maxlength="20" required="required" placeholder="请输入密码"></div>
				<div>
					<input type="text" name="kaptcha" id="kaptcha" class="kaptcha" maxlength="4" required="required" placeholder="请输入验证码">
					&nbsp;
					<img src="/test/captcha" width="150px;" height="50px;" id="kaptchaImage">
				</div>
				<div id="btn_area">
					<input type="submit" name="submit" id="sub_btn" value="登&nbsp;&nbsp;&nbsp;&nbsp;录" onclick="yanzheng();return false;">&nbsp;&nbsp;
					<span style="color: red;">${msg }</span>
				</div>
			</form>
		</div>
	</div>
</div>
<div id="login_bottom">
	版权所有 Copyright &copy; 2018-2019 Powered by Cfy
</div>
</body>
<script type="text/javascript">
	$(function(){
		$('#kaptchaImage').click(function () {
			$(this).attr('src', '/test/captcha');
		})
	});
</script>
</html>