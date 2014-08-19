<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/taglib.inc.jsp"%>
<div id="topBar">
	<input type="hidden" value="${isLogin }" id="loginStatus">
	<div class="topContent">
		<div class="topLogo">
			<a href="/index.do"><img src="/images/logo.gif" /></a>
		</div>
		<div class="topNav">
			<a href="/index.do" <c:if test="${nav == 'home' }">class="selected"</c:if>>首页</a>
			<a href="/opus.do" <c:if test="${ nav == 'opus' }">class="selected"</c:if>>作品</a>
			<a href="/inspiration.do" <c:if test="${ nav == 'inspiration' }">class="selected"</c:if>>灵感</a>
			<%-- <a href="#" <c:if test="${ param.st == 4 }">class="selected"</c:if>>专题</a> --%>
			<a href="/user/userList.do" <c:if test="${  nav == 'dancer' }">class="selected"</c:if>>舞者</a>
		</div>
		<div class="topHeadeRight">
			<!-- <div class="doLogBox" style="display:none;"><a href="#">登录</a>|<a href="#">注册</a></div>
			<div class="logedin">
				<a href="#"><img src="images/avatar.png"></a>
				<div class="son">
					<ul>
						<li><a href="personal.html">我的主页</a></li>
						<li><a href="">我的作品</a></li>
						<li><a href="">我分享的</a></li>
						<li><a href="">我喜欢的</a></li>
						<li><a href="">我收藏的</a></li>
						<li><a href="msg-tomyComments.html">我的评论</a></li>
						<li><a href="edit_personal.html">账号设置</a></li>
						<li><a href="#">退出</a></li>
					</ul>
				</div>
			</div> -->
			<c:choose>
				<c:when test="${!empty isLogin && isLogin == true }">
					<div class="logedin">
						<a href="#"><img src="${headImg }" width="24" height="24"></a>
						<div class="son">
							<ul>
								<li><a href="/video/personal.do?userId=${userId }">我的主页</a></li>
								<!-- <li><a href="">我上传的</a></li>
								<li><a href="">我分享的</a></li>
								<li><a href="">我喜欢的</a></li> -->
								<li><a href="/comment/toMyComments.do">我的消息</a></li>
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
			<c:if test="${!empty isLogin && isLogin == true }">
				<div class="msg_count">
					<a href="#" class="tosms "></a>
					<div class="son">
						<ul>
							<li><a href="/comment/toMyComments.do">给我的评论</a></li>
							<li><a href="/comment/replyToMe.do" class="newone">给我的回复+1</a></li>
							<li><a href="/notice/index.do">系统通知</a></li>
							<li><a href="/announcement/index.do">优舞公告</a></li>
						</ul>
					</div>
				</div>
				<div class="top_sumit">
					<a href="/video/contribute.do" class="tosms"></a>
				</div>
			</c:if>
			<div class="searchBar">
				<form method="get">
					<input id="keyword" name="search" type="text" value='<c:if test="${not empty keyword }">${keyword }</c:if>' placeholder="关键字查找" autocomplete="off" class="searchInput">
					<input type="input" value="" class="yy-icon yy-so" onclick="Video.keywordSearch()">
				</form>
			</div>
		</div>
	</div>
</div>
