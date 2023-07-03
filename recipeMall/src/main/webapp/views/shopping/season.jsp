<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
        isELIgnored="false"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>  
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
    <title>recipeMall</title>
    <script src="https://kit.fontawesome.com/54880b83c5.js" crossorigin="anonymous"></script>
    <script src="${contextPath}/views/js/jquery-3.6.4.min.js"></script>
    <script src="${contextPath}/views/js/jquery-ui.min.js"></script>
    <script src="${contextPath}/views/js/index.js"></script>
    <link rel="stylesheet" href="${contextPath}/views/shopping/css/shopping_common.css">
    <link rel="stylesheet" href="${contextPath}/views/shopping/css/season.css">
    <link rel="shortcut icon" href="../images/smalllogo.png" />
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

    <!-- [s]main_content 영역 -->
    <div id="main_content">
        <div class="spring">
            <h1>봄 대표상품</h1>
            <div class="spring_area">
                <c:forEach var="list" items="${productList}" varStatus="status">
                   <c:choose>
	                   <c:when test="${list.prodSeason eq 1 }">
	                           <div class="spring_content">
	                           	<a href="${contextPath}/shopping/product_info.do?prodCode=${list.prodCode}">
	                                <img src="${contextPath}/views/shopping/productImages/${list.prodImage}" alt="">
	                                <p class="spring_name">${list.prodName}</p>
	                                <p>★ ${list.avgStar} 리뷰 ${list.reviewCnt}</p>
	                               </a>
	                           </div>
	                    </c:when>
                    </c:choose>
                </c:forEach>
            </div>
        </div>
        <div class="spring">
            <h1>여름 대표상품</h1>
            <div class="spring_area">
                <c:forEach var="list" items="${productList}" varStatus="status">
                   <c:choose>
	                   <c:when test="${list.prodSeason eq 2 }">
	                     		<div class="spring_content">
	                           	<a href="${contextPath}/shopping/product_info.do?prodCode=${list.prodCode}">
	                                <img src="${contextPath}/views/shopping/productImages/${list.prodImage}" alt="">
	                                <p class="spring_name">${list.prodName}</p>
	                                <p>★ ${list.avgStar} 리뷰 ${list.reviewCnt}</p>
	                               </a>
	                           </div>
	                    </c:when>
                    </c:choose>
                </c:forEach>
            </div>
        </div>
        <div class="spring">
            <h1>가을 대표상품</h1>
            <div class="spring_area">
                <c:forEach var="list" items="${productList}" varStatus="status">
                   <c:choose>
	                   <c:when test="${list.prodSeason eq 3 }">
	                          <div class="spring_content">
	                           	<a href="${contextPath}/shopping/product_info.do?prodCode=${list.prodCode}">
	                                <img src="${contextPath}/views/shopping/productImages/${list.prodImage}" alt="">
	                                <p class="spring_name">${list.prodName}</p>
	                                <p>★ ${list.avgStar} 리뷰 ${list.reviewCnt}</p>
	                               </a>
	                           </div>
	                    </c:when>
                    </c:choose>
                </c:forEach>
            </div>
        </div>
        <div class="spring">
            <h1>겨울 대표상품</h1>
            <div class="spring_area">
                <c:forEach var="list" items="${productList}" varStatus="status">
                   <c:choose>
	                   <c:when test="${list.prodSeason eq 4 }">
	                      	<div class="spring_content">
	                           	<a href="${contextPath}/shopping/product_info.do?prodCode=${list.prodCode}">
	                                <img src="${contextPath}/views/shopping/productImages/${list.prodImage}" alt="">
	                                <p class="spring_name">${list.prodName}</p>
	                                <p>★ ${list.avgStar} 리뷰 ${list.reviewCnt}</p>
	                               </a>
	                           </div>
	                    </c:when>
                    </c:choose>
                </c:forEach>
            </div>
        </div>
    </div>
    
    <!-- [e]사이드바 영역 -->
    <!-- [e]main_content 영역 -->
    <!-- [s]footer 영역 -->
    <jsp:include page="/views/fix/footer.jsp"/>
    <!-- [e]footer 영역 -->
     <!--로그인 영역-->
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
    <script src="${contextPath}/views/js/login.js"></script>
    <script src="${contextPath}/views/js/naver_login.js"></script>
    <script src="${contextPath}/views/js/kakao_login.js"></script>
    <link rel="stylesheet" href="${contextPath}/views/css/login.css">
   <script src="${contextPath}/views/shopping/js/shopping_common.js"></script>

    <!--[e]전체 컨텐츠 영역  -->
</body>
</html>