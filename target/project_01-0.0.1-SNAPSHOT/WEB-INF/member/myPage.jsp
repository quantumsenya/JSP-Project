<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../layout/header.jsp" %>

<div class="container">
	<div class="jumbotron text-center p-3 my-3 bg-primary text-white">
		<h1>마이페이지</h1>
		
	</div>
	
	<c:choose>
		<c:when test="${not empty auth}">
			<div class="jumbotron  text-center">
				<h1>안녕하세요.</h1>
				<p>${auth.nickname}님, 반가워요. 회원님의 아이디는 "${auth.id}" 이예요.</p>
				<img class="img-hello" src="https://item.kakaocdn.net/do/49af77ed647962e739c3f261899795258b566dca82634c93f811198148a26065">
				<p>회원님의 현재 등급은 "${auth.grade}" 이예요.</p>
				<c:if test="${auth.grade eq '준회원'}">
					<a href="${contextPath}/member/getHigher" class="btn btn-warning">등업신청</a> <br> <br>
				</c:if>
				<c:if test="${auth.grade eq '관리자' or auth.grade eq '운영자'}">
					<a href="${contextPath}/member/memberlist" class="btn btn-warning">회원관리</a> <br> <br>
				</c:if>
				<a href="${contextPath}/member/memberModForm" class="btn btn-primary">회원정보 수정</a> 
				<a href="${contextPath}/member/modNicknameForm" class="btn btn-success">닉네임 변경</a> 
				<a class="btn btn-danger" href="${contextPath}/member/logout">로그아웃</a>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<a class="btn btn-dark" href="${contextPath}/member/quit">회원탈퇴</a>
			</div>
		</c:when>
		<c:otherwise>
			<div class="jumbotron  text-center">
				<h1>로그인 정보가 없어요.</h1>
				<img class="img-notlogin" src="https://item.kakaocdn.net/do/1d495862f49c38232ca8b6cc6a9679a0ff1cf2d4e1bdc11c5e3dd410963d18c7">
				<p>회원정보 조회를 원하시면 로그인 해주세요.</p>
				<a href="${contextPath}/member/loginForm" class="btn btn-primary">로그인 페이지 가기</a>
				<button class="btn btn-light" onclick="history.go(-1)">이전페이지로 이동</button>
			</div>
		</c:otherwise>
	</c:choose>
	
</div>
<%@ include file="../layout/footer.jsp" %>
