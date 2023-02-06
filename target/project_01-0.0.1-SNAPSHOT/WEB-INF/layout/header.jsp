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
<nav class="navbar navbar-expand-sm bg-light navbar-light sticky-top justify-content-between" style="white-space: nowrap;">
  <a class="navbar-brand btn btn-light" href="${contextPath}/index">어쩌고커뮤니티</a>
  
  <!-- 게시판목록 -->
  <ul class="navbar-nav">
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
    		<li class="nav-item">
   				<a class="nav-link btn btn-light" href="${contextPath}/member/myPage">${auth.nickname}(${auth.id})님 로그인 중</a>
   			</li>
   			<li class="nav-item">
      			<a class="nav-link btn btn-light" href="${contextPath}/member/logout">로그아웃</a>
      		</li>
    	</c:when>
    	
    	<c:otherwise>
		    <li class="nav-item">
		      <a class="nav-link btn btn-light" href="${contextPath}/member/loginForm">로그인</a>
		    </li>	       	
		    <li class="nav-item">
		      <a class="nav-link btn btn-light" href="${contextPath}/member/joinMember">회원가입</a>
		    </li>    	
    	</c:otherwise>
    </c:choose>
    </ul>
</nav>
