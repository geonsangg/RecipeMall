$(function () {
   // 글쓰기 숨김메뉴 
   let btn_write = $(".btn_write");
   btn_write.click(function (e) {
      e.stopPropagation(); // 이벤트 전파 중지
      $('.btn_write_sub').toggle(100);
   });
   
   $(document).click(function(e){
       if(!btn_write.is(e.target) && btn_write.has(e.target).length === 0){
           if(!$(e.target).closest('.btn_write_sub').length){
               $('.btn_write_sub').hide(100);
           }
       }
   });


});

function close_div() {
    const banner = document.querySelector(".adbanner");
    banner.style.display = "none";
}

//실시간 차트
$(function () {
    var rank = function () {
        setTimeout(function () {
            $('#rank li:first').animate({ marginTop: '-20px' }, 400, function () {
                $(this).detach().appendTo('ul#rank').removeAttr('style');
            });
            rank();
        }, 3000);
    };
    rank();
});
