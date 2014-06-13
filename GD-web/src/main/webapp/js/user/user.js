
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
		var param = {
			sign : $("#sign").val().trim(),
			description : $("#description").val().trim(),
			sex : sex,
			birthday : birthday,
			realName : $("#realName").val(),
			danceType : danceType
		}
		var url = "/user/updateUserInfo.do";
		AjaxJson.post(url, param).done(function(data) {
			alert(data.result);
		});
	},
	"end" : null
}