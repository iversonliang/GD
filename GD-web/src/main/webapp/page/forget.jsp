<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>忘记密码 - 优舞网</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<%@include file="/WEB-INF/jsp/js.inc.jsp"%>
<%@include file="/WEB-INF/jsp/taglib.inc.jsp"%>
<c:if test="${!empty username}">
<script type="text/javascript">
	window.location.href = "/index.do";
</script>
</c:if>
<script type="text/javascript"> 
    function checkEmail() {
        var email = $("#email").val();
        if (Common.isEmpty(email)) {
			alert("请输入邮箱地址");
			return;
        }
        var reemail = $("#reemail").val();
        if (Common.isEmpty(reemail)) {
			alert("请重复输入邮箱地址");
			return;
        }
        if (email != reemail) {
			alert("两次输入邮箱不一致，请重新输入");
			return;
        }
        var url = "/user/applyResetPassword.do?email=" + email;
        AjaxJson.get(url);
        alert("重置密码邮件已经发送，请进入注册邮箱进行下一步操作");
    }
</script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
<div id="wrapper">
	<div class="main">
		<ul class="tab">
			<li><a href="/page/register.jsp">注册</a></li>
			<li><a href="/page/login.jsp">登录</a></li>
			<li class="active"><a href="/page/forget.jsp">忘记密码</a></li>
		</ul>
		<div class="content bg_w">
			<div class="reg_title yy-icon">请使用注册邮箱找回密码，如遇到困难，请 联系我们 :)</div>
			<div class="contBody">
					<table width="100%" cellspacing="0" cellpadding="0" class="norTable">
						<tbody>
						<tr>
							<th width="100">注册邮箱</th>
							<td><input type="text" class="newTxt w300" tabindex="1" name="email" id="email"><span class="onShow" id="emailTip" style="position: absolute; left: 424px; margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;"></span></td>
						</tr>
						<tr>
							<th>重复邮箱</th>
							<td><input type="text" class="newTxt w300" tabindex="2" name="reemail" id="reemail"><span class="onShow" style="position: absolute; left: 424px; margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;" id="reemailTip"></span></td>
						</tr>
						<tr>
							<th></th>
							<td class="vm"><input type="input" id="subutt" tabindex="3" value="找回密码" class="lBtn" onclick="checkEmail()"></td>
						</tr>
					</tbody></table>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
</body>
</html>