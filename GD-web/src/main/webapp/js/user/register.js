
var Register = {
	/**
	 * 阅读声明checkbox事件
	 */
	"changeRead" : function() {
		var isSelected = Common.isSelected("yuedu");
		if (isSelected) {
			$("#registerBtnDiv").show();
		} else {
			$("#registerBtnDiv").hide();
		}
	},
	"check" : function() {
		var username = $("#username").val();
		if (Common.isEmpty(username)) {
			$("#usernameTip").show();
			return false;
		}
		var password = $("#password").val();
		if (Common.isEmpty(password)) {
			$("#passwordTip").show();
			return false;
		}
		var repassword = $("#repassword").val();
		if (Common.isEmpty(repassword)) {
			$("#repasswordTip").show();
			return false;
		}
		if (repassword != password) {
			$("#repasswordTip").show();
			return false;
		}
		var email = $("#email").val();
		if (Common.isEmpty(email)) {
			$("#emailTip").show();
			return false;
		}
		return true;
	},
	"register" : function() {
		$("span[name=tips]").each(function(index,element) {
			$(this).hide();
		});
		if (!Register.check()) {
			return;
		}
		var sex = 0;
		if (Common.isSelected("male")) {
			sex = 1;
		}
		var param = {
			username : $("#username").val(),
			password : $("#password").val(),
			email : $("#email").val(),
			sex : sex
		}
		var url = "/user/register.do";
		AjaxJson.post(url, param).done(function(data) {
			alert(data);
			if (data == "true") {
				alert("注册失败");
			} else {
				alert("注册成功");
			}
		});
	},
	"end" : null
}