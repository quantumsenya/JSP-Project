<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${contextPath}/resources/css/footer.css" rel="stylesheet">

<br>
<br>
<!-- Footer -->
	<section id="footer">
		<div class="container">
			<div class="row text-center text-xs-center text-sm-left text-md-left">
				<div class="col-xs-12 col-sm-4 col-md-4">
					<h5>Quick links</h5>
					<ul class="list-unstyled quick-links">
						<li><a href="${contextPath}/index/"><i class="fa fa-angle-double-right"></i>Home</a></li>
						<c:choose>
							<c:when test="${not empty auth}">
								<li><a href="${contextPath}/member/myPage"><i class="fa fa-angle-double-right"></i>My Page (${auth.id})</a></li>
								<li><a href="${contextPath}/member/logout"><i class="fa fa-angle-double-right"></i>Logout</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${contextPath}/member/loginForm"><i class="fa fa-angle-double-right"></i>Login</a></li>
								<li><a href="${contextPath}/member/joinMember"><i class="fa fa-angle-double-right"></i>Join</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				<div class="col-xs-12 col-sm-4 col-md-4">
					<h5>Quick links</h5>
					<ul class="list-unstyled quick-links">
						<li><a href="${contextPath}/board/freeboard"><i class="fa fa-angle-double-right"></i>자유게시판</a></li>
						<li><a href="${contextPath}/board/areaboard"><i class="fa fa-angle-double-right"></i>지역게시판</a></li>
						<li><a href="${contextPath}/board/userreview"><i class="fa fa-angle-double-right"></i>회원리뷰</a></li>
						<li><a href="${contextPath}/board/newsroom"><i class="fa fa-angle-double-right"></i>뉴스룸</a></li>
						<li><a href="${contextPath}/board/shareboard"><i class="fa fa-angle-double-right"></i>정보공유</a></li>
						<li><a href="${contextPath}/board/qna"><i class="fa fa-angle-double-right"></i>질문답변</a></li>
					</ul>
				</div>
				<div class="col-xs-12 col-sm-4 col-md-4">
					<h5>Quick links</h5>
					<ul class="list-unstyled quick-links">
						<li><a href="https://www.naver.com"><i class="fa fa-angle-double-right"></i>NAVER</a></li>
						<li><a href="https://www.daum.net"><i class="fa fa-angle-double-right"></i>Daum</a></li>
						<li><a href="https://www.google.com"><i class="fa fa-angle-double-right"></i>Google</a></li>
						<li><a href="https://mvnrepository.com/"><i class="fa fa-angle-double-right"></i>Maven Repository</a></li>
						<li><a href="https://www.w3schools.com/bootstrap4/"><i class="fa fa-angle-double-right"></i>Bootstrap 4</a></li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 mt-2 mt-sm-2 text-center text-white">
					<p class="h6">© All right Reversed.<a class="text-green ml-2" href="#" target="_blank">admin</a></p>
				</div>
				<hr>
			</div>	
		</div>
	</section>
	<!-- ./Footer -->
</body>
</html>