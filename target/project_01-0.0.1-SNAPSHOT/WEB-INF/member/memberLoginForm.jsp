<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../layout/header.jsp" %>

<div class="container">
	<div class="jumbotron  text-center p-3 my-3 bg-primary text-white">
		<h1>로그인</h1>
		<p>커뮤니티에 돌아오신것을 환영해요.</p>
	</div>
	
	<form action="${contextPath}/member/login" method="post">
		<div class="form-group">
			아이디 : <input type="text" class="form-control" name="id" placeholder="아이디를 입력하세요." required="required">
		</div>
		<br>
		<div class="form-group">
			비밀번호 : <input type="password" class="form-control" name="pwd" placeholder="비밀번호를 입력하세요." required="required">
		</div>
		<br>
		<div class="col text-center">
			<button class="btn btn-success">로그인</button>
			<button type="reset" class="btn btn-warning">취소</button>
		</div>
	</form>
</div>
<%@ include file="../layout/footer.jsp" %>
