
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
	"end" : null
}