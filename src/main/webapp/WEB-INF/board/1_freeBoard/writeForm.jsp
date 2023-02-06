<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../layout/header.jsp" %>
<script src="${contextPath}/resources/js/board/freelist.js"></script>
<div class="container">
	<div class="jumbotron  text-center p-3 my-3 bg-dark text-white">
		<h1>글쓰기</h1>
		<p>자유롭게 소통 가능한 게시판이에요.</p>
	</div>
</div>
<div class="container">
	<form action="${contextPath}/board/freeboard/write" method="post" enctype="multipart/form-data">
		<div class="form-group">
			제목 : <input type="text" class="form-control" name="title">
		</div>
		<div class="form-group">
		 <label for="category">분류</label>
			<select class="form-control" id="category" name="category">
				<option value="잡담" selected>잡담</option>
				<option value="유머">유머</option>
				<option value="토론">토론</option>
				<option value="지름">지름</option>
				<option value="게임">게임</option>
				<option value="자랑">자랑</option>
				<option value="추천">추천</option>
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