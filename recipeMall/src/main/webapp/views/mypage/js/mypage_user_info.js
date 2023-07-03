// 프로필 이미지 변경
    // 파일 업로드
    addFile();
    // 파일 삭제
    delFile();
    removeImage();

    function removeImage() {
    $('.profile_img').on('click', '.remove_img_btn', function() {
        let $targetDiv = $(this).closest('.profile_img'); 
        $targetDiv.find('.preview_img').remove();
        $(this).remove();
        let re_photo;

        if ($targetDiv.hasClass('profile_img')) {
            re_photo = $('<label for="profile_img">' +
                '<div class="file_upload_btn">' +
                '<i class="fa-solid fa-image"></i>' +
                '</div>' +
                '</label>' +
                '<input type="file" data-ax-path="file" name="profile_img" id="profile_img" multiple="multiple">'
            );
        }
        $targetDiv.empty();
        $targetDiv.append(re_photo);
        addFile();
    });
    }

    function addFile() {
        $('.profile_img').find('input[type="file"]').change(function(e) {
        let $targetDiv = $(this).parent('.profile_img');
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
    $('.profile_img').mouseenter(function() {
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
    file_reload();
    function file_reload() {
    $('.profile_img').on('click', '.preview_img', function() { 
        let $targetDiv = $(this).parent('.profile_img'); 
        $targetDiv.find('input[type="file"]').remove();
        $targetDiv.append('<input type="file" data-ax-path="file" name="profile_img" id="profile_img" multiple="multiple">');
        addFile();
        $("#profile_img").click();
        
    });
    }