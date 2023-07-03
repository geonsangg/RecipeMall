 // 재료영역 삭제기능
 let newRing_cat = $('.ing_category');
 let newRing_con = $('.r_ing_content').clone();
 
 let newRIng = $('<div class="r_ing flex_area_rsb">' +
  '<div class="r_txt"></div>' +
  '<div class="ing_category">' +
   newRing_cat.html() +
   '</div>'+
   newRing_con.html() +
   '</div>');
 
 btn_del(); // 삭제 기능 적용
 
 function btn_del(){
     $('.ing_input_area').on('mouseover', function() {
         $(this).find('.btn_del').css('display', 'inline-block');
     }).on('mouseleave', function() {
            $(this).find('.btn_del').css('display', 'none');
            
     });
 
     $('.btn_del').click(function() {
         $(this).parents('.ing_input_area').remove();
         $(this).remove();
     });
 }
 
 //재료영역 추가기능
 $('.btn_add_s').click(function() {
     let newInputArea = $('<div class="ing_input_area">' +
         '<input type="text" name="mate_name" placeholder="예)재료" required>' +
         '<input type="text" name="mate_amount" placeholder="예)양" required>' +
         '<div class="btn_del">'+
         '<button >' +
         '<i class="fa-solid fa-circle-xmark"></i>' +
         '</button>' +
         '</div>'+
         '</div>');
         newInputArea.insertBefore($(this));
     btn_del();
 });
 
 //재료영역 묶음추가
 $('.r_ing_container .btn_add').on('click',function() {
     let newRIng2=newRIng.clone();
    newRIng2.find('.btn_add_s').click(function() {
         let newInputArea = $('<p class="ing_input_area">' +
             '<input type="text" name="mate_name" placeholder="예)재료" required>' +
             '<input type="text"  name="mate_amount" placeholder="예)양" required>' +
             '<button class="btn_del">' +
             '<i class="fa-solid fa-circle-xmark"></i>' +
             '</button>' +
             '</p>');
         newInputArea.insertBefore($(this));
         btn_del();
     });
 
     $('.r_ing_ex_area').before(newRIng2);
     btn_del();
     ing_del_all();
 });
 
// 재료영역 묶음삭제
ing_del_all();
function ing_del_all() {
  $('.r_ing').on('click', '.btn_ing_del_all', function() {
    let $rIng = $(this).closest('.r_ing');
    if ($rIng.siblings('.r_ing').length > 0) {
      $rIng.remove();
      ing_r_txt_reload();
    }
  });  
}

function ing_r_txt_reload() {
  $('.r_ing_container').first().find('.r_ing:first .r_txt').html('재료등록');
}

 //순서 영역 추가
 let stepNumber;
$('.r_seq .btn_add').click(function() {
    stepNumber = $('.seq_content_area').length + 1;
    let newStep = '<div class="seq_content_area flex_area_rsb">' +
    '<div class="r_txt"></div>' +
    '<h2>Step <span class="stepNum">'+stepNumber+'</span> </h2>'+
    '<textarea name="step_ex" id="step_ex" cols="30" rows="10" placeholder="예)소고기는 기름기를 떼어내고 적당한크기로 썰어주세요." required></textarea>' +
    '<div class="seq_step_pho seq_step_pho'+ stepNumber +'">'+
    '<label for="rec_file_step'+stepNumber +'">'+
    '<div class="file_upload_btn">'+
    '<i class="fa-solid fa-plus"></i>'+
    '</div>'+
    '</label>'+
    '<input type="file" data-ax-path="file" name="rec_file_step'+stepNumber +'" id="rec_file_step'+stepNumber+'" multiple="multiple" >'+
    '</div>'+
    ' <div class="btn_del_seq">'+
    '<button type="button">'+
    '<i class="fa-solid fa-circle-xmark"></i>'+
    '</button>'+
    '</div>'+
    '</div>';
    $('.btn_add_area').before(newStep);

    // 파일 업로드
    addFile();
    // 파일 삭제
    delFile();
    removeImage();

    //파일다시업로드
    file_reload();
});

// 순서영역 삭제
function seq_del() {
  $('.r_seq').on('mouseover', '.seq_content_area', function() {
    $(this).find('.btn_del_seq').css('display', 'inline-block');
  }).on('mouseleave', '.seq_content_area', function() {
    $(this).find('.btn_del_seq').css('display', 'none');
  });

  $('.r_seq').on('click', '.btn_del_seq', function() {
    $(this).parents('.seq_content_area').remove();
    $(this).remove();
    step_update();
  });
}

seq_del();

//stepnumber update
function step_update() {
  $('.r_seq .seq_content_area').each(function(index) {
     $(this).find('h2').html('Step <span class="stepNum">' + (index + 1)+'</span>');
  });
}


// 파일 업로드
addFile();
// 파일 삭제
delFile();
removeImage();

