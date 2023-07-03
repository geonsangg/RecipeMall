$('.guide h1').on('click', function(){
    $('.Detail').slideUp();
    if($('.Detail').is(':hidden')){
        $('.Detail').slideDown();
    }else{
        $('.Detail').slideUp();
    }
});
$('.detail_go').on('input', function() {
    this.style.height = 'auto';
    this.style.height = (this.scrollHeight) + 'px';
  });