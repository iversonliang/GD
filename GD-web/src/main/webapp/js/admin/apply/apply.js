
var Apply = {
	"pass" : function(applyId) {
		if (!confirm("确认通过该申请吗?")) {
			return;
		}
		var url = "/admin/apply/pass.do?applyId=" + applyId;
		AjaxJson.get(url).done(function(data) {
			if (data.result == true) {
				window.location.reload();
			}
		});
	},
	"end" : null
}