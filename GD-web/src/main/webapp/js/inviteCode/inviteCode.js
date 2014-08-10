var InviteCode = {
	"submitApply" : function() {
		var name = $("#name").val();
		if (Common.isEmpty(name)) {
			alert("请填写姓名");
			return;
		}
		var email = $("#email").val();
		if (Common.isEmpty(email)) {
			alert("请填写邮箱");
			return;
		}
		var location = $("#location").val();
		if (Common.isEmpty(location)) {
			alert("请填写来自哪里");
			return;
		}
		var crew = $("#crew").val();
		var oupsUrl = $("#oupsUrl").val();
		$("#form").submit();
	},
	"activate" : function() {
		$("#form").submit();
	},
	"end" : null
}