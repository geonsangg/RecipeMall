$(function() {
    let loginLink = $(".login");
    let modal = $("#modal");
    let modal_area = $(".loginbox");

      loginLink.on("click", function(event) {
        event.preventDefault();
        modal.css("display", "block");
        $("body").css("overflow", "hidden");
      });

    $(document).on("click", function(event) {
      if (!$(event.target).closest(".loginbox").length && !$(event.target).is(loginLink)) {
        modal.css("display", "none");
        $("body").css("overflow", "auto");
      }
    });
    
});
  
// 로그인
/*function fn_loginCheck() {
	let _id=$('#id').val();
	let _pw=$('#pass').val();
	if(_id == "" || _pw == "") {
			alert('아이디 혹은 비밀번호를 입력해주세요.');
			return;
	} else {
		console.log(_id);
		console.log(_pw);
		
		$.ajax({
			type: "post",
			async: true,
			dataType: "text",
			url: "${contextPath}/loginProc/login",
			data: {id:_id, pw:_pw},
			success: function(data, textStatus) {
				if(data=="true") {
					alert("로그인 성공");
				} else {
					$('.login_check').html('<p>아이디 혹은 비밀번호를 잘못 입력했습니다.</p>').css('color','red');
				}
			},
			error: function(data, textStatus, error) {
				//alert('오류가 발생했습니다 => '+error);
				alert('code:'+data.status+"\n"+"message:"+data.responseText+"\n"+"error:"+error);
			}
		});
	}
}*/