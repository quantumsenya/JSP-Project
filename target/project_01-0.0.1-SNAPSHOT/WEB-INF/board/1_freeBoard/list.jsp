<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../layout/header.jsp" %>
<script src="${contextPath}/resources/js/board/list/freelist.js"></script>
<link href="${contextPath}/resources/css/board.css" rel="stylesheet">
<div class="container">
	<div class="jumbotron  text-center p-3 my-3 bg-dark text-white">
		<h1>자유게시판</h1>
		<p>자유롭게 소통 가능한 게시판이에요.</p>
	</div>
</div>
<div class="container">
	<form id="listForm">
	<table class="table table-striped">
	<thead class="thead-dark">
		<tr class="text-center">
			<th width="80">글번호</th>
			<th width="120">분류</th>
			<th>제목</th>
			<th width="150">작성자</th>
			<th width="50">조회수</th>
			<th width="50">추천</th>
			<th width="150">작성일</th>
		</tr>
	</thead>
		<c:choose>
			<c:when test="${empty list}">
				<tr>
					<td colspan="7" class="text-center"><b>작성된 게시물이 없습니다.</b></td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${list}" var="b">
					<tr class="text-center">
						<td>${b.bno }</td>
						<td>${b.category }</td>
						<td class="text-left">
							<a href="${b.bno}" class="title">${b.title}</a>
							<b>${b.replyCount!=0 ? '['+=b.replyCount+=']':''}</b>
						</td>
						<td>${b.writernick} (${b.writer})</td>
						<td>${b.views}</td>
						<td>${b.suggest}</td>
						<td>
							<fmt:formatDate value="${b.writeDate}" pattern="yyyy-MM-dd hh:mm"/>
						</td>
					</tr>
				</c:forEach>			
			</c:otherwise>
		</c:choose>
	</table>
	<div class="float-right">
		<a href="${contextPath}/board/freeboard/writeForm" class="btn btn-primary">글쓰기</a>
	</div>
	</form>
</div>
<div class="container">
	<ul class="pagination d-flex justify-content-center">
		<c:if test="${p.prev}">
			<li class="page-item"><a class="page-link" href="?pageNum=${p.startPage-1}">←</a></li>
		</c:if>

		<c:forEach begin="${p.startPage}" end="${p.endPage}" var="pageBtn">
			<li class="page-item ${pageBtn eq param.pageNum ? 'active' : ''}">
				<a class="page-link" href="?pageNum=${pageBtn}">${pageBtn }</a>
			</li>
		</c:forEach>
		<c:if test="${p.next}">
			<li class="page-item"><a class="page-link" href="?pageNum=${p.endPage+1}">→</a></li>
		</c:if>
	</ul>
</div>


<%@ include file="../../layout/footer.jsp" %>