<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>优舞网</title>
<%@include file="/WEB-INF/jsp/taglib.inc.jsp"%>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<%@include file="/WEB-INF/jsp/js.inc.jsp"%>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
<div id="wrapper">
	<div class="main">
		<ul class="tab">
			<li class="active"><a href="#">投稿</a></li>
		</ul>
		<div class="content bg_w">
			<!-- <div class="reg_title yy-icon">上传封面</div> -->
			<div class="contBody">
				<!-- <div class="submit_link">
					<input type="text" value="输入或粘贴视频播放页地址">
					<a href="#" class="submit_confirm">确定</a>
					<div class="clear"></div>
					<p>支持： 
						<a target="_blank" href="http://www.youku.com/">优酷网</a>、 <a target="_blank" href="http://www.tudou.com/">土豆网</a>、 
						<a target="_blank" href="http://tv.sohu.com/">搜狐视频</a>、 <a target="_blank" href="http://v.qq.com/">腾讯视频</a>的视频播放页链接。
					</p>
				</div> -->
				<div class="submit_info">
					<form method="post">
						<table width="100%" class="norTable register">
						  <tbody>
						  <tr>
							<th width="100">标题</th>
							<td><input type="text" class="newTxt w180" name="username" id="name" tabindex="1" autocomplete="off" style="color: rgb(153, 153, 153);"> <span id="usernameTip" class="onError" style="margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;"></span></td>
						  </tr>
						  <tr>
							<th width="100">视频地址</th>
							<td><input type="text" class="newTxt w180" name="username" id="url" tabindex="1" autocomplete="off" style="color: rgb(153, 153, 153);"> <span id="usernameTip" class="onError" style="margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;">
								<div class="clear"></div>
								<p>支持： 
									<a target="_blank" href="http://www.youku.com/">优酷网</a>、 <a target="_blank" href="http://www.tudou.com/">土豆网</a>、 
									<a target="_blank" href="http://tv.sohu.com/">搜狐视频</a>、 <a target="_blank" href="http://v.qq.com/">腾讯视频</a>的视频播放页链接。
								</p>
							</span></td>
						  </tr>
						  <tr>
							<th>分类</th>
							<td>
								<span class="selectBox">
								<select name="classify" id="type">
								<option value="-1" selected="selected">选择分类</option>
								<option value="0">编舞</option>
								<option value="1">Solo</option>
								<option value="2">Battle</option>
								<option value="3">教学</option>
								<option value="4">专访</option>
								<option value="5">电影</option>
								</select></span> <span id="provinceTip" class="onShow" style="margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;"></span></td>

							</td>
						  </tr>
						  <tr>
							<th>类型</th>
							<td>
								<span class="selectBox">
								<select name="type" id="sourceType">
								<option value="-1" selected="selected">选择类型</option>
								<option value="0">原创</option>
								<option value="1">转载</option>
								</select></span> <span id="provinceTip" class="onShow" style="margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;"></span></td>

							</td>
						  </tr>
						
						  <tr>
							<th>标签</th>
							<td class="testing"><div class="relative" style="white-space:nowrap"><input type="text" name="label" id="label" tabindex="4" class="newTxt w250"> <span id="emailTip" style="position: absolute; left: 262px; margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;" class="onShow"></span></div></td>
						  </tr>
						 
						<tr>
							<th>简介</th>
							<td colspan="2">
								<textarea id="description" onkeyup="Video.checkDescriptionLength()" maxlength="200" style="width:500px;height:100px;" name="desc" class="newArea w530 h150"></textarea>
								<p class="f12 c999">还可以输入 <b id="leftDescriptionLength" class="cc20000 cf30 abc">200</b> 字符</p>
							</td>
						</tr>						  <tr>
							<th scope="row"></th>
							<td class="vm"><input type="button" onclick="Video.add()" href="javascript:void(0)" tabindex="10" class="lBtn" btnmode="true" value="发布"></td>
						  </tr>
						</tbody></table>
					</form>
				</div>
				<!-- <div class="submit_cover">
					<form method="get">
						<table cellpadding="0" cellspacing="0" width="100%">
							<tbody>
							<tr>
								<td class="" width="360" align="center">
									<img src="" width="320" height="180">
									<p class="c999 pt10">2MB以内，建议尺寸：320x180</p>
								</td>
								<td colspan="3" class="center vm">
								   <input type="text" id="upTxt" disabled="disabled" value="" class="newTxt w336" width="360">
								   <span class="uploadBtn small"><input onchange="$('#upTxt').val($(this).val())" type="file" name="file"><span>浏览...</span></span>
								</td>
							</tr>
						</tbody></table>
						<div class="sumit_avatar"><input type="submit" class="lBtn" value="提 交"></div>
					</form>
				</div> -->
			</div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
</body>
</html>
