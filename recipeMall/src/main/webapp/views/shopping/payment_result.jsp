<%@page import="recipeMall.mypage.CartVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
   isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="orderList" value="${orderMap.orderList}"/>
<c:set var="detailList" value="${orderMap.detailList}"/>
  
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
    <script src="${contextPath}/views/shopping/js/payment.js"></script>
    <script src="${contextPath}/views/js/address_change.js"></script>
    <link rel="stylesheet" href="${contextPath}/views/shopping/css/shopping_common.css">
    <link rel="stylesheet" href="${contextPath}/views/shopping/css/payment_result.css">
    <link rel="shortcut icon" href="${contextPath}/views/images/smalllogo.png" />
    <!-- <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> -->
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


    <!-- [s]광고배너 영역 -->
    <div class="adbanner">
        <p><a href="#">지금 가입하면 무료배송!<button class="banner_btn" onclick="close_div()"><i
                        class="fa-solid fa-x"></i></button></a></p>
    </div>
    <!--[e]광고배너 영역 -->


    <!-- [s]Header 영역 -->
    <jsp:include page="/views/fix/shopHeader.jsp"/>
    <!-- [e]Header 영역 -->
    
    <!-- [s]메인 컨텐츠 영역  -->
    <div id="main_content">
        <div class="succes_payment">
           <h2>주문완료</h2>
           <p>RecipeMall을 이용해주셔서 감사합니다.</p>
           <table border="1">
              <tr>
                 <td rowspan="3" class="titletb">상품 정보</td>
                 <td id="detailZone">
                 <c:forEach var="detail" items="${detailList}">
                    <span>상품이름 : ${detail.prodName} &nbsp;&nbsp;&nbsp;구매수량 : ${detail.orderAmount}</span><br>
                 </c:forEach>
                 </td>
              </tr>
              <tr>
                 <td>배송비 ${orderList[0].deliveryFee}원
              </tr>
              <tr>
                 <td>총 상품가격 ${orderList[0].allOrderPrice}원</td>
              </tr>
              <tr>
                 <td class="titletb">주문번호</td>
                 <td>${orderList[0].orderCode}</td>
              </tr>
              <tr>
                 <td rowspan="4" class="titletb">배송지</td>
                 <td>${orderList[0].name}</td>
              </tr>
              <tr>
                 <td>${orderList[0].phone}</td>
              </tr>
              <tr>
                 <td>${orderList[0].shipAddr}</td>
              </tr>
              <tr>
                 <td>${orderList[0].shipAddr2}</td>
              </tr>
              <tr>
                 <td class="titletb">배송메시지</td>
                 <td>${orderList[0].requestMsg}</td>
              </tr>
              <tr>
                 <td colspan="2"><a href="${contextPath}/shopping/shopping_index.do">홈으로</a></td>
              </tr>
           </table>
        </div>
    </div>
    <!-- [e]메인 컨텐츠 영역  -->
    <!-- [s]footer 영역 -->
    <jsp:include page="/views/fix/footer.jsp"/>
    <!-- [e]footer 영역 -->
    <!--로그인 영역-->
	<jsp:include page="/views/fix/login.jsp" />
	<!--로그인 영역-->
</body>
</html>