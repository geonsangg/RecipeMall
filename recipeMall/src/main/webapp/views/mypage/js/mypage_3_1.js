$(function(){
    $('.btn_product').on('click', function(){
        let reviewId = $(this).data('review-id');
        slide_review(reviewId);
    });
});

function slide_review(reviewId){
    let reviewForm = $(`#review_form_${reviewId}`);
    reviewForm.slideUp();
    let productOpenButton = $(`.btn_product[data-review-id="${reviewId}"]`);
    if(reviewForm.is(':hidden')){
        reviewForm.slideDown();
        productOpenButton.html('<i class="fa-solid fa-chevron-up"></i>');
    } else {
        reviewForm.slideUp();
        productOpenButton.html('<i class="fa-solid fa-chevron-down"></i>');
    }
}