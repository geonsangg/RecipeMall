$(document).ready(function() {
    $('.btn_join').click(function(){
        if($('input[name=check1]').prop('checked')){
            alert("회원가입 성공");
        }else{
            alert("개인정보동의에 체크해주세요");
        }
    });
    $('#all_check').change(function() {
        if ($(this).is(':checked')) {
          $('input[name=check1], input[name=check2]').prop('checked', true);
        } else {
          $('input[name=check1], input[name=check2]').prop('checked', false);
        }
      });
    
      $('input[name=check1], input[name=check2]').change(function() {
        if ($('input[name=check1]').is(':checked') && $('input[name=check2]').is(':checked')) {
          $('#all_check').prop('checked', true);
        } else {
          $('#all_check').prop('checked', false);
        }
      });
});
