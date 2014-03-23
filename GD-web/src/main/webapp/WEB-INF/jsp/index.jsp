<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="/js/jquery-1.6.4.min.js"></script>
<script type="text/javascript"> 
    function changeImg() {
        var imgSrc = $("#imgObj"); 
        var src = imgSrc.attr("src");
        imgSrc.attr("src", chgUrl(src)); 
    } 
    //时间戳     
    //为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳     
    function chgUrl(url) {
        var timestamp = (new Date()).valueOf(); 
        url = url.substring(0, 17); 
        if ((url.indexOf("&") >= 0)) { 
            url = url + "&tamp=" + timestamp; 
        } else { 
            url = url + "?timestamp=" + timestamp; 
        }
        alert(url);
        return url; 
    } 
</script>
</head>
<body>
	<form action="/validate" method="post">
		<h1>hello world</h1>
		<input type="text" name="code"><img id="imgObj" src="getCode?codeId=1" /><input type="button" value="change" onclick="changeImg()">
		<br/>
		<input type="submit">
	</form>
</body>
</html>