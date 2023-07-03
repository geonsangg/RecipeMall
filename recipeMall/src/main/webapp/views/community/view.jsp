<%@page import="recipeMall.board.FreeBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="boardVO" value="${boardMap.boardVO}"/>
<c:set var="reply" value="${boardMap.replyList}"/>

<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>recipemall</title>
    <script src="https://kit.fontawesome.com/54880b83c5.js" crossorigin="anonymous"></script>
    <script src="${contextPath}/views/js/jquery-3.6.4.min.js"></script>
 	<script src="${contextPath}/views/js/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="${contextPath}/views/css/common.css">
    <link rel="stylesheet" href="${contextPath}/viewsl/css/index.css">
    <link rel="stylesheet" href="${contextPath}/views/community/css/view.css">
    <link rel="shortcut icon" href="../images/smalllogo.png" />
    
     <script>
    $('.view_article').on('keyup', function (e) {
        $(this).css('height', 'auto');
        $(this).height(this.scrollHeight - 15);
    });
    </script>
</head>
<body>
	<!--[s]전체 컨텐츠 영역  -->
    <!--[s]건너뛰기 링크-->
    <a id="skipNav" href="">본문 바로가기</a>
    <!--[e]건너뛰기 링크-->


    <!-- [s]광고배너 영역 -->
    <div class="adbanner">
        <p><a href="#">지금 가입하면 무료배송!<button class="banner_btn" onclick="close_div()"><i
                        class="fa-solid fa-x"></i></button></a></p>
    </div>
    <!--[e]광고배너 영역 -->


 	<!-- [s]Header 영역 -->
	<jsp:include page="/views/fix/indexHeader.jsp" />
	<!-- [e]Header 영역 -->

    <div class="free_board_area">
        <!--[s] 자유게시판 타이틀-->
        <div class="title_free_board">
            <h1>자유게시판 <i class="fa-regular fa-pen-to-square"></i></h1>
        </div>
        <div class="title_sub">
            <h4>자유롭게 회원들과 소통합시다</h4>
            <h4>*폭언 욕설은 블랙리스트를 당할 수 있습니다.*</h4>
        </div>
    </div>

    <div class="view_area">
        <div class="title_top">
            <h1>${boardVO.boardName}</h1>
        </div>
        <div class="more_information">
            <div class="more_area">
                <div class="writer_s">${boardVO.id}</div>
                <div class="date">
                    <p>${boardVO.writeDate}</p>
                </div>
            </div>
        </div>
        <c:if test="${not empty boardVO.imgName}">
				<tr>
					<td><input type="hidden" name="originalFileName" value="${boardVO.imgName}">
						<img id="preview" src="${contextPath}/downloadboa.do?imgName=${boardVO.imgName}&boardNo=${boardVO.boardNo}" width="400">						
					</td>
				</tr>
				<tr>
					<td><!-- <input type="file" id="id_imgFile" name="imageFileName" onchange="readImage(this)"  disabled > --></td>
				</tr>
			</c:if>
        <div class="view_area_see">
            <div class="view_article">
            	<p class="boardsun">${boardVO.boardContent}</p>	
            </div>
        </div>
        <div class="Several_area">
           <!--  <div class="Several_good">좋아요<span>9</span></div> -->
            <div class="Several_comment">댓글<span>2</span><span class="view_cnt">${boardVO.cntView} 조회수</span></div>
            <c:if test="${log_id == boardVO.id}">
            	<a class="remove"  href="${contextPath}/free/remove.do?boardNo=${boardVO.boardNo}">삭제하기</a>
            </c:if>
        </div>

        <div class="comment_area">
            <div class="comment_write_area">
                <div class="comment_title_area">
                    <div class="comment_title">
                        <h3>댓글</h3>
                    </div>
                    <div class="btn_area">
                    	<c:if test="${log_id == boardVO.id}">
	                        <button class="recomment1" type="button">
	                            <a href="${contextPath}/free/update.do?boardNo=${boardVO.boardNo}">수정하기</a>
	                        </button>                    	
                    	</c:if>
                        <button class="recomment2" type="button">
                            <a href="${contextPath}/free/free_board.do">목록보기</a>
                        </button>
                    </div>
                </div>
                <div>
                <form class="comment_write" action="${contextPath}/free/reply.do" method="post">
                    <i class="fa-solid fa-user" ></i>${log_id}<input type="text" name="content" class="input_reply" placeholder="칭찬과 격려의 댓글은 작성자에게 큰 힘이 됩니다 :D">
                    <input type="hidden" name="boardNo" value="${boardVO.boardNo}">
                    <input type="hidden" name="id" value="${log_id}">
                    <input type="submit" value="추가" class="reply_submit"><!--text 입력시 disabled해제-->
                </form>
                </div>
            </div>
            <c:forEach var="reply" items="${reply}">
	            <h3 class="comment_id"><span class="reply_span">${reply.id}님</span></h3>
    	        <h3 class="comment_reply">${reply.content}</h3>
            </c:forEach>
        </div>
    </div>

    <!-- [s]footer 영역 -->
	<jsp:include page="/views/fix/footer.jsp" />
	<!-- [e]footer 영역 -->
	
    <!--로그인 영역-->
    <jsp:include page="/views/fix/login.jsp" />
    <!--로그인 영역-->
    <script src="${contextPath}/views/js/login.js"></script>
    <script src="${contextPath}/views/js/naver_login.js"></script>
    <script src="${contextPath}/views/js/kakao_login.js"></script>
    <link rel="stylesheet" href="${contextPath}/views/css/login.css">
    <script src="${contextPath}/views/js/common.js"></script>
    <script src="${contextPath}/views/js/index.js"></script>
    
</body>
</html>