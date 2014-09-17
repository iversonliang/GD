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
		<div class="camZpBoxC">
			<dl>
				<dt>类型：</dt>
				<dd>
					<c:forEach items="${videoTypeList}" var="videoType">
						<a name="videoType" videoTypeId="${videoType.key }" href="javascript:Video.search('videoType', '${videoType.key }')" <c:if test="${param.videoType == videoType.key || (empty param.videoType && videoType.key == 0)}">class="selected"</c:if> >${videoType.desc }</a>
					</c:forEach>
				</dd>
			</dl>
			<dl>
				<dt>等级：</dt>
				<dd>
					<c:forEach items="${videoGradeTypeList}" var="videoGradeType">
						<a name="videoGradeType" videoGradeTypeId="${videoGradeType.key }" href="javascript:Video.search('videoGradeType', '${videoGradeType.key }')" <c:if test="${param.videoGradeType == videoGradeType.key  || (empty param.videoType && videoGradeType.key == 0)}">class="selected"</c:if> >${videoGradeType.desc }</a>
					</c:forEach>
				</dd>
			</dl>
			<dl>
				<dt>排序：</dt>
				<dd>
					<c:forEach items="${sortTypeList}" var="sortType">
						<a name="sortType" sortTypeId="${sortType.key }" href="javascript:Video.search('sortType', '${sortType.key }')" <c:if test="${param.sortType == sortType.key  || (empty param.videoType && sortType.key == 0) }">class="selected"</c:if> >${sortType.desc }</a>
					</c:forEach>
				</dd>
			</dl>
			<dl>
				<dt>时间：</dt>
				<dd>
					<c:forEach items="${timeLimitTypeList}" var="timeLimitType">
						<a name="timeLimitType" timeLimitTypeId="${timeLimitType.key }" href="javascript:Video.search('timeLimitType', '${timeLimitType.key }')" <c:if test="${param.timeLimitType == timeLimitType.key || (empty param.videoType && timeLimitType.key == 0)}">class="selected"</c:if> >${timeLimitType.desc }</a>
					</c:forEach>
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
						<div class="avatar"><a href="/video/personal.do?userId=${video.userId }"><img src="${video.headImg }" width="32" height="32" />${video.nickname }</a></div>
						<c:if test="${video.videoSourceType == 1 }">
							<span>[<a href="/opus.do?videoType=${video.videoType }&sortType=0&videoGradeType=${video.videoGradeType }&timeLimitType=0">作品</a>]</span>
						</c:if>
						<c:if test="${video.videoSourceType == 2 }">
							<span>[<a href="/inspiration.do?videoType=${video.videoType }&sortType=0&videoGradeType=${video.videoGradeType }&timeLimitType=0">灵感</a>]</span>
						</c:if>
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
