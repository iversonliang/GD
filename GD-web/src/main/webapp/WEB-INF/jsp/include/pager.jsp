<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/taglib.inc.jsp"%>

<div id="pages">
<c:if test="${pager.currentPage!=1}">
	<a class="prev" href="${pager.preUrl }" >&lt;</a>
</c:if>
<c:if test="${pager.currentPage==1}">
	<a class="prev" href="javascript:void(0)" >&lt;</a>
</c:if>

<c:if test="${pager.currentPage<=5}" >
	<c:forEach var="index" varStatus="varStatus" begin="1" end="${pager.totalPage}" step="1">
		<c:if test="${index<=10}">
			<a href="${pager.urlExceptPage }&page=${index}" <c:if test="${index == pager.currentPage }" >class='pageactive'</c:if>>${index}</a>
		</c:if>
	</c:forEach> 
</c:if>
<c:if test="${pager.currentPage>5}" >
	<c:forEach var="index" varStatus="varStatus" begin="${pager.currentPage - 5}" end="${pager.currentPage + 4}" step="1">
		<c:if test="${index<=pager.totalPage}">
			<a href="${pager.urlExceptPage }&page=${index}" <c:if test="${index == pager.currentPage }" >class='pageactive'</c:if>>${index}</a>
		</c:if>
	</c:forEach> 
</c:if>

<c:if test="${pager.currentPage!=pager.totalPage}">
	<a class="next" href="${pager.nextUrl }">&gt;</a>
</c:if>
<c:if test="${pager.currentPage==pager.totalPage}">
	<a class="next" href="javascript:void(0)">&gt;</a>
</c:if>
</div>