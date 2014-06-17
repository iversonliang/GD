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
				<div class="avatar"><a href="#"><img src="${user.headImg }" width="50" height="50" /></a></div>
				<div class="userInfo">
					<h2>${video.name }</h2>
					<p><a href="#" target="_blank">${video.nickname }</a> <c:if test="${video.videoSourceType == 1 }">原创</c:if><c:if test="${video.videoSourceType == 2 }">转载</c:if>作品 上传于 <fmt:formatDate value="${video.posttime }" pattern="yyyy-MM-dd" /></p>
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
					<embed src="${video.playUrl }" allowFullScreen="true" quality="high" width="940" height="588" align="middle" allowScriptAccess="always" type="application/x-shockwave-flash"></embed>
				</c:if>
			</div>
			<div class="videoCont">
				<p>${video.description }</p>
			</div>
		</div>
		<div class="authorMore">
			<div class="uitem">
				<div class="avatar"><a href="#"><img src="${user.headImg }" width="120" height="120" /></a></div>
				<div class="userInfo">
					<div class="atPerson">
						<div class="vm">
							<b>
							<a href="http://shaozi.zcool.com.cn" class="c4095ce f14" target="_blank">${video.nickname }</a>
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
					<a href="#" target="_blank" class="more">更多</a>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<div class="s_comment">
			<div class="sc_box">
				<h3 class="sc_title">评论(0)</h3>
				<div class="sc_textbox">
					<form method="get">
						<textarea class="normalArea" id="commentContent"></textarea>
						<div class="applybtn"><a href="#" class="btnfollow">发 布</a></div>
					</form>
				</div>
			</div>
			<div class="sc_list">
				<div class="uitem">
					<div class="avatar"><a href="#"><img src="images/avatar.png" width="50" height="50"></a></div>
					<div class="msgcont">
						<div class="msg_title">
							<h3><a href="#" target="_blank">张建鹏</a> 1小时前：</h3>
							<div class="clear"></div>
						</div>
						<p>落地的时候可以试试坑碰一下，然后发动的节奏加快点。</p>
						<div class="toMyCom">
							<div class="toMyComTop"></div>
							<div class="mblContent"><a href="#" target="_blank">GD郭团辉</a>：赞喔！</div>
						</div>
						<div class="msg_sub"><a href="#">回复</a></div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="uitem">
					<div class="avatar"><a href="#"><img src="images/avatar_other.jpg" width="50" height="50"></a></div>
					<div class="msgcont">
						<div class="msg_title">
							<h3><a href="#" target="_blank">GD郭团辉</a> 1小时前：</h3>
							<div class="clear"></div>
						</div>
						<p>赞喔！</p>
						<div class="msg_sub"><a href="#">回复</a></div>
					</div>
					<div class="clear"></div>
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
