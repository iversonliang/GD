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
				<div class="avatar"><a href="javascript:void(0)" class="noPointer"><img src="${user.headImg }"></a></div>
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
					作品数：<a href="javascript:void(0)" class="noPointer">${user.videoCount }</a>&nbsp;&nbsp;&nbsp;&nbsp;
					人气：<a href="javascript:void(0)" class="noPointer">545645</a>
				</div>
				<div class="clear"></div>
			</div>	
			<ul class="tab">
				<li <c:if test="${type == 1 }">class="active"</c:if>><a href="/video/personal.do?userId=${user.userId }&type=1">作品</a></li>
				<li <c:if test="${type == 2 }">class="active"</c:if>><a href="/video/personal.do?userId=${user.userId }&type=2">分享</a></li>
				<li <c:if test="${type == 3 }">class="active"</c:if>><a href="/video/personal.do?userId=${user.userId }&type=3">喜欢</a></li>
			</ul>
		</div>
	</div>
	<div class="main">
		<div class="content">
			<c:forEach items="${videoVoList}" var="video">
				<div class="citem">
					<div class="cover">
						<a href="/video/video.do?vid=${video.videoId }" target="_blank">
						<img src="${video.imgUrl }" />
						<div class="play_button"></div>
						</a>
					</div>
					<div class="citemtxt"><a class="citemtitle" target="_blank" href="/video/video.do?vid=${video.videoId }">${video.name }</a></div>
					<div class="itemInfo">
						<span class="yy-icon time">${video.deployTimeTips }</span>
						<a href="javasrcipt:vodi(0)" class="yy-icon comment noPointer"><span>${video.comments }</span></a>
						<a href="javasrcipt:vodi(0)" class="yy-icon like noPointer"><span>${video.love }</span></a>
						<a href="javasrcipt:vodi(0)" class="yy-icon views noPointer"><span>${video.play }</span></a>
					</div>
					<c:if test="${isMyPage == true }">
						<div class="edit">
							<table cellpadding="0" cellspacing="0">
								<tbody><tr>
									<c:if test="${param.type < 3 }">
										<td><a href="#">编辑</a></td>
									</c:if>
									<td><a href="/video/delete.do?type=${param.type }&vid=${video.videoId }" class="delete">删除</a></td>
								</tr>
							</tbody>
							</table>
						</div>
					</c:if>
				</div>
			</c:forEach>
		</div>
		<jsp:include page="/WEB-INF/jsp/include/pager.jsp"></jsp:include>
</div>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

</body>
</html>
