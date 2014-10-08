
var Video = {
	"delete" : function(videoId) {
		if (!confirm("确认要删除该视频吗？")) {
			return;
		}
		var url = "/admin/video/delete.do?videoId=" + videoId;
		AjaxJson.get(url).done(function(data) {
			if (data.result == true) {
				window.location.reload();
			}
		});
	},
	"unDelete" : function(videoId) {
		if (!confirm("确认要删除该视频吗？")) {
			return;
		}
		var url = "/admin/video/unDelete.do?videoId=" + videoId;
		AjaxJson.get(url).done(function(data) {
			if (data.result == true) {
				window.location.reload();
			}
		});
	},
	"showVideGradeTypePopUp" : function(videoId) {
		$("#popUpVideoId").val(videoId);
		$("#videoGradeTypePopUp").show();
	},
	"hideVideGradeTypePopUp" : function() {
		$("#popUpVideoId").val(0);
		$("#videoGradeTypePopUp").hide();
	},
	"showPopUp" : function(videoId) {
		$("#popUpVideoId").val(videoId);
		$("#homeTypePopUp").show();
	},
	"hidePopUp" : function() {
		$("#popUpVideoId").val(0);
		$("#homeTypePopUp").hide();
	},
	"setHomeType" : function() {
		var videoId = $("#popUpVideoId").val();
		var indexNum = $("#indexNum").val();
		var url = "/admin/video/setHomeType.do?videoId=" + videoId + "&homeType=1&indexNum=" + indexNum;
		AjaxJson.get(url).done(function(data) {
			if (data.result == true) {
				window.location.reload();
			}
		});
	},
	"delHomeType" : function(videoId) {
		if (!confirm("确认要取消该视频为首页推荐吗？")) {
			return;
		}
		var url = "/admin/video/delHomeType.do?videoId=" + videoId;
		AjaxJson.get(url).done(function(data) {
			if (data.result == true) {
				window.location.reload();
			}
		});
	},
	"setGradeType" : function() {
		var videoId = $("#popUpVideoId").val();
		var videoGradeType = $('input[name="radiobutton"]:checked').val();
		var url = "/admin/video/setVideoGradeType.do?videoId=" + videoId + "&videoGradeType=" + videoGradeType;
		AjaxJson.get(url).done(function(data) {
			if (data.result == true) {
				window.location.reload();
			}
		});
	},
	"end" : null
}