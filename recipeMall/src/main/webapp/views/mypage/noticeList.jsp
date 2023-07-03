<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("utf-8");
session.getAttribute("isLogon");
session.getAttribute("log_id");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="noticeList" value="${noticeMap.noticeList}"/>
<c:set var="totArticles" value="${noticeMap.totArticles}"/>
<c:set var="section" value="${noticeMap.section}"/>
<c:set var="pageNum" value="${noticeMap.pageNum}"/>
<c:set var="ep" value="${(totArticles mod 100)}"/>
<c:choose>
	<c:when test="${section > totArticles/100}">
		<c:set var="endPage" value="${(ep%10)==0?ep/10:ep/10+1}"/>
	</c:when>
	<c:otherwise>
		<c:set var="endPage" value="10"/>
	</c:otherwise>
</c:choose>
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
				<!-- <h3 class="menu_title">고객센터</h3> -->
				<h2 class="notice_title">공지사항</h2>
				<!-- <div class="cs_center"></div> -->
				<div class="notice_content">
					<div class="n_search">
						<form class="search_form" action="">
							<select>
								<option>제목</option>
								<option>내용</option>
								<option>제목+내용</option>
							</select>
							<input type="search" name="search" class="search_in">
							<input type="submit" value="검색" class="search_do">
						</form>
					</div>
					<div class="notice_list">
						<div class="top">
							<div class="num">번호</div>
							<div class="title">제목</div>
							<div class="date">작성일</div>
						</div>
						<c:choose>
							<c:when test="${empty noticeList}">
								등록된 공지사항이 없습니다.
							</c:when>
							<c:when test="${!empty noticeList}">
								<c:forEach var="notice" items="${noticeList}">
									<div>
										<div class="num">${notice.noticeNo}</div>
										<div class="title">
											<a href="${contextPath}/notice/noticeView.do?noticeNo=${notice.noticeNo}">${notice.noticeTitle}</a>
										</div>
										<div class="date">${notice.noticeDate}</div>
									</div>
								</c:forEach>
							</c:when>
						</c:choose>
					</div>
					<div class="page">
						<c:set var="lastPage" value="${(totArticles mod 100)}"/>
						<c:if test="${totArticles != 0}">
							<c:choose>
								<c:when test="${totArticles > 100}">
									<c:forEach var="page" begin="1" end="${endPage}" step="1">
										<c:if test="${section > 1 && page == 1}">
											<!-- <a href="${contextPath}/board/listArticles.do?section=${section-1}&pageNum=10"> prev</a> -->
											<a href="${contextPath}/notice/noticeList.do?section=${section-1}&pageNum=${currentPage}"> prev</a>
										</c:if>
										<c:choose>
											<c:when test="${pageNum==page}">
												<a class="selPage" href="${contextPath}/notice/noticeList.do?section=${section}&pageNum=${page}">${(section-1)*10+page}</a>
												<c:set var="currentPage" value="${pageNum}" scope="application"/>
											</c:when>
											<c:otherwise>
												<a class="noLine" href="${contextPath}/notice/noticeList.do?section=${section}&pageNum=${page}">${(section-1)*10+page}</a>
											</c:otherwise>
										</c:choose>
										<c:if test="${page == 10 && totArticles/100>section}">
											<!-- <a href="${contextPath}/board/listArticles.do?section=${section+1}&pageNum=1"> next</a> -->
											<a href="${contextPath}/notice/noticeList.do?section=${section+1}&pageNum=${currentPage}"> next</a>
										</c:if>
									</c:forEach>
								</c:when>
								<c:when test="${totArticles <= 100}">
									<c:if test="${(totArticles mod 10) == 0}">
										<c:set var="totArticles" value="${totArticles-1}"/>
									</c:if>
									<c:forEach var="page" begin="1" end="${totArticles/10+1}" step="1">
										<c:choose>
											<c:when test="${page == pageNum}">
												<a class="selPage" href="${contextPath}/notice/noticeList.do?section=${section}&pageNum=${page}">${page}</a>
											</c:when>
											<c:otherwise>
												<a class="noLine" href="${contextPath}/notice/noticeList.do?section=${section}&pageNum=${page}">${page}</a>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:when>
							</c:choose>
						</c:if>
					</div>
				</div>
				<!--[e]공지사항 영역-->
			</div>
		<!-- [s]content_mypage_e -->
		</div>
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