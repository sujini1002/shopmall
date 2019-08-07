<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Shop Homepage - Start Bootstrap Template</title>
	<!-- Bootstrap core CSS -->
	<link href="${pageContext.servletContext.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<!-- Custom styles for this template -->
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-memberjoin.css" rel="stylesheet">
</head>
<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="login" />
	</c:import>
	<!-- /.Navigation -->
	<div class="container">
 		<div class="card card-container">
 			<h1 class="my-title">JoinUs</h1>
 			<p id="profile-name" class="profile-name-card"></p>
 			<form:form modelAttribute="memberVo" method="post" action="${pageContext.servletContext.contextPath }/member/join" class="form-signup" name="joinForm">
                <div class="from-control-div">
                	<span class="from-control-title">아이디*</span>
                	<input type="text" id="inputId" class="form-control" placeholder="아이디" name="id" required>
                	<p class="from-control-error"><form:errors path="id"/></p>
                </div>
 				<div class="from-control-div">
 					<span class="from-control-title">비밀번호*</span>
 					<input type="password" id="inputPassword" class="form-control" placeholder="비밀번호" name="password" required>
 					<span class="from-control-error"><form:errors path="password"/></span>
 				</div>
 				<div class="from-control-div">
 					<span class="from-control-title">비밀번호 확인*</span>
 					<input type="password" id="inputPasswordCheck" class="form-control" placeholder="비밀번호 확인" name="passwordcheck" required>
 				</div>
 				<div class="from-control-div">
 					<span class="from-control-title">이름*</span>
 					<input type="text" id="inputName" class="form-control" placeholder="이름" name="name" required>
 					<span class="from-control-error"><form:errors path="name"/></span>
 				</div>
 				<div class="from-control-div">
 					<span class="from-control-title">전화번호*</span>
 					<input type="text" id="inputphone" class="form-control" placeholder="전화번호" name="phone" required>
 					<span class="from-control-error"><form:errors path="phone"/></span>
 				</div>
 				<div class="from-control-div">
 					<span class="from-control-title">이메일*</span>
 					<input type="email" id="inputEmail" class="form-control" placeholder="이메일" name="email" required>
 					<span class="from-control-error"><form:errors path="email"/></span>
 				</div>
 				<div class="from-control-div">
 					<span class="from-control-title">우편번호</span>
 					<input type="text" id="inputPostid" class="form-control" placeholder="우편번호" name="postid">
 					<span class="from-control-error"><form:errors path="postid"/></span>
 				</div>
 				<div class="from-control-div">
 					<span class="from-control-title">기본주소</span>
 					<input type="text" id="inputBaseDeliver" class="form-control" placeholder="기본주소" name="base_deliver">
 				</div>
 				<div class="from-control-div">
 					<span class="from-control-title">상세주소</span>
 					<input type="text" id="inputDetailDeliver" class="form-control" placeholder="상세주소" name="detail_deliver">
 				</div>
 				
 				<button class="btn btn-lg btn-primary btn-block btn-signup" type="submit">로그인</button>
 			</form:form>
 		</div>
 	</div>
</body>
</html>