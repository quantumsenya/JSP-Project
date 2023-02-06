<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../layout/header.jsp" %>
<script src="${contextPath}/resources/js/reply/freereply.js"></script>
<script src="${contextPath}/resources/js/board/detail/freedetail.js"></script>
<link href="${contextPath}/resources/css/image.css" rel="stylesheet">
<div class="container">
	<div class="jumbotron  text-center p-3 my-3 bg-dark text-white">
		<h1>자유게시판/글조회</h1>
		<p>자유롭게 소통 가능한 게시판이에요.</p>
	</div>
	<form id="viewForm" enctype="multipart/form-data">
	<table class="table">
		<tr>
			<th>분류</th>
			<td>
				${board.category }
			</td>
			<th>작성일</th>
			<td>${board.writeDate }</td>

		</tr>
		<tr>
			<th>글번호</th>
			<td>
				${board.bno}
				<input type="hidden" name="bno" value="${board.bno}">
			</td>
			<th>조회수</th>
			<td>${board.views}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.writernick} (${board.writer})</td>
			<th>추천수</th>
			<td>
				<c:if test="${not empty auth and auth.id!=board.writer}">
					<a type="button" class="btn btn-warning suggest" href="${contextPath}/board/freeboard/suggest?bno=${board.bno}">
					${board.suggest}</a>(누르면 추천됩니다.)
				</c:if>
				<c:if test="${empty auth or auth.id == board.writer}">
					${board.suggest}
				</c:if>
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan="3">
				<input type="text" name="title" class="form-control" value="${board.title}" readonly="readonly">
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="3">
				<textarea rows="10" name="content" class="form-control" readonly="readonly">${board.content}</textarea>
			</td>
		</tr>
		<tr>
			<th>첨부이미지</th>
			<td colspan="3">
				<input type="file" name="imageFile" class="form-control viewMode">
				<div class="my-3">
					<c:if test="${not empty board.imageFile}">
						<input type="hidden" name="origFile" value="${board.imageFile}">
						<div class="preview">
							<img class="origImg" src="${contextPath}/fileDownload?no=${board.bno}&imageFileName=${board.imageFile}&path=board/freeboard">
						</div>
					</c:if>
					<c:if test="${empty board.imageFile}">
						<div class="preview">
							<p>등록된 이미지가 없습ㄴ다.</p>
						</div>
					</c:if>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="4" class="text-center">
				<c:if test="${auth.id eq board.writer or auth.grade eq '관리자'}">
					<button type="button" class="btn btn-warning toModForm">수정하기</button>
					<button type="button" class="btn btn-danger remove">삭제</button>
				</c:if>
				<button type="button" class="btn btn-info toList">목록</button>
			</td>
		</tr>
		<tr class="viewMode">
			<c:if test="${auth.id eq board.writer}">
				<td colspan="4" class="text-center">
					<button type="button" class="btn btn-success modify">수정</button>
					<button type="button" class="btn btn-light backViewMode">취소</button>
				</td>
			</c:if>
		</tr>
	</table>
	</form>
	
	<div class="replyList">
	<div class="card">
		<div class="card-header bg-dark text-white">댓글목록</div>
		<div class="card-body bg-light text-dark">
			<ul class="list-group list-group-flush">

			</ul>
		</div>
	</div>
	
	</div>
	
	<div class="replyForm">
	
		<!-- 댓글입력테이블 -->
		<c:if test="${not empty auth}">
			<table class="table">
				<tr>
					<th colspan="2" >
						<ul class="d-flex justify-content-between">
							<li>댓글을 작성 해 주세요</li>
							<li class="form-inline">작성자 : ${auth.nickname} (${auth.id})
								<input type="hidden" class="reply_writernick form-control ml-2" name="writernick" value="${auth.nickname}" readonly="readonly">
								<input type="hidden" class="reply_writer" name="writer" value="${auth.id}" readonly="readonly">
							</li>
						</ul>
					</th>
				</tr>
				<tr>
					<td class="col-1 text-center">내용</td>
					<td>
						<textarea rows="5" class="form-control reply_content"></textarea>
					</td>
				</tr>
				<tr class="text-right">
					<td colspan="2"><button class="btn btn-secondary reply_write">등록</button></td>
				</tr>
			</table>
		</c:if><!-- 댓글입력테이블 -->
		
		<!-- 미로그인 댓글 차단 -->
		<c:if test="${empty auth}">
			<table class="table">
				<tr>
					<th colspan="2" >
						<ul class="d-flex justify-content-between">
							<li>댓글을 작성 해 주세요</li>
							<li class="form-inline">작성자 : 
								 <a href="${contextPath}/member/loginForm" class="btn btn-primary">로그인</a>
							</li>
						</ul>
					</th>
				</tr>
				<tr>
					<td class="col-1 text-center">내용</td>
					<td>
						<textarea rows="5" class="form-control reply_content" readonly="readonly">로그인 후 댓글 작성이 가능합니다.</textarea>
					</td>
				</tr>
				<tr class="text-right">
					<td colspan="2"><button class="btn btn-secondary reply_write" disabled="disabled">등록</button></td>
				</tr>
			</table>
		</c:if><!-- 미로그인 댓글 차단 -->
	</div>
</div>

<div class="modal fade" id="feedback">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
    
      <div class="modal-header">
        <h4 class="modal-title">댓글 등록</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      
      <div class="modal-body">
        Modal body..
      </div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
      </div>
      
    </div>
  </div>
</div>
<%@ include file="../../layout/footer.jsp" %>