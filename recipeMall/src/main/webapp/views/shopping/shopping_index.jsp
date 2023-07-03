<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
   isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="productList" value="${shoppingIndexMap.productList}"/>
<c:set var="prodCountList" value="${shoppingIndexMap.prodCountList}"/>
<%
   request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <title>recipeMall</title>
    <script src="https://kit.fontawesome.com/54880b83c5.js" crossorigin="anonymous"></script>
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="${contextPath }/views/shopping/js/shopping_common.js"></script>
    <script src="${contextPath}/views/shopping/js/shopping_index.js"></script>
    <link rel="stylesheet" href="${contextPath}/views/shopping/css/shopping_common.css"/>
    <link rel="stylesheet" href="${contextPath}/views/shopping/css/shopping_index.css"/>
    <link rel="shortcut icon" href="${contextPath}/views/images/smalllogo.png"/>
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
            
            <!-- [s]쇼핑 바로가기 영역 -->
            <div class="direct_menu_area">
                <div class="direct_menu">
                	<a href="${contextPath}/shopping/matarial.do?prodKind=1">
	                    <img src="${contextPath}/views/images/vegi.png" alt="채소/과일">
	                    <p>채소/과일</p>
                    </a>
                </div>
                <div class="direct_menu">
                	<a href="${contextPath}/shopping/matarial.do?prodKind=2">
	                    <img src="${contextPath}/views/images/meat.png" alt="정육">
	                    <p>정육</p>
                    </a>	
                </div>
                <div class="direct_menu">
                	<a href="${contextPath}/shopping/matarial.do?prodKind=3">
	                    <img src="${contextPath}/views/images/fish.png" alt="해산물">
	                    <p>해산물</p>
                    </a>
                </div>
                <div class="direct_menu">
                	<a href="${contextPath}/shopping/matarial.do?prodKind=4">
	                    <img src="${contextPath}/views/images/spa.png" alt="면/양념">
	                    <p>면/양념</p>
                    </a>
                </div>
                <div class="direct_menu">
                	<a href="${contextPath}/shopping/matarial.do?prodKind=5">
	                    <img src="${contextPath}/views/images/bread.png" alt="베이커리">
	                    <p>베이커리</p>
                    </a>
                </div>
                <div class="direct_menu">
                	<a href="${contextPath}/shopping/matarial.do?prodKind=6">
	                    <img src="${contextPath}/views/images/etc.png" alt="기타">
	                    <p>기타</p>
                    </a>
                </div>
            </div>
            <!-- [e]쇼핑 바로가기 영역 -->
            <!-- [s]베스트 영역 -->
            <div class="best">
                <h1>베스트</h1>
                <div class="best_area">
                   <c:forEach var="list" items="${prodCountList}" varStatus="status" begin="0" end="4">
                          <div class="best_content">
	                          <a href="${contextPath}/shopping/product_info.do?prodCode=${list.prodCode}">
	                              <img src="${contextPath}/views/shopping/productImages/${list.prodImage}" alt="">
	                              <p class="etc_name">${list.prodName}</p>
	                              <p>★ ${list.avgStar} 리뷰 ${list.reviewCnt}</p>
	                          </a>
                          </div>
                   </c:forEach>
                </div>
            </div>
            <!-- [e]베스트 영역 -->
            
            <!-- [s]기타추천 영역 -->
            <div class="etc_area">
                <h1>기타 추천 상품</h1>
                <div class="etc_product">
                   <c:forEach var="list" items="${productList}" varStatus="status">
                      <c:if test="${status.index<10}">
                          <div class="etc_content">
                          	<a href="${contextPath}/shopping/product_info.do?prodCode=${list.prodCode}">
	                            <img src="${contextPath}/views/shopping/productImages/${list.prodImage}" alt="">
	                            <p class="etc_name">${list.prodName}</p>
	                            <p>★ ${list.avgStar} 리뷰 ${list.reviewCnt}</p>
                            </a>
                          </div>
                       </c:if>
                    </c:forEach>
                </div>
            </div>
            <!-- [e]기타추천 영역 -->
        </div>
    <!-- [e]main_content 영역 -->
    <!-- [s]footer 영역 -->
    <jsp:include page="/views/fix/footer.jsp"/>
    <!-- [e]footer 영역 -->
    <!--로그인 영역-->
	<jsp:include page="/views/fix/login.jsp" />
	<!--로그인 영역-->
    
    <script src="${contextPath }/views/js/login.js"></script>
    <script src="${contextPath }/views/js/naver_login.js"></script>
    <script src="${contextPath }/views/js/kakao_login.js"></script>
    <link rel="stylesheet" href="${contextPath }/views/css/login.css">
    <!--[e]전체 컨텐츠 영역  -->
</body>
</html>