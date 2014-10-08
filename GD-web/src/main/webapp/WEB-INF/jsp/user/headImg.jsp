<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改头像 - 优舞网</title>

<link rel="stylesheet" type="text/css" href="/css/style.css">
<%@include file="/WEB-INF/jsp/js.inc.jsp"%>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
<div id="wrapper">
	<div class="main">
		<ul class="tab">
			<li><a href="/user/userInfo.do">基本资料</a></li>
			<li class="active"><a href="/user/headImg.do">修改头像</a></li>
			<li><a href="/user/updatePassword.do">修改密码</a></li>
		</ul>
		<div class="content bg_w">
			<div class="reg_title yy-icon">设置属于自己的头像，彰显个性！</div>
			<div class="contBody">
				<form method="get">
					<table cellpadding="0" cellspacing="0" width="100%">
						<tbody>
						<tr>
							<td class="" width="240" align="center">
								<img id="headImg" src="${headImg }" width="145" height="145">
								<p class="c999 pt10">当前头像</p>
							</td>
							<td colspan="3" class="center vm">
							   <input type="text" id="upTxt" disabled="disabled" value="" class="newTxt w336" width="360">
							   <span class="uploadBtn small"><input id="file" type="file" name="file" onchange="User.upload();"><span>选择上传</span></span>
							</td>
						</tr>
					</tbody></table>
					<input type="hidden" id="headImgUrl" />
					<div class="sumit_avatar"><input id="updateHeadBtn" onclick="User.updateHeadImg()" style="display:none" type="button" class="lBtn" value="提 交"></div>
				</form>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

</body>
</html>
