$(function () {
    let index=0;
    $('.main_category li:not(:eq(5))').click(function (event) {
        index = $(this).index();
        $('.main_category li').css('color','#000');
        $('.main_category li').eq(index).css('color', 'rgba(204, 14, 14, 0.79)');
        if(index == 6 || index == 7) {
            $('.main_category li:eq(5)').css('color', 'rgba(204, 14, 14, 0.79)');
        }else {
            $('.main_category li:eq(5)').css('color','#000');
        }
    });
    
    $('.form_matarial > .btn_search').click(function() {
        $(this).closest('form').submit();
   });
});
