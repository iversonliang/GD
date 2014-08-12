
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
	"keywordSearch" : function() {
		var url = "/video/search.do";
		window.location.href = Video.getSearchParam(url);
	},
	"search" : function(type, value) {
		var url = window.location.pathname;
		window.location.href = Video.getSearchParam(url, type, value);
	},
	"getSearchParam" : function(url, type, value) {
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
		
		if (type == "videoGradeType") {
			url += hasParam ? "&" : "?";
			url += "videoGradeType=" + value;
			hasParam = true;
		} else {
			var videoGradeType = $("a[name=videoGradeType][class=selected]").attr("videoGradeTypeId");
			if (Common.isNotEmpty(videoGradeType)) {
				url += hasParam ? "&" : "?";
				url += "videoGradeType=" + videoGradeType;
				hasParam = true;
			}
		}
		
		if (type == "timeLimitType") {
			url += hasParam ? "&" : "?";
			url += "timeLimitType=" + value;
			hasParam = true;
		} else {
			var timeLimitType = $("a[name=timeLimitType][class=selected]").attr("timeLimitTypeId");
			if (Common.isNotEmpty(timeLimitType)) {
				url += hasParam ? "&" : "?";
				url += "timeLimitType=" + timeLimitType;
				hasParam = true;
			}
		}
		
		var keyword = $("#keyword").val();
		if (Common.isNotEmpty(keyword)) {
			url += hasParam ? "&" : "?";
			url += "keyword=" + keyword;
		}
		return url;
	},
	"love" : function(vid) {
		var url = "/video/love.do?vid=" + vid;
		AjaxJson.get(url).done(function(data) {
			if (data.isLogin == false) {
				window.location.href = "/page/login.jsp";
			}
			if (data.result == true) {
				$("#likeButton").hide()
			}
		});
	},
	"end" : null
}