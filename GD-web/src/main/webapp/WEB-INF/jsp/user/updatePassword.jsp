<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改密码 - 优舞网</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<%@include file="/WEB-INF/jsp/js.inc.jsp"%>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
<div id="wrapper">
	<div class="main">
		<ul class="tab">
			<li><a href="/user/userInfo.do">基本资料</a></li>
			<li><a href="/user/headImg.do">修改头像</a></li>
			<li  class="active"><a href="/user/updatePassword.do">修改密码</a></li>
		</ul>
		<div class="content bg_w">
			<div class="reg_title yy-icon">密码要注意保管哦<!-- 如遇到困难，请 <a href="#" target="_blank">联系我们</a> --></div>
			<div class="contBody">
				<form method="post">
					<table width="100%" cellspacing="0" cellpadding="0" class="norTable">
						<tbody>
						<tr>
							<th width="100">原始密码</th>
							<td>
								<input type="password" onblur="User.checkPassword()" onfocus="User.hideTips()" class="newTxt w300" tabindex="1" name="password" id="password">
								<span class="onShow" style="margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;">
									<span id="passwordTip" style="display:none" name="tips" class="txtMsg alert f12 ml1"></span>
								</span>
							</td>
						</tr>
						<tr>
							<th>新密码</th>
							<td><input type="password" onblur="User.checkNewPassword()" onfocus="User.hideTips()" class="newTxt w300" tabindex="1" name="newPassword" id="newPassword">
								<span class="onShow" style="margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;">
									<span id="newPasswordTip" style="display:none" name="tips" class="txtMsg alert f12 ml1"></span>
								</span></td>
						</tr>
						<tr>
							<th>确认密码</th>
							<td>
								<input type="password" onblur="User.checkReNewPassword()" onfocus="User.hideTips()" class="newTxt w300" tabindex="1" name="reNewPassword" id="reNewPassword">
								<span class="onShow" style="margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;">
									<span id="reNewPasswordTip" style="display:none" name="tips" class="txtMsg alert f12 ml1"></span>
								</span>
							</td>
						</tr>
						<tr>
							<th></th>
							<td class="vm"><input type="button" onclick="User.resetPassword()" tabindex="3" value="重置密码" class="lBtn"></td>
						</tr>
					</tbody></table>
				</form>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

</body>
</html>
