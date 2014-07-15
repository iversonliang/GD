
var Comment = {
	"DEFAULT_VALUE" : "",
	"replyUserId" : "",
	"replyNickname" : "",
	"replyContent" : "",
	"defaultParam" : function() {
		Comment.replyNickname = Comment.DEFAULT_VALUE;
		Comment.replyUserId = Comment.DEFAULT_VALUE;
		Comment.replyContent = Comment.DEFAULT_VALUE;
	},
	"add" : function(videoId) {
		var isLogin = $("#loginStatus").val();
		if (isLogin == "false") {
			window.location.href = "/page/login.jsp";
			return;
		}
		var content = $("#commentContent").val();
		var param = {
			content : content,
			replyUserId : Comment.replyUserId,
			replyNickname : Comment.replyNickname,
			replyContent : Comment.replyContent,
			videoId : videoId
		}
		var url = "/comment/add.do";
		AjaxHtml.post(url, param).done(function(data) {
			$("#commentArea").html(data);
			var commentCount = $("#commentCount").html();
			commentCount = parseInt(commentCount);
			commentCount++;
			$("#commentCount").html(commentCount);
			$("#commentContent").val(Comment.DEFAULT_VALUE);
			Comment.defaultParam();
		});
	},
	"setReply" : function(replyUserId, replyNickname, replyContent) {
		Comment.replyNickname = replyNickname;
		Comment.replyUserId = replyUserId;
		Comment.replyContent = replyContent;
	},
	/**
	 * 计算评论长度
	 */
	"updateLength" : function(commentId) {
		var content = $("#content" + commentId).val().trim();
		var length = 200 - content.stringLength();
		$("#leftCount" + commentId).html(length);
	},
	"showToMyCommentsBox" : function(commentId) {
		$("#replyBox" + commentId).show();
	},
	"hideToMyCommentsBox" : function(commentId) {
		$("#content" + commentId).val("");
		$("#leftCount" + commentId).html(200);
		$("#replyBox" + commentId).hide();
	},
	"replyToMyComments" : function(commentId, replyUserId, replyNickname, replyContent) {
		var contentLength = $("#leftCount" + commentId).html();
		if (contentLength < 0) {
			alert("回复内容超长，请删除超出部分内容");
			return;
		}
		var videoId = $("#comment" + commentId + "videoId").val();
		var param = {
			content : $("#content" + commentId).val(),
			replyUserId : replyUserId,
			replyNickname : replyNickname,
			replyContent : replyContent,
			videoId : videoId
		}
		var url = "/comment/add.do";
		AjaxHtml.post(url, param).done(function(data) {
			window.location.reload();
		});
	},
	"end" : null
}