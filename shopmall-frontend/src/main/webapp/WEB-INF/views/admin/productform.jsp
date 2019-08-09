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
	<script   src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery.min.js"></script>
	<!-- Bootstrap core CSS -->
	<link href="${pageContext.servletContext.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<!-- Tag it Library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.12/jquery-ui.min.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/flick/jquery-ui.css">
	<link href="${pageContext.servletContext.contextPath}/assets/css/tagit/jquery.tagit.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.servletContext.contextPath}/assets/css/tagit/tagit.ui-zendesk.css" rel="stylesheet" type="text/css">
	<script   src="${pageContext.servletContext.contextPath }/assets/js/lib/tag-it-min.js"></script>
	<script   src="${pageContext.servletContext.contextPath }/assets/js/lib/tag-it.js"></script>
	<!-- Custom styles for this template -->
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-admin.css" rel="stylesheet">
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-product-form.css" rel="stylesheet">
	
	<script type="text/javascript">
		//이미지 미리보기
		function readFile(input) {
	        if (input.files && input.files[0]) {
	            var reader = new FileReader();
	            reader.onload = function(e) {
	            	var tmp = input.id;
	            	console.log(tmp);
	            	$('#'+tmp).siblings('input[type="image"]').attr('src',e.target.result);
	                $('#preview-img').attr('src', e.target.result);
	            }
	            reader.readAsDataURL(input.files[0]);
	        }
	    }
		
		$(document).ready(function () {
			// 이미지 미리보기
			$('input[type="file"]').on('change',function(){
				readFile(this);
			});
			
			// file 이미지 버튼
			$("input[class='img']").click(function(e) {
        	    e.preventDefault();
        	    $(this).next().click();
           	});
	         
			//셀렉트 박스 
			$('#category-top-select').on('change',function(){
				// 기존 하위 카테고리 목록 제거
				$('#category-bottom-select').empty();
				// 기본 값인 하위 카테고리 목록 추가
				$('#category-bottom-select').append('<option value="null" disabled selected>하위 카테고리를 선택하세요.</option>');
				// 상위 카테고리의 value 값 추출 
				var top = $('#category-top-select').val();
				
				// 하위 카테고리에 목록 추가
				<c:forEach items="${category}" var="vo">
					var catg_top_no = '${vo.catg_top_no}';
					if(top == catg_top_no){
						var tags = '<option value="'+'${vo.no}'+'">'+'${vo.name}'+'</option>';
						$('#category-bottom-select').append(tags);
					}
				</c:forEach>
				
			});
			
			//옵션 디테일을 위한 태그잇 라이브러리 
			 $(".myTags").tagit();
			
			//옵션 값의 input 가져오기
			$('#btn-option').on('click',function(){
				//기존 옵션 값 삭제
				$('#option-one-value').find('input[name="sizeDetail"]').remove();
				$('#option-two-value').find('input[name="colorDetail"]').remove();
				$('#inventory-wrapper').empty();
				
				//상품 재고 기본 값 추가
				var tags = '<div class="row"><div class="col-lg-4"><h6>품목 명</h6></div><div class="col-lg-4"><h6>재고수량</h6></div><div class="col-lg-4"><h6>비고</h6></div></div>';
				$('#inventory-wrapper').append(tags);
				
				//사이즈 값 추가
				var length = $("#option-one-value").find("ul").find('input[name="tags"]').length;
				var oneArray = new Array(length);
				for(var i = 0;i<length;i++){
					oneArray[i] = $("#option-one-value").find("ul").find('input[name="tags"]')[i].value;
					var tags = '<input type="hidden" name="oneDetail" value="'+oneArray[i]+'"/>'
					$('#option-one-value').append(tags);
				}
				//색상 값 추가
				var length = $("#option-two-value").find("ul").find('input[name="tags"]').length;
				var twoArray = new Array(length);
				for(var i = 0;i<length;i++){
					twoArray[i] = $("#option-two-value").find("ul").find('input[name="tags"]')[i].value;
					var tags = '<input type="hidden" name="twoDetail" value="'+twoArray[i]+'"/>'
					$('#option-two-value').append(tags);
				}
				
				//상품 재고 추가
				var oneLength = $("#option-one-value").find("ul").find('input[name="tags"]').length;
				var twoLength = $("#option-two-value").find("ul").find('input[name="tags"]').length;
				for(var i=0;i<oneLength;i++){
					if(twoLength>0){
						for(var j=0;j<twoLength;j++){
							var opt_value = oneArray[i]+'/'+twoArray[j];
							var tags = '<div class="row inventroy-wrapper">'+
											'<div class="col-lg-4">'+
												'<input type="text" name="opt_value" class="form-control" value="'+opt_value+'" readonly />'+
											'</div>'+
											'<div class="col-lg-4">'+
												'<input type="text" name="inventory" class="form-control"/>'+
											'</div>'+
											'<div class="col-lg-4">'+
												'<span>-1을 입력하시면 재고관리 안하는 것으로 간주 합니다.</span>'+
											'</div>'+
										'</div>';
								
							$('#inventory-wrapper').append(tags);
						}
					}else{
						var opt_value = oneArray[i];
						var tags = '<div class="row inventroy-wrapper">'+
										'<div class="col-lg-4">'+
											'<input type="text" name="opt_value" class="form-control" value="'+opt_value+'" readonly />'+
										'</div>'+
										'<div class="col-lg-4">'+
											'<input type="text" name="inventory" class="form-control"/>'+
										'</div>'+
										'<div class="col-lg-4">'+
											'<span>-1을 입력하시면 재고관리 안하는 것으로 간주 합니다.</span>'+
										'</div>'+
									'</div>';
							
						$('#inventory-wrapper').append(tags);
					}
					
				}
				
			});
			
		});
	</script>
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
							<input type="text" id="inputTitle" name="price" class="form-control" placeholder="판매가격을 입력하세요" required/>
						</div>
					</div>
					<div class="row shop-product-wrapper">
						<div class="col-lg-3">
							<h5>상품 상세 설명</h5>
						</div>
						<div class="col-lg-9">
							<textarea rows="20" cols="100" name="detail" class="form-control"></textarea>
						</div>
					</div>
					<div class="row shop-product-wrapper">
						<div class="col-lg-3">
							<h5>상품 이미지</h5>
						</div>
						<div class="col-lg-9">
							<div class="row product-form-img img-layout">
								<div class="col-lg-5 title-img">
									<h5>대표이미지</h5>
										<input type="image" class = "img" id="img0" src="${pageContext.servletContext.contextPath}/assets/picture/imgbtn128.png" />
										<input type="file" name="titleimage" id="file0" class="images"/>
								</div>
								<div class="col-lg-7">
									<h5>추가 이미지</h5>
									<div class="row ">
										<div class="col-lg-4">
											<input type="image" class = "img" id="img1" src="${pageContext.servletContext.contextPath}/assets/picture/imgbtn128.png" />
											<input type="file" class="images" name="images" id="file1" class="prod-img"/>
										</div>
										<div class="col-lg-4">
											<input type="image" class = "img" id="img2" src="${pageContext.servletContext.contextPath}/assets/picture/imgbtn128.png" />
											<input type="file" class="images" name="images"  id="file2" class="prod-img"/>
										</div>
										<div class="col-lg-4">
											<input type="image" class = "img" id="img3" src="${pageContext.servletContext.contextPath}/assets/picture/imgbtn128.png" />
											<input type="file" class="images" name="images"  id="file3" class="prod-img"/>
										</div>
									</div>
								</div>
							</div>
							<div>
								
							</div>
						</div>
					</div>
					<div class="row shop-product-wrapper">
						<div class="col-lg-3">
							<h5>상품 카테고리</h5>
						</div>
						<div class="col-lg-9">
							<div class="row">
								<div class="col-lg-6">
									<select class="browser-default custom-select" id="category-top-select" name="topCategoryNo">
										<option value="null" disabled selected>상위 카테고리를 선택하세요</option>
										<c:forEach items="${category}" var="vo">
											<c:if test="${vo.catg_top_no eq null}">
												<option value="${vo.no }">${vo.name }</option>
											</c:if>
										</c:forEach>
									</select>
								</div>
								<div class="col-lg-6">
									<select class="browser-default custom-select" id="category-bottom-select" name="bottomCategoryNo">
										  <option value="null" disabled selected>하위 카테고리를 선택하세요.</option>
									</select>
								</div>
							</div>
						</div>
					</div><div class="row shop-product-wrapper">
						<div class="col-lg-3">
							<h5>상품 옵션</h5>
						</div>
						<div class="col-lg-9">
							<div class="row product-option-wrapper">
								<div class="col-lg-4">
									<h6>옵션 명</h6>
								</div>
								<div class="col-lg-8">
									<h6>옵션 값</h6>
								</div>
							</div>
							<div class="row product-option-wrapper">
								<div class="col-lg-4">
									<input type="text" name="oneOption" class="form-control" />
								</div>
								<div class="col-lg-8" id="option-one-value">
									<ul class="myTags"></ul>
								</div>
							</div>
							<div class="row product-option-wrapper">
								<div class="col-lg-4">
									<input type="text" name="twoOption" class="form-control" />
								</div>
								<div class="col-lg-8" id="option-two-value">
									<ul class="myTags"></ul>
								</div>
							</div>
							<div class="row product-option-wrapper">
								<div class="col-lg-12">
									<button class="btn btn-lg btn-primary btn-block btn-signin" id="btn-option" type="button">옵션 만들기</button>
								</div>
							</div>
						</div>
					</div>
					<div class="row shop-product-wrapper">
						<div class="col-lg-3">
							<h5>상품 재고</h5>
						</div>
						<div class="col-lg-9" id="inventory-wrapper">
							<div class="row inventroy-wrapper">
								<div class="col-lg-4">
									<h6>품목 명</h6>
								</div>
								<div class="col-lg-4">
									<h6>재고수량</h6>
								</div>
								<div class="col-lg-4">
									<h6>판매여부</h6>
								</div>
							</div>
						</div>
					</div>
					<div class="row shop-product-wrapper">
						<div class="col-lg-3">
							<h5>판매 여부</h5>
						</div>
						<div class="col-lg-9">
								<!-- Default unchecked -->
								<div class="custom-control custom-radio">
								  <input type="radio" class="custom-control-input" id="issaletrue" name="issale" value="true" checked>
								  <label class="custom-control-label" for="issaletrue">판매함</label>
								</div>
								
								<!-- Default checked -->
								<div class="custom-control custom-radio">
								  <input type="radio" class="custom-control-input" id="issalefalse" name="issale" value="false">
								  <label class="custom-control-label" for="issalefalse">판매안함</label>
								</div>
						</div>
					</div>
					<div class="row shop-product-wrapper">
						<div class="col-lg-12">
							<button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">상품등록</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		
	</div>

</body>
</html>

