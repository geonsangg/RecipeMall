$(function(){
    $('.product_open').on('click', function(){
        let reviewId = $(this).data('review-id');
        slide_review(reviewId);
        // 프로필 이미지 변경
            // 파일 업로드
            addFile();
            // 파일 삭제
            delFile();
            removeImage();
            // 파일 다시 업로드
            file_reload();
    });
});

function slide_review(reviewId){
    let reviewForm = $(`#review_form_${reviewId}`);
    reviewForm.slideUp();
    if(reviewForm.is(':hidden')){
        reviewForm.slideDown();
    } else {
        reviewForm.slideUp();
    }
}


    

function removeImage() {
$('.review_img_insert').on('click', '.remove_img_btn', function() {
    let $targetDiv = $(this).closest('.review_img_insert'); 
    $targetDiv.find('.preview_img').remove();
    $(this).remove();
    let re_photo;

    if ($targetDiv.hasClass('review_img_insert')) {
        re_photo = $('<label for="review_img_insert">' +
            '<div class="file_upload_btn">' +
            '<i class="fa-solid fa-image"></i>' +
            '</div>' +
            '</label>' +
            '<input type="file" data-ax-path="file" name="review_img_insert" id="review_img_insert" multiple="multiple">'
        );
    }
    $targetDiv.empty();
    $targetDiv.append(re_photo);
    addFile();
});
}

function addFile() {
    $('.review_img_insert').find('input[type="file"]').change(function(e) {
    let $targetDiv = $(this).parent('.review_img_insert');
    let file = e.target.files[0];
    let reader = new FileReader();
    reader.onload = function(e) {
        $targetDiv.empty();
        let image = $('<img class="preview_img">').attr('src', e.target.result).attr('alt', '프로필 이미지');
        $targetDiv.append(image);
        delFile();
    };
    reader.readAsDataURL(file);
    });
}

function delFile() {
$('.review_img_insert').mouseenter(function() {
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
function file_reload() {
$('.review_img_insert').on('click', '.preview_img', function() { 
    let $targetDiv = $(this).parent('.review_img_insert'); 
    $targetDiv.find('input[type="file"]').remove();
    $targetDiv.append('<input type="file" data-ax-path="file" name="review_img_insert" id="review_img_insert" multiple="multiple">');
    addFile();
    $("#review_img_insert").click();
    
});
}