<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>优舞网</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<%@include file="/WEB-INF/jsp/js.inc.jsp"%>
<script type="text/javascript"> 
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
					    	<input type="text" class="newTxt w180" name="username" id="username" tabindex="1" autocomplete="off" style="color: rgb(153, 153, 153);"> 
					    	<span class="onError" style="margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;">
					    		<span id="usernameTip" name="tips" style="display:none" class="txtMsg alert f12 ml1">您输入的用户名不符合要求，请检查。</span>
					    	</span>
					    </td>
                      </tr>
                     
                      <tr>
	                	<th>密码</th>
	                    <td>
	                    	<input type="password" class="newTxt w180" id="password" name="password" tabindex="2"> 
	                    	<span class="onShow" style="margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;">
	                    		<span id="passwordTip" name="tips" style="display:none" class="txtMsg alert f12 ml1">您输入的密码不符合要求，请检查。</span>
	                    	</span>
	                    </td>
                      </tr>
                    
                      <tr>
	                	<th>确认密码</th>
	                    <td>
	                    	<input type="password" class="newTxt w180" name="repassword" id="repassword" tabindex="3"> 
	                    	<span class="onShow" style="margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;">
	                    		<span id="repasswordTip" name="tips" style="display:none" class="txtMsg alert f12 ml1">您两次输入的密码不一致，请检查。</span>
	                    	</span>
	                    </td>
                      </tr>
                    
                      <tr>
	                	<th>电子邮箱</th>
	                    <td>
	                    	<input type="text" id="email" name="email" tabindex="4" class="newTxt w250"> 
	                    	<span style="margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;" class="onShow">
	                    		<span id="emailTip" name="tips" style="display:none" class="txtMsg alert f12 ml1">您输入的邮箱不符合要求，请检查。</span>
	                    	</span>	
	                    </td>
                      </tr>
                     <tr>
							<th>验证码</th>
							<td>
								<div class="relative loginRx">
									<input type="code" id="code" name="code" class="newTxt w250" tabindex="2"> 
								</div>
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
	                    	<select name="province" id="province" tabindex="8" onchange="ChangeOption('regform','province','city')">
	                    	<option value="0" selected="selected">选择省份</option>
	                    	<option value="1">北京</option>
							<option value="2">上海</option>
							<option value="3">天津</option>
							<option value="4">重庆</option>
							<option value="5">黑龙江</option>
							<option value="6">辽宁</option>
							<option value="7">吉林</option>
							<option value="8">河北</option>
							<option value="9">内蒙古</option>
							<option value="10">陕西</option>
							<option value="11">山西</option>
							<option value="12">甘肃</option>
							<option value="13">宁夏</option>
							<option value="14">新疆</option>
							<option value="15">西藏</option>
							<option value="16">青海</option>
							<option value="17">四川</option>
							<option value="18">云南</option>
							<option value="19">贵州</option>
							<option value="20">湖南</option>
							<option value="21">湖北</option>
							<option value="22">河南</option>
							<option value="23">山东</option>
							<option value="24">安徽</option>
							<option value="25">江苏</option>
							<option value="26">浙江</option>
							<option value="27">台湾</option>
							<option value="28">香港</option>
							<option value="29">澳门</option>
							<option value="30">广东</option>
							<option value="31">广西</option>
							<option value="32">江西</option>
							<option value="33">福建</option>
							<option value="34">海南</option>
							<option value="35">其它</option>
							<option value="36">美国</option>
							<option value="37">欧洲</option>
							<option value="38">日本</option>
							<option value="39">韩国</option>
							<option value="40">新加坡</option>
							<option value="41">加拿大</option>
							<option value="42">亚　洲</option>
							<option value="43">非　洲</option>
							<option value="44">澳　洲</option>
							<option value="45">南美洲</option>
							<option value="46">东南亚</option>
	                    	</select></span>
	                    	<span class="selectBox ml10">
	                    	<select name="city" id="city">
	                    	<option value="0" selected="selected">选择城市</option>
	                    	</select></span> <span id="provinceTip" class="onShow" style="margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;"></span></td>
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