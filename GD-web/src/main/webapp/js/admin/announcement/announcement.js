
var Announcement = {
	"delete" : function(announcementId) {
		if (!confirm("确认要删除该公告吗？")) {
			return;
		}
		var url = "/admin/announcement/delete.do?announcementId=" + announcementId;
		AjaxJson.get(url).done(function(data) {
			if (data.result == true) {
				window.location.reload();
			}
		});
	},
	"end" : null
}