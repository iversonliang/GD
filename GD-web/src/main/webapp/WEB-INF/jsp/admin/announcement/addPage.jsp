<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0">
<title>优舞网后台</title>
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
				<span>新增公告：</span>
			</div>
			<form action="/admin/announcement/add.do" >
				标题：<textarea rows="" cols="50" name="title"></textarea><br/>
				内容：<textarea rows="" cols="50" name="content"></textarea><br/>
				图标：<input type="file" name="imgUrl"><br/>
				<input type="submit" value="确定">
			</form>
		</div>
	</div>
</div>
</body>
<div id="footer">
	<div class="inner">
		<p class="manage"></p>
		<p class="copyright">版权所有，保留一切权利！ © 2014
			<a href="#">优舞 – Goodancer</a>
		</p>
	</div>
</div>

</body>
</html>
