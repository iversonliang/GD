
var User = {
	"addDanceType" : function() {
		var danceTypeObj = $("#danceType").find("option:selected");
		if (danceTypeObj.val() == 0) {
			return;
		}
		danceTypeObj.remove();
		User.appendDanceTypeLabel(danceTypeObj.text());
	},
	"removeDaneTypeSelectNode" : function(danceType) {
		$("#danceType option").each(function(index, value) {
			if ($(this).html() == danceType) {
				$(this).remove();
			}
		});
	},
	"appendDanceTypeLabel" : function(danceType) {
		var li = "<li style='margin-bottom:5px'><span name='selectedDanceType'>";
		li += danceType + "</span><a href='javascript:void(0)' onclick='User.delDanceType(\"";
		li += danceType + "\", this)' class='delete'>x</a></li>";
		$("#danceTypeList").append(li);
	},
	"delDanceType" : function(danceType, obj) {
		$(obj).parent().remove();
		var option = "<option>" + danceType + "</option>";
		$("#danceType").append(option);
	},
	/**
	 * 计算签名长度
	 */
	"checkSignLength" : function() {
		var sign = $("#sign").val().trim();
		var length = 38 - sign.stringLength();
//		if (length < 28) {
//			sign = sign.substring(0, 10);
//			$("#sign").val(sign);
//			length = 38 - sign.stringLength();
//			if (length < 28) {
//				sign = sign.substring(0, 8);
//				$("#sign").val(sign);
//				length = 38 - sign.stringLength();
//			}
//		}
		$("#leftSignLength").html(length);
	},
	/**
	 * 计算签名长度
	 */
	"checkNicknameLength" : function() {
		var sign = $("#nickname").val().trim();
		var length = 10 - sign.stringLength();
		$("#leftNicknameLength").html(length);
	},
	/**
	 * 计算个人简介长度
	 */
	"checkDescriptionLength" : function() {
		var description = $("#description").val().trim();
		var length = 200 - description.stringLength();
		$("#leftDescriptionLength").html(length);
	},
	/**
	 * 保存个人资料
	 */
	"save" : function() {
		var sex = 0;
		if (Common.isSelected("male")) {
			sex = 1;
		}
		var birthday = "";
		var year = $("#year").val();
		var month = $("#month").val();
		var day = $("#day").val();
		if (year != 0 && month != 0 && day != 0) {
			birthday = year + "-" + month + "-" + day + " 00:00:00";
		}
		var danceType = "";
		var danceTypeCount = $("span[name=selectedDanceType]").length;
		$("span[name=selectedDanceType]").each(function(index, val) {
			danceType += $(this).html();
			if (index != danceTypeCount - 1) {
				danceType += "/";
			}
		});
		var province = $("#province").val();
		province = province.indexOf("请选择") != -1 ? "" : province;
		var city = $("#city").val();
		city = city.indexOf("请选择") != -1 ? "" : city;
		var param = {
			sign : $("#sign").val().trim(),
			description : $("#description").val().trim(),
			sex : sex,
			birthday : birthday,
			realName : $("#realName").val(),
			danceType : danceType,
			province : province,
			city : city,
			nickname : $("#nickname").val()
		}
		var url = "/user/updateUserInfo.do";
		AjaxJson.post(url, param).done(function(data) {
			window.location.reload();
		});
	},
	"upload" : function() {
		$.ajaxFileUpload({
			url : '/user/upload.do',
			secureuri : false,
			fileElementId : 'file',
			dataType : 'json',
			data : {
				docType : 1
			},
			success : function(data, status) {
				data = $.parseJSON(data);
				if (data.result == true) {
					$("#headImgUrl").val(data.url);
					$("#headImg").attr("src", data.url);
					$("#updateHeadBtn").show();
				}
			},
			error : function(data, status, e) {
				alert("上传失败!");
				return;
			}
		});
	},
	"updateHeadImg" : function() {
		var imgUrl = $("#headImgUrl").val();
		var url = "/user/updateHeadImg.do?url=" + imgUrl;
		AjaxJson.get(url).done(function(data) {
			window.location.reload();
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
	 * 检查密码
	 */
	"checkNewPassword" : function() {
		var newPassword = $("#newPassword").val();
		if (Common.isEmpty(newPassword)) {
			$("#newPasswordTip").html("请输入密码");
			$("#newPasswordTip").show();
			return false;
		}
		var pattern = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
		var reg = new RegExp(pattern);
		if (!newPassword.match(reg)) {
			$("#newPasswordTip").html("用户密码必须是数字和字母组合，在6-16位以内");
			$("#newPasswordTip").show();
			return false;
		}
		return true;
	},
	/**
	 * 检查密码
	 */
	"checkReNewPassword" : function() {
		var reNewPassword = $("#reNewPassword").val();
		if (Common.isEmpty(reNewPassword)) {
			$("#reNewPasswordTip").html("请输入密码");
			$("#reNewPasswordTip").show();
			return false;
		}
		var newPassword = $("#newPassword").val();
		if (newPassword != reNewPassword) {
			$("#reNewPasswordTip").html("两次输入的密码不一致");
			$("#reNewPasswordTip").show();
			return false;
		}
		return true;
	},
	/**
	 * 隐藏tips
	 */
	"hideTips" : function() {
		$("span[name=tips]").each(function(index,element) {
			$(this).hide();
		});
	},
	"resetPassword" : function() {
		if (!User.checkPassword()) {
			return;
		}
		if (!User.checkNewPassword()) {
			return;
		}
		if (!User.checkReNewPassword()) {
			return;
		}
		var param = {
			password : $("#password").val(),
			newPassword : $("#newPassword").val()
		}
		var url = "/user/resetPassword.do";
		AjaxJson.post(url, param).done(function(data) {
			window.location.reload();
		});
	},
	"end" : null
}