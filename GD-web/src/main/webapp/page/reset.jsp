<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>重置密码 - 优舞网</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<%@include file="/WEB-INF/jsp/js.inc.jsp"%>
<%@include file="/WEB-INF/jsp/taglib.inc.jsp"%>
<script type="text/javascript"> 
	$(function() {
		var code = Common.getUrlParam("code");
		$("#code").val(code);
	});

	function reset() {
		if (!checkPassword()) {
			return;
		}
		if (!checkRepassword()) {
			return;
		}
		var code = $("#code").val();
		var password = $("#password").val();
		var url = "/user/resetForgetPassword.do?code=" + code + "&password=" + password;
		AjaxJson.get(url).done(function(data) {
			if (data.result == true) {
				alert("修改密码成功，请重新登录");
				window.location.href = "/page/login.jsp";
			} else {
				alert(data.message);
			}
		});
	}

	function checkPassword() {
		var password = $("#password").val();
		if (Common.isEmpty(password)) {
			alert("请输入密码");
			return false;
		}
		var pattern = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
		var reg = new RegExp(pattern);
		if (!password.match(reg)) {
			alert("用户密码必须是数字和字母组合，在6-16位以内");
			return false;
		}
		return true;
	}
	
	function checkRepassword() {
		var repassword = $("#repassword").val();
		if (Common.isEmpty(repassword)) {
			alert("请再次输入密码确认");
			return false;
		}
		var password = $("#password").val();
		if (password != repassword) {
			alert("您两次输入的密码不一致，请检查");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
<div id="wrapper">
	<div class="main">
		<ul class="tab">
			<li class="active"><a href="/page/forget.jsp">重置密码</a></li>
		</ul>
		<div class="content bg_w">
			<div class="reg_title yy-icon">请输入密码，记得要好好保管密码喔</div>
			<div class="contBody">
					<table width="100%" cellspacing="0" cellpadding="0" class="norTable">
						<tbody>
						<tr>
							<th width="100">重置密码</th>
							<td><input type="password" class="newTxt w300" tabindex="1" name="password" id="password"><span class="onShow" id="passwordTip" style="position: absolute; left: 424px; margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;"></span></td>
						</tr>
						<tr>
							<th>确认密码</th>
							<td><input type="password" class="newTxt w300" tabindex="2" name="repassword" id="repassword"><span class="onShow" style="position: absolute; left: 424px; margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;" id="repasswordTip"></span></td>
						</tr>
						<tr>
							<th></th>
							<td class="vm"><input type="button" id="subutt" tabindex="3" value="重置" class="lBtn" onclick="reset()"></td>
						</tr>
					</tbody></table>
					<input id="code" type="hidden" />
			</div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
</body>
</html>