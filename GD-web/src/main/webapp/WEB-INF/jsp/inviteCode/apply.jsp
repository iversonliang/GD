<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>优舞网</title>
<%@include file="/WEB-INF/jsp/taglib.inc.jsp"%>
<link rel="stylesheet" type="text/css" href="/css/invitation.css">
<%@include file="/WEB-INF/jsp/js.inc.jsp"%>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
<div class="invitation">
	<div class="main">
		<div class="content">
			<h2>申请激活码</h2>
			<p>填写以下基本信息，我们会尽快审核，以邮件的形式发送。</p>
			<p style="display:none;color:red">请输入正确的邮箱地址</p>
			<form id="form" method="get" action="/inviteCode/add.do">
				<input id="name" name="name" type="text" value="" placeholder="姓名（必填）" autocomplete="off" class="activeInput">
				<input id="email" name="email" type="text" value="" placeholder="邮箱地址（必填）" autocomplete="off" class="activeInput">
				<input id="location" name="location" type="text" value="" placeholder="来自哪里（必填）" autocomplete="off" class="activeInput">
				<input id="crew" name="crew" type="text" value="" placeholder="归属舞团" autocomplete="off" class="activeInput">
				<input id="oupsUrl" name="oupsUrl" type="text" value="" placeholder="作品地址" autocomplete="off" class="activeInput">
				<a href="javascript:InviteCode.submitApply()" class="btn_sumit">提交</a>
			</form>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

</body>
</html>
