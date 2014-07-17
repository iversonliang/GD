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
				<div class="btn"><input type="button" value="添加" onclick="window.location.href='/admin/announcement/addPage.do'"></div>
			</div>
			<table cellspacing="1">
				<tr>
					<th>图标</th>
					<th>标题</th>
					<th>内容</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${announcementList}" var="announcement">
					<tr>
						<td><img src="${announcement.imgUrl }"></td>
						<td>${announcement.title }</td>
						<td>${announcement.content }</td>
						<td>
							<div class="btns">
								<div class="btn"><input type="button" value="编辑"></div>
								<div class="btn"><input type="button" value="删除" onclick="Announcement.delete('${announcement.announcementId}')"></div>
							</div>
						</td>	
						</tr>
				</c:forEach>
			</table>
		<jsp:include page="/WEB-INF/jsp/include/pager.jsp"></jsp:include>
		</div>
	</div>
</div>
</body>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

</body>
</html>
