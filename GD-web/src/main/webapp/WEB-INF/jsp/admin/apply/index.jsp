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
				<div class="btn"><input type="button" value="生成邀请码" onclick="InviteCode.showPopUp()"></div>
			</div>
			<table cellspacing="1">
				<tr>
					<th style="width:120px">名称</th>
					<th>邮箱地址</th>
					<th>来自哪里</th>
					<th>舞团</th>
					<th>作品地址</th>
					<th>申请时间</th>
					<th>分配邀请码</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${list}" var="apply">
					<tr>
						<td>${apply.name }</td>
						<td>${apply.email}</td>
						<td>${apply.location}</td>
						<td>${apply.crew}</td>
						<td>${apply.oupsUrl}</td>
						<td><fmt:formatDate value="${apply.posttime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${apply.inviteCodeId}</td>
						<td>
							<div class="btns">
								<c:if test="${apply.pass == false}">
									<div class="btn"><input type="button" value="通过" onclick="Apply.pass('${apply.applyId }')"></div>
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

<input type="hidden" id="popUpAdId" />
<div id="popUp" class="popup" style="display:none">
		<div class="p_title"><h3>标题</h3><a href="javascript:InviteCode.hidePopUp()" class="close">x</a></div>
		<div class="p_cont">
			生成邀请码数量：<input type="text" class="w180" name="" id="num" autocomplete="off" style="color: rgb(153, 153, 153);">
			<input onclick="InviteCode.create()" type="button" value="确定">
		</div>
	</div>
	
</body>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

</body>
</html>