function removeImage() {
  $('.r_in_photo, .seq_step_pho').on('click', '.remove_img_btn', function() {
    let $targetDiv = $(this).closest('.r_in_photo, .seq_step_pho '); 
    let stepNum = parseInt($targetDiv.siblings('h2').find('.stepNum').text());
    $targetDiv.find('.preview_img').remove();
    $(this).remove();
    let re_photo;

    if ($targetDiv.hasClass('r_in_photo')) {
      re_photo = $('<label for="rec_file">' +
        '<div class="file_upload_btn">' +
        '요리 대표사진을 등록해주세요 <br>' +
        '<i class="fa-solid fa-image"></i>' +
        '</div>' +
        '</label>' +
        '<input type="file" data-ax-path="file" name="rec_file" id="rec_file" multiple="multiple">'
      );
    } else if ($targetDiv.hasClass('seq_step_pho')) { 
      re_photo = $('<label for="rec_file_step'+stepNum+'">' + 
      '<div class="file_upload_btn">' +
      '<i class="fa-solid fa-plus"></i>' +
      '</div>' +
      '</label>' +
      '<input type="file" data-ax-path="file" name="rec_file_step'+stepNum+'" id="rec_file_step'+stepNum+'" multiple="multiple">'
      );
    } 

    $targetDiv.empty();
    $targetDiv.append(re_photo);
    addFile();
  });
}

function addFile() {
  $('.r_in_photo, .seq_step_pho').each(function() { 
    let $targetDiv = $(this);
    $targetDiv.find('input[type="file"]').change(function(e) {
      let file = e.target.files[0];
      let reader = new FileReader();
      reader.onload = function(e) {
		//input태그까지 지워져서 수정함 -아래 두줄
        $targetDiv.find('label').remove();
        $targetDiv.find('img').remove();
        
        let image = $('<img class="preview_img">').attr('src', e.target.result).attr('alt', '레시피 대표 이미지').attr('onclick', 'file_reload($(this).parent())');
        $targetDiv.append(image);
        delFile();
      };
      reader.readAsDataURL(file);
    });
  });
}

function delFile() {
  $('.r_in_photo, .seq_step_pho').mouseenter(function() {
    if ($(this).find('.preview_img').length > 0) {
      if ($(this).find('.remove_img_btn').length === 0) {
        $(this).append('<div class="remove_img_btn"><i class="fa-solid fa-circle-xmark"></i></div>');
      }
    }
  }).mouseleave(function() {
    $(this).find('.remove_img_btn').remove();
  });
}  

// 파일 다시 업로드

function file_reload($targetDiv) {
    $targetDiv.find('input[type="file"]').remove();
   
    if ($targetDiv.hasClass('r_in_photo')) {
      $targetDiv.append('<input type="file" data-ax-path="file" name="rec_file" id="rec_file" multiple="multiple">');
      $("#rec_file").click();
      $targetDiv.find('input[type="file"]').change(function(e) {
	      let file = e.target.files[0];
	      let reader = new FileReader();
	      reader.onload = function(e) {
			//input태그까지 지워져서 수정함 -아래 두줄
	        $targetDiv.find('img').remove();
	        let image = $('<img class="preview_img">').attr('src', e.target.result).attr('alt', '레시피 대표 이미지').attr('onclick', 'file_reload($(this).parent())');
	        $targetDiv.append(image);
	        delFile();
	      };
	      reader.readAsDataURL(file);
 		 });
      
    } else if ($targetDiv.hasClass('seq_step_pho')) { 
      stepNum = parseInt($targetDiv.siblings('h2').find('.stepNum').text());
      $targetDiv.append('<input type="file" data-ax-path="file" name="rec_file_step'+stepNum+'" id="rec_file_step'+stepNum+'" multiple="multiple">');
	  $("#rec_file_step"+stepNum).click();
     console.log($("#rec_file_step"+stepNum));
     
	    $targetDiv.find('input[type="file"]').change(function(e) {
	      let file = e.target.files[0];
	      let reader = new FileReader();
	      reader.onload = function(e) {
			//input태그까지 지워져서 수정함 -아래 두줄
	        $targetDiv.find('img').remove();
	        let image = $('<img class="preview_img">').attr('src', e.target.result).attr('alt', '레시피 대표 이미지').attr('onclick', 'file_reload($(this).parent())');
	        $targetDiv.append(image);
	        delFile();
	      };
	      reader.readAsDataURL(file);
 		 });
    }
  
}

 // 동영상 썸네일 추출
 $('#in_video_link').on('input', function() {
     let videoUrl = $('#in_video_link').val();
     let videoId = extractVideoId(videoUrl);
     let thumbnailUrl = 'https://img.youtube.com/vi/' + videoId + '/maxresdefault.jpg';
 
     if (videoId) {
         $('.r_in_video').empty().css('background-image', 'url(' + thumbnailUrl + ')');
     } else {
         $('.r_in_video').css('background-image', 'none');
         let re_video = $('<div>').html('동영상 썸네일<br><i class="fa-solid fa-video"></i>');
         $('.r_in_video').empty(); // 기존 내용 삭제
         $('.r_in_video').append(re_video); // 원래 있던 내용 추가
     }
 });
   
   function extractVideoId(url) {
     let regExp = /^.*(youtu.be\/|v\/|u\/\w\/|embed\/|watch\?v=|\&v=|\?v=)([^#\&\?]*).*/;
     let match = url.match(regExp);
   
     if (match && match[2].length === 11) {
       return match[2];
     } else {
       return null;
     }
   }


 
