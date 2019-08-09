<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
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
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-admin-product-list.css" rel="stylesheet">
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
					<h2>Product List</h2>
					<table class="table">
						  <thead class="thead-dark">
							    <tr>
							      <th scope="col">#</th>
							      <th scope="col">상품명</th>
							      <th scope="col">판매가</th>
							      <th scope="col">판매여부</th>
							      <th scope="col">상품분류</th>
							      <th scope="col">상품등록일</th>
							    </tr>
						  </thead>
						  <tbody>
						  		<c:forEach items="${productList}" var="vo" varStatus="i">
						  			<tr>
									      <th scope="row">${i.index + 1 }</th>
									      <td>
									      		<c:forEach items="${vo.prodImgList }" var="img">
									      			<c:if test="${img.istitle eq true }">
									      				<img class="product-img" src="${pageContext.servletContext.contextPath }/assets/images/${img.url}"/>	
									      			</c:if>
									      		</c:forEach>
									      		<a href="#">${vo.title }</a>
									      </td>
									      <td>${vo.price }</td>
									      <td>${vo.issale }</td>
									      <td>
									      	<c:forEach items="${vo.category}" var="category">
									      		<c:choose>
									      			<c:when test="${category.top_category eq null }">
									      				${category.bottom_category }
									      			</c:when>
									      			<c:otherwise>
									      				${category.top_category } > ${category.bottom_category }
									      			</c:otherwise>
									      		</c:choose>
									      	</c:forEach>
									      </td>
									      <td>
									      	${vo.prod_date }
									      </td>
							    	</tr>
						  		</c:forEach>
						  </tbody>
					</table>
			</div>
		</div>
	
	</div>
	
</body>

</html>
