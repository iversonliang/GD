<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录 - 优舞网</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<%@include file="/WEB-INF/jsp/js.inc.jsp"%>
<%@include file="/WEB-INF/jsp/taglib.inc.jsp"%>
<c:if test="${!empty username}">
<script type="text/javascript">
	window.location.href = "/index.do";
</script>
</c:if>
<script type="text/javascript"> 
	document.onkeydown=keyDownSearch;  
	
	function keyDownSearch(e) {    
	    // 兼容FF和IE和Opera    
	    var theEvent = e || window.event;    
	    var code = theEvent.keyCode || theEvent.which || theEvent.charCode;    
	    if (code == 13) {    
	        Login.login();
	    }    
	}  

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
<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
<div id="wrapper">
	<div class="main">
		<ul class="tab">
			<li><a href="/page/register.jsp">注册</a></li>
			<li class="active"><a href="/page/login.jsp">登录</a></li>
			<li><a href="/page/forget.jsp">忘记密码</a></li>
		</ul>
		<div class="content bg_w">
			<div class="reg_title yy-icon">你是优舞者吗？登录与众多舞者一起交流分享快乐吧！</div>
			<div class="contBody">
				<form method="post">
					<table width="100%" cellspacing="0" cellpadding="0" class="norTable">
						<tbody><tr>
							<th width="100">用户名/邮箱</th>
							<td><input id="username" type="text" name="user" value="" class="newTxt w250" tabindex="1" autocomplete="off"></td>
						</tr>
						<tr>
							<th>密码</th>
							<td>
								<div class="relative loginRx">
									<input type="password" id="password" name="pass" class="newTxt w250" tabindex="2"> 
																
								</div>
							</td>
						</tr>
						<!-- <tr>
							<th>验证码</th>
							<td>
								<div class="relative loginRx">
									<input type="code" id="code" name="code" class="newTxt w250" tabindex="2"> 
								</div>
							</td>
						</tr> -->
						<tr id="errorTips" style="display:none">
							<th></th>
							<td>
								<div class="relative">
									<span class="onShow" style="margin: 0px; padding: 0px; background-color: transparent; ">
							    		<span id="tips" name="tips"  class="txtMsg alert f12 ml1" style="margin-left:0px"></span>
							    	</span>
								</div>
							</td>
						</tr>
						<!-- <tr>
							<th></th>
							<td>
								<div class="relative loginRx">
									<img id="imgObj" src="/user/getCode.do" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="changeImg()">看不清，换一张</a>				
								</div>
							</td>
						</tr>
						 -->
						<!-- <tr>
							<th></th>
							<td><label><input name="autolog" type="checkbox" value="1" id="autolog" tabindex="3"> 下次自动登录</label></td>
						</tr> -->
						<tr class="last">
							<th></th>
							<td class="vm"><input type="button" value="登 录" class="lBtn" tabindex="4" onclick="Login.login()"> 
								<a class="ml10 c4095ce" href="/page/forget.jsp">忘记密码</a> <span class="c999">|</span> 
								<a class="c4095ce" href="/page/register.jsp">注册</a>
							</td>
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