<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("utf-8");
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
			<jsp:include page="/views/fix/mypageSidebar.jsp" />
			<!-- sidebar -->
			<!-- mypage_content -->
			<div class="mypage_content">
				<h3 class="menu_title">고객센터</h3>
				<div class="inq_v">
					<table class="inq_view_tbl">
						<tr>
							<th>분류</th>
							<td>
						<c:choose>
							<c:when test="${inqVO.inqCate == 1}">쇼핑</c:when>
							<c:when test="${inqVO.inqCate == 2}">커뮤니티</c:when>
						</c:choose>
							</td>
							<th>등록일</th>
							<td>${inqVO.inqDate}</td>
						</tr>
						<tr>
							<th>제목</th>
							<td colspan="3">${inqVO.inqTitle}</td>
						</tr>
						<tr class="inquery">
							<th>문의</th>
							<td colspan="3">
								<div class="inq_box">${inqVO.inqContent}</div>
							</td>
						</tr>
						<tr class="a">
							<th>답변</th>
								<td colspan="3">
						<c:choose>
							<c:when test="${inqVO.hasRe == 0}">
								현재 답변 대기중입니다.
							</c:when>
							<c:when test="${inqVO.hasRe == 1}">
									<div class="inq_box">${inqVO.inqReply}</div>
							</c:when>
						</c:choose>
								</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- mypage_content -->
		</div>
	</div>
	<!-- content_mypage_e -->

	<!-- footer_s -->
	<jsp:include page="/views/fix/footer.jsp" />
	<!-- footer_e -->
</body>
</html>