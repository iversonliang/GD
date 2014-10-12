<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>舞者 - 优舞网</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<%@include file="/WEB-INF/jsp/taglib.inc.jsp"%>
<%@include file="/WEB-INF/jsp/js.inc.jsp"%>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
<div id="wrapper">
	<div class="main">
		<ul class="tab">
			<li <c:if test="${param.type == 0 || empty param.type}">class="active"</c:if> ><a href="/user/userList.do?type=0&st=5">推荐舞者</a></li>
			<li <c:if test="${param.type == 1}">class="active"</c:if> ><a href="/user/userList.do?type=1&st=5">活跃舞者</a></li>
		</ul>
		<div class="content bg_w">
			<c:forEach items="${userVoList}" var="user">
				<div class="uitem" >
					<div class="avatar"><a href="/video/personal.do?userId=${user.userId }"><img src="${user.headImg }" width="120" height="120" /></a></div>
					<div class="userInfo">
						<div class="atPerson">
							<div class="vm">
								<b>
								<a href="/video/personal.do?userId=${user.userId }" class="c4095ce f14" target="_blank"><c:if test="${empty user.nickname}">${user.username }</c:if><c:if test="${!empty user.nickname}">${user.nickname }</c:if></a>
								</b>
							</div>
							<c:if test="${user.sex == 0 }">女</c:if><c:if test="${user.sex == 1 }">男</c:if> <span class="c999">/</span> ${user.province} - ${user.city}<br>
							<div class="c999">
								<p class="atPersonDes">${user.sign }</p>
								<!-- 粉丝：<a href="#" target="_blank">4787</a>&nbsp;&nbsp;&nbsp;&nbsp; -->
								作品数：<a href="/video/personal.do?userId=${user.userId }" target="_blank">${user.videoCount }</a>&nbsp;&nbsp;&nbsp;&nbsp;
								<%-- 人气：<a href="/video/personal.do?userId=${user.userId }" target="_blank">545645</a> --%>
							</div>
							<!-- <div class="userList_cz">
								<a href="#" class="btnfollow">加关注</a>
							</div> -->
						</div>
					</div>
					<div class="atImg">
						<c:forEach items="${user.videoList}" var="video">
							<a href="/video/video.do?vid=${video.videoId }&st=2" target="_blank"><img src="${video.imgUrl }" width="120" height="75"></a>
						</c:forEach>
						<c:if test="${!empty user.videoList }">
							<a href="#" target="_blank" class="more">更多</a>
						</c:if>
					</div>
					<div class="clear"></div>
				</div>
			</c:forEach>
		</div>
		<jsp:include page="/WEB-INF/jsp/include/pager.jsp"></jsp:include>
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

</body>
</html>
