<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="taglib.jinc" %>


<c:if test="${pager.currentPage!=1}">
	<a class="prev" href="<c:out value='${pager.preUrl }' />" >&lt;</a>
</c:if>
<c:if test="${pager.currentPage==1}">
	<a class="prev" href="#" >&lt;</a>
</c:if>

<c:if test="${pager.currentPage<=5}" >
	<c:forEach var="index" varStatus="varStatus" begin="1" end="${pager.totalPage}" step="1">
		<c:if test="${index<10}"><a href="<c:out value='${pager.urlExceptPage }' />&page=<c:out value='${index}' />" 
		<c:if test="${index == pager.currentPage }" >class='on'</c:if>  ><c:out value='${index}' /></a></c:if>
	</c:forEach> 
</c:if>
<c:if test="${pager.currentPage>5}" >
	<c:forEach var="index" varStatus="varStatus" begin="${pager.currentPage - 5}" end="${pager.totalPage}" step="1">
		<c:if test="${index<(pager.currentPage + 4)}"><a href="<c:out value='${pager.urlExceptPage }' />&page=<c:out value='${index}' />" 
		<c:if test="${index == pager.currentPage }" >class='on'</c:if>  ><c:out value='${index}' /></a></c:if>
	</c:forEach> 
</c:if>

<c:if test="${pager.currentPage!=pager.totalPage}">
	<a class="next" href="<c:out value='${pager.nextUrl }' />">&gt;</a>
</c:if>
<c:if test="${pager.currentPage==pager.totalPage}">
	<a class="next" href="#">&gt;</a>
</c:if>