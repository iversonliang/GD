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
</head>

<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
<div id="header">
	<div class="bannar">
		<div class="bannar_pic">
			<c:if test="${requestScope.isLogin == null || isLogin == false }">
				<a href="/page/register.jsp" class="btn">立即注册</a>
			</c:if>
		</div>
	</div>
</div>
<div id="wrapper">
	<h2>推荐视频</h2>
	<div class="main">
		<div class="content">
			<div class="citem">
				<div class="cover">
					<a href="#">
					<img src="/images/cover.jpg" />
					<div class="play_button"></div>
					</a>
				</div>
				<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a></div>
				<div class="itemInfo">
					<span class="yy-icon time">1月前</span>
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
					<a href="#">
					<img src="/images/cover.jpg" />
					<div class="play_button"></div>
					</a>
				</div>
				<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a></div>
				<div class="itemInfo">
					<span class="yy-icon time">1月前</span>
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
					<a href="#">
					<img src="/images/cover.jpg" />
					<div class="play_button"></div>
					</a>
				</div>
				<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a></div>
				<div class="itemInfo">
					<span class="yy-icon time">1月前</span>
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
					<a href="#">
					<img src="/images/cover.jpg" />
					<div class="play_button"></div>
					</a>
				</div>
				<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a></div>
				<div class="itemInfo">
					<span class="yy-icon time">1月前</span>
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
					<a href="#">
					<img src="/images/cover.jpg" />
					<div class="play_button"></div>
					</a>
				</div>
				<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a></div>
				<div class="itemInfo">
					<span class="yy-icon time">1月前</span>
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
					<a href="#">
					<img src="/images/cover.jpg" />
					<div class="play_button"></div>
					</a>
				</div>
				<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a></div>
				<div class="itemInfo">
					<span class="yy-icon time">1月前</span>
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
					<a href="#">
					<img src="/images/cover.jpg" />
					<div class="play_button"></div>
					</a>
				</div>
				<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a></div>
				<div class="itemInfo">
					<span class="yy-icon time">1月前</span>
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
					<a href="#">
					<img src="/images/cover.jpg" />
					<div class="play_button"></div>
					</a>
				</div>
				<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a></div>
				<div class="itemInfo">
					<span class="yy-icon time">1月前</span>
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
					<a href="#">
					<img src="/images/cover.jpg" />
					<div class="play_button"></div>
					</a>
				</div>
				<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a></div>
				<div class="itemInfo">
					<span class="yy-icon time">1月前</span>
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
					<a href="#">
					<img src="/images/cover.jpg" />
					<div class="play_button"></div>
					</a>
				</div>
				<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a></div>
				<div class="itemInfo">
					<span class="yy-icon time">1月前</span>
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
					<a href="#">
					<img src="/images/cover.jpg" />
					<div class="play_button"></div>
					</a>
				</div>
				<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a></div>
				<div class="itemInfo">
					<span class="yy-icon time">1月前</span>
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
					<a href="#">
					<img src="/images/cover.jpg" />
					<div class="play_button"></div>
					</a>
				</div>
				<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a></div>
				<div class="itemInfo">
					<span class="yy-icon time">1月前</span>
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
					<a href="#">
					<img src="/images/cover.jpg" />
					<div class="play_button"></div>
					</a>
				</div>
				<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a></div>
				<div class="itemInfo">
					<span class="yy-icon time">1月前</span>
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
					<a href="#">
					<img src="/images/cover.jpg" />
					<div class="play_button"></div>
					</a>
				</div>
				<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a></div>
				<div class="itemInfo">
					<span class="yy-icon time">1月前</span>
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
					<a href="#">
					<img src="/images/cover.jpg" />
					<div class="play_button"></div>
					</a>
				</div>
				<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a></div>
				<div class="itemInfo">
					<span class="yy-icon time">1月前</span>
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
					<a href="#">
					<img src="/images/cover.jpg" />
					<div class="play_button"></div>
					</a>
				</div>
				<div class="citemtxt"><a class="citemtitle" target="_blank" href="#">MONTWALK男士皮具项目提案</a></div>
				<div class="itemInfo">
					<span class="yy-icon time">1月前</span>
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
			<a href="#" id="pageactive">1</a>
			<a href="#">2</a>
			<a href="#">3</a>
			<a href="#">4</a>
			<a href="#">5</a>
			<span>...</span>
			<a href="#">87</a>
			<a href="#" id="pagenext"><span class="yy-icon pagenext"></span></a></div>
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

</body>
</html>
