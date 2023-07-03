$(function () {
    // 실행하면 상위메뉴만 보이게
	$('.sub').hide();
	// 클릭하면 하위메뉴 펼쳐지고 다른 하위메뉴는 접혀지는 효과
	$('.lnb').click(function () {
		$('.depth2').slideUp('fast');
		$(this).next().slideDown('fast');
		$('.lnb').removeClass('on');
		$(this).addClass('on');
	});
});