<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../layout/header.jsp" %>
<script src="${contextPath}/resources/js/board/arealist.js"></script>
<div class="container">
	<div class="jumbotron  text-center p-3 my-3 bg-dark text-white">
		<h1>회원리뷰/글쓰기</h1>
		<p>회원분들의 제품 사용 후기를 작성하고 공유 할 수 있는 게시판이예요.</p>
	</div>
</div>
<div class="container">
	<form action="${contextPath}/board/userreview/write" method="post" enctype="multipart/form-data">
		<div class="form-group">
			제목 : <input type="text" class="form-control" name="title">
		</div>
		<div class="form-group">
		 <label for="category">분류</label>
			<select class="form-control" id="category" name="category">
				<option value="지름" selected>지름</option>
				<option value="개봉기">개봉기</option>
				<option value="생활리뷰">생활리뷰</option>
				<option value="기기리뷰">기기리뷰</option>
				<option value="게임리뷰">게임리뷰</option>
				<option value="관람리뷰">관람리뷰</option>
				<option value="칼럼">칼럼</option>
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