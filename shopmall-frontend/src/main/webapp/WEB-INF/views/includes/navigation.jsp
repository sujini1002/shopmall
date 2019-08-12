<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="#">&nbsp;</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
					
					<sec:authorize access="isAuthenticated()">
							<sec:authorize access="hasRole('ADMIN')">
								<li class="nav-item">
									<a class="nav-link" href="${pageContext.servletContext.contextPath }">메인 홈</a>
								</li>
								<li class="nav-item">
									<a class="nav-link" href="${pageContext.servletContext.contextPath }/admin">관리자 홈</a>
								</li>
								<li class="nav-item active">
									<a class="nav-link" href="${pageContext.servletContext.contextPath }/member/logout">로그아웃<span class="sr-only">(current)</span></a>
								</li>
							</sec:authorize>
							<sec:authorize access="!hasRole('ADMIN')">
								<li class="nav-item">
									<a class="nav-link" href="${pageContext.servletContext.contextPath }">홈</a>
								</li>
								<li class="nav-item active">
									<a class="nav-link" href="${pageContext.servletContext.contextPath }/member/logout">로그아웃<span class="sr-only">(current)</span></a>
								</li>
								<li class="nav-item">
									<a class="nav-link" href="${pageContext.servletContext.contextPath }/member/info">My</a>
								</li>
								<li class="nav-item">
									<sec:authentication property = "principal.no" var = "no"/>
									<a class="nav-link" href="${pageContext.servletContext.contextPath }/cart/${no}">장바구니</a>
								</li>
							</sec:authorize>
					</sec:authorize>
					<sec:authorize access="!isAuthenticated()">
						
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }">홈</a>
						</li>
						<li class="nav-item active">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/member/login">로그인<span class="sr-only">(current)</span></a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/member/join">회원가입</a>
						</li>
					</sec:authorize>
			</ul>
		</div>
	</div>
</nav>