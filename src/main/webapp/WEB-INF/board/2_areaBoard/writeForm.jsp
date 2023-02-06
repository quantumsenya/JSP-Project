<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../layout/header.jsp" %>
<script src="${contextPath}/resources/js/board/arealist.js"></script>
<div class="container">
	<div class="jumbotron  text-center p-3 my-3 bg-dark text-white">
		<h1>지역게시판/글쓰기</h1>
		<p>지역 회원들과 소통 할 수 있어요.</p>
	</div>
</div>
<div class="container">
	<form action="${contextPath}/board/areaboard/write" method="post" enctype="multipart/form-data">
		<div class="form-group">
			제목 : <input type="text" class="form-control" name="title">
		</div>
		<div class="form-group">
		 <label for="category">분류</label>
			<select class="form-control" id="category" name="category">
				<option value="서울" selected>서울</option>
				<option value="경기/인천">경기/인천</option>
				<option value="강원">강원</option>
				<option value="대전/세종/충청">대전/세종/충청</option>
				<option value="대구/경북">대구/경북</option>
				<option value="부산/울산/경남">부산/울산/경남</option>
				<option value="광주/전라">광주/전라</option>
				<option value="제주">제주</option>
				<option value="해외">해외</option>
				<option value="기타">기타</option>
			</select>
		</div>
		<div class="form-group">
			내용 : <textarea rows="10" class="form-control" name="content"></textarea>
		</div>
		<div class="form-group">
			작성자 : <input type="text" class="form-control" name="writer" value="${auth.id}" readonly="readonly">
			<input type="text" name="writernick" value="${auth.nickname}" readonly="readonly" hidden>
		</div>
		<div class="form-group">
			첨부파일 : <input type="file" class="form-control" name="imageFile">
		</div>
		<button class="btn btn-primary">글쓰기</button>
	</form>
	<div class="preview"></div>
</div>

<%@ include file="../../layout/footer.jsp" %>