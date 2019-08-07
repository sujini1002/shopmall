<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%--     <div class="col-lg-3">
				<h1 class="my-4">Admin</h1>
				<div class="list-group">
						<a href="${pageContext.servletContext.contextPath }/admin/product" class="list-group-item">상품 등록</a>
						<a href="${pageContext.servletContext.contextPath }/" class="list-group-item">상품 조회</a>
						<a href="${pageContext.servletContext.contextPath }/" class="list-group-item">회원 관리</a>
				</div>
	</div> --%>
	
	<div class="bg-light border-right col-lg-3">
      <div class="sidebar-heading"><h1>Admin </h1></div>
      <div class="list-group list-group-flush">
	       <a href="${pageContext.servletContext.contextPath }/admin/product" class="list-group-item list-group-item-action bg-light">상품 등록</a>
		   <a href="${pageContext.servletContext.contextPath }/" class="list-group-item list-group-item-action bg-light">상품 조회</a>
		   <a href="${pageContext.servletContext.contextPath }/" class="list-group-item list-group-item-action bg-light">회원 관리</a>
      </div>
    </div>