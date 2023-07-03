$(function () {
	$(document).on('click', '.kakaoPayGoGo', function() { 
		   if ($('.input_check:not(:checked)').length !== 0) {
		     alert('모든 동의 항목에 체크해주세요.');
		   } else if($('#original_address2').val() === '') {
			 alert('상세 주소를 입력해주세요.');
			}else {
				var firstValue = $('input[name="prodName"]:first').val();
			    var count = $('input[name="prodName"]').length - 1;
			    var prodName = firstValue + " 외" +count;
				let allProdPrice = $('input[name="allProdPrice"]').val();
				let addr = $('input[name="userAddr"]').val();
				let addr2 = $('input[name="userAddr2"]').val();
				let name = $('input[name="orderer_name"]').val();
				let phone = $('input[name="orderer_phone"]').val();
				let post = $('#noUse').val();
				
				let IMP = window.IMP;
				IMP.init('imp01346518');
				
				IMP.request_pay({
		            pg : 'kakaopay',
		            pay_method : 'card',
		            merchant_uid : 'merchant_' + new Date().getTime(),
		            name : prodName,
		            amount : allProdPrice,
		            buyer_email : 'test@email.com',
		            buyer_name : name,
		            buyer_tel : phone,
		            buyer_addr : addr + ' ' + addr2,
		            buyer_postcode : post,	      
		        }, function(rsp) {
		            if ( rsp.success ) {
		              $('#paymentForm').submit();	                    
		            } else {
		                msg = '결제에 실패하였습니다.';
		                msg += '에러내용 : ' + rsp.error_msg;
		                alert(msg);
		            }
		        });
        
   		 }
    });
});	
