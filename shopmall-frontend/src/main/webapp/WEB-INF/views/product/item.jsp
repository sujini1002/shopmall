<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% pageContext.setAttribute("newline", "\n");%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Shop Homepage - Start Bootstrap Template</title>
	<!-- Bootstrap core CSS -->
	<link href="${pageContext.servletContext.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<!-- Custom styles for this template -->
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-item.css" rel="stylesheet">
</head>

<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="shopping" />
	</c:import>
	<!-- /.Navigation -->

	<!-- Page Content -->
	<div class="container">

		<div class="row content-wrapper">
			<!-- 카테고리  -->
			<div class="col-lg-3">
				<h1 class="my-4">ShopMall</h1>
				<div class="list-group">
					<a href="#" class="list-group-item active">Category 1</a>
					<a href="#" class="list-group-item">Category 2</a>
					<a href="#" class="list-group-item">Category 3</a>
				</div>
			</div>
			<!-- /.col-lg-3 -->

			<div class="col-lg-9 item-wrapper">
				<div class="row">
					<div class="col-lg-7">
						<div class="img-title col-lg-12">
							<c:forEach items="${product.prodImgList}" var="vo">
								<c:if test="${vo.istitle eq true }">
									<img src="${pageContext.servletContext.contextPath }/assets/images/${vo.url}"/>
								</c:if>
							</c:forEach>
						</div>
						<div class="img-list col-lg-12">
							<div class="row">
								<c:forEach items="${product.prodImgList}" var="vo">
									<c:if test="${vo.istitle eq false }">
										<div class="col-lg-4">
											<img src="${pageContext.servletContext.contextPath }/assets/images/${vo.url}"/>
										</div>
									</c:if>
								</c:forEach>
							</div>
						</div>
						
					</div>
					<div class="col-lg-5 product-info">
						<div class="col-lg-12">
							<h2>${product.title }</h2>
						</div>
						<div class="col-lg-12">
							<div class="row">
								<div class="col-lg-3">
									<h5> price</h5>
								</div>
								<div class="col-lg-9">
									<h4>${product.price }</h4>
								</div>
							</div>
						</div>
						<c:forEach items="${product.optionList }" var="vo">
							<div class="col-lg-12 product-option">
								<div class="row">
									<div class="col-lg-3">
											<h5>${vo.name }</h5>
									</div>
									<div class="col-lg-9">
											<select class="browser-default custom-select" id="option-top-select" name="option1">
												<option value="null" disabled selected>${vo.name }을/를 선택하세요</option>
												<c:forEach items="${vo.optionDetailList }" var="od">
													<option value="${od.no }">${od.value }</option>
												</c:forEach>
											</select>
									</div>
								</div>
							</div>
						</c:forEach>
						<div class="col-lg-12">
							
						</div>
						<div class="col-lg-12">
							<div class="row">
								<div class="col-lg-4">
									
								</div>
								<div class="col-lg-4"></div>
								<div class="col-lg-4"></div>
							</div>
						</div>
						<div class="col-lg-12">
							<div class="row">
								<div class="col-lg-6">
									<button class="">구매하기</button>
								</div>
								<div class="col-lg-6">
									<button class="">장바구니</button>
								</div>
							</div>
						</div>
						
					</div>
				</div>
				<hr/>
				<div class="row product-content">
					<div class="col-lg-12 title">
						<h4>상품 상세설명</h4>
					</div>
					<div class="col-lg-12 detail">
						<p>
							${fn:replace(product.detail,newline,"<br>") }
						</p>
					</div>				
				</div>
			</div>
			<!-- /.col-lg-9 -->

		</div>

	</div>
	<!-- /.container -->

	<!-- Footer -->
	<c:import url='/WEB-INF/views/includes/footer.jsp' />
	<!-- /.Footer -->
</body>

</html>