<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("utf-8");
session.getAttribute("isLogon");
session.getAttribute("log_id");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>RecipeMall-마이페이지</title>
<script src="https://kit.fontawesome.com/54880b83c5.js" crossorigin="anonymous"></script>
<script src="${contextPath}/views/js/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/views/js/jquery-ui.min.js"></script>
<script src="${contextPath}/views/js/common.js"></script>
<script src="${contextPath}/views/mypage/js/mypage_main.js"></script>

<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage.css">
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage_common.css">
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage_2_1.css">
<link rel="shortcut icon" href="${contextPath}/views/images/smalllogo.png" />
</head>
<body>
	<!--[s]건너뛰기 링크-->
	<a id="skipNav" href="">본문 바로가기</a>
	<!--[e]건너뛰기 링크-->
	<!-- [s]Header 영역 -->
	<!-- [s]Header 영역 -->
	<jsp:include page="/views/fix/mypageHeader.jsp" />
	<!-- [s]Header 영역 -->
	<!-- [s]Header 영역 -->
	<!-- content_mypage_s -->
	<div id="main_content" class="mypage_box">
		<div id="mypage">
			<h2 class="mypage_title">
				<a href="${contextPath}/main/mypage.do">마이페이지</a>
			</h2>
			<!-- sidebar -->
			<jsp:include page="/views/fix/mypageSidebar.jsp"/>
			<!-- sidebar -->
			<!-- mypage_content -->
			<div class="mypage_content">
				<h3 class="menu_title">메인 마이페이지</h3>
				<div class="mypage_content_order_header">
					<h3>
						진행 중인 주문<span>최근 30일 내에 진행중인 주문정보입니다.</span>
					</h3>
				</div>
				<div class="mypage_content_order">
					<ul>
						<li class="order_sub"><span>결제완료</span><Strong><em>0</em></Strong></li>
						<li class="order_sub"><span>상품준비중</span><Strong><em>0</em></Strong></li>
						<li class="order_sub"><span>배송중</span><Strong><em>0</em></Strong></li>
						<li class="order_sub"><span>배송완료</span><Strong><em>0</em></Strong></li>
					</ul>
				</div>
				<div class="order_message">
					<h3>조금만 기다려주세요 상품이 곧 도착합니다!</h3>
				</div>
				<div class="moving_box">
					<div class="order_place">
						<img src="${contextPath}/views/images/market.JPG" alt="market">
					</div>
					<div class="truck_img">
						<img src="${contextPath}/views/images/truck.JPG" alt="truck">
					</div>
					<div class="order_home">
						<img src="${contextPath}/views/images/house.JPG" alt="house">
					</div>
				</div>

			</div>
			<!-- mypage_content -->
		</div>
	</div>
	<!-- content_mypage_e -->

	<!-- [s]footer 영역 -->
	<jsp:include page="/views/fix/footer.jsp" />
	<!-- [e]footer 영역 -->
</body>
</html>