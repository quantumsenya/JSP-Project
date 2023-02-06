<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<!-- Page Content-->
<div class="container px-4 px-lg-5">
    <!-- Heading Row-->
    <div class="row gx-4 gx-lg-5 align-items-center my-5">
        <div class="col-lg-7"><img class="img-fluid rounded mb-4 mb-lg-0" src="https://www.itworld.co.kr/files/itworld/2021/02/How%20to%20clean%20a%20keyboard-1.jpg" alt="..." /></div>
        <div class="col-lg-5">
            <h1 class="font-weight-light">커뮤니티에 오신것을 환영합니다.</h1>
            <p>이 커뮤니티는 다른 사용자와 함께 질문하고,
               도움을 받으며, 모든 것을 논의할 수 있는 공간입니다.<br>
               질문하기 전에 꼭 위의 검색창을 사용하여 정보를 검색해 보세요. <br>
               이 커뮤니티에서 활동하기 전 가이드라인을 숙지 해 주시길 바라며, 가이드라인은</p>
            <a class="btn btn-primary" data-toggle="modal" data-target="#guide">여기에서 참조하세요.</a>
        </div>
    </div>
    <!-- Content Row-->
    <div class="row gx-4 gx-lg-5">
        <div class="col-md-4 mb-5">
            <div class="card h-100">
                <div class="card-body">
                    <h2 class="card-title">자유게시판</h2>
                    <p class="card-text">커뮤니티 회원들과 자유롭게 대화 할 수 있는 게시판이예요.</p>
                </div>
                <div class="card-footer"><a class="btn btn-primary btn-sm" href="${contextPath}/board/freeboard/">이동하기</a></div>
            </div>
        </div>
        <div class="col-md-4 mb-5">
            <div class="card h-100">
                <div class="card-body">
                    <h2 class="card-title">지역게시판</h2>
                    <p class="card-text">같은 지역 회원끼리 소통 할 수 있는 게시판이예요.</p>
                </div>
                <div class="card-footer"><a class="btn btn-primary btn-sm" href="${contextPath}/board/areaboard/">이동하기</a></div>
            </div>
        </div>
        <div class="col-md-4 mb-5">
            <div class="card h-100">
                <div class="card-body">
                    <h2 class="card-title">회원 리뷰</h2>
                    <p class="card-text">회원분들의 제품 사용 후기를 작성하고 공유 할 수 있는 게시판이예요.</p>
                </div>
                <div class="card-footer"><a class="btn btn-primary btn-sm" href="${contextPath}/board/userreview/">이동하기</a></div>
            </div>
        </div>
             <div class="col-md-4 mb-5">
            <div class="card h-100">
                <div class="card-body">
                    <h2 class="card-title">뉴스룸</h2>
                    <p class="card-text">커뮤니티 관리자와 공식 채널의 소식을 확인 할 수 있는 게시판이예요.</p>
                </div>
                <div class="card-footer"><a class="btn btn-primary btn-sm" href="${contextPath}/board/newsroom">이동하기</a></div>
            </div>
        </div>
        <div class="col-md-4 mb-5">
            <div class="card h-100">
                <div class="card-body">
                    <h2 class="card-title">정보공유</h2>
                    <p class="card-text">핫딜/이벤트/특가 등 여러가지 정보들을 공유 할 수 있는 게시판이예요.</p>
                </div>
                <div class="card-footer"><a class="btn btn-primary btn-sm" href="${contextPath}/board/shareboard">이동하기</a></div>
            </div>
        </div>
        <div class="col-md-4 mb-5">
            <div class="card h-100">
                <div class="card-body">
                    <h2 class="card-title">질문답변</h2>
                    <p class="card-text">궁금한 부분이나 알고싶은점을 질문하고, 답변 해 줄 수 있는 게시판이예요.</p>
                </div>
                <div class="card-footer"><a class="btn btn-primary btn-sm" href="${contextPath}/board/qna">이동하기</a></div>
            </div>
        </div>
    </div> <!-- 카드 끝 -->
</div>

<!-- 커뮤니티 규칙 모달 -->
<div class="modal fade" id="guide">
  <div class="modal-dialog modal-dialog-centered modal-lg">
    <div class="modal-content">
    
      <div class="modal-header">
        <h4 class="modal-title">커뮤니티 규칙</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
        <%@ include file="more.jsp" %>
      </div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
      </div>
      
    </div>
  </div>
</div>

<%@ include file="../layout/footer.jsp" %>