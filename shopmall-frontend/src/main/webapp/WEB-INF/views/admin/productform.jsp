<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Shop Homepage - Start Bootstrap Template</title>
	<!-- Bootstrap core CSS -->
	<link href="${pageContext.servletContext.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<!-- Custom styles for this template -->
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-admin.css" rel="stylesheet">
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-product-form.css" rel="stylesheet">
</head>
<body>
	<!-- Navigation -->
		<c:import url='/WEB-INF/views/admin/includes/navigation.jsp'>
			<c:param name="active" value="shopping" />
		</c:import>
	<!-- /.Navigation -->
	<div class="container">
		<div class="row">
			<!-- sidebar -->
			<c:import url='/WEB-INF/views/admin/includes/sidebar.jsp'>
				<c:param name="active" value="shopping" />
			</c:import>
			<!-- /.sidebar -->
			
			<div class="col-lg-9">
				<form method="post" action="${pageContext.servletContext.contextPath}/admin/product" enctype="multipart/form-data">
					<div class="row shop-product-wrapper">
						<div class="col-lg-3">
							<h5>상품명</h5>
						</div>
						<div class="col-lg-9">
							<input type="text" id="inputTitle" name="title" class="form-control" placeholder="상품명을 입력하세요" required/>
						</div>
					</div>
					<div class="row shop-product-wrapper">
						<div class="col-lg-3">
							<h5>판매 가격</h5>
						</div>
						<div class="col-lg-9">
							<input type="text" id="inputTitle" name="title" class="form-control" placeholder="판매가격을 입력하세요" required/>
						</div>
					</div>
					<div class="row shop-product-wrapper">
						<div class="col-lg-3">
							<h5>상품 상세 설명</h5>
						</div>
						<div class="col-lg-9">
							
						</div>
					</div>
					<div class="row shop-product-wrapper">
						<div class="col-lg-3">
							<h5>상품 이미지</h5>
						</div>
						<div class="col-lg-9">
							
						</div>
					</div>
					<div class="row shop-product-wrapper">
						<div class="col-lg-3">
							<h5>상품 카테고리</h5>
						</div>
						<div class="col-lg-9">
							
						</div>
					</div><div class="row shop-product-wrapper">
						<div class="col-lg-3">
							<h5>상품 옵션</h5>
						</div>
						<div class="col-lg-9">
							
						</div>
					</div>
					<div class="row shop-product-wrapper">
						<div class="col-lg-3">
							<h5>상품 재고</h5>
						</div>
						<div class="col-lg-9">
							
						</div>
					</div>
					
				</form>
			</div>
		</div>
		
	</div>

</body>
</html>