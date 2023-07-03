<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="reviewList" value="${bestMap.reviewList}"/>
<c:set var="bestList" value="${bestMap.bestList}"/>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>recipeMall</title>
<script src="https://kit.fontawesome.com/54880b83c5.js" crossorigin="anonymous"></script>
    <script src="${contextPath}/views/js/jquery-3.6.4.min.js"></script>
    <script src="${contextPath}/views/js/jquery-ui.min.js"></script>
    <script src="${contextPath}/views/shopping/js/shopping_best.js"></script>
    <script src="${contextPath}/views/js/login.js"></script>
    <script src="${contextPath}/views/js/naver_login.js"></script>
    <script src="${contextPath}/views/js/kakao_login.js"></script>
    <link rel="stylesheet" href="${contextPath}/views/shopping/css/shopping_common.css">
    <link rel="stylesheet" href="${contextPath}/views/shopping/css/shopping_best.css">
    <link rel="stylesheet" href="${contextPath}/views/css/login.css">
    <link rel="shortcut icon" href="${contextPath}/views/images/smalllogo.png" />
    <script src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>
    <script src="https://t1.kakaocdn.net/kakao_js_sdk/2.1.0/kakao.min.js"
  integrity="sha384-dpu02ieKC6NUeKFoGMOKz6102CLEWi9+5RQjWSV0ikYSFFd8M3Wp2reIcquJOemx" crossorigin="anonymous"></script>
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
    <jsp:include page="/views/fix/shopHeader.jsp"/>
    <!-- [e]Header 영역 -->
    <div id="main_content">
        <!-- [s]베스트 영역  -->
        <div class="best_area">
            <!-- [s]베스트 버튼 영역 -->
            <div class="best_button">
                <button>어제의 베스트</button>
                <button>역대 베스트</button>
            </div>
            <!-- [e]베스트 버튼 영역 -->

            <!-- [s]베스트 품목 영역 -->
            <ul class="yesterday_best btn_best">
            	<c:forEach var="reviewList" items="${reviewList}" begin="0" end="15" >
                	<li>
                    	<a href="${contextPath}/shopping/product_info.do?prodCode=${reviewList.prodCode}">
                           <img src="${contextPath}/views/shopping/productImages/${reviewList.prodImage}" alt="이미지">
                       	</a>
                       	<a href="${contextPath}/shopping/product_info.do?prodCode=${reviewList.prodCode}">${reviewList.prodName}</a>
                       	<a href="${contextPath}/shopping/product_info.do?prodCode=${reviewList.prodCode}">${reviewList.prodPrice}</a>
                       	<a href="${contextPath}/shopping/product_info.do?prodCode=${reviewList.prodCode}">★${reviewList.avgStar} 리뷰 ${reviewList.reviewCnt}</a>
                    </li>	
                </c:forEach>               
            </ul>
            <ul class="all_best btn_best">
            	<c:forEach var="bestList" items="${bestList}" begin="0" end="15" >
                	<li>
                    	<a href="${contextPath}/shopping/product_info.do?prodCode=${bestList.prodCode}">
                           <img src="${contextPath}/views/shopping/productImages/${bestList.prodImage}" alt="이미지">
                       	</a>
                       	<a href="${contextPath}/shopping/product_info.do?prodCode=${bestList.prodCode}">${bestList.prodName}</a>
                       	<a href="${contextPath}/shopping/product_info.do?prodCode=${bestList.prodCode}">${bestList.prodPrice}</a>
                       	<a href="${contextPath}/shopping/product_info.do?prodCode=${bestList.prodCode}">★${bestList.avgStar} 리뷰 ${bestList.reviewCnt}</a>
                    </li>	
                </c:forEach>               
            </ul>
            <!-- [e]베스트 품목 영역 -->
        </div>
        <!-- [e]베스트 영역 -->
    </div>
    <!-- [s]footer 영역 -->
    <jsp:include page="/views/fix/footer.jsp"/>
    <!-- [e]footer 영역 -->
    <div id="modal">
        <div class="loginArea">
            <form action="member.js" method="post">
                <fieldset>
                    <legend>입력 칸</legend>
                    <div class="loginbox">
                            <p>
                                <label for="id"></label>
                                <input type="text" id="id" name="id" placeholder="아이디" required>
                            </p><p>
                                <label for="pass"></label>
                                <input type="password" id="pass" name="pass" placeholder="비밀번호" required>
                            </p>
                            <label for="login" class="loggin"><input type="checkbox" name="login" id="login"><span>로그인 상태 유지</span></label>
                            <button type="submit" class="green">로그인</button>    
                            <p class="underBar">또는</p>
                            <a id="naverIdLogin_loginButton" class="naver" href="javascript:void(0)">
                                <span>네이버 로그인</span>
                            </a>
                            <a id="kakao-login-btn" class="kakao_login" href="javascript:loginWithKakao()">
                            <img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="222" height="50"
                                alt="카카오 로그인 버튼" />
                            </a>
                            <p id="token-result"></p>
                            <a href="../join.html">회원가입</a>
                            <a href="#">계정찾기</a>
                            <a href="#">비밀번호 찾기</a>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</body>
</html>