<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>优舞网</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<%@include file="/WEB-INF/jsp/taglib.inc.jsp"%>
<%@include file="/WEB-INF/jsp/js.inc.jsp"%>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
<div class="wrapper pwrapper">
	<div class="userBox">
		<div class="userarea">
			<div class="uitem">
				<div class="avatar"><a href="#"><img src="${user.headImg }"></a></div>
				<div class="userInfo">
					<div class="atPerson">
						<div class="vm">${user.username }</div>
						<c:if test="${user.sex == 0 }">女</c:if><c:if test="${user.sex == 1 }">男</c:if> <span>/</span> 欧洲 <span >/</span> 街舞　<br>
						<!--<div>
							<p class="atPersonDes">个人签名个人签名个人签名个人签名</p>
						</div>-->
						<div class="userList_cz">
							<!-- <a href="#" class="btnfollow">加关注</a> -->
							<c:if test="${isMyPage == true }">
								<a href="/user/userInfo.do" class="btnedit">编辑资料</a>
							</c:if>
						</div>
					</div>
				</div>
				<div class="userPdata">
					<p class="hbzl-s4">人气：<span class="hbzl-s4t hbzl-s4tb">281</span></p>
					作品数：<a href="#">${user.videoCount }</a>&nbsp;&nbsp;&nbsp;&nbsp;
					人气：<a href="#" target="_blank">545645</a>
				</div>
				<div class="clear"></div>
			</div>	
			<ul class="tab">
				<li class="active"><a href="#">作品</a></li>
				<li><a href="#">分享</a></li>
				<li><a href="#">喜欢</a></li>
			</ul>
		</div>
	</div>
	<div class="main">
		<div class="content">
			<div class="citem">
				<div class="cover">
					<a href="#">
					<img src="images/cover.jpg" />
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
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#">
					<img src="images/cover.jpg" />
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
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#">
					<img src="images/cover.jpg" />
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
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#">
					<img src="images/cover.jpg" />
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
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#">
					<img src="images/cover.jpg" />
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
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#">
					<img src="images/cover.jpg" />
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
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#">
					<img src="images/cover.jpg" />
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
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#">
					<img src="images/cover.jpg" />
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
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#">
					<img src="images/cover.jpg" />
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
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#">
					<img src="images/cover.jpg" />
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
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#">
					<img src="images/cover.jpg" />
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
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#">
					<img src="images/cover.jpg" />
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
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#">
					<img src="images/cover.jpg" />
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
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#">
					<img src="images/cover.jpg" />
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
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#">
					<img src="images/cover.jpg" />
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
			</div>
			<div class="citem">
				<div class="cover">
					<a href="#">
					<img src="images/cover.jpg" />
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
