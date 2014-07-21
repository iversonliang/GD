
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
	"setHomeType" : function(videoId) {
		if (!confirm("确认要设置该视频为首页推荐吗？")) {
			return;
		}
		var url = "/admin/video/setHomeType.do?videoId=" + videoId + "&homeType=1";
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
	"end" : null
}