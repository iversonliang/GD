<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<%@include file="/WEB-INF/jsp/taglib.inc.jsp"%>
<meta name="viewport" content="width=device-width; initial-scale=1.0">
<title>首页</title>
<%@include file="/WEB-INF/jsp/js.inc.admin.jsp"%>
<link href="/css/admin/style.css" rel="stylesheet" type="text/css">
</head>

<body>
<div id="topBar">
	<h1>优舞网后台</h1>
</div>
<div id="container">
	<jsp:include page="/WEB-INF/jsp/include/admin/menu.jsp"></jsp:include>
	<div id="main">
		<div class="form">
			<table cellspacing="1">
				<tr>
					<th style="width:220px">视频</th>
					<th style="width:180px">标题</th>
					<th>作者</th>
					<th>播放次数</th>
					<th>评论数</th>
					<th>喜爱数</th>
					<th>首页类型</th>
					<th>顺序</th>
					<th>推荐等级</th>
					<th>是否删除</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${videoList}" var="video">
					<tr>
						<td style="padding:5px"><a target="_blank" href="/video/video.do?vid=${video.videoId }"><img src="${video.imgUrl }" style="width:200px"></a></td>
						<td><a target="_blank" href="/video/video.do?vid=${video.videoId }">${video.name}</a></td>
						<td>${video.nickname}</td>
						<td>${video.play}</td>
						<td>${video.comments}</td>
						<td>${video.love}</td>
						<td>
							<c:if test="${video.homeType == 0}">非首页视频</c:if>
							<c:if test="${video.homeType == 1}">推荐视频</c:if>
						</td>
						<td>${video.indexNum}</td>
						<td><c:if test="${video.videoGradeType == 0 }">没有</c:if><c:if test="${video.videoGradeType == 1 }">编辑推荐</c:if><c:if test="${video.videoGradeType == 2 }">普通推荐</c:if></td>
						<th><c:if test="${video.del == true}">是</c:if>
							<c:if test="${video.del == false}">否</c:if></th>
						<td>
							<div class="btns">
								<div class="btn"><input type="button" value="设置编辑推荐" onclick="Video.showVideGradeTypePopUp('${video.videoId}')"></div><br />
								<c:if test="${video.del == false}">
									<c:if test="${video.homeType == 0}">
										<div class="btn"><input type="button" value="设置首页推荐" onclick="Video.showPopUp('${video.videoId}')"></div><br />
									</c:if>
									<c:if test="${video.homeType > 0}">
										<div class="btn"><input type="button" value="取消首页推荐" onclick="Video.delHomeType('${video.videoId}')"></div><br />
									</c:if>
									<div class="btn"><input type="button" value="删除" onclick="Video.delete('${video.videoId}')"></div>
								</c:if>
								<c:if test="${video.del == true}">
									<div class="btn"><input type="button" value="取消删除" onclick="Video.unDelete('${video.videoId}')"></div>
								</c:if>
							</div>
						</td>	
						</tr>
				</c:forEach>
			</table>
		<jsp:include page="/WEB-INF/jsp/include/pager.jsp"></jsp:include>
		</div>
	</div>
</div>

<input type="hidden" id="popUpVideoId" />
<div id="homeTypePopUp" class="popup" style="display:none">
		<div class="p_title"><h3>标题</h3><a href="javascript:Video.hidePopUp()" class="close">x</a></div>
		<div class="p_cont">
			编辑推荐等级：
				<input type="radio" name="radiobutton" value="0">没有 
				<input type="radio" name="radiobutton" value="1">编辑推荐 
				<input type="radio" name="radiobutton" value="2">普通推荐
				&nbsp;&nbsp;<input onclick="Video.setGradeType()" type="button" value="确定">
		</div>
	</div>
	
<div id="videoGradeTypePopUp" class="popup" style="display:none">
		<div class="p_title"><h3>标题</h3><a href="javascript:Video.hidePopUp()" class="close">x</a></div>
		<div class="p_cont">
			首页推荐位置：<input type="text" class="w180" name="" id="indexNum" autocomplete="off" style="color: rgb(153, 153, 153);">&nbsp;&nbsp;<input onclick="Video.setHomeType()" type="button" value="确定">
		</div>
	</div>

</body>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

</body>
</html>