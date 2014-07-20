<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/taglib.inc.jsp"%>
<div id="nav">
	<ul>
		<li <c:if test="${type == 'video' || param.type == 'video' }">class="selected"</c:if>><a href="/admin/video/index.do">首页推荐</a></li>
		<li <c:if test="${type == 'announcement' || param.type == 'announcement' }">class="selected"</c:if>><a href="/admin/announcement/index.do">公告</a></li>
	</ul>
</div>