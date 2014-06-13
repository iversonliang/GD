String.prototype.stringLength = function() {
	return this.replace(/[^\x00-\xff]/g, "xx").length;
}

var Common = {
	/*
	 * 复制邀请码到粘贴板
	 */
	"copy2ClipBoard" : function(data) {
		window.clipboardData.setData("Text", data);
	},
	/*
	 * 获取URL参数
	 */
	"getUrlParam" : function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	},
	/**
	 * 判断参数是否为空
	 */
	"isEmpty" : function(param) {
		if (param == null) {
			return true;
		}
		if (param.length == 0) {
			return true;
		}
		if (isNaN(param) && param == "") {
			return true;
		}
		if (typeof (param) == "undefined") {
			return true;
		}
		return false;
	},
	/**
	 * 判断参数是否不为空
	 */
	"isNotEmpty" : function(param) {
		return !Common.isEmpty(param);
	},
	/*
	 * json字符串转换成对象
	 */
	"toObj" : function(data) {
		return eval("(" + data + ")");
	},
	/*
	 * 是否选中checkbox
	 */
	"isSelected" : function(id) {
		return document.getElementById(id).checked;
	},
	/*
	 * 复杂对象转url参数字符串
	 */
	"parse2Param" : function(obj) {
		var ret = [];
		for ( var key in obj) {
			key = encodeURIComponent(key);
			var values = obj[key];
			if (values && values.constructor == Array) {// 数组
				var queryValues = [];
				for (var i = 0, len = values.length, value; i < len; i++) {
					value = values[i];
					queryValues.push(Common.toQueryPair(key, value));
				}
				ret = ret.concat(queryValues);
			} else { // 字符串
				ret.push(Common.toQueryPair(key, values));
			}
		}
		return ret.join('&');
	},
	"toQueryPair" : function(key, value) {
		if (typeof value == 'undefined') {
			return key;
		}
		return key + '='
				+ encodeURIComponent(value === null ? '' : String(value));
	},
	/*
	 * data对象转成字符串
	 */
	"date2Str" : function(date, pattern) {
		var z = {
			y : date.getFullYear(),
			M : date.getMonth() + 1,
			d : date.getDate(),
			h : date.getHours(),
			m : date.getMinutes(),
			s : date.getSeconds()
		};
		if (Common.isEmpty(pattern)) {
			pattern = "yyyy-MM-dd hh:mm:ss";
		}
		return pattern.replace(/(y+|M+|d+|h+|m+|s+)/g, function(v) {
			var a = (v.length > 1 ? "00" : "");
			var b = 'z.' + v.slice(-1);
			var value = eval(b);
			value = eval(b) < 10 ? "0" + value : value;
			var c = a + value;
			var d = v.length > 2 ? v.length : 2;
			var result = c.slice(-d);
			return result;
		});
	},
	"str2Date" : function(dateStr) {
		var temp = dateStr.split(" ");
		var temp1 = temp[0].split("-");
		var temp2 = temp[1].split(":");
		var year = parseInt(temp1[0]);
		var month = parseInt(temp1[1]);
		var day = parseInt(temp1[2]);
		var hour = parseInt(temp2[0]);
		var minute = parseInt(temp2[1]);
		var second = parseInt(temp2[2]);
		return Common.getDate(year, month, day, hour, minute, second);
	},
	/*
	 * 根据年月日获取时间对象
	 */
	"getDate" : function(year, month, day, hour, minute, second) {
		var date = new Date();
		if (Common.isNotEmpty(year)) {
			if (("" + year).length < 4) {
				alert("年份不合法:" + year);
			}
			date.setYear(year);
		}
		if (Common.isNotEmpty(month)) {
			if (month > 12 || month < 1) {
				alert("月份不合法：" + month);
			}
			date.setMonth(month - 1);
		}
		if (Common.isNotEmpty(day)) {
			if (day < 1 || day > 31) {
				alert("日期不合法：" + day);
			}
			date.setDate(day);
		}
		if (Common.isNotEmpty(hour)) {
			if (hour > 23 || hour < 1) {
				alert("小时不合法：" + hour);
			}
			date.setHours(hour);
		}
		if (Common.isNotEmpty(minute)) {
			if (minute > 59 || minute < 0) {
				alert("分钟不合法：" + minute);
			}
			date.setMinutes(minute);
		}
		if (Common.isNotEmpty(second)) {
			if (second > 59 || second < 0) {
				alert("秒数不合法：" + second);
			}
			date.setSeconds(second);
		}
		return date;
	},
	"before" : function(date1, date2) {
		var time1 = date1.getTime();
		var time2 = date2.getTime();
		return time1 < time2;
	},
	/*
	 * 判断是否隐藏
	 */
	"isHide" : function(id) {
		return $("#" + id).is(":hidden");
	},
	/*
	 * 判断是否隐藏
	 */
	"isShow" : function(id) {
		return !$("#" + id).is(":hidden");
	},
	/*
	 * 获取下拉框选择的文本内容
	 */
	"getSelectedText" : function(id) {
		return $("#" + id).find("option:selected").text();
	},
	/*
	 * 获取下拉框选择的文本内容（多选）
	 */
	"getMultiSelectedText" : function(id) {
		var text = [];
		$("#" + id + " option:selected").each(function() {
			text.push($(this).text());
		});
		return text;
	},
	"end" : null
}
