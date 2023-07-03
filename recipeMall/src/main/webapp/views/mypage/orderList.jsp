<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="orderList1" value="${orderMap.orderList1 }"/>
<c:set var="orderList2" value="${orderMap.orderList2 }"/>
<%
   request.setCharacterEncoding("utf-8");
session.getAttribute("isLogon");   // 로그인 상태값을 갖고 있는 키
session.getAttribute("log_id");   // 로그인한 유저 아이디를 갖고 있는 키

%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RecipeMall-마이페이지</title>
    <script src="https://kit.fontawesome.com/54880b83c5.js" crossorigin="anonymous"></script>
    <script src="${contextPath }/views/js/jquery-3.6.4.min.js"></script>
    <script src="${contextPath }/views/js/jquery-ui.min.js"></script>
    <script src="${contextPath}/views/js/common.js"></script>
    <script src="${contextPath }/views/mypage/js/mypage_2_1.js"></script>
    <link rel="stylesheet" href="${contextPath }/views/mypage/css/mypage.css">
    <link rel="stylesheet" href="${contextPath }/views/mypage/css/mypage_common.css">
    <link rel="stylesheet" href="${contextPath }/views/mypage/css/mypage_2_1.css">
</head>
<script type="text/javascript">
$(function() {
     // 현재 날짜 가져오기
     let currentDate = new Date();

     // 주문 날짜 가져오기
     let orderDateElement = document.getElementById("orderDate");
     let orderDateString = orderDateElement.textContent;
     let orderDate = new Date(orderDateString);

     // 현재 날짜와 주문 날짜의 일 값 비교
     let currentDay = currentDate.getDate();
     let orderDay = orderDate.getDate();

     // 결제완료 상태 업데이트
     if (currentDay === orderDay) {
       $('.mypage_content_order .order_sub:nth-child(1) em').text("1");
     }
     // 상품준비중 상태 업데이트
     else if (currentDay - orderDay === 1) {
       $('.mypage_content_order .order_sub:nth-child(2) em').text("1");
     }
     // 배송중 상태 업데이트
     else if (currentDay - orderDay === 2) {
       $('.mypage_content_order .order_sub:nth-child(3) em').text("1");
     }
     // 배송완료 상태 업데이트
     else {
       $('.mypage_content_order .order_sub:nth-child(4) em').text("1");
     }
   });
</script>

<body>
     <!--[s]건너뛰기 링크-->
     <a id="skipNav" href="">본문 바로가기</a>
     <!--[e]건너뛰기 링크-->
    <!-- [s]Header 영역 -->
    <!-- [s]Header 영역 -->
    <jsp:include page="/views/fix/mypageHeader.jsp"/>
   <!-- [s]Header 영역 -->
    <!-- [s]Header 영역 -->
    <!-- content_mypage_s -->
    <div id="main_content" class="mypage_box">
        <div id="mypage">
            <h2 class="mypage_title"><a href="${contextPath}/main/mypage.do">마이페이지</a></h2>
            <!-- sidebar -->
            <jsp:include page="/views/fix/mypageSidebar.jsp"/>
            <!-- sidebar -->
            <!-- mypage_content -->
            <div class="mypage_content" >
                <!-- mypage_content_주문목록/배송조회 시작-->
                <h3>주문목록 / 배송조회</h3>
                <div class="mypage_content_order_header">
                    <h3>진행 중인 주문<span>최근 30일 내에 진행중인 주문정보입니다.</span></h3>
                </div>
                <div class="mypage_content_order">
                    <ul>
                        <li class="order_sub"><span>결제완료</span><Strong><em>0</em></Strong></li>
                        <li class="order_sub"><span>상품준비중</span><Strong><em>0</em></Strong></li>
                        <li class="order_sub"><span>배송중</span><Strong><em>0</em></Strong></li>
                        <li class="order_sub"><span>배송완료</span><Strong><em>0</em></Strong></li>
                    </ul>
                </div>
                <div class="mypage_content_lately_info">
                    <div class="mypage_content_lately_info_header">
                        <h3>조회기간</h3>
                    </div>
                    <div class="date_check_box">
                        <form action="${contextPath }/mypage/selectOrder.do" name="frmDateSearch">
                            <div class="date_check_list">
                                <button type="button" class="btnDate Edate">전체</button>
                                <button type="button" class="btnDate Sdate">7일</button>
                                <button type="button" class="btnDate Odate">1개월</button>
                                <input type="hidden" name="id" value="${log_id}">
                            </div>
                            <div class="date_check_calendar">
                                <input type="date" name="Fdate" id="Fdate">~<input type="date" name="Ldate" id="Ldate">
                            </div>
                            <button type="submit" class="btn_date_check"><em>조회</em></button>
                        </form>
                    </div>
                    <div class="mypage_content_lately_info_result">
                        <span>주문목록 조회 내역 </span>
                        <div class="info_result_table">
                            <table>
                                <tr>
                                    <th>날짜</th><th>상품명</th><th>상품금액</th><th>수량</th>
                                </tr>
                                <c:choose>
                                 <c:when test="${empty orderMap}">
                                    <tr>
                                       <td colspan="4" align="center">구매한 상품이 없습니다.</td>
                                    </tr>
                                 </c:when>
                                 <c:when test="${!empty orderMap}">
                                    <c:forEach var="order" items="${orderList2}">
                                       <tr>
                                          <td id="orderDate">${order.orderDate}</td>
                                          <td>${order.prodName}</td>
                                          <td>${order.prodPrice}</td>
                                          <td>${order.orderAmount}</td>
                                         </tr>
                                      </c:forEach>
                                 </c:when>
                              </c:choose>
                            </table>
                        </div>
                    </div>
                </div>
                
                <!-- mypage_content_주문목록/배송조회 끝-->
            </div>
            <!-- mypage_content -->
        </div>
    </div>
    <!-- content_mypage_e -->

    <!-- footer_s -->
    <!-- [s]footer 영역 -->
    <jsp:include page="/views/fix/footer.jsp"/>
    <!-- [e]footer 영역 -->
    <!-- footer_e -->
</body>
</html>