<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>


	<div class="page_control">
		<div class="Paging cs-fr">
			<c:if test="${pager.totalPage>1}">
			<a href="${pager.preUrl }">上一页</a>
			
			<!-- 小于或者等于5页 直接输出-->
			<c:if test="${pager.totalPage <=5 }">
				<c:forEach var="index" varStatus="varStatus" begin="1" end="${pager.totalPage}" step="1">
					<a href="${pager.urlExceptPage }&page=${index}" <c:if test="${index == pager.currentPage }" >class='on'</c:if>  >${index}</a>
				</c:forEach> 
			</c:if>
			
			<!-- 大于5页分情况 -->
			<c:if test="${pager.totalPage >5 }">
				<!-- 前两个页数位置在此输出 -->
				<c:if test="${pager.currentPage<=2}">
					<c:forEach var="index" varStatus="varStatus" begin="1" end="${pager.totalPage}" step="1">
						<c:if test="${index<6}">
							<a href="${pager.urlExceptPage }&page=${index}" <c:if test="${index == pager.currentPage }" >class='on'</c:if>  >${index}</a>
						</c:if>
					</c:forEach> 
				</c:if>
				<!-- 中间位置在此输出 -->
				<c:if test="${pager.currentPage>=3 && pager.currentPage <= pager.totalPage - 2}">
					<c:forEach var="index" varStatus="varStatus" begin="${pager.currentPage - 2}" end="${pager.totalPage }" step="1">
						<c:if test="${index<=(pager.currentPage + 2)}">
							<a href="${pager.urlExceptPage }&page=${index}" <c:if test="${index == pager.currentPage }" >class='on'</c:if>  >${index}</a>
						</c:if>
					</c:forEach> 
				</c:if>
				<!-- 后两个页数位置在此输出 -->
				<c:if test="${pager.currentPage >= pager.totalPage -1  && pager.totalPage >= 3}">
					<c:forEach var="index" varStatus="varStatus" begin="${pager.totalPage - 4 }" end="${pager.totalPage}" step="1">
						<c:if test="${index >= 1}">
							<a href="${pager.urlExceptPage }&page=${index}" <c:if test="${index == pager.currentPage }" >class='on'</c:if>  >${index}</a>
						</c:if>
					</c:forEach>
				</c:if>
			</c:if>
			
			
			<a href="${pager.nextUrl }">下一页</a>
			</c:if>
		</div>
	</div>