function close_div() {

    const banner = document.querySelector(".adbanner");
    banner.style.display = "none";

}

$(".top").click(function () {
    $("html, body").animate({ scrollTop: 0 }, "slow");
    return false;
});

//이미지슬라이드
let slideNumElement = $('.slide_num');
let totalSlides;

$('.img_slide').bxSlider({
    auto: true,
    speed: 500,
    autoHover: true,
    controls: true,
    pager: false,
    onSliderLoad: function () {
        totalSlides = $('.img_slide').children('.slide_img').not('.bx-clone').length;
    },
    onSlideAfter: function ($slideElement, oldIndex, newIndex) {
        let currentIndex = newIndex + 1;
        slideNumElement.text(currentIndex + ' / ' + totalSlides);
    }
});

$('.bx-wrapper').hover(function () {
    $('.bx-prev, .bx-next').css('display', 'block');
}, function () {
    $('.bx-prev, .bx-next').css('display', 'none');
})





// 베스트상품 카테고리 이벤트
let bestButton = $('.btn_product_category_area button');
let index;
let bestcontent_name = ['.cate1', '.cate2', '.cate3', '.cate4']
let oldindex = -1;
let old;
let sw;
$(bestButton).click(function () {
    index = $(this).index();
    old = index;
    if (index != oldindex) {
        $(bestButton).removeClass('btn_clk');
        $(bestButton[index]).addClass('btn_clk');
        // $('.fun_contentArea').slideUp(1000);
        // $(funcontent_name[index-1]).slideDown(1000);
        // if (index==1|| index==3) {
        //     $(fairy).animate({
        //     top : fairymove_top[index-1],
        //     left : -10
        //     },500,'linear').removeClass('fairychoice_transform')
        // }else{
        //     $(fairy).animate({
        //     top : fairymove_top[index-1],
        //     left : 113,
        //     },500,'linear').addClass('fairychoice_transform')
        // }
        oldindex = index;
    }
});
$(bestButton).hover(function () {
    index = $(this).index();
    $(bestButton[index]).addClass('btn_clk');
}, function () {
    index = $(this).index();
    $(bestButton[index]).removeClass('btn_clk');
    $(bestButton[old]).addClass('btn_clk');
});

