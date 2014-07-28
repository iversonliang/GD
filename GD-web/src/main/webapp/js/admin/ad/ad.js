var Ad = {
	"showPopUp" : function(adId) {
		$("#popUpAdId").val(adId);
		$("#adPopUp").show();
	},
	"hidePopUp" : function() {
		$("#popUpAdId").val(0);
		$("#adPopUp").hide();
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
					$("#imgUrl").val(data.url);
				}
			},
			error : function(data, status, e) {
				alert("上传失败!");
				return;
			}
		});
	},
	"add" : function() {
		var imgUrl = $("#imgUrl").val();
		if (Common.isEmpty(imgUrl)) {
			alert("请上传图片");
			return;
		}
		var url = $("#url").val();
		if (Common.isEmpty(url)) {
			alert("请输入地址");
			return;
		}
		var adAreaType = $("#adAreaType").val();
		if (Common.isEmpty(adAreaType)) {
			alert("请选择区域");
			return;
		}
		var param = {
			url : url,
			imgUrl : imgUrl,
			adAreaType : adAreaType
		}
		var indexNum = $("#indexNum").val();
		if (Common.isNotEmpty(indexNum)) {
			param.indexNum = indexNum;
		}
		var addUrl = "/admin/ad/add.do";
		AjaxJson.post(addUrl, param).done(function(data) {
			if (data.result == true) {
				window.location.reload();
			}
		});
	},
	"end" : null
}