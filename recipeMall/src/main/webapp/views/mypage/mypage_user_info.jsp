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
<script src="${contextPath}/views/mypage/js/mypage_user_info.js"></script>
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage.css">
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage_common.css">
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage_user.css">
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
				<h3 class="menu_title">내 정보</h3>
				<div class="user_info">
					<div class="drop_out_area">
						<button class="drop_out" onclick="location.href='${contextPath}/user/deleteUser.do?id=${log_id}'">회원탈퇴</button>
					</div>
					<form action="${contextPath}/user/updateUser.do" method="post">
						<input type="hidden" name="id" value="${log_id}">
						<table class="info_tbl">
							<caption>내 정보</caption>
							<tbody>
								<tr class="user_id">
									<th>아이디</th>
									<td><span>${user.id}</span></td>
								</tr>
								<tr class="user_nkn">
									<th>닉네임</th>
									<td><input type="text" name="user_nikname" id="nikname"
										class="info_input" value="${user.nikName}"></td>
								</tr>
								<tr class="user_profile_img">
									<th>프로필 이미지</th>
									<td>
										<div class="profile_img ">
											<label for="profile_img">
												<div class="file_upload_btn">
													<i class="fa-solid fa-image"></i>
												</div>
											</label> <input type="file" data-ax-path="file" name="profile_img"
												id="profile_img" multiple="multiple">
										</div>
									</td>
								</tr>

								<tr class="user_pw">
									<th>비밀번호</th>
									<td>
										<input type="text" name="pw" value="${user.pw}">
									</td>
								</tr>
								<tr class="user_ph">
									<th>휴대폰번호</th>
									<td><input type="text" name="user_phone" id="user_phone"
										class="info_input" value="${user.phone}">
										<button class="btn_n">휴대폰 인증</button></td>
								</tr>
								<tr class="user_addr">
									<th>주소</th>
									<td><a class="a_addr" href="mypage_main_2_3.html"> 배송지
											주소 관리는 <em>[주문 배송 <i
												class="fa-solid fa-hand-point-right"></i> 배송지 등록]
										</em>에서 수정, 등록 합니다.
									</a></td>
								</tr>
							</tbody>
						</table>
						<button class="info_update btn btn_c" type="submit">회원정보 수정</button>
					</form>
				</div>
			</div>
			<!-- mypage_content -->
		</div>
	</div>
	<!-- content_mypage_e -->
	
	<!-- footer_s -->
	<jsp:include page="/views/fix/footer.jsp" />
	<!-- footer_e -->
	<script src="${contextPath}/views/js/login.js"></script>
	<script src="${contextPath}/views/js/naver_login.js"></script>
	<script src="${contextPath}/views/js/kakao_login.js"></script>
	<link rel="stylesheet" href="${contextPath}/views/css/login.css">
</body>
</html>