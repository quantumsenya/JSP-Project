<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="layout/header.jsp" %>

<div class="jumbotron col text-center">
	<div class="container p-3 my-3 bg-dark text-white">
		<h1>404: 페이지를 찾을 수 없어요.</h1>
		<p>여긴 어디죠?</p>
		<img class="img-error" src="https://item.kakaocdn.net/do/58119590d6204ebd70e97763ca933baf82f3bd8c9735553d03f6f982e10ebe70">
		<div class="col text-center">
			<a href="${contextPath}/index/" class="btn btn-light" >메인페이지로 이동</a>
			<button class="btn btn-light" onclick="history.go(-1)">이전페이지로 이동</button>
		</div>
		<p></p>
	</div>
	<br>
</div>

<%@ include file="layout/footer.jsp" %>