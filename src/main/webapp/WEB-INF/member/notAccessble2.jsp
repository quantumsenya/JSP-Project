<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../layout/header.jsp" %>

<div class="jumbotron col text-center">
	<div class="container p-3 my-3 bg-dark text-white">
		<h1>접근권한이 없습니다.</h1>
		<p>이 게시판은 정회원 이상 작성 가능합니다.</p>
		<p>현재 회원님의 등급은 "${auth.grade}" 입니다.</p>
		<img class="img-error" src="https://item.kakaocdn.net/do/58119590d6204ebd70e97763ca933baf00df9b2a481d32ec42ae698738782a76">
		<br>
		<p>마이페이지에서 등업신청을 해 주세요.</p>
		<br>
		<div class="col text-center">
			<br>
			<button class="btn btn-light" onclick="history.go(-1)">이전페이지로 이동</button>
			<a href="${contextPath}/member/myPage" class="btn btn-light" >마이페이지로 이동</a>
		</div>
		<p></p>
	</div>
	<br>
</div>
<%@ include file="../layout/footer.jsp" %>
