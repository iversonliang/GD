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

<script type="text/javascript">
$(function() {
	Slide.init();
});

</script>

</head>

<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
<%-- <c:if test="${isLogin == false }">
<div id="header">
	<div class="bannar">
		<div class="bannar_pic">
			<c:if test="${requestScope.isLogin == null || isLogin == false }">
				<a href="/page/register.jsp" class="btn">立即注册</a>
			</c:if>
		</div>
	</div>
</div>
</c:if> --%>

<div class="recommend">
	<div id="focus">
		<ul>
			<c:forEach items="${slideList}" var="ad">
				<li><a href="${ad.url }" target="_blank"><img src="${ad.imgUrl }" alt="" /></a></li>
			</c:forEach>
		</ul>
	</div>
	<div class="r_side">
		<div class="r_ad"><a href="#" target="_blank"><img src="/images/ad/310-110.jpg" /></a></div>
		<div class="r_ad"><a href="#" target="_blank"><img src="/images/ad/ad01.jpg" /></a></div>
		<div class="r_links">
			<span><a href="#" target="_blank"><b>·</b> 第三季的《中国好声音》开播了。</a></span>
			<span><a href="#" target="_blank"><b>·</b> 那些好听的英文歌</a></span>
		</div>
	</div>
</div>

<div id="wrapper">
	<h2>推荐视频</h2>
	<div class="main">
		<div class="content">
			<c:forEach items="${videoVoList}" var="video">
				<div class="citem">
					<div class="cover">
						<c:choose>
							<c:when test="${video.videoSourceType == 1 }"><a target="_blank" href="/video/video.do?vid=${video.videoId }&st=2"></c:when>
							<c:otherwise><a target="_blank" href="/video/video.do?vid=${video.videoId }&st=3"></c:otherwise>
						</c:choose>
						<img src="${video.imgUrl }" />
						<div class="play_button"></div>
						</a>
					</div>
					<div class="citemtxt">
						<c:choose>
							<c:when test="${video.videoSourceType == 1 }"><a class="citemtitle" target="_blank" href="/video/video.do?vid=${video.videoId }&st=2" title="${video.name }">${video.name }</a></c:when>
							<c:otherwise><a class="citemtitle" target="_blank" href="/video/video.do?vid=${video.videoId }&st=3" title="${video.name }">${video.name }</a></c:otherwise>
						</c:choose>
					</div>
					<div class="itemInfo">
						<span class="yy-icon time">${video.deployTimeTips }</span>
						<a href="#" class="yy-icon comment"><span>${video.love }</span></a>
						<a href="#" class="yy-icon like"><span>${video.comments }</span></a>
						<a href="#" class="yy-icon views"><span>${video.play }</span></a>
					</div>
					<div class="user">
						<div class="avatar"><a href="/user/personal.do?userId=${video.userId }"><img src="${video.headImg }" width="32" height="32" />${video.nickname }</a></div>
						<span>( <a href="#">作品</a> )</span>
					</div>
				</div>
			</c:forEach>
		</div>
		<jsp:include page="/WEB-INF/jsp/include/pager.jsp"></jsp:include>
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

</body>
</html>
