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
			<div class="btns">
				<span>操作按钮：</span>
				<div class="btn"><input type="button" value="添加" onclick="Ad.showPopUp()"></div>
			</div>
			<table cellspacing="1">
				<tr>
					<th style="width:220px">图片</th>
					<th>广告区域</th>
					<th>顺序</th>
					<th>连接地址</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${adList}" var="ad">
					<tr>
						<td style="padding:5px"><a target="_blank" href="${ad.url }"><img src="${ad.imgUrl }" style="width:200px"></a></td>
						<td>
							<c:if test="${ad.adAreaType == 1}">幻灯片</c:if>
							<c:if test="${ad.adAreaType == 2}">幻灯片后面</c:if>
						</td>
						<td>${ad.indexNum}</td>
						<td>${ad.url}</td>
						<td>
							<div class="btns">
								<div class="btn"><input type="button" value="删除" onclick="Ad.delete('${ad.adId}')"></div>
							</div>
						</td>	
						</tr>
				</c:forEach>
			</table>
		<jsp:include page="/WEB-INF/jsp/include/pager.jsp"></jsp:include>
		</div>
	</div>
</div>

<input type="hidden" id="popUpAdId" />
<div id="adPopUp" class="popup" style="display:none">
		<div class="p_title"><h3>标题</h3><a href="javascript:Ad.hidePopUp()" class="close">x</a></div>
		<div class="p_cont">
			连接地址：<input type="text" class="w180" name="" id="url" autocomplete="off" style="color: rgb(153, 153, 153);"><br/><br/>
			图片上传：<input type="text" id="imgUrl" disabled="disabled" value="" class="newTxt w336" width="160">
			<span class="uploadBtn small"><input id="file" type="file" name="file" onchange="Ad.upload();"><span>选择上传</span></span><br/><br/>
			顺序：<input type="text" class="w180" name="" id="indexNum" autocomplete="off" style="color: rgb(153, 153, 153);"><br/><br/>
			区域：<select id="adAreaType">
					<option value="0">请选择</option>
					<c:forEach items="${adAreaList}" var="adArea">
						<c:if test="${adArea.key > 0 }">
							<option value="${adArea.key }">${adArea.desc }</option>
						</c:if>
					</c:forEach>
				 </select><br/><br/>
			<input onclick="Ad.add()" type="button" value="确定">
		</div>
	</div>
	
</body>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

</body>
</html>