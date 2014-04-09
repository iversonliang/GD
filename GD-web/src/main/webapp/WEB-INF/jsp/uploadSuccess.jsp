<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>
<%@include file="/WEB-INF/jsp/taglib.inc.jsp"%>
<script type="text/javascript" src="/js/jquery-1.6.4.min.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<p>上传了${fn:length(fileNames) }几个文件</p>
	<c:if test="${not empty fileNames }">
		<ul>
			<c:forEach var="fileName" items="${fileNames }">
				<li>${fileName}</li>
			</c:forEach>
		</ul>
	</c:if>
</body>
</html>