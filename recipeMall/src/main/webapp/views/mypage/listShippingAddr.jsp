<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>RecipeMall-마이페이지</title>
<script src="https://kit.fontawesome.com/54880b83c5.js" crossorigin="anonymous"></script>
<script src="${contextPath}/views/js/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/views/js/jquery-ui.min.js"></script>
<script src="${contextPath}/views/js/common.js"></script>
<script src="${contextPath}/views/js/doro.js"></script>
<script src="${contextPath}/views/mypage/js/mypage_2_3.js"></script>
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage.css">
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage_common.css">
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage_2_3.css">
<style type="text/css">
.shippingTable {
	border-collapse: collapse;
	width: 100%;
	margin-bottom: 20px;
}

.shippingTable th span {
	font-size: 0.8em;
}

.shippingTable th {
	background-color: #c3c3c3;
	text-align: center;
}

.shippingTable td a {
	color: blue;
}

.shippingTable td a:hover {
	text-decoration: underline;
}

.shippingTable td {
	font-size: 0.8em;
}

.shippingAddbtn {
	margin-top: 20px;
	font-size: 1.2em padding: 0 5px 0 5px;
	text-align: center;
}
</style>
</head>
<body>
	<!-- [s]건너뛰기 링크-->
	<a id="skipNav" href="">본문 바로가기</a>
	<!-- [e]건너뛰기 링크-->
	<!-- [s]Header 영역 -->
	<jsp:include page="/views/fix/mypageHeader.jsp" />
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
				<!-- mypage_content 배송지등록 시작-->
				<h3>나의 배송지 관리</h3>
				<div class="mypage_content_aHeader">
					<h4>나의 배송지</h4>
				</div>
				<div class="mypage_content_address">
					<table border="1" style="border-collapse: collapse;"
						class="shippingTable">
						<tr align="center" bgcolor="c3c3c3">
							<th><span>받는분</span></th>
							<th><span>배송지</span></th>
							<th><span>우편번호</span></th>
							<th><span>주소</span></th>
							<th><span>전화번호</span></th>
							<th><span>휴대폰번호</span></th>
							<th><span>삭제</span></th>
						</tr>
						<c:choose>
							<c:when test="${empty shippingAddrList}">
								<tr>
									<td colspan="7" align="center">등록된 배송지가 없습니다.</td>
								</tr>
							</c:when>
							<c:when test="${!empty shippingAddrList}">
								<c:forEach var="shipping" items="${shippingAddrList}">
									<tr align="center">
										<td>${shipping.shippingName}</td>
										<td>${shipping.shippingTitle}</td>
										<td>${shipping.shippingZonecode}</td>
										<td>${shipping.shippingAddress}
											${shipping.shippingAddressSub}</td>
										<td>${shipping.shippingPhone}</td>
										<td>${shipping.shippingCellphone}</td>
										<td><a
											href="${contextPath}/mypage/delShipping.do?shippingName=${shipping.shippingName}">삭제</a></td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
					<p align="center" class="shippingAddbtn">
						<a href="${contextPath}/mypage/addShippingForm.do">&lt; 배송지등록</a>
					</p>
				</div>
				<!-- mypage_content -->
			</div>
		</div>
		<!-- content_mypage_e -->

		<!-- footer_s -->
		<jsp:include page="/views/fix/footer.jsp" />
		<!-- footer_e -->
	</div>	
</body>
</html>
</html>