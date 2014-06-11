<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/taglib.inc.jsp"%>
<div id="topBar">
	<div class="topContent">
		<div class="topLogo">
			<a href="/index.do"><img src="/images/logo.gif" /></a>
		</div>
		<div class="topNav">
			<a href="/index.do" class="selected">首页</a>
			<a href="#">作品</a>
			<a href="#">灵感</a>
			<a href="#">专题</a>
			<a href="#">舞者</a>
		</div>
		<div class="topHeadeRight">
			<c:choose>
				<c:when test="${isLogin == true }">
					<div class="logedin">
					<a href="#"><img src="/images/avatar.png" width="24" height="24"></a>
					<div class="son">
						<ul>
							<li><a href="">我的主页</a></li>
							<li><a href="">我上传的</a></li>
							<li><a href="">我分享的</a></li>
							<li><a href="">我喜欢的</a></li>
							<li><a href="">我的消息</a></li>
							<li><a href="/user/userInfo.do">账号设置</a></li>
							<li><a href="/user/logout.do">退出</a></li>
						</ul>
					</div>
				</div>
				</c:when>
				<c:otherwise>
					<div class="doLogBox"><a href="/page/login.jsp">登录</a>|<a href="/page/register.jsp">注册</a></div>
				</c:otherwise>
			</c:choose>
			<div class="searchBar">
				<form action="" method="get">
					<input name="search" type="text" value="" placeholder="关键字查找" autocomplete="off">
					<input type="submit" value="" class="yy-icon yy-so">
				</form>
			</div>
		</div>
	</div>
</div>
