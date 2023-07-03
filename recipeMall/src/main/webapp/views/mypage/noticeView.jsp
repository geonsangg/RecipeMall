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
<title>recipeMall</title>
<script src="https://kit.fontawesome.com/54880b83c5.js" crossorigin="anonymous"></script>
<script src="${contextPath}/views/js/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/views/js/jquery-ui.min.js"></script>
<script src="${contextPath}/views/js/common.js"></script>
<script src="${contextPath}/views/mypage/js/mypage.js"></script>

<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage.css">
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage_common.css">
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage_6.css">
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage_6_3.css">
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
				<a href="${contextPath}/main/mypage.do">마이페이지</a>
			</h2>
			<!-- sidebar -->
			<jsp:include page="/views/fix/mypageSidebar.jsp" />
			<!-- sidebar -->
			<!--[s]공지사항 영역-->
			<div class="mypage_content">
				<h2 class="notice_t">공지사항</h2>
				<div class="notice_d">
					<table class="notice_tbl">
						<caption>공지사항 상세화면</caption>
						<tbody>
							<tr>
								<th>제목</th>
								<td>${notice.noticeTitle}</td>
							</tr>
							<tr>
								<th>등록일</th>
								<td>${notice.noticeDate}</td>
							</tr>
							<tr>
								<td class="notice_c" colspan="2">
									${notice.noticeContent}
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<dl class="prevNext">
					<dt class="prev">이전글</dt>
					<dd class="listx"><a href="${contextPath}/notice/noticeView.do?noticeNo=${notice.noticeNo-1}">이전글이 없습니다.</a></dd>
					<dt class="next">다음글</dt>
					<dd class="listx"><a href="${contextPath}/notice/noticeView.do?noticeNo=${notice.noticeNo+1}" onclick="fnDetail('');" >다음글이 없습니다.</a></dd>
				</dl>
				<div class="btn_a">
					<button type="button" class="btn_c list_btn" onclick="location.href='${contextPath}/mypage/notice/noticeList.do'">목록</button>
				</div>
			</div>
			<!--[e]공지사항 영역-->
		</div>
		<!-- [s]content_mypage_e -->
	</div>
	<!-- [e]main_content 영역 -->

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