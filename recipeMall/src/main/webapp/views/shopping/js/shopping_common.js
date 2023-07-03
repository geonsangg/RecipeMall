$(function(){
    $('.side_cart').on('click',function(){
        slide_cart();
    });
    $('.side_seen').on('click',function(){
        slide_seen();
    });
});


function slide_cart(){
    let hidden_cart=$('.hidden_cart');
    let hidden_seen=$('.hidden_seen');
    hidden_seen.fadeOut();
    if(hidden_cart.is(':hidden')){
        hidden_cart.fadeIn();
        
    }else{
        hidden_cart.fadeOut();
    }
   
};
function slide_seen(){
    let hidden_seen=$('.hidden_seen');
    let hidden_cart=$('.hidden_cart');
    hidden_cart.fadeOut();
    if(hidden_seen.is(':hidden')){
        hidden_seen.fadeIn();
        
    }else{
        hidden_seen.fadeOut();
    }
   
};
function close_div() {
    const banner = document.querySelector(".adbanner");
    banner.style.display = "none";
}

// 글쓰기 숨김메뉴 
let btn_write = $(".btn_write");
btn_write.click(function (e) {
   e.stopPropagation(); // 이벤트 전파 중지
   $('.btn_write_sub').toggle(100);
   $('.bx-wrapper').css('z-index',1);
});
$(document).click(function(e){
    if(!btn_write.is(e.target) && btn_write.has(e.target).length === 0){
        if(!$(e.target).closest('.btn_write_sub').length){
            $('.btn_write_sub').hide(100);
        }
    }
});
