
var Video = {
	"currCommentPage" : 1,
	"loadComment" : function (vid) {
		var url = "/video/loadComment.do?vid=" + vid + "&page=" + (Video.currCommentPage + 1);
		AjaxHtml.get(url).done(function (data) {
			$("#commentArea").append(data);
			Video.currCommentPage++;
		});
	},
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
					alert("发布成功！");
					window.location.href = "/opus.do";
				} else {
					alert("发布成功！");
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
				$("#notLove").hide();
				$("#loveNum").html(data.loveNum);
				$("#love").show();
			}
		});
	},
	"checkUpdate" : function() {
		var sourceType = $("#sourceType").val();
		if (sourceType == 0) {
			alert("请选择类型");
			return false;
		}
		var videoType = $("#videoType").val();
		if (videoType == -1) {
			alert("请选择分类");
			return false;
		}
		return true;
	},
	"update" : function() {
		if (!Video.checkUpdate()) {
			return;
		}
		var videoSourceType = $("#sourceType").val();
		var name = $("#name").val();
		var videoType = $("#videoType").val();
		var label = $("#label").val();
		var description = $("#description").val();
		var param = {
			vid : Common.getUrlParam("vid"),
			videoSourceType : videoSourceType,
			name : name,
			videoType : videoType,
			label : label,
			description : description
		}
		var url = "/video/update.do"
		AjaxJson.post(url, param).done(function(data) {
			if (data.result == true) {
				alert("修改成功");
			}
		});
	},
	"upload" : function() {
		$.ajaxFileUpload({
			url : '/video/upload.do',
			secureuri : false,
			fileElementId : 'file',
			dataType : 'json',
			data : {
				docType : 1
			},
			success : function(data, status) {
				data = $.parseJSON(data);
				if (data.result == true) {
					$("#coverImgUrl").val(data.url);
					$("#coverImg").attr("src", data.url);
					$("#updateCoverBtn").show();
				}
			},
			error : function(data, status, e) {
				alert("上传失败!");
				return;
			}
		});
	},
//	"updateCoverImg" : function(vid) {
//		var imgUrl = $("#coverImgUrl").val();
//		var url = "/video/updateCoverImg.do?vid=" + vid + "&url=" + imgUrl;
//		alert(url);
//		AjaxJson.get(url).done(function(data) {
//			window.location.reload();
//		});
//	},
	"showDel" : function(type, videoId) {
		$("#deleteType").val(type);
		$("#deleteVideoId").val(videoId);
		$("#deleteDiv").show();
	},
	"hideDel" : function() {
		$("#deleteDiv").hide();
	},
	"delete" : function() {
		var type = $("#deleteType").val();
		var videoId = $("#deleteVideoId").val();
		var url = "/video/delete.do?type=" + type + "&vid=" + videoId;
		window.location.href = url;
	},
	"end" : null
}