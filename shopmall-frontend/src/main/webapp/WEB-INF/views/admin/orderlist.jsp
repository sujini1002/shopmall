<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-admin.css" rel="stylesheet">
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-admin-order-list.css" rel="stylesheet">
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
			<div class="col-lg-9 list-content">
				<h2>Orders List</h2>
				<table class="table">
						  <thead class="thead-dark">
							    <tr>
							      <th scope="col">#</th>
							      <th scope="col" >주문코드</th>
							      <th scope="col" >주문명</th>
							      <th scope="col">주문자</th>
							      <th scope="col">주문일</th>
							      <th scope="col">주문상황</th>
							      <th scope="col">결제금액</th>
							      <th scope="col">결제수단</th>
							    </tr>
						  </thead>
						  <tbody>
						  		<c:forEach items="${orders }" var="vo" varStatus="i">
						  			<tr>
						  				<td scope="col" class="o-index">${i.index + 1 }</td>
						  				<td class="o-code">${vo.order_code }</td>
						  				<td class="o-title">
						  					<c:choose>
						  						<c:when test="${fn:substring(vo.order_title,fn:length(vo.order_title)-2,fn:length(vo.order_title)) eq '0건' }">
						  							${fn:substring(vo.order_title,0,fn:length(vo.order_title)-3) }
						  						</c:when>
						  						<c:otherwise>
						  							${vo.order_title }
						  						</c:otherwise>
						  					</c:choose>
						  				</td>
						  				<td class="o-id">${vo.id }</td>
						  				<td class="o-date">${vo.order_date }</td>
						  				<td class="o-status">${vo.status }</td>
						  				<td class="o-payment">${vo.payment }</td>
						  				<td class="o-payway">${vo.pay_way }</td>
						  			</tr>
						  		</c:forEach>
						  </tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>