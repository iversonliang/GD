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
			<li><a href="/comment/toMyComments.do">给我的评论</a></li>
			<li class="active"><a href="javascript:void(0);">给我的回复</a></li>
			<li><a href="/comment/myComments.do">我的评论</a></li>
		</ul>
		<div class="content bg_w">
			<div class="reg_title yy-icon">您发表的评论共收到 <em>${pager.totalCount }</em> 条回复。</div>
			
			<c:forEach items="${commentVoList}" var="comment">
				<div class="uitem">
					<div class="avatar"><a target="_blank" href="/user/personal.do?userId=${comment.replyUserId }"><img src="${comment.headImg }" width="50" height="50" /></a></div>
					<div class="msgcont">
						<div class="msg_title">
							<h3><a href="/user/personal.do?userId=${comment.userId }" target="_blank">${comment.nickname }</a>
								<c:choose>
									<c:when test="${comment.videoSourceType == 1 }">评论了我的原创作品《<a href="/video/video.do?vid=${comment.videoId }&st=2" target="_blank">${comment.videoName }</a>》：</c:when>
									<c:otherwise>评论了我的原创作品《<a href="/video/video.do?vid=${comment.videoId }&st=3" target="_blank">${comment.videoName }</a>》：</c:otherwise>
								</c:choose>
							</h3>
							<span class="msg_time"><fmt:formatDate value="${comment.posttime }" pattern="yyyy-MM-dd hh:mm:ss"/></span>
							<div class="clear"></div>
						</div>
						<p>${comment.content }</p>
						<div class="toMyCom">
							<div class="toMyComTop"></div>
							<div class="mblContent">我说：${comment.replyContent }</div>
						</div>
						<div class="msg_sub"><a href="#">回复</a></div>
					</div>
					<div class="clear"></div>
				</div>
			</c:forEach>
		</div>
		<jsp:include page="/WEB-INF/jsp/include/pager.jsp"></jsp:include>
</div>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

</body>
</html>
