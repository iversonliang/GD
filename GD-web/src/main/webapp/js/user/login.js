
var Login = {
	"login" : function() {
		if (!Login.check()) {
			return;
		}
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
				$("#tips").html(data.message);
				$("#errorTips").show();
			}
		});
	},
	"check" : function () {
		var username = $("#username").val();
		if (Common.isEmpty(username)) {
			$("#tips").html("请输入用户名");
			$("#errorTips").show();
			return false;
		}
		var password = $("#password").val();
		if (Common.isEmpty(password)) {
			$("#tips").html("请输入登录密码");
			$("#errorTips").show();
			return false;
		}
		var code = $("#code").val();
		if (Common.isEmpty(code)) {
			$("#tips").html("请输入验证码");
			$("#errorTips").show();
			return false;
		}
		if (code.length != 4) {
			$("#tips").html("验证码长度为4位，请检查");
			$("#errorTips").show();
			return false;
		}
		return true;
	},
	"end" : null
}