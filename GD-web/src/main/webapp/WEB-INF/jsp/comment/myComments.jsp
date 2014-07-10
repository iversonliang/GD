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
			<li><a href="/comment/replyToMe.do">给我的回复</a></li>
			<li class="active"><a href="javascript:void(0);">我的评论</a></li>
		</ul>
		<div class="content bg_w">
			<div class="reg_title yy-icon">您发表过 <em>${pager.totalCount }</em> 条评论。</div>
			<c:forEach items="${commentVoList}" var="comment">
				<div class="uitem">
					<div class="avatar"><a href="#"><img src="${comment.headImg }" width="50" height="50" /></a></div>
					<div class="msgcont">
						<div class="msg_title">
							<c:choose>
								<c:when test="${comment.videoSourceType == 1 }"><h3>我评论了原创作品《<a href="/video/video.do?vid=${comment.videoId }&st=2" target="_blank">${comment.videoName }</a>》：</h3></c:when>
								<c:otherwise><h3>我评论了原创作品《<a href="/video/video.do?vid=${comment.videoId }&st=3" target="_blank">${comment.videoName }</a>》：</h3></c:otherwise>
							</c:choose>
							<span class="msg_time"><fmt:formatDate value="${comment.posttime }" pattern="yyyy-MM-dd hh:mm:ss"/></span>
							<div class="clear"></div>
						</div>
						<p>${comment.content }</p>
						<div class="msg_sub">
							<c:choose>
								<c:when test="${comment.videoSourceType == 1 }"><a target="_blank" href="/video/video.do?vid=${comment.videoId }&st=2">查看</a></c:when>
								<c:otherwise><a target="_blank" href="/video/video.do?vid=${comment.videoId }&st=3">查看</a></c:otherwise>
							</c:choose>
						</div>
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
