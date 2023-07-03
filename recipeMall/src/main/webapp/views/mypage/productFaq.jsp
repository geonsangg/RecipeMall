<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
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
<script src="${contextPath}/views/mypage/js/mypage_3_1.js"></script>
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage.css">
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage_common.css">
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage_3_1.css">
<link rel="shortcut icon" href="${contextPath}/views/images/smalllogo.png" />
</head>

<body>
	<!--[s]전체 컨텐츠 영역  -->
	<!--[s]건너뛰기 링크-->
	<a id="skipNav" href="">본문 바로가기</a>
	<!--[e]건너뛰기 링크-->
	<!-- [s]Header 영역 -->
	<jsp:include page="/views/fix/mypageHeader.jsp" />
	<!-- [e]Header 영역 -->

	<!-- [s]main_content 영역 -->
	<div id="main_content" class="mypage_box">
		<!--[s]content_mypage_e -->
		<div id="mypage">
			<h2 class="mypage_title">
				<a href="${contextPath}/main/mypage.do?id=${log_id}">마이페이지</a>
			</h2>
			<!-- sidebar -->
			<jsp:include page="/views/fix/mypageSidebar.jsp" />
			<!-- sidebar -->
			<!-- mypage_content -->
			<div class="mypage_content">
				<h3>문의내역 / 답글보기</h3>
				<hr>
			</div>
			<div class="mypage_product_area">
				<c:forEach var="faq" items="${productFaqList}">
					<div class="mypage_product_qna">
						<div class="mypage_product_q">
							<span class="txt_q">Q.</span>
							<h4>${faq.prodFaqTitle}</h4>
							<h6>${faq.prodName}</h6>
							<p>${faq.content}</p>
						</div>
						<button type="button" class="btn_product" data-review-id="1">
							<i class="fa-solid fa-chevron-down"></i>
						</button>
						<div class="mypage_product_a" id="review_form_1">
							<span class="txt_a">A.</span>
							<c:choose>
								<c:when test="${faq.hasComent != 0 or faq.hasComent ne null}">	<!-- admin으로 답변 입력해줄때 1이란 값 넣어줘야 합니다. -->
									<p>${faq.prodContent}</p>	
								</c:when>
								<c:otherwise>
									<p>등록된 답변이 없습니다.</p>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</c:forEach>
			</div>
			<!-- mypage_content -->
		</div>
	</div>
	<!-- content_mypage_e -->
	<!-- [s]footer 영역 -->
	<jsp:include page="/views/fix/footer.jsp" />
	<!-- [e]footer 영역 -->
	<!--로그인 영역-->
	<jsp:include page="/views/fix/login.jsp"/>
	<!--로그인 영역-->
	
	<script src="${contextPath}/views/js/login.js"></script>
	<script src="${contextPath}/views/js/naver_login.js"></script>
	<script src="${contextPath}/views/js/kakao_login.js"></script>
	<link rel="stylesheet" href="${contextPath}/views/css/login.css">
	<link rel="stylesheet" href="${contextPath}/views/js/common.js">
</body>
</html>
