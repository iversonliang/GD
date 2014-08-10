var InviteCode = {
	"showPopUp" : function() {
		$("#popUp").show();
	},
	"hidePopUp" : function() {
		$("#popUp").hide();
	},
	"create" : function() {
		var num = $("#num").val();
		if (Common.isEmpty(num) || isNaN(num)) {
			alert("生成数量有误");
			return;
		}
		var url = "/admin/inviteCode/create.do?num=" + num;
		AjaxJson.get(url).done(function(data) {
			if (data.result == true) {
				window.location.reload();
			}
		});
	},
	"send" : function(inviteCodeId) {
		var url = "/admin/inviteCode/send.do?inviteCodeId=" + inviteCodeId;
		AjaxJson.get(url).done(function(data) {
			if (data.result == true) {
				window.location.reload();
			}
		});
	},
	"end" : null
}