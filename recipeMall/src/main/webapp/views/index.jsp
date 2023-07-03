<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>    
<c:set var="reviewList" value="${indexMap.reviewList}"/>
<c:set var="freeBoardList" value="${indexMap.freeBoardList}"/>
<c:set var="recipeList" value="${indexMap.recipeList}"/>
<%
   request.setCharacterEncoding("utf-8");
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
    <!-- bx슬라이더 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
    <link rel="stylesheet" href="${contextPath}/views/css/common.css">
    <link rel="stylesheet" href="${contextPath}/views/css/index.css">
    <link rel="shortcut icon" href="${contextPath}/images/smalllogo.png" />
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




    <!-- [s]main_content 영역 -->
    <div id="main_content">
        <!-- [s]배너이미지+이미지슬라이드 영역 -->
        <section class="main_imageslider flex_area_rsb">
            <div class="fix_banner">
                <a href="${contextPath}/recipe/specific.do?recipeNo=31">
                    <img class="banner_img" src="${contextPath}/views/images/fix_banner_img.jpg" alt="레시피이미지">
                    <div class="b_txt_area">
                        <div class="b_txt">감자 한박스 뽀개기 가능한 감자요리-☆</div>
                        <div class="id_img"></div>
                        <span class="id">소은맘</span>
                    </div>
                </a>
            </div>
            <div class="img_slide_area">
                <div class="img_slide">
                    <img class="slide_img" src="${contextPath}/views/images/banner_slide1.PNG" alt="슬라이더1">
                    <img class="slide_img" src="${contextPath}/views/images/banner_slide2.PNG" alt="슬라이더2">
                    <img class="slide_img" src="${contextPath}/views/images/banner_slide3.PNG" alt="슬라이더3">
                    <img class="slide_img" src="${contextPath}/views/images/banner_slide4.PNG" alt="슬라이더4">
                    <img class="slide_img" src="${contextPath}/views/images/banner_slide5.PNG" alt="슬라이더5">
                </div>
                <div class="slide_num">1 / 5</div>
            </div>
        </section> 
        <!-- [e]배너이미지+이미지슬라이드 영역 -->
       <!-- [s]레시피 바로가기 영역-->
        <section class="direct_recipe">
            <div class="direct_menu_area flex_area_rsb">
                <div class="direct_menu" onclick="location.href='${contextPath}/recipe/recipe.do?cate=ko'">
                    <img src="${contextPath}/views/images/disah_ko.png" alt="한식">
                    <p>한식</p>
                </div>
                <div class="direct_menu" onclick="location.href='${contextPath}/recipe/recipe.do?cate=ch'">
                    <img src="${contextPath}/views/images/dish_ch.png" alt="중식">
                    <p>중식</p>
                </div>
                <div class="direct_menu" onclick="location.href='${contextPath}/recipe/recipe.do?cate=us'">
                    <img src="${contextPath}/views/images/dish_us.png" alt="양식">
                    <p>양식</p>
                </div>
                <div class="direct_menu" onclick="location.href='${contextPath}/recipe/recipe.do?cate=ja'">
                    <img src="${contextPath}/views/images/dish_ja.png" alt="일식">
                    <p>일식</p>
                </div>
                <div class="direct_menu" onclick="location.href='${contextPath}/recipe/recipe.do?cate=bun'">
                    <img src="${contextPath}/views/images/dish_bun.png" alt="분식">
                    <p>분식</p>
                </div>
                <div class="direct_menu" onclick="location.href='${contextPath}/recipe/recipe.do?cate=ba'">
                    <img src="${contextPath}/views/images/dish_ba.png" alt="베이커리">
                    <p>베이커리</p>
                </div>
            </div>
        </section>
        <!-- [e]레시피 바로가기 영역 -->
        <!-- [s]오늘의 레시피  영역 -->
        <section class="today_recipe ">
            <h1 class="s_title">

                <span class="css_g_color">
                    <i class="fa-solid fa-quote-left"></i>
                    오늘의</span> 레시피

            </h1>
            <p class="btn_more"><a href="${contextPath}/recipe/recipe.do">더보기</a></p>
            <div class="recipe_content_area flex_area_rsb">
                <c:forEach var="list" begin="0" end="2" items="${recipeList}">
                	<a href="${contextPath}/recipe/specific.do?recipeNo=${list.recipeNo}">
		                   <div class="recipe_content">
		                       <div class="r_img_area">
		                           <img src="${contextPath}/download.do?imgName=${list.imgVO.imgName}&recipeNo=${list.recipeNo}" width="100%" height="100%">
		                       </div>
		                       <p class="r_title">
		                           <span class="r_title_c">오늘의 레시피</span>
		                           ${list.recipeTitle}
		                       </p>
		                   </div>
                	</a>
                </c:forEach>
            </div>
        </section>
        <!-- [e]오늘의 레시피 영역 -->
        <!-- [s]자유게시판+광고 영역 -->
        <section class="board_advertise flex_area_rsb">
            <div class="board">
                <h1 class="s_title">
                    자유 <span class="css_g_color">게시판
                        <i class="fa-solid fa-clipboard"></i>
                </h1>
                <!-- cf : 예외 클래스 -->
                <p class="btn_more cf"><a href="${contextPath}/free/free_board.do">더보기</a></p>
                </span>
                <ul class="board_content_area">
                    <c:forEach var="list" items="${freeBoardList}" begin="0" end="4">
                    <li class="board_content">
                    	<a href="${contextPath}/free/view.do?boardNo=${list.boardNo}">
                           <span class="board_name">${list.boardName}</span>
                           <span class="write_date">${list.writeDate}</span>
                         </a>
                     </li>
                    </c:forEach>
                </ul>

            </div>
            <div class="advertise">
                <img src="${contextPath}/views/images/광고.jpg" alt="" width="100%" height="100%">
            </div>
        </section>
        <!-- [e]자유게시판+광고 영역 -->
        
        <!-- [s]베스트 상품 영역 -->
        <section class="best_product">
            <p class="btn_more"><a href="${contextPath}/shopping/shopping_best.do">더보기</a></p>
            <h1 class="s_title"><span class="css_g_color">베스트</span> 상품</h1>
            <div class="best_product_content_area cate1  flex_area_rsb ">
                <c:forEach var="list" items="${reviewList}" begin="1" end="4" >
                   <a href="${contextPath}/shopping/product_info.do?prodCode=${list.prodCode}">
	                   <div class="best_product_content">
	                       <div class="best_product_content_img">
	                           <img src="${contextPath}/views/shopping/productImages/${list.prodImage}" alt="" width="100%" height="100%">
	                       </div>
	                       <div class="best_product_content_txt">
	                           <h2>${list.prodName}</h2>
	                           <h3>${list.prodPrice}</h3>
	                           <p>★ ${list.avgStar} 리뷰 ${list.reviewCnt}</p>
	                       </div>
	                   </div>
                   </a>
                </c:forEach>
            </div>
            <div class="best_product_content_area cate2  flex_area_rsb ">
                <c:forEach var="list" items="${reviewList}" begin="5" end="8" >
                 <a href="${contextPath}/shopping/product_info.do?prodCode=${list.prodCode}">
                   <div class="best_product_content">
                       <div class="best_product_content_img">
                           <img src="${contextPath}/views/shopping/productImages/${list.prodImage}" alt="" width="100%" height="100%">
                       </div>
                       <div class="best_product_content_txt">
                           <h2>${list.prodName}</h2>
                           <h3>${list.prodPrice}</h3>
                           <p>★ ${list.avgStar} 리뷰 ${list.reviewCnt}</p>
                       </div>
                   </div>
                  </a>
                </c:forEach>
            </div>
        </section>
        <!-- [e]베스트 상품 영역 -->

    </div>
    <!-- [e]main_content 영역 -->
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