<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%
	request.setCharacterEncoding("utf-8");
	session.getAttribute("isLogon");	// 로그인 상태값을 갖고 있는 키
	session.getAttribute("log_id");	// 로그인한 유저 아이디를 갖고 있는 키

%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>recipeMall</title>
    
    <script src="https://kit.fontawesome.com/54880b83c5.js" crossorigin="anonymous"></script>
    <script src="${contextPath}/views/js/jquery-3.6.4.min.js"></script>
    <script src="${contextPath}/views/js/jquery-ui.min.js"></script>
    <script src="${contextPath}/views/viewCommunity/js/notice.js"></script>
     <link  rel="stylesheet" href="${contextPath}/views/community/css/notice_write.css"/>
    <link rel="shortcut icon" href="${contextPath}/views/images/dish4.png" />
</head>
<body>
    <header class="top_menu">
        <div class="recipe_mall">
            <h1><a href="${contextPath}/views/index.jsp" class="recipe_a">레시피몰</a></h1>
            <p class="id_in">${log_id}님</p>
        </div>
      
    </header>

    <div class="under_bar"></div>
    <div class="middle_space_area">
        <div class="middle_space">
            <div class="guide">
                <h1 onclick="myFunction()">자유게시판 작성 가이드 <span class="sub_guide">좋은 꿀팁들을 같이 공유 해보아요!</span><i class="fa-solid fa-chevron-down"></i></h1>   
                <div>
                    <ul class="Detail">
                        <li><i class="fa-solid fa-circle"></i>간단한 자기 소개 후 집에 관한 이야기를 들려주세요.</li>
                        <li><i class="fa-solid fa-circle"></i>정보를 많이 입력할수록 상위 노출되어 조회수가 올라갑니다.</li>
                        <li><i class="fa-solid fa-circle"></i>사진과 제목은 에디터에 의해 변경 될 수 있습니다.</li>
                        <li><i class="fa-solid fa-circle"></i>글 작성과 이미지 업로드 시, 타인의 지식 재산권을 침해하지 않도록 유의해주세요</li>
                        <li><i class="fa-solid fa-circle"></i>폭언 욕설은 삼가해 주세요.</li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="writing_space">
            <form id="write_frm_btn" action="${contextPath}/free/addWrite.do" method="post" enctype="multipart/form-data">
            	<input type="hidden" name="id" value="${log_id}">
                <div><input type="text" placeholder="제목을 입력해주세요" class="title_go" name="boardName"></div>
                <div><textarea cols="100" placeholder="내용을 입력해주세요" class="detail_go" name="boardContent"></textarea></div>
                <input type="file" data-ax-path="file" name="boardimg" id="boardimg" multiple="multiple" class="file_file">  
                <input type="submit" value="완료" class="btn1">
                <a class="btn2" onclick="location.href='${contextPath}/free/free_board.do'">목록보기</a>
            </form>
        </div>
    </div>

    <script>
    $('textarea').on('keyup', function (e) {
        $(this).css('height', 'auto');
        $(this).height(this.scrollHeight - 15);
    });
    </script>
</body>
</html>