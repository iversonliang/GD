<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/taglib.inc.jsp"%>

<c:forEach items="${commentList}" var="comment">
	<div class="uitem">
		<div class="avatar">
			<a href="/video/personal.do?userId=${comment.userId }" target="_blank"><img src="${comment.headImg }" width="50" height="50"></a>
		</div>
		<div class="msgcont">
			<div class="msg_title">
				<h3>
					<a href="/video/personal.do?userId=${comment.userId }" target="_blank">${comment.nickname }</a> ${comment.deployTimeTips }：
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
				<a href="javascript:Comment.showReplyCommentBox('${comment.commentId }', '${comment.userId }', '${comment.nickname }', '${comment.content }', '${comment.replyUserId }', '${comment.replyNickname }', '${comment.replyContent }')">回复</a>
			</div>
			<div class="crbBox" style="display: none;" id="replyBox${comment.commentId }">
				<textarea class="commentArea" id="content${comment.commentId }" onkeyup="Comment.updateLength('${comment.commentId }')"></textarea>
				<div class="commentFunc">
					<p class="f12">您还可以输入 <span class="cf30 abc" id="leftCount${comment.commentId }">200</span> 个字符</p>
				</div>
				<div class="">
					<a href="javascript:Comment.add(${videoId }, ${comment.commentId })" class="commentConfirm">确认回复</a>
					<a href="javascript:Comment.hideToMyCommentsBox('${comment.commentId }')" class="commentCancel">取消回复</a>
				</div>
				<input type="hidden" id="comment${comment.commentId }videoId" value="8">
			</div>
		</div>
		<div class="clear"></div>
	</div>
</c:forEach>

<script>
	var hasMore = '${hasMore}';
	if (hasMore == false || hasMore == "false") {
		$("#moreCommentDiv").hide();
	}
</script>