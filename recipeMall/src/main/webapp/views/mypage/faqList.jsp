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
<script src="${contextPath}/views/mypage/js/mypage.js"></script>

<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage.css">
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage_common.css">
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage_6.css">
<link rel="shortcut icon" href="${contextPath}/views/images/smalllogo.png" />
</head>
<body>
	<!--[s]건너뛰기 링크-->
	<a id="skipNav" href="">본문 바로가기</a>
	<!--[e]건너뛰기 링크-->
	<!-- [s]Header 영역 -->
	<jsp:include page="/views/fix/mypageHeader.jsp" />
	<!-- [e]Header 영역 -->
	<!-- content_mypage_s -->
	<div id="main_content" class="mypage_box">
		<div id="mypage">
			<h2 class="mypage_title">
				<a href="${contextPath}/main/mypage.do">마이페이지</a>
			</h2>
			<!-- sidebar -->
			<jsp:include page="/views/fix/mypageSidebar.jsp"/>
			<!-- sidebar -->
			<c:if test="${isLogon == true }">
				<!-- mypage_content -->
				<div class="mypage_content">
					<h3 class="menu_title">고객센터</h3>
					<div class="cs_center">
						<h4 class="c_title">자주하는 질문</h4>
						<div class="accordions cs_faq">
							<ul class="faq_list">
								<c:forEach var="faqList" items="${faqList}">
									<li class="accordion qna_box">
										<div class="accordion_title question">
											<button class="click_btn">
												<p class="qna_title">
													<span class="i">Q.</span> ${faqList.faqTitle}
												</p>
												<span class="fa"><i class="fa-solid fa-chevron-up"></i></span>
											</button>
										</div>
										<div class="accordion_body answer">
											<div class="accordion_content">
												<p>${faqList.faqContent}</p>
											</div>
										</div>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
				<!-- mypage_content -->
			</c:if>
			<c:if test="${isLogon != true}">
				<div>로그인 후 이용해주세요</div>
			</c:if>
		</div>
	</div>
	<!-- content_mypage_e -->

	<!-- footer_s -->
	<jsp:include page="/views/fix/footer.jsp" />
	<!-- footer_e -->
</body>
</html>