<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>优舞网</title>
<%@include file="/WEB-INF/jsp/taglib.inc.jsp"%>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<%@include file="/WEB-INF/jsp/js.inc.jsp"%>

<script type="text/javascript">
$(function() {
	var sWidth = $("#focus").width(); //获取焦点图的宽度（显示面积）
	var len = $("#focus ul li").length; //获取焦点图个数
	var index = 0;
	var picTimer;
	
	//以下代码添加数字按钮和按钮后的半透明条，还有上一页、下一页两个按钮
	var btn = "<div class='btnBg'></div><div class='btn'>";
	for(var i=0; i < len; i++) {
		btn += "<span></span>";
	}
	btn += "</div><div class='preNext pre'></div><div class='preNext next'></div>";
	$("#focus").append(btn);
	$("#focus .btnBg").css("opacity",0.5);

	//为小按钮添加鼠标滑入事件，以显示相应的内容
	$("#focus .btn span").css("opacity",0.4).mouseenter(function() {
		index = $("#focus .btn span").index(this);
		showPics(index);
	}).eq(0).trigger("mouseenter");

	//上一页、下一页按钮透明度处理
	$("#focus .preNext").css("opacity",0.2).hover(function() {
		$(this).stop(true,false).animate({"opacity":"0.5"},300);
	},function() {
		$(this).stop(true,false).animate({"opacity":"0.2"},300);
	});

	//上一页按钮
	$("#focus .pre").click(function() {
		index -= 1;
		if(index == -1) {index = len - 1;}
		showPics(index);
	});

	//下一页按钮
	$("#focus .next").click(function() {
		index += 1;
		if(index == len) {index = 0;}
		showPics(index);
	});

	//本例为左右滚动，即所有li元素都是在同一排向左浮动，所以这里需要计算出外围ul元素的宽度
	$("#focus ul").css("width",sWidth * (len));
	
	//鼠标滑上焦点图时停止自动播放，滑出时开始自动播放
	$("#focus").hover(function() {
		clearInterval(picTimer);
	},function() {
		picTimer = setInterval(function() {
			showPics(index);
			index++;
			if(index == len) {index = 0;}
		},4000); //此4000代表自动播放的间隔，单位：毫秒
	}).trigger("mouseleave");
	
	//显示图片函数，根据接收的index值显示相应的内容
	function showPics(index) { //普通切换
		var nowLeft = -index*sWidth; //根据index值计算ul元素的left值
		$("#focus ul").stop(true,false).animate({"left":nowLeft},300); //通过animate()调整ul元素滚动到计算出的position
		//$("#focus .btn span").removeClass("on").eq(index).addClass("on"); //为当前的按钮切换到选中的效果
		$("#focus .btn span").stop(true,false).animate({"opacity":"0.4"},300).eq(index).stop(true,false).animate({"opacity":"1"},300); //为当前的按钮切换到选中的效果
	}
});

</script>

</head>

<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
<%-- <c:if test="${isLogin == false }">
<div id="header">
	<div class="bannar">
		<div class="bannar_pic">
			<c:if test="${requestScope.isLogin == null || isLogin == false }">
				<a href="/page/register.jsp" class="btn">立即注册</a>
			</c:if>
		</div>
	</div>
</div>
</c:if> --%>

<div class="recommend">
	<div id="focus">
		<ul>
			<li><a href="#" target="_blank"><img src="/images/slider/01.jpg" alt="" /></a></li>
			<li><a href="#" target="_blank"><img src="/images/slider/02.jpg" alt="" /></a></li>
			<li><a href="#" target="_blank"><img src="/images/slider/03.jpg" alt="" /></a></li>
			<li><a href="#" target="_blank"><img src="/images/slider/04.jpg" alt="" /></a></li>
			<li><a href="#" target="_blank"><img src="/images/slider/05.jpg" alt="" /></a></li>
		</ul>
	</div>
	<div class="r_side">
		<div class="r_ad"><a href="#" target="_blank"><img src="/images/ad/310-110.jpg" /></a></div>
		<div class="r_ad"><a href="#" target="_blank"><img src="/images/ad/ad01.jpg" /></a></div>
		<div class="r_links">
			<span><a href="#" target="_blank"><b>·</b> 第三季的《中国好声音》开播了。</a></span>
			<span><a href="#" target="_blank"><b>·</b> 那些好听的英文歌</a></span>
		</div>
	</div>
</div>

<div id="wrapper">
	<h2>推荐视频</h2>
	<div class="main">
		<div class="content">
			<c:forEach items="${videoVoList}" var="video">
				<div class="citem">
					<div class="cover">
						<c:choose>
							<c:when test="${video.videoSourceType == 1 }"><a target="_blank" href="/video/video.do?vid=${video.videoId }&st=2"></c:when>
							<c:otherwise><a target="_blank" href="/video/video.do?vid=${video.videoId }&st=3"></c:otherwise>
						</c:choose>
						<img src="${video.imgUrl }" />
						<div class="play_button"></div>
						</a>
					</div>
					<div class="citemtxt">
						<c:choose>
							<c:when test="${video.videoSourceType == 1 }"><a class="citemtitle" target="_blank" href="/video/video.do?vid=${video.videoId }&st=2" title="${video.name }">${video.name }</a></c:when>
							<c:otherwise><a class="citemtitle" target="_blank" href="/video/video.do?vid=${video.videoId }&st=3" title="${video.name }">${video.name }</a></c:otherwise>
						</c:choose>
					</div>
					<div class="itemInfo">
						<span class="yy-icon time">${video.deployTimeTips }</span>
						<a href="#" class="yy-icon comment"><span>${video.love }</span></a>
						<a href="#" class="yy-icon like"><span>${video.comments }</span></a>
						<a href="#" class="yy-icon views"><span>${video.play }</span></a>
					</div>
					<div class="user">
						<div class="avatar"><a href="/user/personal.do?userId=${video.userId }"><img src="${video.headImg }" width="32" height="32" />${video.nickname }</a></div>
						<span>( <a href="#">作品</a> )</span>
					</div>
				</div>
			</c:forEach>
		</div>
		<jsp:include page="/WEB-INF/jsp/include/pager.jsp"></jsp:include>
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

</body>
</html>
