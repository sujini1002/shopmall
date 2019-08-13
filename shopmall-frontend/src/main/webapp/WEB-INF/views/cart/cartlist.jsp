<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Shop Homepage - Start Bootstrap Template</title>
	<script   src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery.min.js"></script>
	<!-- Bootstrap core CSS -->
	<link href="${pageContext.servletContext.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<!-- Custom styles for this template -->
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-cart-list.css" rel="stylesheet">
	
</head>
<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="shopping" />
	</c:import>
	<!-- /.Navigation -->
	<div class="container">

		<div class="row cart-wrapper">
			<div class="col-lg-1">
			</div>
			<div class="col-lg-10">
				<div class="row cart-content">
					<div class="col-lg-12">
						<sec:authorize access="isAuthenticated()">
							<sec:authentication property = "principal.name" var = "name"/>
							<h2>${name }의 장바구니</h2>
						</sec:authorize>
					</div>
					<div class="col-lg-12">
						<table class="table">
							  <thead class="thead-dark">
								    <tr>
								      <th scope="col">#</th>
								      <th scope="col">상품이미지</th>
								      <th scope="col">상품정보</th>
								      <th scope="col">판매가</th>
								      <th scope="col">수량</th>
								      <th scope="col">합계</th>
								    </tr>
							  </thead>
							  <tbody>
							  		<c:set var="total" value="0"/>
							  		<c:forEach items="${cartList }" var="vo" varStatus="i">
							  			<tr>
								  			<th scope="row">${i.index+1 }</th>
								  			<td class="cart-img">
								  				<img src="${pageContext.servletContext.contextPath }/assets/images/${vo.url}"/>
								  			</td>
								  			<td>
												<h5 class="cart-title">${vo.title }</h5>
												<h6 class="cart-value">${vo.opt_value }</h6>
								  			</td>
								  			<td>
													${vo.price }
								  			</td>
								  			<td>
								  					${vo.count }
								  			</td>
								  			<td>
								  					${vo.price * vo.count }
								  					<c:set var="total" value="${total + vo.price * vo.count }"/>
								  			</td>
							  			</tr>
							  		</c:forEach>
							  			<tr>
							  				<td colspan="6">
							  					<h3>총 주문 금액  : ${total } 원</h3>
							  				</td>
							  			</tr>
							  </tbody>
						 </table>
						 
					</div>
					<hr class="cart-hr"/>
					<div class="col-lg-12">
						<div class="row">
							<div class="col-lg-4"></div>
							<div class="col-lg-4"></div>
							<div class="col-lg-4 div-order-btn">
								<input type="button" class="btn cart-btn" value="주문하기" onclick="location.href='${pageContext.servletContext.contextPath }/order/write'">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-1">
			</div>
		</div>
	</div>
	<!-- Footer -->
	<c:import url='/WEB-INF/views/includes/footer.jsp' />
	<!-- /.Footer -->
</body>
</html>