<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>优舞网</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<%@include file="/WEB-INF/jsp/js.inc.jsp"%>
<%@include file="/WEB-INF/jsp/taglib.inc.jsp"%>
<c:if test="${!empty username}">
<script type="text/javascript">
	window.location.href = "/index.do";
</script>
</c:if>
<script type="text/javascript" src="/js/lib/comefrom.js"></script>
<script type="text/javascript">
	$(function() {init()})
    function changeImg() {
        var imgSrc = $("#imgObj"); 
        var src = imgSrc.attr("src");
        imgSrc.attr("src", chgUrl(src)); 
    } 
      
    function chgUrl(url) {
        var timestamp = (new Date()).valueOf(); 
        url = url.substring(0, 17); 
        if ((url.indexOf("&") >= 0)) { 
            url = url + "&tamp=" + timestamp; 
        } else { 
            url = url + "?timestamp=" + timestamp; 
        }
        return url; 
    } 
</script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
<div id="wrapper">
	<div class="main">
		<ul class="tab">
			<li class="active"><a href="/page/register.jsp">注册</a></li>
			<li><a href="/page/login.jsp">登录</a></li>
			<li><a href="/page/forget.jsp">忘记密码</a></li>
		</ul>
		<div class="content bg_w">
			<div class="reg_title yy-icon">欢迎注册优舞网，以下各项均必填。</div>
			<div class="contBody">
				<form method="post">
					<table width="100%" class="norTable register">
					  <tbody><tr>
						<th width="100">用户名</th>
					    <td>
					    	<input type="text" onblur="Register.checkUsername()" onfocus="Register.hideTips()" class="newTxt w180" name="username" id="username" tabindex="1" autocomplete="off" style="color: rgb(153, 153, 153);"> 
					    	<span class="onError" style="margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;">
					    		<span id="usernameTip" name="tips" style="display:none" class="txtMsg alert f12 ml1"></span>
					    	</span>
					    </td>
                      </tr>
                     
                      <tr>
	                	<th>密码</th>
	                    <td>
	                    	<input type="password" onblur="Register.checkPassword()" onfocus="Register.hideTips()" class="newTxt w180" id="password" name="password" tabindex="2"> 
	                    	<span class="onShow" style="margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;">
	                    		<span id="passwordTip" name="tips" style="display:none" class="txtMsg alert f12 ml1"></span>
	                    	</span>
	                    </td>
                      </tr>
                    
                      <tr>
	                	<th>确认密码</th>
	                    <td>
	                    	<input type="password" onblur="Register.checkRepassword()" onfocus="Register.hideTips()" class="newTxt w180" name="repassword" id="repassword" tabindex="3"> 
	                    	<span class="onShow" style="margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;">
	                    		<span id="repasswordTip" name="tips" style="display:none" class="txtMsg alert f12 ml1"></span>
	                    	</span>
	                    </td>
                      </tr>
                    
                      <tr>
	                	<th>电子邮箱</th>
	                    <td>
	                    	<input type="text" onblur="Register.checkEmail()" onfocus="Register.hideTips()" id="email" name="email" tabindex="4" class="newTxt w250"> 
	                    	<span style="margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;" class="onShow">
	                    		<span id="emailTip" name="tips" style="display:none" class="txtMsg alert f12 ml1">您输入的邮箱不符合要求，请检查</span>
	                    	</span>	
	                    </td>
                      </tr>
                     <tr>
						<th>验证码</th>
						<td>
								<input type="code" onblur="Register.checkCode()" onfocus="Register.hideTips()" id="code" name="code" class="newTxt w250" tabindex="2"> 
								<span style="margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;" class="onShow">
	                    			<span id="codeTip" name="tips" style="display:none" class="txtMsg alert f12 ml1">请输入验证码</span>
	                    		</span>	
						</td>
					</tr>
                      <tr>
							<th></th>
							<td>
								<div class="relative loginRx">
									<img id="imgObj" src="/user/getCode.do" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="changeImg()">看不清，换一张</a>				
								</div>
							</td>
					  </tr>
                      <tr>
	                	<th>性别</th>
	                    <td>
	                    <span class="labelBox"><label><input type="radio" value="1" id="male" tabindex="5" name="maleChoose" checked=""> 男</label></span>
	                    <span class="labelBox ml10"><label><input type="radio" value="2" id="female" tabindex="6" name="maleChoose"> 女</label></span> </td>
                      </tr>
                      
                      <tr>
	                	<th>性质</th>
	                    <td>
	                    <span class="labelBox"><label><input type="radio" value="1" id="single" tabindex="5" name="groupChoose" checked=""> 个人</label></span>
	                    <span class="labelBox ml10"><label><input type="radio" value="2" id="crew" tabindex="6" name="groupChoose"> 团体</label></span> </td>
                      </tr>
					 
					 <!--<tr>
	                	<th>舞种</th>
	                    <td><span class="selectBox">
	                    	<select name="zhiye" id="zhiye">
	                    		<option value="0" selected="selected" tabindex="7">选择舞种</option>
								<option value="1">Breakin</option>
								<option value="2">HiphopJazz</option>
								<option value="3">Lockin</option>
								<option value="4">Lyrical</option>
								<option value="5">Krump</option>
								<option value="6">Poppin</option>
								<option value="7">All Style</option>
								
								
								<option value="13">其他</option>
								
	                    	</select></span> <span id="zhiyeTip" class="onShow" style="margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;"></span></td>
                      </tr>
					 -->
                      
                      <tr>
	                	<th>现居</th>
	                    <td>
							<span class="selectBox">
								<select id="province" name="province" onChange = "select()"></select>
							</span>
							<span class="selectBox ml10">
								<select id="city" name="city" onChange = "select()"></select>
							</span>
							<span style="margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;" class="onShow">
	                    		<span id="locationTip" name="tips" style="display:none" class="txtMsg alert f12 ml1">请选择居住地</span>
	                    	</span>	
						</td>
                      </tr>
                     
                      
                      <tr>
	                	<th></th>
	                    <td><label class="regCheckLink"><input type="checkbox" tabindex="9" name="checkbox" id="yuedu" onchange="Register.changeRead()"> <span class="c999">我已阅读并接受</span> <a class="c4095ce" href="/service/copyright.html" target="_blank">版权声明</a> <span class="c999">和</span> <a class="c4095ce" href="/service/privacy.html" target="_blank">隐私保护</a> <span class="c999">条款</span></label> <span id="yueduTip" class="onShow" style="margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;"></span></td>
                      </tr>
					 
                      <tr>
                        <th scope="row"></th>
                        <td class="vm">
                        	<span style="display:none" id="registerBtnDiv">
                        		<input type="button" id="regbtn" onclick="Register.register()" tabindex="10" class="lBtn" btnmode="true" value="免费注册">&nbsp;&nbsp;&nbsp;
                        	</span>
                        	<span class="caaa">已注册请</span> <a href="/page/login.jsp" class="c009cff">登录</a>
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