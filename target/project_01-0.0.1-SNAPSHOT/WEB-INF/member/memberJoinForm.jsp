<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container">
	<div class="jumbotron text-center p-3 my-3 bg-primary text-white">
		<h1>회원가입</h1>
		<p>회원가입 후 커뮤니티의 모든 기능을 이용하실 수 있어요.</p>
	</div>
	
	<form action="${contextPath}/member/join" method="post">
		<div class="form-group">
			이름 : <input type="text" class="form-control" name="name" placeholder="회원님의 이름을 입력 해 주세요." required="required">
		</div>
		<div class="form-group">
			아이디 : <input type="text" class="form-control" name="id" placeholder="사용할 아이디를 입력 해 주세요." required="required">
		</div>
		<div class="form-group">
			비밀번호 : <input type="password" class="form-control" name="pwd" placeholder="사용할 비빌번호를 입력 해 주세요." required="required">
		</div>
		<div class="form-group">
			비밀번호 확인 : <input type="password" class="form-control" name="conf_pwd" placeholder="비밀번호를 다시 입력 해 주세요." required="required">
		</div>
		<div class="form-group">
			닉네임 : <input type="text" class="form-control" name="nickname" placeholder="사용할 닉네임을 입력 해 주세요." required="required">
		</div>
		<div class="form-group">
			이메일 : <input type="text" class="form-control" name="email" placeholder="회원님의 이메일을 입력 해 주세요." required="required">
		</div>
		<div class="form-group">
			전화번호 : <input type="text" class="form-control" name="phone" placeholder="회원님의 전화번호를 입력 해 주세요." required="required">
		</div>
		<div class="form-inline text-center">
			<input type="checkbox" class="form-control" name="private" required="required">
				<a data-toggle="modal" data-target="#privacy" style="color: blue;">개인정보 수집 및 이용</a>동의 (필수)
		</div>
		<div class="text-center mt-3">
			<button class="btn btn-success">회원가입</button>
			<button type="reset" class="btn btn-warning">취소</button>
		</div>
	</form>
</div>

<!-- 개인정보알림 모달 -->
<div class="modal fade" id="privacy">
  <div class="modal-dialog modal-dialog-centered modal-lg">
    <div class="modal-content">
    
      <div class="modal-header">
        <h4 class="modal-title">개인정보 수집 및 이용 동의</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
        <%@ include file="privacy.jsp" %>
      </div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
      </div>
      
    </div>
  </div>
</div>
<%@ include file="../layout/footer.jsp" %>