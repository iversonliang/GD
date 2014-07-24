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
<div id="header">
	<div class="bannar">
		<div class="bannar_pic">
			<c:if test="${requestScope.isLogin == null || isLogin == false }">
				<a href="/page/register.jsp" class="btn">立即注册</a>
			</c:if>
		</div>
	</div>
</div>
<div id="wrapper">
	<div class="main">
		<h2>灵感作品</h2>
		<div class="camZpBoxC">
			<dl>
				<dt>类型：</dt>
				<dd>
					<c:forEach items="${videoTypeList}" var="videoType">
						<a name="videoType" videoTypeId="${videoType.key }" href="javascript:Video.search('videoType', '${videoType.key }')" <c:if test="${param.videoType == videoType.key}">class="selected"</c:if> >${videoType.desc }</a>
					</c:forEach>
				</dd>
			</dl>
			<dl>
				<dt>等级：</dt>
				<dd>
					<a href="javascript:void(0)" class="selected">全部</a>
					<a href="javascript:void(0)">编辑精选</a>
					<a href="javascript:void(0)">普通推荐</a>
				</dd>
			</dl>
			<dl>
				<dt>排序：</dt>
				<dd>
					<c:forEach items="${sortTypeList}" var="sortType">
					<a name="sortType" sortTypeId="${sortType.key }" href="javascript:Video.search('sortType', '${sortType.key }')" <c:if test="${param.sortType == sortType.key}">class="selected"</c:if> >${sortType.desc }</a>
					</c:forEach>
				</dd>
			</dl>
			<dl>
				<dt>时间：</dt>
				<dd>
					<a href="#" class="selected">不限</a>
					<a href="#">三天内</a>
					<a href="#">一周内</a>
					<a href="#">一个月内</a>
					<a href="#">一年个月内</a>
				</dd>
			</dl>
		</div>
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
		<!-- <div id="pages">
			<a href="#" id="pageactive">1</a>
			<a href="#">2</a>
			<a href="#">3</a>
			<a href="#">4</a>
			<a href="#">5</a>
			<span>...</span>
			<a href="#">87</a>
			<a href="#" id="pagenext"><span class="yy-icon pagenext"></span></a></div>
		</div> -->
		<jsp:include page="/WEB-INF/jsp/include/pager.jsp"></jsp:include>
</div>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

</body>
</html>
