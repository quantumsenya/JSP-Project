<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../layout/header.jsp" %>


<div class="jumbotron col text-center">
	<div class="container p-3 my-3">
		<h1>존재하지 않는 페이지</h1>
			<img class="img-error" src="https://item.kakaocdn.net/do/c6711410690de98cb3caa81418dbfb928f324a0b9c48f77dbce3a43bd11ce785">
		<p>이 페이지는 구현되지 않을 예정입니다!!!</p>
	</div>
	<br>
	<div class="col text-center">
		<a href="${contextPath}/index/" class="btn btn-light" >메인페이지로 이동</a>
		<button class="btn btn-light" onclick="history.go(-1)">이전페이지로 이동</button>
	</div>
</div>


<%@ include file="../layout/footer.jsp" %>