$(function(){
    $('.product_count_minus').on('click',function(){
        minus();
        $('.option2').html($('.count').val())
    });
    $('.product_count_plus').on('click',function(){
        plus();
        $('.option2').html($('.count').val())
    });
    $('.product_date_select').change(function(){
        option1();
    })
    $('.regular_order').change(function (){
        option3();
    });
    let p_info=$('.product_info');
    $('.product_info_title>ul>li').on('click',function(){
	let index=$(this).index();
	$(p_info).css('display','none');
	$('.product_info').eq(index).css('display','block');
	});
});
function minus(){
    let num=parseInt($('.product_count input').val());
    if(num>1){
        $('.product_count input').val(num-1);
    }
}
function plus(){
    let num=parseInt($('.product_count input').val());
    $('.product_count input').val(num+1);
}
function option1(){
    $('.option1').html($('.product_date_select').val());
}
