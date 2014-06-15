<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>优舞网</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<%@include file="/WEB-INF/jsp/taglib.inc.jsp"%>
<%@include file="/WEB-INF/jsp/js.inc.jsp"%>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
<div id="wrapper">
	<div class="main">
		<ul class="tab">
			<li class="active"><a href="#">推荐舞者</a></li>
			<li><a href="#">活跃舞者</a></li>
		</ul>
		<div class="content bg_w">
			<c:forEach items="${list}" var="user">
				<div class="uitem" >
					<div class="avatar"><a href="#"><img src="${user.headImg }" width="120" height="120" /></a></div>
					<div class="userInfo">
						<div class="atPerson">
							<div class="vm">
								<b>
								<a href="http://shaozi.zcool.com.cn" class="c4095ce f14" target="_blank">${user.username }</a>
								</b>
							</div>
							<c:if test="${user.sex == 0 }">女</c:if><c:if test="${user.sex == 1 }">男</c:if> <span class="c999">/</span> 欧洲 <span class="c999">/</span> 街舞　<br>
							<div class="c999">
								<p class="atPersonDes">${user.sign }</p>
								粉丝：<a href="#" target="_blank">4787</a>&nbsp;&nbsp;&nbsp;&nbsp;
								作品数：<a href="#" target="_blank">81</a>&nbsp;&nbsp;&nbsp;&nbsp;
								人气：<a href="#" target="_blank">545645</a>
							</div>
							<div class="userList_cz">
								<a href="#" class="btnfollow">加关注</a>
							</div>
						</div>
					</div>
					<div class="atImg">
						<a href="#" target="_blank"><img src="images/cover.jpg" width="120" height="75"></a>
						<a href="#" target="_blank"><img src="images/cover.jpg" width="120" height="75"></a>
						<a href="#" target="_blank"><img src="images/cover.jpg" width="120" height="75"></a>
						<a href="#" target="_blank" class="more">更多</a>
					</div>
					<div class="clear"></div>
				</div>
			</c:forEach>
		</div>
		<div id="pages">
			<a href="http://www.uehtml.com/?page=1" id="pageactive">1</a>
			<a href="http://www.uehtml.com/?page=2">2</a>
			<a href="http://www.uehtml.com/?page=3">3</a>
			<a href="http://www.uehtml.com/?page=4">4</a>
			<a href="http://www.uehtml.com/?page=5">5</a>
			<span>...</span>
			<a href="http://www.uehtml.com/?page=87">87</a>
			<a href="http://www.uehtml.com/?page=2" id="pagenext"><span class="yy-icon pagenext"></span></a></div>
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

</body>
</html>
