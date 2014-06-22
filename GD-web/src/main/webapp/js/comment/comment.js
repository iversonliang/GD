
var Comment = {
	"DEFAULT_VALUE" : "",
	"replyUserId" : "",
	"replyNickname" : "",
	"replyContent" : "",
	"add" : function(videoId) {
		var isLogin = $("#loginStatus").val();
		if (isLogin == "false") {
			alert("请先登录");
			return;
		}
		var content = $("#commentContent").val();
		var param = {
			content : content,
			replyUserId : Comment.replyUserId,
			replyNickName : Comment.replyNickname,
			replyContent : Comment.replyContent,
			videoId : videoId
		}
		var url = "/comment/add.do";
		AjaxHtml.post(url, param).done(function(data) {
//			if (data.result == true) {
//				$("#commentContent").val("");
//				Comment.replyNickname = Comment.DEFAULT_VALUE;
//				Comment.replyUserId = Comment.DEFAULT_VALUE;
//				Comment.replyContent = Comment.DEFAULT_VALUE;
//			}
			$("#commentArea").html(data);
		});
	},
	"end" : null
}