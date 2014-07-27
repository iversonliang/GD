
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
	/**
	 * 检查参数
	 */
	"check" : function() {
		var username = $("#username").val();
		if (!Register.checkUsernameReg(username)) {
			return false;
		}
		if (!Register.checkPassword()) {
			return false;
		}
		if (!Register.checkRepassword()) {
			return false;
		}
		var email = $("#email").val();
		if (!Register.checkEmailReg(email)) {
			return false;
		}
		if (!Register.checkCode()) {
			return false;
		}
		if (!Register.checkComefrom()) {
			return false;
		}
		return true;
	},
	/**
	 * 注册
	 */
	"register" : function() {
		Register.hideTips();
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
			code : $("#code").val(),
			sex : sex,
			province : $("#province").val(),
			city : $("#city").val()
		}
		var url = "/user/register.do";
		AjaxJson.post(url, param).done(function(data) {
			if (data.result == true) {
				var url = "/user/userInfo.do";
				if (Common.isNotEmpty(data.redirect)) {
					url = data.redirect;
				}
				window.location.href = url;
			} else {
				if (data.errorCode == 3) {
					$("#codeTip").html(data.message);
					$("#codeTip").show();
				} else if (data.errorCode == 9) {
					$("#emailTip").html(data.message);
					$("#emailTip").show();
				} else if (data.errorCode == 8) {
					$("#usernameTip").html(data.message);
					$("#usernameTip").show();
				} else {
					alert(data.message);
				}
			}
		});
	},
	/**
	 * 隐藏tips
	 */
	"hideTips" : function() {
		$("span[name=tips]").each(function(index,element) {
			$(this).hide();
		});
	},
	/**
	 * 检查用户名居住地
	 */
	"checkComefrom" : function() {
		var province = $("#province").val();
		if (province.indexOf("请选择") != -1) {
			$("#locationTip").html("请输入省份或城市");
			$("#locationTip").show();
			return false;
		}
		var city = $("#city").val();
		if (city.indexOf("请选择") != -1) {
			$("#locationTip").html("请输入城市或区域");
			$("#locationTip").show();
			return false;
		}
		return true;
	},
	/**
	 * 检查用户名规则
	 */
	"checkUsernameReg" : function(username) {
		if (Common.isEmpty(username)) {
			$("#usernameTip").html("请输入用户名");
			$("#usernameTip").show();
			return false;
		}
		var pattern = "^[0-9A-Za-z]{6,16}$";
		var reg = new RegExp(pattern);
		if (!username.match(reg)) {
			$("#usernameTip").html("用户名需要在6-16位以内的字母或数字");
			$("#usernameTip").show();
			return false;
		}
		return true;
	},
	/**
	 * 检查用户名
	 */
	"checkUsername" : function() {
		var username = $("#username").val();
		if (!Register.checkUsernameReg(username)) {
			return;
		}
		var url = "/user/checkUsername.do?username=" + username;
		AjaxJson.get(url).done(function(data) {
			if (data.result == false) {
				$("#usernameTip").html(data.message);
				$("#usernameTip").show();
			}
		});
	},
	/**
	 * 检查邮箱规则
	 */
	"checkEmailReg" : function(email) {
		var pattern = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		var reg = new RegExp(pattern);
		if (!email.match(reg)) {
			$("#emailTip").html("邮箱格式错误");
			$("#emailTip").show();
			return false;
		}
		return true;
	},
	/**
	 * 检查邮箱
	 */
	"checkEmail" : function() {
		var email = $("#email").val();
		if (!Register.checkEmailReg(email)) {
			return;
		}
		var url = "/user/checkEmail.do?email=" + email;
		AjaxJson.get(url).done(function(data) {
			if (data.result == false) {
				$("#emailTip").html(data.message);
				$("#emailTip").show();
			}
		});
	},
	/**
	 * 检查密码
	 */
	"checkPassword" : function() {
		var password = $("#password").val();
		if (Common.isEmpty(password)) {
			$("#passwordTip").html("请输入密码");
			$("#passwordTip").show();
			return false;
		}
		var pattern = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
		var reg = new RegExp(pattern);
		if (!password.match(reg)) {
			$("#passwordTip").html("用户密码必须是数字和字母组合，在6-16位以内");
			$("#passwordTip").show();
			return false;
		}
		return true;
	},
	/**
	 * 检查重复确认密码
	 */
	"checkRepassword" : function() {
		var repassword = $("#repassword").val();
		if (Common.isEmpty(repassword)) {
			$("#repasswordTip").html("请再次输入密码确认");
			$("#repasswordTip").show();
			return false;
		}
		var password = $("#password").val();
		if (password != repassword) {
			$("#repasswordTip").html("您两次输入的密码不一致，请检查");
			$("#repasswordTip").show();
			return false;
		}
		return true;
	},
	/**
	 * 检查验证码
	 */
	"checkCode" : function() {
		var code = $("#code").val();
		if (Common.isEmpty(code)) {
			$("#codeTip").html("请输入验证码");
			$("#codeTip").show();
			return false;
		}
		if (code.length != 4) {
			$("#codeTip").html("验证码长度为4位，请检查");
			$("#codeTip").show();
			return false;
		}
		return true;
	},
	"end" : null
}