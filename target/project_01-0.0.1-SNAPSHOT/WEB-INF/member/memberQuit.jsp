<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../layout/header.jsp" %>

<div class="container">
	<div class="jumbotron text-center p-3 my-3 bg-danger text-white">
		<h1>회원 탈퇴하기</h1>
		
	</div>
	
	<c:choose>
		<c:when test="${not empty auth}">
			<div class="jumbotron  text-center">
				<h1>정말 탈퇴하시겠어요?</h1>
				<p>${auth.nickname}님, 탈퇴하게 되면 되돌릴 수 없게 돼요,</p>
				<img class="img-hello" src="https://item.kakaocdn.net/do/1d495862f49c38232ca8b6cc6a9679a04022de826f725e10df604bf1b9725cfd">
				<p>회원님의 개인정보는 탈퇴 즉시 삭제되고, 복구 할 수 없게 됩니다.</p>
				<p>회원님이 작성한 게시물들은 자동으로 삭제되지 않습니다.</p>
				<p>앞으로 커뮤니티의 기능을 사용 할 수 없습니다.</p>
				<br>
				<h3>정말 탈퇴하시겠어요?</h3>
				<br>
				<p>탈퇴하시려면 비밀번호를 입력 해 주세요.</p>
				<form action="${contextPath}/member/doQuit">
					<input type="text" name="id" value="${auth.id}" readonly="readonly" hidden>
					비밀번호 입력 : <input type="password" name="pwd"> <br>
					<br>
					<button class="btn btn-warning">탈퇴할래요</button>
				</form>
				<br>
				<button class="btn btn-dark" onclick="history.go(-1)">다시 생각해보기</button>
				<br>
			</div>
		</c:when>
		<c:otherwise>
			<div class="jumbotron  text-center">
				<h1>로그인 정보가 없어요.</h1>
				<img class="img-notlogin" src="https://item.kakaocdn.net/do/1d495862f49c38232ca8b6cc6a9679a0ff1cf2d4e1bdc11c5e3dd410963d18c7">
				<p>작업을 계속 하려면 로그인 해 주세요.</p>
				<button class="btn btn-light" onclick="history.go(-1)">이전페이지로 이동</button>
			</div>
		</c:otherwise>
	</c:choose>
	
</div>
<%@ include file="../layout/footer.jsp" %>
