<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("utf-8");
session.getAttribute("log_id");
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
<script src="${contextPath }/views/js/doro.js"></script>
<script src="${contextPath }/views/mypage/js/mypage_2_3.js"></script>
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage.css">
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage_common.css">
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage_2_3.css">
</head>
<body>
	<!--[s]건너뛰기 링크-->
	<a id="skipNav" href="">본문 바로가기</a>
	<!--[e]건너뛰기 링크-->
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
				<!-- mypage_content 배송지등록 시작-->
				<h3>나의 배송지 관리</h3>
				<div class="mypage_content_aHeader">
					<h4>배송지 등록</h4>
				</div>
				<div class="mypage_content_address">
					<form action="${contextPath}/mypage/addShipping.do" method="post">
						<table>
							<colgroup>
								<col style="width: 20%;">
								<col style="width: 80%;">
							</colgroup>
							<tr>
								<th><span class="important">배송지 이름</span></th>
								<td><input type="text" name="shippingTitle"></td>
							</tr>
							<tr>
								<th><span class="important">받으실 분</span></th>
								<td><input type="hidden" name="id" value="${log_id }">
									<input type="text" name="shippingName" maxlength="20">
								</td>
							</tr>
							<tr>
								<th><span class="important">받으실 곳</span></th>
								<td class="member_address">
									<div class="address_postcode">
										<input type="text" name="shippingZonecode"
											id="address_postcode_sub">
										<button type="button" onclick="sample4_execDaumPostcode();"
											class="btn_post_search">우편번호검색</button>
									</div>
									<div class="address_input">
										<input type="text" name="shippingAddress"
											id="shipping_address"> <input type="text"
											name="shippingAddressSub" id="shipping_address_sub"
											placeholder="상세주소를 입력해주세요.">
									</div>
								</td>
							</tr>
							<tr>
								<th>전화번호</th>
								<td><input type="text" name="shippingPhone"
									id="shipping_phone"></td>
							</tr>
							<tr>
								<th><span class="important">휴대폰 번호</span></th>
								<td><input type="text" name="shippingCellphone"></td>
							</tr>
						</table>
						<div class="mypage_content_address_submit">
							<button type="reset" class="btn__address_cancel">
								<Strong>다시입력</Strong>
							</button>
							<button type="submit" class="btn_address_submit">
								<strong>저장</strong>
							</button>
						</div>
					</form>
				</div>
				<!-- mypage_content_배송지등록 끝-->
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