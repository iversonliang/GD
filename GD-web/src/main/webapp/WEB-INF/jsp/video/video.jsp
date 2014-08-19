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
<div class="wrapper single">
	<div class="main">
		<div class="articleInfo">
			<div class="author">
				<div class="avatar"><a href="/video/personal.do?userId=${video.userId }" target="_blank"><img src="${user.headImg }" width="50" height="50" /></a></div>
				<div class="userInfo">
					<h2>${video.name }</h2>
					<p><a href="/video/personal.do?userId=${video.userId }" target="_blank">${video.nickname }</a> <c:if test="${video.videoSourceType == 1 }">原创</c:if><c:if test="${video.videoSourceType == 2 }">转载</c:if>作品 上传于 <fmt:formatDate value="${video.posttime }" pattern="yyyy-MM-dd" /></p>
				</div>
			</div>
			<div class="articledata">
				<p class="hbzl-s4">人气：<span class="hbzl-s4t hbzl-s4tb">${video.play }</span></p>
			</div>
			<div class="clear"></div>
		</div>
		<div class="playerarea">
			<div class="player">
				<c:if test="${video.sourceSiteType == 1 }">
					<%-- <embed src="${video.playUrl }" allowFullScreen="true" quality="high" width="940" height="588" align="middle" allowScriptAccess="always" type="application/x-shockwave-flash"></embed> --%>
					<embed src="${video.playUrl }" quality="high" width="940" height="588" align="middle" allowScriptAccess="never" allowNetworking="internal" allowfullscreen="true" autostart="0" type="application/x-shockwave-flash"></embed>
				</c:if>
			</div>
			<div class="videoCont">
				<p>${video.description }</p>
			</div>
			<c:if test="${!isLiked }">
				<div class="plike" id="likeButton">
					<a href="javascript:Video.love(${video.videoId })"><span class="plikew"></span>喜欢(0)</a>
				</div>
			</c:if>
		</div>
		<div class="authorMore">
			<div class="uitem">
				<div class="avatar"><a href="/video/personal.do?userId=${video.userId }" target="_blank"><img src="${user.headImg }" width="120" height="120" /></a></div>
				<div class="userInfo">
					<div class="atPerson">
						<div class="vm">
							<b>
							<a href="/video/personal.do?userId=${video.userId }" class="c4095ce f14" target="_blank">${video.nickname }</a>
							</b>
						</div>
						<c:if test="${user.sex == 0 }">女</c:if><c:if test="${user.sex == 1 }">男</c:if> <span class="c999">/</span> 欧洲 <span class="c999">/</span> 街舞　<br>
						<div class="c999">
							<p class="atPersonDes">${user.sign }</p>
							<!-- 粉丝：<a href="#" target="_blank">4787</a>&nbsp;&nbsp;&nbsp;&nbsp; -->
							作品数：<a href="javascript:void(0)" style="cursor:default">${user.videoCount }</a>&nbsp;&nbsp;&nbsp;&nbsp;
							人气：<a href="javascript:void(0)" style="cursor:default">545645</a>
						</div>
						<!-- <div class="userList_cz">
							<a href="#" class="btnfollow">加关注</a>
						</div> -->
					</div>
				</div>
				<div class="atImg">
					<c:forEach items="${userVideoList}" var="video">
						<a href="/video/video.do?vid=${video.videoId }&st=2"><img src="${video.imgUrl }" width="120" height="75"></a>
					</c:forEach>
					<a href="/video/personal.do?userId=${video.userId }" target="_blank" class="more">更多</a>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<div class="s_comment">
			<div class="sc_box">
				<h3 class="sc_title">评论(<span id="commentCount">${video.comments }</span>)</h3>
				<div class="sc_textbox">
					<form method="get">
						<textarea class="normalArea" id="commentContent"></textarea>
						<div class="applybtn"><a href="javascript:Comment.add(${video.videoId })" class="btnfollow">发 布</a></div>
					</form>
				</div>
			</div>
			<div class="sc_list">
				<div id="commentArea">
					<jsp:include page="/WEB-INF/jsp/comment/loadComment.jsp"></jsp:include>
				</div>
				<div class="more">
					<a href="#">更多</a>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
</body>
</html>
