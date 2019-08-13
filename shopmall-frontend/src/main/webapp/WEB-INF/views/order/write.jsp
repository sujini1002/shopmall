<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
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
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-order-write.css" rel="stylesheet">
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('input[name="issame"]').on('change',function(){
				if($("#issame-true").is(":checked")){
					// 기본 주소와 동일 
					// 이름,우편번호, 기본주소 ,상세주소 , 휴대전화 번호
					$('input[name="rev_name"]').val($('input[name="name"]').val());
					$('input[name="rev_phone"]').val($('input[name="phone"]').val());
					$('input[name="rev_postid"]').val($('input[name="postid"]').val());
					$('input[name="rev_base_deliver"]').val($('input[name="base_deliver"]').val());
					$('input[name="rev_detail_deliver"]').val($('input[name="detail_deliver"]').val());
				}else{
					//새로운 배송지
					$('input[name="rev_name"]').val('');
					$('input[name="rev_phone"]').val('');
					$('input[name="rev_postid"]').val('');
					$('input[name="rev_base_deliver"]').val('');
					$('input[name="rev_detail_deliver"]').val('');
				}
		       
			});
			
			$('input[name="pay_way"]').on('change',function(){
				
			});
		});
	</script>
</head>
<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="shopping" />
	</c:import>
	<!-- /.Navigation -->
	<div class="container">

		<div class="row order-wrapper">
			<div class="col-lg-1">
			</div>
			<div class="col-lg-10">
				<form action="${pageContext.servletContext.contextPath }/order" method="post">
				<div class="row order-content">
					<div class="col-lg-12">
							<h2>주문 내역 작성</h2>
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
									  			<td class="order-img">
									  				<img src="${pageContext.servletContext.contextPath }/assets/images/${vo.url}"/>
									  			</td>
									  			<td>
													<h5 class="order-title">${vo.title }</h5>
													<h6 class="order-value">${vo.opt_value }</h6>
													
													<input type="hidden" name="inventory_no" value="${vo.inventory_no }"/>
									  			</td>
									  			<td>
														${vo.price }
									  			</td>
									  			<td>
									  					${vo.count }
									  					<input type="hidden" name="count" value="${vo.count }"/>
									  			</td>
									  			<td>
									  					${vo.price * vo.count }
									  					<c:set var="total" value="${total + vo.price * vo.count }"/>
									  					<input type="hidden" name="price" value="${vo.price * vo.count }">
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
						<div class="col-lg-12 order-input">
							<div class="row">
								<div class="col-lg-12">
									<h4 class="order-statement-title">주문자 정보*</h4>
									<input type="hidden" name="member_code" value="${member.no }"/>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-3 order-statement-field">
									<h5>주문자 성명*</h5>
								</div>
								<div class="col-lg-9">
									<input type="text" name="name" class="form-control" value="${member.name }" required/>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-3 order-statement-field">
									<h5>주문자 배송지*</h5>
								</div>
								<div class="col-lg-9">
									<div class="row order-deliver">
										<div class="col-lg-12">
											<input type="text" name="postid"  class="form-control postid" value="${member.postid }" required/>
											<button type="button" class="btn btn-info"> 우편번호 </button>
										</div>
										<div class="col-lg-12">
											<input type="text" name="base_deliver" class="form-control base_deliver" value="${member.base_deliver }" required/>
										</div>
										<div class="col-lg-12">
											<input type="text" name="detail_deliver" class="form-control detail-deliver" value="${member.detail_deliver }" required/>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-3 order-statement-field">
									<h5>휴대전화 번호*</h5>
								</div>
								<div class="col-lg-9">
									<input type="text" name="phone"  class="form-control" value="${member.phone }" required/>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-3 order-statement-field">
									<h5>이메일*</h5>
								</div>
								<div class="col-lg-9">
									<input type="email" name="email" class="form-control" value="${member.email }" required/>
								</div>
							</div>
							<div class="row"></div>
							<div class="row">
								<div class="col-lg-12">
									<h4 class="order-statement-title">배송지 정보*</h4>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-3 order-statement-field">
									<h5>배송지 선택</h5>
								</div>
								<div class="col-lg-9">
									<label class="radio-inline">
								      <input type="radio" name="issame" id="issame-true">기본 주문자 정보와 동일
								    </label>
								    &nbsp;	
								    <label class="radio-inline">
								      <input type="radio" name="issame" id="issame-false" checked>새로운 배송지
								    </label>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-3 order-statement-field">
									<h5>받으시는 분*</h5>
								</div>
								<div class="col-lg-9">
									<input type="text" name="rev_name"  class="form-control" value="" required/>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-3 order-statement-field">
									<h5>배송지*</h5>
								</div>
								<div class="col-lg-9">
									<div class="row order-deliver">
										<div class="col-lg-12">
											<input type="text" name="rev_postid"  class="form-control postid" value="" required/>
											<button type="button" class="btn btn-info"> 우편번호 </button>
										</div>
										<div class="col-lg-12">
											<input type="text" name="rev_base_deliver" class="form-control base_deliver" value="" required/>
										</div>
										<div class="col-lg-12">
											<input type="text" name="rev_detail_deliver" class="form-control detail_deliver" value="" required/>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-3 order-statement-field">
									<h5>휴대전화 번호*</h5>
								</div>
								<div class="col-lg-9">
									<input type="text" name="rev_phone"  class="form-control" value="" required/>
								</div>
							</div>
						</div>
						
						<div class="col-lg-12">
							<div class="row order-payment">
								<div class="col-lg-12">
									<h4  class="order-statement-title">결제 정보</h4>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-3 order-statement-field">
									<h5 class="order-statement-title">결제 수단*</h5>
								</div>
								<div class="col-lg-9">
									<label class="radio-inline">
								      <input type="radio" name="pay_way"  value="신용카드" checked>신용카드
								    </label>
								    &nbsp;	
								    <label class="radio-inline">
								      <input type="radio" name="pay_way" value="무통장입금">무통장입금
								    </label>
								    <div class="deposit-content">
									    <div class="row">
									    	<div class="col-lg-3 order-statement-field">
									    		<h5>입금은행</h5>
									    	</div>
									    	<div class="col-lg-9">
									    		<input type="text" name="bank"  class="form-control postid" value=""/>
									    	</div>
									    </div>
									    <div class="row">
									    	<div class="col-lg-3 order-statement-field">
									    		<h5>입금자명</h5>
									    	</div>
									    	<div class="col-lg-9">
									    		<input type="text" name="deposit_name"  class="form-control" value=""/>
									    	</div>
									    </div>
									    <div class="row">
									    	<div class="col-lg-3 order-statement-field">
									    		<h5>환불계좌</h5>
									    	</div>
									    	<div class="col-lg-9">
									    		<input type="text" name="refund_account"  class="form-control" value=""/>
									    	</div>
									    </div>
								    </div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-3 order-statement-field">
									<h5 class="order-statement-title deliver-price-title">배송비</h5>
								</div>
								<div class="col-lg-9 deliver-price">
									<c:choose>
										<c:when test="${total >= 70000 }">
											0
										    <input type="hidden" name="deliver_price" value="0"/>
										</c:when>
										<c:otherwise>
											2500
											<input type="hidden" name="deliver_price" value="2500"/>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							
							<div class="row">
								<div class="col-lg-3 order-statement-field">
									<h5 class="order-statement-title total-price">총 결제금액</h5>
								</div>
								<div class="col-lg-9">
									<c:choose>
										<c:when test="${total >= 70000 }">
											${total }
											<input type="hidden" name="payment" value="${total }"/>
										</c:when>
										<c:otherwise>
											<h3>${total + 2500 }</h3>
											<input type="hidden" name="payment" value="${total + 2500 }"/>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
						<div class="col-lg-12"></div>
						<div class="col-lg-12 order-submit">
							<input type="submit" value="주문하기" class="btn btn-info order-submit-btn"/>
						</div>
				</div>
				</form>
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