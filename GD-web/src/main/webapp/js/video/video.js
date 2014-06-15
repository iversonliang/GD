
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
	"end" : null
}