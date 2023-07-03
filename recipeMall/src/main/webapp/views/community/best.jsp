<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
   request.setCharacterEncoding("utf-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>  
<!DOCTYPE html>
<html>
<head>
 	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>recipeMall</title>
    <script src="https://kit.fontawesome.com/54880b83c5.js" crossorigin="anonymous"></script>
    <link rel="shortcut icon" href="${contextPath}/views/images/dish4.png" />
    <link rel="stylesheet" href="${contextPath}/views/css/common.css">
    <link rel="stylesheet" href="${contextPath}/views/community/css/best.css">
    
    <!-- 공통 -->
    <script src="${contextPath}/views/js/jquery-3.6.4.min.js"></script>
    <script src="${contextPath}/views/js/jquery-ui.min.js"></script>
    <script src="${contextPath}/views/js/login.js"></script>
    <script src="${contextPath}/views/js/naver_login.js"></script>
    <script src="${contextPath}/views/js/kakao_login.js"></script>
    <link rel="stylesheet" href="${contextPath}/views/css/login.css">
    <script src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>
    <script src="https://t1.kakaocdn.net/kakao_js_sdk/2.1.0/kakao.min.js"
    integrity="sha384-dpu02ieKC6NUeKFoGMOKz6102CLEWi9+5RQjWSV0ikYSFFd8M3Wp2reIcquJOemx" crossorigin="anonymous"></script>
</head>
<body>
	<!--[s]전체 컨텐츠 영역  -->
    <!--[s]건너뛰기 링크-->
    <a id="skipNav" href="">본문 바로가기</a>
    <!--[e]건너뛰기 링크-->
	<!-- [s]Header 영역 -->
	<jsp:include page="/views/fix/indexHeader.jsp" />
	<!-- [e]Header 영역 -->


    <!-- [s]main_content 영역 -->
    <div id="main_content">
		<div class="best_title"> <span> 베스트 </span>레시피 
		<i class="fa-solid fa-medal"></i></div>
        <!-- [s]레시피 리스트 영역 -->
        <section class="r_list">
        
            <ul class="r_content_area flex_area_rsb">
                <!--나머지-->
            	<c:forEach var="best" items="${bestList}" begin="0" end="8">
            		<li class="r_content">
            		<a href="${contextPath}/recipe/specific.do?recipeNo=${best.recipeNo}">
	                    <div class="r_thumb r_thumb1">
	                    <img src="${contextPath}/download.do?imgName=${best.imgVO.imgName}&recipeNo=${best.recipeNo}" alt="베스트 상품">
	                    </div>
	                    <div class="r_title_area">
	                        <p class="r_title">${best.recipeTitle}</p>
	                        <div class="cnt_like_area">
	                            <span class="cnt_like">${best.cntLike}</span>
	                            <i class="fa-regular fa-heart"></i>
	                        </div>
	                        <p class="r_id">${best.id}</p>
	                    </div>
	                </a>
	                </li>
            	</c:forEach>
            </ul>
           
        </section>

        <!-- [e]레시피 리스트 영역 -->



    </div>
    <!-- [e]main_content 영역 -->
    <!-- [s]footer 영역 -->
	<jsp:include page="/views/fix/footer.jsp" />
	<!-- [e]footer 영역 -->
	<!--로그인 영역-->
	<jsp:include page="/views/fix/login.jsp"/>
	<!--로그인 영역-->
    <script src="../js/login.js"></script>
    <script src="../js/naver_login.js"></script>
    <script src="../js/kakao_login.js"></script>
    <link rel="stylesheet" href="../css/login.css">
    <script src="${contextPath}/views/js/common.js"></script>
    <script src="${contextPath}/views/community/js/insert.js"></script>
    <script src="js/best.js"></script>
</body>
</html>