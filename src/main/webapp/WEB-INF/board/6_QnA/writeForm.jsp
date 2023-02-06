<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../layout/header.jsp" %>
<script src="${contextPath}/resources/js/board/qnalist.js"></script>
<div class="container">
	<div class="jumbotron  text-center p-3 my-3 bg-dark text-white">
		<h1>질문답변/글쓰기</h1>
		<p>궁금한 부분이나 알고싶은점을 질문하고, 답변 해 줄 수 있는 게시판이예요.</p>
	</div>
</div>
<div class="container">
	<form action="${contextPath}/board/qna/write" method="post" enctype="multipart/form-data">
		<div class="form-group">
			제목 : <input type="text" class="form-control" name="title">
		</div>
		<div class="form-group">
		 <label for="category">분류</label>
			<select class="form-control" id="category" name="category">
				<option value="생활" selected>생활</option>
				<option value="컴퓨터">컴퓨터</option>
				<option value="게임">게임</option>
				<option value="음악">음악</option>
				<option value="자동차">자동차</option>
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