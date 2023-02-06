<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@ include file="../layout/header.jsp" %>
<c:set var="m" value="${memberInfo}"/>

<div class="container">
	<div class="jumbotron  text-center p-3 my-3 bg-primary text-white">
		<h1>계정/개인정보 수정</h1>
		<p>계정과 개인정보를 수정할 수 있어요.</p>
	</div>
	
	<form action="${contextPath}/member/modMember" method="post">
		<div class="form-group">
			아이디 : <input type="text" class="form-control" name="id" value="${auth.id}" readonly="readonly">
		</div>
		<div class="form-group">
			현재 비밀번호 : <input type="password" class="form-control" name="origPwd" value="${m.pwd}" placeholder="현재 비밀번호를 입력 해 주세요."  required="required">
		</div>
		<div class="form-group">
			변경할 비밀번호 : <input type="password" class="form-control" name="pwd" value="${m.pwd}" placeholder="변경할 비밀번호를 입력 해 주세요."  required="required">
		</div>
		<div class="form-group">
			비밀번호 확인 : <input type="password" class="form-control" name="pwd" value="${m.pwd}" placeholder="변경할 비밀번호를 입력 해 주세요."  required="required">
		</div>		
		<div class="form-group">
			이름 : <input type="text" class="form-control" name="name" value="${m.name}">
		</div>
		<div class="form-group">
			전화번호 : <input type="email" class="form-control" name="email" value="${m.email}">
		</div>
		<div class="form-group">
			이메일 : <input type="email" class="form-control" name="email" value="${m.email}">
		</div>
		<br>
		
		<div class="col text-center">
			<button class="btn btn-primary">수정하기</button>
			<button type="reset" class="btn btn-danger">취소</button>
		</div>
	</form>
</div>
<%@ include file="../layout/footer.jsp" %>