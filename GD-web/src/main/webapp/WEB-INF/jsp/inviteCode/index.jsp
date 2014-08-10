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
			<h2>优舞网内测中</h2>
			<p>是不是看到别人的作品技痒了呢？是不是有很多作品不知道往哪里放？<br />优舞只为更好的展示你，立即激活，成为优舞者吧！</p>
			<div class="ihave">	
				<div class="active">
					<form id="form" method="get" action="/inviteCode/activate.do">
						<input name="inviteCode" type="text" value="" placeholder="在这里输入你的激活码" autocomplete="off" class="activeInput">
						<a href="javascript:InviteCode.activate()" class="btn_sumit">提交</a>
					</form>
				</div>
			</div>
			<div class="apply">	
				<h3>没有激活码?</h3>
				<p>你那么优秀，爸妈都知道我们竟然不知道？一定是我们的问题！<br />填写资料让我们认识你吧！足够优秀我们会给你发送激活码。</p>
				<div class="email">
						<a href="/inviteCode/apply.do" class="btn_apply">申请激活码</a>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

</body>
</html>
