<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="m" value="${memberInfo}"/>
<c:set var="auth" value="${sessionScope.auth}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>커뮤니티에 오신것을 환영합니다.</title>
<!-- JQUERY -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<!-- BOOTSTRAP 4 CDN -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link href="${contextPath}/resources/css/header.css" rel="stylesheet">
<script>
	const contextPath = "${contextPath}"
	let auth = {
			id : "${auth.id}",
			grade : "${auth.grade}"
	}
</script>
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-light navbar-light sticky-top justify-content-around" style="white-space: nowrap; ">
 
		<!-- 게시판목록 -->
		<ul class="navbar-nav">
			<a class="navbar-brand btn btn-light" href="${contextPath}/">7Day.com</a>
			<li class="nav-item">
				<a class="nav-link btn btn-light" href="${contextPath}/board/freeboard">자유게시판</a>
			</li>
			<li class="nav-item">
				<a class="nav-link btn btn-light" href="${contextPath}/board/areaboard">지역게시판</a>
			</li>
			<li class="nav-item">
				<a class="nav-link btn btn-light" href="${contextPath}/board/userreview">회원리뷰</a>
			</li>
			<li class="nav-item">
				<a class="nav-link btn btn-light" href="${contextPath}/board/newsroom">뉴스룸</a>
			</li>
			<li class="nav-item">
				<a class="nav-link btn btn-light" href="${contextPath}/board/shareboard">정보공유</a>
			</li> 
			<li class="nav-item">
				<a class="nav-link btn btn-light" href="${contextPath}/board/qna">질문답변</a>
			</li>
		</ul>
		 
		  <!-- 로그인, 로그아웃 회원가입 -->	
		<ul class="navbar-nav">
	
			<c:choose>
		    	<c:when test="${not empty auth}">
					<div class="dropdown">
						<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
							${auth.nickname}(${auth.id})님
						</button>
						<div class="dropdown-menu dropdown-menu-right">
							<a class="dropdown-item" href="${contextPath}/member/myPage">마이페이지</a>
							<a class="dropdown-item" href="${contextPath}/member/memberModForm">회원정보 수정</a>
							<c:if test="${auth.grade eq '관리자' or auth.grade eq '운영자'}">
								<a class="dropdown-item" href="${contextPath}/member/memberlist">회원관리</a>
							</c:if>
							<div class="dropdown-header"></div>
							<a class="dropdown-item" style="color: red;" href="${contextPath}/member/logout">로그아웃</a>
						</div>
					</div>
		    	</c:when>
	    	
				<c:otherwise>
					<div class="dropdown">
						<button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown">
							로그아웃 상태
						</button>
						<div class="dropdown-menu dropdown-menu-right">
							<a class="dropdown-item" href="${contextPath}/member/loginForm">로그인</a>
							<a class="dropdown-item" href="${contextPath}/member/joinMember">회원가입</a>
						</div>
					</div>	
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>


