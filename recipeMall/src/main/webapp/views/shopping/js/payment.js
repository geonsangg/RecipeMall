$(function() {

	// 계좌이체 버튼 클릭 시 처리
	$("#accountTransfer").click(function() {
		$("#paymentButton").removeClass('kakaoPayGoGo').addClass("gogobutton");
	});

	// 카카오페이 버튼 클릭 시 처리
	$("#kakaoPayg").click(function() {
		$("#paymentButton").removeClass('gogobutton').addClass("kakaoPayGoGo");
	});


	$("#original_address2").on("input", function() {
		var newValue = $(this).val();
		$("input[name='userAddr2']").val(newValue);
	});

	$(document).on('click', '.gogobutton', function() {
		if ($('.input_check:not(:checked)').length !== 0) {
			alert('모든 동의 항목에 체크해주세요.');
		} else if ($('#original_address2').val() === '') {
			alert('상세 주소를 입력해주세요.');
		} else if ($('.transfer_name').val() === '') {
			alert('입금자를 입력해주세요.');
		} else {
			$('#paymentForm').submit();
		}
	});




	$('#deilvery_message').change(function() {
		let selectedValue = $(this).val();

		if (selectedValue === '6') {
			var inputHtml = '<input type="text" name="custom_delivery_message" id="custom_delivery_message" placeholder="직접 입력한 내용">';
			$('#custom_delivery_message_container').html(inputHtml);
		} else {
			$('#custom_delivery_message_container').empty();
		}
	});

	$('#deilvery_message').change(function() {
		let selectedValue = $(this).val();
		let deliveryMsgInput = $('#deliveryMSG');

		if (selectedValue === '6') {
			var customDeliveryMessage = $('#custom_delivery_message').val();
			deliveryMsgInput.val(customDeliveryMessage);
			alert(deliveryMsgInput.val());
		} else {
			deliveryMsgInput.val(selectedValue);
			alert(deliveryMsgInput.val());
		}
	});


	$('#custom_delivery_message').keyup(function() {
		var customDeliveryMessage = $(this).val();
		$('#deliveryMSG').val(customDeliveryMessage);
	});

	$('.show_agree').click(function() {
		$(this).next().toggle();
	});

	let index = 0;
	$('.payment_type ul li').click(function() {
		index = $(this).index();
		$('.payment_type ul li button').css('background', '#fff').css('color', '#000');
		$('.payment_type ul li button').eq(index).css('background', 'rgba(204, 14, 14, 0.79)').css('color', '#fff');
	});


	$("#all_agree").click(function() {
		let checked = $(this).is(':checked');
		if (checked) {
			$('.agree_check').find('input').prop('checked', true);
		} else {
			$('.agree_check').find('input').prop('checked', false);
		}
	});

	$(".input_check").click(function() {
		let chk_group = $(".input_check");
		let chk_group_cnt = chk_group.length;
		let checked_cnt = $('.input_check input:checked').length;

		if (checked_cnt <= chk_group_cnt) {
			$('#all_agree').prop('checked', false);
		} else if (checked_cnt == chkGroup_cnt) {
			$('#all_agree').prop('checked', true);
		}
	});

	let payment_index = 0;
	$('.payment_type li').click(function() {
		payment_index = $(this).index();
		$('.payment_method').css('display', 'none');
		if (payment_index == 0) {
			$('.card_payment').css('display', 'block');
		} else if (payment_index == 1) {
			$('.transfer_number').css('display', 'block');
		}
	});
});

