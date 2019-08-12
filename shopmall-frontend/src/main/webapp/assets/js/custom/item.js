$.ajax({
						type : 'POST',
						url : 'localhost:8888/shopmall/api/cart/find',
						headers: {  "Access-Control-Allow-Origin:": "*"},
						dataType : 'json',
						data :{
							prd_no : '${product.no}',
							opt_value : result
						},
						beforeSend : function(xhr)
	                    {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
	                          xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
	                    },
						success: function(data){
							console.log(data);
							//prd_inven_no = parseInt(//data)
						},
						error : function(error){
							console.log(error);
						}
					}); 

//장바구니 추가
/* $('#asdf').on('click',function(){
	var value = $('#input-option-value').val();
	
	if(value==''){
		alert('옵션을 선택하세요');
	}else{
		$.ajax({
			type : 'POST',
			url : 'localhost:8888/shopmall/api/cart',
			dataType : 'json',
			data :{
				member_code:$('#input-member-no').val(),
				prd_no :prd_inven_no,
				count : $('#inputCount').val()
			},
			success: function(data){
				console.log(data);
			},
			error : function(error){
				console.log(error);
			}
		});
	}
}); */