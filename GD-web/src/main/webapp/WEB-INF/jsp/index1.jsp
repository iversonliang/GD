<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>优舞网</title>
<%@include file="/WEB-INF/jsp/taglib.inc.jsp"%>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<script type="text/javascript" src="/js/jquery-1.6.4.min.js"></script>
<script type="text/javascript"> 
    function changeImg() {
        var imgSrc = $("#imgObj"); 
        var src = imgSrc.attr("src");
        imgSrc.attr("src", chgUrl(src)); 
    } 
      
    function chgUrl(url) {
        var timestamp = (new Date()).valueOf(); 
        url = url.substring(0, 17); 
        if ((url.indexOf("&") >= 0)) { 
            url = url + "&tamp=" + timestamp; 
        } else { 
            url = url + "?timestamp=" + timestamp; 
        }
        return url; 
    } 
</script>
</head>
<body>
	<!-- <form action="/validate" method="post">
		<input type="text" name="code"><img id="imgObj" src="/getCode" /><input type="button" value="change" onclick="changeImg()">
		<br/>
		<input type="submit">
	</form> -->
	<jsp:include page="/WEB-INF/jsp/include/top.jsp"></jsp:include>
<div id="wrapper">
	<h2>推荐视频</h2>
	<div class="main">
		<div class="content">
			<div class="citem">
				<div class="cover">
					<a href="#"><img src="/images/cover.jpg" width="230" height="148" />
						<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a><div class="citemtc"><span class="yy-icon yy-time">1月前</span></div></div>
					</a>
				</div>
				<div class="itemInfo">
					<a href="#" class="yy-icon comment"><span>23</span></a>
					<a href="#" class="yy-icon like"><span>42</span></a>
					<a href="#" class="yy-icon views"><span>248</span></a>
				</div>
				<div class="user">
					<div class="avatar"><a href="#"><img src="/images/avatar.png" width="32" height="32" />杨雅捷</a></div>
					<span>( <a href="#">作品</a> )</span>
				</div>
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#"><img src="/images/cover.jpg" width="230" height="148" />
						<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a><div class="citemtc"><span class="yy-icon yy-time">1月前</span></div></div>
					</a>
				</div>
				<div class="itemInfo">
					<a href="#" class="yy-icon comment"><span>23</span></a>
					<a href="#" class="yy-icon like"><span>42</span></a>
					<a href="#" class="yy-icon views"><span>248</span></a>
				</div>
				<div class="user">
					<div class="avatar"><a href="#"><img src="/images/avatar.png" width="32" height="32" />杨雅捷</a></div>
					<span>( <a href="#">作品</a> )</span>
				</div>
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#"><img src="/images/cover.jpg" width="230" height="148" />
						<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a><div class="citemtc"><span class="yy-icon yy-time">1月前</span></div></div>
					</a>
				</div>
				<div class="itemInfo">
					<a href="#" class="yy-icon comment"><span>23</span></a>
					<a href="#" class="yy-icon like"><span>42</span></a>
					<a href="#" class="yy-icon views"><span>248</span></a>
				</div>
				<div class="user">
					<div class="avatar"><a href="#"><img src="/images/avatar.png" width="32" height="32" />杨雅捷</a></div>
					<span>( <a href="#">作品</a> )</span>
				</div>
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#"><img src="/images/cover.jpg" width="230" height="148" />
						<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a><div class="citemtc"><span class="yy-icon yy-time">1月前</span></div></div>
					</a>
				</div>
				<div class="itemInfo">
					<a href="#" class="yy-icon comment"><span>23</span></a>
					<a href="#" class="yy-icon like"><span>42</span></a>
					<a href="#" class="yy-icon views"><span>248</span></a>
				</div>
				<div class="user">
					<div class="avatar"><a href="#"><img src="/images/avatar.png" width="32" height="32" />杨雅捷</a></div>
					<span>( <a href="#">作品</a> )</span>
				</div>
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#"><img src="/images/cover.jpg" width="230" height="148" />
						<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a><div class="citemtc"><span class="yy-icon yy-time">1月前</span></div></div>
					</a>
				</div>
				<div class="itemInfo">
					<a href="#" class="yy-icon comment"><span>23</span></a>
					<a href="#" class="yy-icon like"><span>42</span></a>
					<a href="#" class="yy-icon views"><span>248</span></a>
				</div>
				<div class="user">
					<div class="avatar"><a href="#"><img src="/images/avatar.png" width="32" height="32" />杨雅捷</a></div>
					<span>( <a href="#">作品</a> )</span>
				</div>
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#"><img src="/images/cover.jpg" width="230" height="148" />
						<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a><div class="citemtc"><span class="yy-icon yy-time">1月前</span></div></div>
					</a>
				</div>
				<div class="itemInfo">
					<a href="#" class="yy-icon comment"><span>23</span></a>
					<a href="#" class="yy-icon like"><span>42</span></a>
					<a href="#" class="yy-icon views"><span>248</span></a>
				</div>
				<div class="user">
					<div class="avatar"><a href="#"><img src="/images/avatar.png" width="32" height="32" />杨雅捷</a></div>
					<span>( <a href="#">作品</a> )</span>
				</div>
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#"><img src="/images/cover.jpg" width="230" height="148" />
						<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a><div class="citemtc"><span class="yy-icon yy-time">1月前</span></div></div>
					</a>
				</div>
				<div class="itemInfo">
					<a href="#" class="yy-icon comment"><span>23</span></a>
					<a href="#" class="yy-icon like"><span>42</span></a>
					<a href="#" class="yy-icon views"><span>248</span></a>
				</div>
				<div class="user">
					<div class="avatar"><a href="#"><img src="/images/avatar.png" width="32" height="32" />杨雅捷</a></div>
					<span>( <a href="#">作品</a> )</span>
				</div>
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#"><img src="/images/cover.jpg" width="230" height="148" />
						<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a><div class="citemtc"><span class="yy-icon yy-time">1月前</span></div></div>
					</a>
				</div>
				<div class="itemInfo">
					<a href="#" class="yy-icon comment"><span>23</span></a>
					<a href="#" class="yy-icon like"><span>42</span></a>
					<a href="#" class="yy-icon views"><span>248</span></a>
				</div>
				<div class="user">
					<div class="avatar"><a href="#"><img src="/images/avatar.png" width="32" height="32" />杨雅捷</a></div>
					<span>( <a href="#">作品</a> )</span>
				</div>
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#"><img src="/images/cover.jpg" width="230" height="148" />
						<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a><div class="citemtc"><span class="yy-icon yy-time">1月前</span></div></div>
					</a>
				</div>
				<div class="itemInfo">
					<a href="#" class="yy-icon comment"><span>23</span></a>
					<a href="#" class="yy-icon like"><span>42</span></a>
					<a href="#" class="yy-icon views"><span>248</span></a>
				</div>
				<div class="user">
					<div class="avatar"><a href="#"><img src="/images/avatar.png" width="32" height="32" />杨雅捷</a></div>
					<span>( <a href="#">作品</a> )</span>
				</div>
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#"><img src="/images/cover.jpg" width="230" height="148" />
						<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a><div class="citemtc"><span class="yy-icon yy-time">1月前</span></div></div>
					</a>
				</div>
				<div class="itemInfo">
					<a href="#" class="yy-icon comment"><span>23</span></a>
					<a href="#" class="yy-icon like"><span>42</span></a>
					<a href="#" class="yy-icon views"><span>248</span></a>
				</div>
				<div class="user">
					<div class="avatar"><a href="#"><img src="/images/avatar.png" width="32" height="32" />杨雅捷</a></div>
					<span>( <a href="#">作品</a> )</span>
				</div>
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#"><img src="/images/cover.jpg" width="230" height="148" />
						<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a><div class="citemtc"><span class="yy-icon yy-time">1月前</span></div></div>
					</a>
				</div>
				<div class="itemInfo">
					<a href="#" class="yy-icon comment"><span>23</span></a>
					<a href="#" class="yy-icon like"><span>42</span></a>
					<a href="#" class="yy-icon views"><span>248</span></a>
				</div>
				<div class="user">
					<div class="avatar"><a href="#"><img src="/images/avatar.png" width="32" height="32" />杨雅捷</a></div>
					<span>( <a href="#">作品</a> )</span>
				</div>
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#"><img src="/images/cover.jpg" width="230" height="148" />
						<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a><div class="citemtc"><span class="yy-icon yy-time">1月前</span></div></div>
					</a>
				</div>
				<div class="itemInfo">
					<a href="#" class="yy-icon comment"><span>23</span></a>
					<a href="#" class="yy-icon like"><span>42</span></a>
					<a href="#" class="yy-icon views"><span>248</span></a>
				</div>
				<div class="user">
					<div class="avatar"><a href="#"><img src="/images/avatar.png" width="32" height="32" />杨雅捷</a></div>
					<span>( <a href="#">作品</a> )</span>
				</div>
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#"><img src="/images/cover.jpg" width="230" height="148" />
						<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a><div class="citemtc"><span class="yy-icon yy-time">1月前</span></div></div>
					</a>
				</div>
				<div class="itemInfo">
					<a href="#" class="yy-icon comment"><span>23</span></a>
					<a href="#" class="yy-icon like"><span>42</span></a>
					<a href="#" class="yy-icon views"><span>248</span></a>
				</div>
				<div class="user">
					<div class="avatar"><a href="#"><img src="/images/avatar.png" width="32" height="32" />杨雅捷</a></div>
					<span>( <a href="#">作品</a> )</span>
				</div>
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#"><img src="/images/cover.jpg" width="230" height="148" />
						<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a><div class="citemtc"><span class="yy-icon yy-time">1月前</span></div></div>
					</a>
				</div>
				<div class="itemInfo">
					<a href="#" class="yy-icon comment"><span>23</span></a>
					<a href="#" class="yy-icon like"><span>42</span></a>
					<a href="#" class="yy-icon views"><span>248</span></a>
				</div>
				<div class="user">
					<div class="avatar"><a href="#"><img src="/images/avatar.png" width="32" height="32" />杨雅捷</a></div>
					<span>( <a href="#">作品</a> )</span>
				</div>
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#"><img src="/images/cover.jpg" width="230" height="148" />
						<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a><div class="citemtc"><span class="yy-icon yy-time">1月前</span></div></div>
					</a>
				</div>
				<div class="itemInfo">
					<a href="#" class="yy-icon comment"><span>23</span></a>
					<a href="#" class="yy-icon like"><span>42</span></a>
					<a href="#" class="yy-icon views"><span>248</span></a>
				</div>
				<div class="user">
					<div class="avatar"><a href="#"><img src="/images/avatar.png" width="32" height="32" />杨雅捷</a></div>
					<span>( <a href="#">作品</a> )</span>
				</div>
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#"><img src="/images/cover.jpg" width="230" height="148" />
						<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a><div class="citemtc"><span class="yy-icon yy-time">1月前</span></div></div>
					</a>
				</div>
				<div class="itemInfo">
					<a href="#" class="yy-icon comment"><span>23</span></a>
					<a href="#" class="yy-icon like"><span>42</span></a>
					<a href="#" class="yy-icon views"><span>248</span></a>
				</div>
				<div class="user">
					<div class="avatar"><a href="#"><img src="/images/avatar.png" width="32" height="32" />杨雅捷</a></div>
					<span>( <a href="#">作品</a> )</span>
				</div>
			</div>
		</div>
		<div id="pages">
			<a href="http://www.uehtml.com/?page=1" id="pageactive">1</a>
			<a href="http://www.uehtml.com/?page=2">2</a>
			<a href="http://www.uehtml.com/?page=3">3</a>
			<a href="http://www.uehtml.com/?page=4">4</a>
			<a href="http://www.uehtml.com/?page=5">5</a>
			<span>...</span>
			<a href="http://www.uehtml.com/?page=87">87</a>
			<a href="http://www.uehtml.com/?page=2" id="pagenext"><span class="yy-icon pagenext"></span></a></div>
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
</body>
</html>