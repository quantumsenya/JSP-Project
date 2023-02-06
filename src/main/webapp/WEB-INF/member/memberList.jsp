<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../layout/header.jsp" %>
<link href="${contextPath}/resources/css/board.css" rel="stylesheet">
<div class="container">
	<div class="jumbotron  text-center p-3 my-3 bg-dark text-white">
		<h1>회원관리</h1>
	</div>
</div>
<div class="container">
	<table class="table table-striped">
	<thead class="thead-dark">
		<tr class="text-center">
			<th width="100">회원번호</th>
			<th width="100">등급</th>
			<th width="300">닉네임 (ID)</th>
			<th width="100">이름</th>
			<th width="150">전화번호</th>
			<th width="200">이메일</th>
			<th width="150">가입일</th>
			<th width="100">강퇴</th>
		</tr>
	</thead>
		<c:forEach items="${member}" var="m">
			<tr class="text-center"  height="60">
				<td>${m.mno}</td>
				<td>${m.grade}</td>
				<td>${m.nickname} (${m.id})</td>
				<td>${m.name}</td>
				<td>${m.phone}</td>
				<td>${m.email}</td>
				<td>
					<fmt:formatDate value="${m.joinDate}" pattern="yyyy-MM-dd"/>
				</td>
				<c:choose>
					<c:when test="${m.id!=auth.id and m.grade!='운영자' and m.grade!='관리자'}">
						<td>
							<form action="${contextPath}/member/kick" method="post">
								<input type="hidden" value="${m.id}" name="id">
								<button class="btn btn-danger">X</button>
							</form>
						</td>
					</c:when>
					<c:otherwise>
						<td></td>
					</c:otherwise>
				</c:choose>
			</tr>
		</c:forEach>
	</table>
</div>

<%@ include file="../layout/footer.jsp" %>
