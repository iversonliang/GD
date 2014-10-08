<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人信息 - 优舞网</title>
<%@include file="/WEB-INF/jsp/taglib.inc.jsp"%>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<%@include file="/WEB-INF/jsp/js.inc.jsp"%>
<script type="text/javascript" src="/js/lib/comefrom.js"></script>
<script type="text/javascript">
	$(function() {init()})
</script>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
<div id="wrapper">
	<div class="main">
		<ul class="tab">
			<li class="active"><a href="/user/userInfo.do">基本资料</a></li>
			<li><a href="/user/headImg.do">修改头像</a></li>
			<li><a href="/user/updatePassword.do">修改密码</a></li>
		</ul>
		<div class="content bg_w">
			<div class="reg_title yy-icon">请如实填写以下内容，让其他舞者可以找到和关注你。</div>
			<div class="contBody">
				<form>
					<table width="650px" cellspacing="0" cellpadding="0" class="norTable tpersonal">
						<tbody>
						<tr>
							<th width="100">用户名</th>
							<td>${user.username }</td>
						</tr>
						<tr>
							<th>邮箱</th>
							<td class="vm" id="emailArea">
							${user.email }
							<%-- <span style="color:red;margin-left:20px;"><c:if test="${user.status == 1 }"> 邮箱已验证</c:if><c:if test="${user.status == 0 }"> 邮箱未验证</c:if></span>
							<span class="ml10 f12"><a class="grayBtn" href="#">修改邮箱</a></span> --%>
							</td>
						</tr>					
						<tr>
							<th>个人签名</th>
							<td><input maxlength="500" id="sign" onkeyup="User.checkSignLength()" name="signature" type="text" value="${user.sign }" class="newTxt w530"><p class="f12 c999">还可以输入 <b id="leftSignLength" class="cc20000 cf30 abc">38</b> 字符</p></td>
						</tr>
						<tr>
							<th>真实姓名</th>
							<td class="vm" colspan="2">
								<input id="realName" name="realName" type="text" value="${user.realName }" class="newTxt w350">
								<!-- <span class="selectBox ml10">
									<select name="realname_l" class="ml10">
										<option value="1" selected="selected">所有人可见</option>
										<option value="2">注册舞者可见</option>
										<option value="3">粉丝可见</option>
										<option value="4">保密</option>
									</select>
								</span> -->
							</td>
						</tr>
						<tr>
							<th>性别</th>
							<td>
							<span class="labelBox"><label><input type="radio" id="male" value="1" id="male" tabindex="5" name="maleChoose"<c:if test="${user.sex == 1 }">checked=""</c:if>> 男</label></span>
							<span class="labelBox ml10"><label><input type="radio" id="female" value="2" id="female" tabindex="6" name="maleChoose" <c:if test="${user.sex == 0 }">checked=""</c:if>> 女</label></span> </td>
                      	</tr>
						<tr>
							<th>生日</th>
							<td class="vm" colspan="2">
								<span class="selectBox">
								<select name="birthday_y" id="year">
									<option value="0" selected="selected">选择年份</option>
									<option value="1970">1970</option>
									<option value="1971">1971</option>
									<option value="1972">1972</option>
									<option value="1973">1973</option>
									<option value="1974">1974</option>
									<option value="1975">1975</option>
									<option value="1976">1976</option>
									<option value="1977">1977</option>
									<option value="1978">1978</option>
									<option value="1979">1979</option>
									<option value="1980">1980</option>
									<option value="1981">1981</option>
									<option value="1982">1982</option>
									<option value="1983">1983</option>
									<option value="1984">1984</option>
									<option value="1985">1985</option>
									<option value="1986">1986</option>
									<option value="1987">1987</option>
									<option value="1988">1988</option>
									<option value="1989">1989</option>
									<option value="1990">1990</option>
									<option value="1991">1991</option>
									<option value="1992">1992</option>
									<option value="1993">1993</option>
									<option value="1994">1994</option>
									<option value="1995">1995</option>
									<option value="1996">1996</option>
									<option value="1997">1997</option>
									<option value="1998">1998</option>
									<option value="1999">1999</option>
									<option value="2000">2000</option>
									<option value="2001">2001</option>
									<option value="2002">2002</option>
									<option value="2003">2003</option>
									<option value="2004">2004</option>
									<option value="2005">2005</option>
									<option value="2006">2006</option>
									<option value="2007">2007</option>
									<option value="2008">2008</option>
									<option value="2009">2009</option>
									<option value="2010">2010</option>
								</select></span>
								<span class="selectBox ml10">
								<select name="birthday_m" id="month">
									<option value="0" selected="selected">月份</option>
									<option value="01">1</option>
									<option value="02">2</option>
									<option value="03">3</option>
									<option value="04">4</option>
									<option value="05">5</option>
									<option value="06">6</option>
									<option value="07">7</option>
									<option value="08">8</option>
									<option value="09">9</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
								</select></span>
								<span class="selectBox ml10">
								<select name="birthday_d" id="day">
									<option value="0" selected="selected">日期</option>
									<option value="01">1</option>
									<option value="02">2</option>
									<option value="03">3</option>
									<option value="04">4</option>
									<option value="05">5</option>
									<option value="06">6</option>
									<option value="07">7</option>
									<option value="08">8</option>
									<option value="09">9</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
									<option value="13">13</option>
									<option value="14">14</option>
									<option value="15">15</option>
									<option value="16">16</option>
									<option value="17">17</option>
									<option value="18">18</option>
									<option value="19">19</option>
									<option value="20">20</option>
									<option value="21">21</option>
									<option value="22">22</option>
									<option value="23">23</option>
									<option value="24">24</option>
									<option value="25">25</option>
									<option value="26">26</option>
									<option value="27">27</option>
									<option value="28">28</option>
									<option value="29">29</option>
									<option value="30">30</option>
									<option value="31">31</option>
								</select></span>
								<!-- <span class="selectBox ml10">
									<select name="realname_l" class="ml10">
										<option value="1" selected="selected">所有人可见</option>
										<option value="2">注册舞者可见</option>
										<option value="3">粉丝可见</option>
										<option value="4">保密</option>
									</select>
								</span> -->
							</td>
						</tr>
						<tr>
							<th>所在地区</th>
							<td>
								<span class="selectBox">
									<select id="province" name="province" onChange = "select()"></select>
								</span>
								<span class="selectBox ml10">
									<select id="city" name="city" onChange = "select()"></select>
								</span>
							</td>
						</tr>
						<!-- <tr>
							<th>擅长舞种</th>
							<td class="vm" colspan="2">
								<span class="selectBox">
								<select name="style" id="danceType">
									<option selected="selected" value="0">选择舞种</option>
									<option>Breakin</option>
									<option>Hiphop</option>
									<option>Jazz</option>
									<option>Lockin</option>
									<option>Lyrical</option>
									<option>Krump</option>
									<option>Poppin</option>
									<option>Raggae</option>
									<option>现代舞</option>
									<option>鬼步</option>
								</select></span>
								<span><input type="button" id="addDanceType" tabindex="3" onclick="User.addDanceType()" value="添加" class="lBtn sBtn"></span>
								<ul class="selectTags" id="danceTypeList">
									<li style="margin-bottom:5px"><span name="selectedDanceType">Lyrical</span><a href="javascript:void(0)" onclick="User.delDanceType('Lyrical', this)" class="delete">x</a></li>
								</ul>
							</td>
						</tr> -->
						<tr>
							<th>个人简介</th>
							<td colspan="2"><textarea id="description" value="${user.description }" onkeyup="User.checkDescriptionLength()" maxlength="200" style="width:500px;height:100px;" name="desc" class="newArea w530 h150"></textarea><p class="f12 c999">还可以输入 <b id="leftDescriptionLength" class="cc20000 cf30 abc">200</b> 字符</p></td>
						</tr>
						<tr>
							<th></th>
							<td colspan="2"><input type="button" onclick="User.save()" value="保 存" class="lBtn"></td>
						</tr>
					</tbody></table>
					<div class="per_avatar">
						<a href="/user/headImg.do"><img width="145" height="145" src="${user.headImg }"></a>
						<a class="change_avatar" href="/user/headImg.do">修改头像</a>
					</div>
				</form>
				<div class="clear"></div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
<script type="text/javascript">
	var description = '${user.description}';
	$("#description").val(description)
	var danceTypeStr = '${user.danceType}';
	if (Common.isNotEmpty(danceTypeStr)) {
		var temp = danceTypeStr.split("/");
		for (var i=0;i<temp.length;i++) {
			User.appendDanceTypeLabel(temp[i]);
			User.removeDaneTypeSelectNode(temp[i]);
		}
	}
	var birthday = '${user.birthday}';
	if (Common.isNotEmpty(birthday)) {
		birthday = birthday.substring(0, 10);
		temp = birthday.split("-");
		$("#year").val(temp[0]);
		$("#month").val(temp[1]);
		$("#day").val(temp[2]);
	}
	User.checkSignLength();
	User.checkDescriptionLength();
	$(function(){
		$("#province").val('${user.province}');
		select();
		$("#city").val('${user.city}');
	})
</script>
</body>
</html>
