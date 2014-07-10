<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>优舞网</title>
<%@include file="/WEB-INF/jsp/taglib.inc.jsp"%>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<%@include file="/WEB-INF/jsp/js.inc.jsp"%>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
<div id="wrapper">
	<div class="main">
		<ul class="tab">
			<li class="active"><a href="javascript:void(0);">给我的评论</a></li>
			<li><a href="/comment/replyToMe.do">给我的回复</a></li>
			<li><a href="/comment/myComments.do">我的评论</a></li>
		</ul>
		<div class="content bg_w">
			<div class="reg_title yy-icon">您已经收到 <em>27</em> 条评论了，要记得及时回复他们哦。</div>
			<div class="uitem">
				<div class="avatar"><a href="#"><img src="images/avatar.png" width="50" height="50" /></a></div>
				<div class="msgcont">
					<div class="msg_title">
						<h3><a href="#" target="_blank">张建鹏</a> 评论了我的原创作品《<a href="#" target="_blank">JC舞蹈训练营 羽凡 编舞作品 Bottlez</a>》：</h3>
						<span class="msg_time">2014-04-24 7:31</span>
						<div class="clear"></div>
					</div>
					<p>落地的时候可以试试坑碰一下，然后发动的节奏加快点。</p>
					<div class="msg_sub"><a href="#">回复</a></div>
				</div>
				<div class="clear"></div>
			</div>
			<div class="uitem">
				<div class="avatar"><a href="#"><img src="images/avatar.png" width="50" height="50" /></a></div>
				<div class="msgcont">
					<div class="msg_title">
						<h3><a href="#" target="_blank">张建鹏</a> 评论了我的原创作品《<a href="#" target="_blank">JC舞蹈训练营 羽凡 编舞作品 Bottlez</a>》：</h3>
						<span class="msg_time">2014-04-24 7:31</span>
						<div class="clear"></div>
					</div>
					<p>落地的时候可以试试坑碰一下，然后发动的节奏加快点。</p>
					<div class="msg_sub"><a href="#">回复</a></div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/jsp/include/pager.jsp"></jsp:include>
</div>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

</body>
</html>
