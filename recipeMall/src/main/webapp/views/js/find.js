$(function() {
	
	$(document).on('click', '#gogoUpdate', function() {
		if ($('#pw1').val().length < 4) {
			alert('비밀번호를 4자리 이상 입력해주세요.');
		} else if ($('#pw1').val() !== $('#pw2').val()) {
			alert('비밀번호가 일치하지 않습니다.');
		} else {
			$('#updatePass').submit();
		}
	});
});