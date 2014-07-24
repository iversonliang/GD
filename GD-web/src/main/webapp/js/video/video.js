
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
			if (data.result == true) {
				var sourceType = $("#sourceType").val();
				if (sourceType == 1) {
					window.location.href = "/opus.do";
				} else {
					window.location.href = "/inspiration.do";
				}
			}
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
		var sourceType = $("#sourceType").val();
		if (sourceType == 0) {
			alert("请选择类型");
			return false;
		}
		return true;
	},
	"search" : function(type, value) {
		var url = "/inspiration.do";
		var hasParam = false;
		if (type == "videoType") {
			url += "?videoType=" + value;
			hasParam = true;
		} else {
			var videoType = $("a[name=videoType][class=selected]").attr("videoTypeId");
			if (Common.isNotEmpty(videoType)) {
				url += hasParam ? "&" : "?";
				url += "videoType=" + videoType;
				hasParam = true;
			}
		}
		
		if (type == "sortType") {
			url += hasParam ? "&" : "?";
			url += "sortType=" + value;
			hasParam = true;
		} else {
			var sortType = $("a[name=sortType][class=selected]").attr("sortTypeId");
			if (Common.isNotEmpty(sortType)) {
				url += hasParam ? "&" : "?";
				url += "sortType=" + sortType;
				hasParam = true;
			}
		}
		
		
		window.location.href = url;
	},
	"end" : null
}