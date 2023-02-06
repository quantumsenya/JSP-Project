<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../layout/header.jsp" %>

<div class="jumbotron col text-center">
	<div class="container p-3 my-3 bg-dark text-white">
		<h1>등업신청</h1>
		<p>현재 회원님의 등급은 "${auth.grade}" 입니다.</p>
		<p>등업신청 후 정회원이 되실 수 있습니다.</p>
		<img class="img-getHigh" src="https://item.kakaocdn.net/do/58119590d6204ebd70e97763ca933bafff1cf2d4e1bdc11c5e3dd410963d18c7">
		<p>등업신청을 진행하시겠습니까?</p>
		<form action="${contextPath}/member/doHigher" method="post">
		<p>확인을 위해 비밀번호를 입력 해 주세요.</p>
		<input type="password" name="pwd"> <br>
		<input type="text" name="id" value="${auth.id}" readonly="readonly" hidden>
			<div class="col text-center">
				<br>
				<button type="submit" class="btn btn-light">등업신청</button>
				<button type="button" class="btn btn-light" onclick="history.go(-1)">이전페이지로 이동</button>
			</div>
		</form>
		<p></p>
	</div>
	<br>
</div>
<%@ include file="../layout/footer.jsp" %>
