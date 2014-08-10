<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/taglib.inc.jsp"%>
<div id="nav">
	<ul>
		<li <c:if test="${type == 'video' || param.type == 'video' }">class="selected"</c:if>><a href="/admin/video/index.do">首页推荐</a></li>
		<li <c:if test="${type == 'ad' || param.type == 'ad' }">class="selected"</c:if>><a href="/admin/ad/index.do">首页广告</a></li>
		<li <c:if test="${type == 'announcement' || param.type == 'announcement' }">class="selected"</c:if>><a href="/admin/announcement/index.do">公告</a></li>
		<li <c:if test="${type == 'inviteCode' || param.type == 'inviteCode' }">class="selected"</c:if>><a href="/admin/inviteCode/index.do">邀请码</a></li>
		<li <c:if test="${type == 'apply' || param.type == 'apply' }">class="selected"</c:if>><a href="/admin/apply/index.do">邀请码申请名单</a></li>
	</ul>
</div>