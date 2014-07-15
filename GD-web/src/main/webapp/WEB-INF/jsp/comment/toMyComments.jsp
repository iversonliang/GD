<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>优舞网</title>
<%@include file="/WEB-INF/jsp/taglib.inc.jsp"%>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<link rel="stylesheet" type="text/css" href="/css/media_queries.css">
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
			<div class="reg_title yy-icon">您已经收到 <em>${pager.totalCount }</em> 条评论了，要记得及时回复他们哦。</div>
			<c:forEach items="${commentVoList}" var="comment">
				<div class="uitem">
					<div class="avatar"><a target="_blank" href="/user/personal.do?userId=${comment.userId }"><img src="${comment.headImg }" width="50" height="50" /></a></div>
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
						<div class="msg_sub"><a href="javascript:Comment.showToMyCommentsBox('${comment.commentId }')">回复</a></div>
					</div>
					<div class="clear"></div>
					<div class="crbBox" style="display: none;" id="replyBox${comment.commentId }">
						<textarea class="commentArea" id="content${comment.commentId }" onkeyup="Comment.updateLength('${comment.commentId }')"></textarea>
						<div class="commentFunc">
							<p class="f12">您还可以输入 <span class="cf30 abc" id="leftCount${comment.commentId }">200</span> 个字符</p>
						</div>
						<div class="">
							<a href="javascript:Comment.replyToMyComments('${comment.commentId }', '${comment.userId }', '${comment.nickname }', '${comment.content }')" class="commentConfirm">确认回复</a>
							<a href="javascript:Comment.hideToMyCommentsBox('${comment.commentId }')" class="commentCancel">取消回复</a>
						</div>
						<input type="hidden" id="comment${comment.commentId }videoId" value="${comment.videoId }"/>
					</div>
				</div>
			</c:forEach>
		</div>
		<jsp:include page="/WEB-INF/jsp/include/pager.jsp"></jsp:include>
</div>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

</body>
</html>
