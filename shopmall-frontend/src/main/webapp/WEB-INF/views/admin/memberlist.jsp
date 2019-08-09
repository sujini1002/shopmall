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
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-admin-member.css" rel="stylesheet">
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
					<h2>Member List</h2>
					<table class="table list-tr">
						  <thead class="thead-dark">
							    <tr>
							      <th scope="col">#</th>
							      <th scope="col">이름</th>
							      <th scope="col">아이디</th>
							      <th scope="col">휴대전화</th>
							      <th scope="col">배송지</th>
							      <th scope="col">메일주소</th>
							      <th scope="col">탈퇴여부</th>
							    </tr>
						  </thead>
						  <tbody>
						  		<c:forEach items="${memberList}" var="vo" varStatus="i">
						  			<tr>
									      <th scope="row">${i.index + 1 }</th>
									      <td>${vo.name }</td>
									      <td>${vo.id }</td>
									      <td>${vo.phone }</td>
									      <td>
									      		<c:if test="${vo.postid ne '' and vo.base_deliver ne '' and vo.detail_deliver ne '' }">
									      			(${vo.postid }) ${vo.base_deliver } ${vo.detail_deliver }
									      		</c:if>
									      		
									      </td>
									      <td>${vo.email }</td>
									      <td >
									      		<c:choose>
									      			<c:when test="${vo.isdrop eq true }">
									      				탈퇴
									      			</c:when>
									      			<c:otherwise>
									      				회원
									      			</c:otherwise>
									      		</c:choose>
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
