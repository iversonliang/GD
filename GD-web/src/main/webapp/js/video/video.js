
var Video = {
	/**
	 * 计算简介长度
	 */
	"checkDescriptionLength" : function() {
		var description = $("#description").val().trim();
		var length = 200 - description.stringLength();
		$("#leftDescriptionLength").html(length);
	},
	"add" : function() {
		if (!Video.deployCheck()) {
			return;
		}
		var param = {
			name : $("#name").val(),
			description : $("#description").val(),
			url : $("#url").val(),
			label : $("#label").val(),
			type : $("#type").val(),
			sourceType : $("#sourceType").val()
		}
		var url = "/video/add.do";
		AjaxJson.post(url, param).done(function (data) {
			alert(data.result);
		});
	},
	"deployCheck" : function() {
//		var name = $("#name").val();
//		if (Common.isEmpty(name)) {
//			alert("输入视频名称");
//			return false;
//		}
		var url = $("#url").val();
		if (Common.isEmpty(url)) {
			alert("请输入视频地址");
			return false;
		}
		return true;
	},
	"end" : null
}