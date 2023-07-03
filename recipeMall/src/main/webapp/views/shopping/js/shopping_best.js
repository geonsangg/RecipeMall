$(function () {
    let best_index=0;
    $('.best_button > button').click(function (event) {
        best_index = $(this).index();
        $('.btn_best').hide();
        $('.btn_best').eq(best_index).css('display', 'flex');
        $('.best_button > button').css('background-color', '#fff').css('color', '#000').css('border','1px solid #ccc');
        $(this).css('background-color', 'rgba(204, 14, 14, 0.79)').css('color', '#fff').css('border','none');
    });
});