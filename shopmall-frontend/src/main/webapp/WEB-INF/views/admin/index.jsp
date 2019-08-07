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
		</div>
	</div>

</body>

</html>
