<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../layout/header.jsp" %>

<div class="jumbotron col text-center">
	<div class="container p-3 my-3 bg-dark text-white">
		<h1>회원탈퇴 완료</h1>
		<p>그동안 커뮤니티를 이용 해 주셔서 감사합니다.</p>
		<p>회원님의 개인정보는 즉시 삭제되었습니다.</p>
		<img class="img-error" src="https://item.kakaocdn.net/do/c6711410690de98cb3caa81418dbfb9266d8fd08427c1f00d04db607cc4cdc8e">
		<div class="col text-center">
			<a href="${contextPath}/index/" class="btn btn-light" >메인페이지로 이동</a>
		</div>
		<p></p>
	</div>
	<br>
</div>
<%@ include file="../layout/footer.jsp" %>
