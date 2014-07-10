<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/taglib.inc.jsp"%>

<c:forEach items="${commentList}" var="comment">
	<div class="uitem">
		<div class="avatar">
			<a href="/user/personal.do?userId=${comment.userId }" target="_blank"><img src="${comment.headImg }" width="50" height="50"></a>
		</div>
		<div class="msgcont">
			<div class="msg_title">
				<h3>
					<a href="/user/personal.do?userId=${comment.userId }" target="_blank">${comment.nickname }</a> ${comment.deployTimeTips }：
				</h3>
				<div class="clear"></div>
			</div>
			<p>${comment.content }</p>
			<c:if test="${!empty comment.replyContent }">
				<div class="toMyCom">
					<div class="toMyComTop"></div>
					<div class="mblContent">
						<a href="#" target="_blank">${comment.replyNickname }</a>：${comment.replyContent }
					</div>
				</div>
			</c:if>
			<div class="msg_sub">
				<a href="javascript:Comment.setReply('${comment.userId }', '${comment.nickname }', '${comment.content }')">回复</a>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</c:forEach>