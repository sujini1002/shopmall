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
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-product-list.css" rel="stylesheet">
</head>
<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="shopping" />
	</c:import>
	<!-- /.Navigation -->
	
	<div class="container">
		<div class="row content-wrapper">

			<div class="col-lg-3">
				<h1 class="my-4">ShopMall</h1>
				<div class="list-group">
					<c:forEach items="${category}" var="vo" varStatus="status">
						<c:if test="${vo.catg_top_no eq null }">
							<a href="${pageContext.servletContext.contextPath }/product/category/${vo.no}" class="list-group-item">${vo.name }</a>
						</c:if>
					</c:forEach>
				</div>
			</div>
			<!-- /.col-lg-3 -->

			<div class="col-lg-9">
				<div class="row product-list">
					<c:forEach items="${product}" var="vo">
						<div class="col-lg-4 col-md-6 mb-4">
							<div class="card h-100">
								<a href="${pageContext.servletContext.contextPath }/product/${vo.no}"><img class="card-img-top"
									src="${pageContext.servletContext.contextPath }/assets/images/${vo.url}" alt=""></a>
								<div class="card-body">
									<h4 class="card-title">
										<a href="${pageContext.servletContext.contextPath }/product/${vo.no}">
											<span>${vo.title }</span>
										</a>
									</h4>
									<h5>${vo.price }</h5>
									<p class="card-text"></p>
								</div>
								<div class="card-footer">
									<small class="text-muted">&#9733; &#9733; &#9733;
										&#9733; &#9734;</small>
								</div>
							</div>
						</div>
					</c:forEach>
					
				</div>
				<!-- /.row -->
			</div>
			<!-- /.col-lg-9 -->
			
		</div>
		<!-- /.row -->
	</div>
	<!-- /.container -->

	<!-- Footer -->
	<c:import url='/WEB-INF/views/includes/footer.jsp' />
	<!-- /.Footer -->
</body>

</html>
