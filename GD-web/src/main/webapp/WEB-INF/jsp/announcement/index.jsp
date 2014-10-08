<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统公告 - 优舞网</title>
<%@include file="/WEB-INF/jsp/taglib.inc.jsp"%>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<%@include file="/WEB-INF/jsp/js.inc.jsp"%>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
<div id="wrapper">
	<div class="main">
		<ul class="tab">
			<li><a href="/notice/index.do">系统消息</a></li>
			<li class="active"><a href="/announcement/index.do">优舞公告</a></li>
		</ul>
		<div class="content bg_w">
			<div class="reg_title yy-icon">你共收到了 <em>${pager.totalCount }</em> 条优舞公告，请注意查看哦。<!-- 如遇到问题，请 <a href="#">在线提交</a> 给我们。 --></div>
			<c:forEach items="${announcementList}" var="announcement">
				<div class="uitem">
					<div class="avatar"><a href="#"><img src="${announcement.imgUrl }" width="50" height="50" /></a></div>
					<div class="msgcont">
						<div class="msg_title">
							<h3>${announcement.title }</h3>
							<span class="msg_time"><fmt:formatDate value="${announcement.posttime }" pattern="yyyy-MM-dd HH:mm"/></span>
							<div class="clear"></div>
						</div>
						<p class="fs_12">${announcement.content }</p>
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
