<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../layout/header.jsp" %>

<div class="jumbotron col text-center">
	<div class="container p-3 my-3 bg-dark text-white">
		<h1>등업 완료</h1>
		<p>${auth.nickname}님의 계정이 등업되었습니다.</p>
		<p>현재 등급은 "${auth.grade}" 입니다.</p>
		<img class="img-good" src="https://item.kakaocdn.net/do/58119590d6204ebd70e97763ca933bafba2da8249bd9ffef143efb890203e009">
		<div class="col text-center">
			<a href="${contextPath}/index/" class="btn btn-light" >메인페이지로 이동</a>
			<a href="${contextPath}/member/myPage" class="btn btn-light" >마이페이지로 이동</a>
		</div>
		<p></p>
	</div>
	<br>
</div>
<%@ include file="../layout/footer.jsp" %>
