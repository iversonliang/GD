<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>优舞网</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<script type="text/javascript" src="/js/jquery-1.6.4.min.js"></script>
<script type="text/javascript"> 
    function changeImg() {
        var timestamp = (new Date()).valueOf(); 
        var url = "/user/getCode.do";
        if ((url.indexOf("&") >= 0)) { 
            url = url + "&tamp=" + timestamp; 
        } else { 
            url = url + "?timestamp=" + timestamp; 
        }
        $("#imgObj").attr("src", url); 
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
	<div class="main">
		<ul class="tab">
			<li><a href="/page/register.jsp">注册</a></li>
			<li class="active"><a href="/page/login.jsp">登录</a></li>
			<li><a href="/page/forget.jsp">忘记密码</a></li>
		</ul>
		<div class="content bg_w">
			<div class="reg_title yy-icon">你是优舞者吗？登录与25,486位舞者一起交流分享快乐吧！</div>
			<div class="contBody">
				<form action="/user/login.do" method="post">
					<table width="100%" cellspacing="0" cellpadding="0" class="norTable">
						<tbody><tr>
							<th width="100">用户名/邮箱</th>
							<td><input type="text" name="user" value="" class="newTxt w250" tabindex="1" autocomplete="off"></td>
						</tr>
						<tr>
							<th>密码</th>
							<td>
								<div class="relative loginRx">
									<input type="password" name="pass" class="newTxt w250" tabindex="2"> 
																
								</div>
							</td>
						</tr>
						<tr>
							<th>验证码</th>
							<td>
								<div class="relative loginRx">
									<input type="code" name="code" class="newTxt w250" tabindex="2"> 
									<img id="imgObj" src="/user/getCode.do" /><input type="button" value="change" onclick="changeImg()">							
								</div>
							</td>
						</tr>
						
						<tr>
							<th></th>
							<td><label><input name="autolog" type="checkbox" value="1" id="autolog" tabindex="3"> 下次自动登录</label></td>
						</tr>
						<tr class="last">
							<th></th>
							<td class="vm"><input type="submit" value="登 录" class="lBtn" tabindex="4"> <a class="ml10 c4095ce" href="toforgetpsw.do">忘记密码</a> <span class="c999">|</span> <a class="c4095ce" href="toregister.do?tourl=">注册</a></td>
						</tr>
					</tbody></table>
				</form>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
</body>
</html>