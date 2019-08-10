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
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-admin-index.css" rel="stylesheet">
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
			<div class="col-lg-9 index-content">
				<h2>최근 내역</h2>
				<table class="table">
						  <thead class="thead-dark">
							    <tr>
							      <th scope="col">#</th>
							      <th scope="col">제목</th>
							      <th scope="col">작성자</th>
							      <th scope="col">분류</th>
							      <th scope="col">상품등록일</th>
							    </tr>
						  </thead>
						  <tbody>
						  		<tr>
						  			<th scope="row">1</th>
						  			<td>
						  				<span class="bold">강수진님께서  madeops 상품을 장바구니에 담았습니다.</span>
						  			</td>
						  			<td>
						  				<span>관리자1</span>
						  			</td>
						  			<td>
						  				<span>장바구니</span>
						  			</td>
						  			<td>
						  				<span>2019-08-10</span>
						  			</td>
						  		</tr>
						  		<tr>
						  			<th scope="row">2</th>
						  			<td>
						  				<span class="bold">강수진님께서  크롭skinny 상품을 장바구니에 담았습니다.</span>
						  			</td>
						  			<td>
						  				<span>관리자1</span>
						  			</td>
						  			<td>
						  				<span>장바구니</span>
						  			</td>
						  			<td>
						  				<span>2019-08-10</span>
						  			</td>
						  		</tr>
						  		<tr>
						  			<th scope="row">3</th>
						  			<td>
						  				<span class="bold">신영만님께서 wood earing을  상품을 주문하였습니다.</span>
						  			</td>
						  			<td>
						  				<span>관리자2</span>
						  			</td>
						  			<td>
						  				<span>주문</span>
						  			</td>
						  			<td>
						  				<span>2019-08-09</span>
						  			</td>
						  		</tr>
						  		<tr>
						  			<th scope="row">4</th>
						  			<td>
						  				<span class="bold">신영만님께서 베리스 made ops 상품을 주문하였습니다.</span>
						  			</td>
						  			<td>
						  				<span>관리자1</span>
						  			</td>
						  			<td>
						  				<span>주문</span>
						  			</td>
						  			<td>
						  				<span>2019-08-09</span>
						  			</td>
						  		</tr>
						  		<tr>
						  			<th scope="row">5</th>
						  			<td>
						  				<span class="bold">나미리님께서  88cotton 상품을 장바구니에 담았습니다.</span>
						  			</td>
						  			<td>
						  				<span>관리자3</span>
						  			</td>
						  			<td>
						  				<span>장바구니</span>
						  			</td>
						  			<td>
						  				<span>2019-08-09</span>
						  			</td>
						  		</tr>
						  		<tr>
						  			<th scope="row">6</th>
						  			<td>
						  				<span class="bold">채성아님께서  도토리 check skirt 상품을 주문하였습니다.</span>
						  			</td>
						  			<td>
						  				<span>관리자2</span>
						  			</td>
						  			<td>
						  				<span>주문</span>
						  			</td>
						  			<td>
						  				<span>2019-08-09</span>
						  			</td>
						  		</tr>
						  		<tr>
						  			<th scope="row">7</th>
						  			<td>
						  				<span class="bold">신짱구님께서 트임mtm을 장바구니에 담았습니다.</span>
						  			</td>
						  			<td>
						  				<span>관리자2</span>
						  			</td>
						  			<td>
						  				<span>장바구니</span>
						  			</td>
						  			<td>
						  				<span>2019-08-09</span>
						  			</td>
						  		</tr>
						  		<tr>
						  			<th scope="row">8</th>
						  			<td>
						  				<span class="bold">이유리님께서 캔디cardigan을 주문하였습니다.</span>
						  			</td>
						  			<td>
						  				<span>관리자1</span>
						  			</td>
						  			<td>
						  				<span>주문</span>
						  			</td>
						  			<td>
						  				<span>2019-08-09</span>
						  			</td>
						  		</tr>
						  		<tr>
						  			<th scope="row">9</th>
						  			<td>
						  				<span class="bold">강수진님께서  로트 made shirt를 주문하였습니다.</span>
						  			</td>
						  			<td>
						  				<span>관리자3</span>
						  			</td>
						  			<td>
						  				<span>주문</span>
						  			</td>
						  			<td>
						  				<span>2019-08-08</span>
						  			</td>
						  		</tr>
						  		<tr>
						  			<th scope="row">10</th>
						  			<td>
						  				<span class="bold">봉미선님께서 벨티드 -made jacket을 주문하였습니다.</span>
						  			</td>
						  			<td>
						  				<span>관리자1</span>
						  			</td>
						  			<td>
						  				<span>주문</span>
						  			</td>
						  			<td>
						  				<span>2019-08-08</span>
						  			</td>
						  		</tr>
						  		<tr>
						  			<th scope="row">11</th>
						  			<td>
						  				<span class="bold">봉미선님께서 벨티드 made-jacket을 장바구니에 담았습니다.</span>
						  			</td>
						  			<td>
						  				<span>관리자1</span>
						  			</td>
						  			<td>
						  				<span>장바구니</span>
						  			</td>
						  			<td>
						  				<span>2019-08-08</span>
						  			</td>
						  		</tr>
						  		<tr>
						  			<th scope="row">12</th>
						  			<td>
						  				<span class="bold">나미리님께서 merci cotton bag을 장바구니에 담았습니다.</span>
						  			</td>
						  			<td>
						  				<span>관리자2</span>
						  			</td>
						  			<td>
						  				<span>장바구니</span>
						  			</td>
						  			<td>
						  				<span>2019-08-08</span>
						  			</td>
						  		</tr>
						  		<tr>
						  			<th scope="row">13</th>
						  			<td>
						  				<span class="bold">채성아님께서 크롭 skinny를 주문하였습니다.</span>
						  			</td>
						  			<td>
						  				<span>관리자4</span>
						  			</td>
						  			<td>
						  				<span>주문</span>
						  			</td>
						  			<td>
						  				<span>2019-08-07</span>
						  			</td>
						  		</tr>
						  		<tr>
						  			<th scope="row">14</th>
						  			<td>
						  				<span class="bold">김코난님께서 백리본 ops를 주문하였습니다.</span>
						  			</td>
						  			<td>
						  				<span>관리자2</span>
						  			</td>
						  			<td>
						  				<span>주문</span>
						  			</td>
						  			<td>
						  				<span>2019-08-07</span>
						  			</td>
						  		</tr>
						  		<tr>
						  			<th scope="row">15</th>
						  			<td>
						  				<span class="bold">김코난님께서 데이지skirt를 장바구니에 담았습니다.</span>
						  			</td>
						  			<td>
						  				<span>관리자3</span>
						  			</td>
						  			<td>
						  				<span>장바구니</span>
						  			</td>
						  			<td>
						  				<span>2019-08-07</span>
						  			</td>
						  		</tr>
						  		<tr>
						  			<th scope="row">16</th>
						  			<td>
						  				<span class="bold">이유리님께서 글래디 check nb를 주문 취소하였습니다.</span>
						  			</td>
						  			<td>
						  				<span>관리자1</span>
						  			</td>
						  			<td>
						  				<span>주문취소</span>
						  			</td>
						  			<td>
						  				<span>2019-08-07</span>
						  			</td>
						  		</tr>
						  		<tr>
						  			<th scope="row">17</th>
						  			<td>
						  				<span class="bold">신짱아님께서 wood earing을 주문하였습니다.</span>
						  			</td>
						  			<td>
						  				<span>관리자2</span>
						  			</td>
						  			<td>
						  				<span>주문</span>
						  			</td>
						  			<td>
						  				<span>2019-08-06</span>
						  			</td>
						  		</tr>
						  		<tr>
						  			<th scope="row">18</th>
						  			<td>
						  				<span class="bold">강수진님께서 cream hat을 장바구니에 담았습니다.</span>
						  			</td>
						  			<td>
						  				<span>관리자3</span>
						  			</td>
						  			<td>
						  				<span>장바구니</span>
						  			</td>
						  			<td>
						  				<span>2019-08-06</span>
						  			</td>
						  		</tr>
						  		<tr>
						  			<th scope="row">19</th>
						  			<td>
						  				<span class="bold">신영만님께서 린넨 후드 ops를 장바구니에 담았습니다.</span>
						  			</td>
						  			<td>
						  				<span>관리자3</span>
						  			</td>
						  			<td>
						  				<span>장바구니</span>
						  			</td>
						  			<td>
						  				<span>2019-08-05</span>
						  			</td>
						  		</tr>
						  		<tr>
						  			<th scope="row">20</th>
						  			<td>
						  				<span class="bold">김철수님께서 미드힐 카라멜 sandal을 주문하였습니다.</span>
						  			</td>
						  			<td>
						  				<span>관리자4</span>
						  			</td>
						  			<td>
						  				<span>주문</span>
						  			</td>
						  			<td>
						  				<span>2019-08-05</span>
						  			</td>
						  		</tr>
						  </tbody>
				</table>
			</div>
		</div>
	</div>

</body>

</html>
