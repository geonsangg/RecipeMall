$(function () {
    $('.inquering').click(function() {
		$(location).attr('href','mypage_main_6_2_in.html');
	});
    
    // 일대일 문의 취소할 경우 목록으로 돌아가기
    $('.cancel').click(function () {
        location.href='${contextPath}/service/inqList.do?id=${log_id}';
    });

    // 자주하는 질문 accordion
    $('.accordion_title').click(function () {

        function slideDown(target) {
          slideUp();
          $(target).addClass('on').next().slideDown();
          $(target).find('.fa').find('i').removeClass('fa-chevron-up');
          $(target).find('.fa').find('i').addClass('fa-chevron-down');
        }
      
        function slideUp() {
          $('.accordion_title').removeClass('on').next().slideUp();
          $('.fa').find('i').removeClass('fa-chevron-down');
          $('.fa').find('i').addClass('fa-chevron-up');
        }
      
        $(this).hasClass('on') ? slideUp() : slideDown(this);
        console.log(event.target.className);
    });
    
    /* ------------------------- mypage post/comment ---------------------------- */
    // 체크박스
    let post_chk;
    let comm_chk;

    function chk_all(chk) {
        if(chk) {
            $('.chk').prop('checked',true); 
        } else {    
            $('.chk').prop('checked',false); 
        }
    }
    // post check
    $('#post_chk_all').click(function () {
        post_chk=$(this).is(':checked');
        chk_all(post_chk);
    });
    
    // comment check
    $('#comment_chk_all').click(function () {
        comm_chk=$(this).is(':checked');
        chk_all(comm_chk);
    });
    
    $('.chk').click(function () {
        $('#post_chk_all').prop('checked',false);
        $('#comment_chk_all').prop('checked',false);
    });

    /* ------------- 글/댓글 게시판 메뉴 분류, css 유지 ---------- */
    let item=$('.menu_li');

    item.on('click',function () {
        $(this).addClass('active');
        item.not($(this)).removeClass('active');
    });

    function menu(board, recipe, free) {
        if(board == '레시피') {
            recipe.removeClass('status');
            free.addClass('status');
        } else if(board == '자유게시판') {
            free.removeClass('status');
            recipe.addClass('status');
        } else {
            recipe.removeClass('status');
            free.removeClass('status');
        }
    }

    $('ul.post_menu li').click(function () {
        let board=$(this).text();
        let recipe=$('table.post_list_tb td:contains("레시피")').parent();
        let free=$('table.post_list_tb td:contains("자유게시판")').parent();
        
        menu(board, recipe, free);
    });

    $('ul.comment_menu li').click(function () {
        let board=$(this).text();
        let recipe=$('table.comment_list_tb td:contains("레시피")').parent();
        let free=$('table.comment_list_tb td:contains("자유게시판")').parent();
        
        menu(board, recipe, free);
    });

    /* ------------------ 문의 목록 분류 -------------------------- */
    $('ul.inq_menu li').click(function () {
        let inq=$(this).text();
        let comm_tr=$('table.inq_list_tb td:contains("커뮤니티")').parent();
        let shop_tr=$('table.inq_list_tb td:contains("쇼핑")').parent();
        console.log(inq);
        if(inq == '커뮤니티') {
          shop_tr.addClass('status');
          comm_tr.removeClass('status');
        } else if(inq == '쇼핑') {
          shop_tr.removeClass('status');
          comm_tr.addClass('status');
        } else {
          comm_tr.removeClass('status');
          shop_tr.removeClass('status');
        }
      });

    /* ------------- 1:1문의 파일첨부 시 파일 이름 출력 ---------- */
    const handler = {
        init() {
            const fileInput = document.querySelector('#inq_file');
            const preview = document.querySelector('.file_list');
            fileInput.addEventListener('change', () => {  
                //console.dir(fileInput)                  
                const files = Array.from(fileInput.files)
                files.forEach(file => {
                    preview.innerHTML += `
                        <p id="${file.lastModified}">
                            ${file.name}
                            <button data-index='${file.lastModified}' class='file_remove'>X</button>
                        </p>`;
                });
            });
        },
    
        removeFile: () => {
            document.addEventListener('click', (e) => {
                if(e.target.className !== 'file_remove') return;
                const removeTargetId = e.target.dataset.index;
                const removeTarget = document.getElementById(removeTargetId);
                const files = document.querySelector('#inq_file').files;
                const dataTranster = new DataTransfer();
    
    // document.querySelector('#file-input').files =
    // Array.from(files).filter(file => file.lastModified !== removeTarget);
    
                Array.from(files)
                    .filter(file => file.lastModified != removeTargetId)
                    .forEach(file => {
                    dataTranster.items.add(file);
                });
    
                document.querySelector('#inq_file').files = dataTranster.files;
    
                removeTarget.remove();
            });
        }
    }
    
    handler.init();
    handler.removeFile();
});