
var Login = {
	"login" : function() {
		var param = {
			username : $("#username").val(),
			password : $("#password").val(),
			code : $("#code").val()
		}
		var url = "/user/login.do";
		AjaxJson.post(url, param).done(function(data) {
			if (data.result == true) {
				var url = "/index.do";
				if (Common.isNotEmpty(data.redirect)) {
					url = data.redirect;
				}
				window.location.href = url;
			} else {
				$("#tips").html(data.tips);
				$("#errorTips").show();
			}
		});
	},
	"end" : null
}