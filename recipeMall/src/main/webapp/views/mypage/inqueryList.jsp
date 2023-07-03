<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("utf-8");
session=request.getSession(false);
session.getAttribute("isLogon");
session.getAttribute("log_id");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="inqueryList" value="${inqueryMap.inqueryList}"/>
<c:set var="totArticles" value="${inqueryMap.totArticles}"/>
<c:set var="section" value="${inqueryMap.section}"/>
<c:set var="pageNum" value="${inqueryMap.pageNum}"/>
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
<title>RecipeMall-마이페이지</title>
<script src="https://kit.fontawesome.com/54880b83c5.js" crossorigin="anonymous"></script>
<script src="${contextPath}/views/js/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/views/js/jquery-ui.min.js"></script>
<script src="${contextPath}/views/js/common.js"></script>
<script src="${contextPath}/views/mypage/js/mypage.js"></script>

<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage_6.css">
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage.css">
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage_common.css">
<link rel="shortcut icon" href="${contextPath}/views/images/smalllogo.png" />
<script type="text/javascript">
function fn_inqForm() {
	alert('확인');
	location.href="${contextPath}/service/inqForm.do?id=${log_id}";
}
</script>
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
				<h3 class="menu_title">고객센터</h3>
				<div class="inq_list">
					<div class="inq_select">
						<ul class="inq_s">
							<li>
								<div class="inq_class">
									<ul class="inq_menu">
										<li class="board_all menu_li active"><a class="active_a"
											href="#">전체</a></li>
										<li class="board_community menu_li"><a href="#">커뮤니티</a></li>
										<li class="board_shopping menu_li"><a href="#">쇼핑</a></li>
									</ul>
								</div>
							</li>
							<li>
								<div class="inq_date_s inq_h">
									<ul>
										<li><a href="#">처음</a></li>
										<li><a href="#">최신</a></li>
									</ul>
								</div>
							</li>
							<li>
								<div class="inq_cnt inq_h">
									<select name="" id="">
										<option value="">10개</option>
										<option value="">30개</option>
									</select>
								</div>
							</li>
							<li><button class="inq_view_btn">보기</button></li>
						</ul>
					</div>
					<div class="my_inq_list">
						<table class="inq_list_tb">
							<thead>
								<tr>
									<th>번호</th>
									<th>분류</th>
									<th>제목</th>
									<th>작성일</th>
									<th>상태</th>
								</tr>
							</thead>
							<tbody>
							<c:choose>
								<c:when test="${empty inqueryList}">
									<tr><td colspan="5">등록된 문의가 없습니다.</td></tr>
								</c:when>
								<c:when test="${!empty inqueryList}">
									<c:forEach var="inquery" items="${inqueryList}">
										<tr>
											<td class="inq_n"><a href="#">${inquery.inqNo}</a></td>
											<td id="inq_bd" class="inq_board">
										<c:choose>
											<c:when test="${inquery.inqCate == 1}">
												쇼핑
											</c:when>
											<c:when test="${inquery.inqCate == 2}">
												커뮤니티
											</c:when>
										</c:choose>
											</td>
											<td class="inq_title"><a href="${contextPath}/service/inqView.do?id=${log_id}&inqNo=${inquery.inqNo}">${inquery.inqTitle}</a></td>
											<td class="inq_date"><a href="#">${inquery.inqDate}</a></td>
											<td class="inq_status">
										<c:choose>
											<c:when test="${inquery.hasRe == 0}">
												답변 대기중
											</c:when>
											<c:when test="${inquery.hasRe == 1}">
												답변 완료
											</c:when>
										</c:choose>
											</td>
										</tr>
									</c:forEach>
								</c:when>
							</c:choose>
							</tbody>
						</table>
					</div>
					<!-- <button class="inquering btn_c" type="button" onclick="javascript:fn_inqForm()">문의하기</button> -->
					<a class="inquering btn_c" href="${contextPath}/service/inqForm.do">문의하기</a>
					<div class="paging_box">
						<c:set var="lastPage" value="${(totArticles mod 100)}"/>
						<ul>
						<c:if test="${totArticles != 0}">
							<c:choose>
								<c:when test="${totArticles > 100}">
									<c:forEach var="page" begin="1" end="${endPage}" step="1">
										<c:if test="${section > 1 && page == 1}">
											<!-- <a href="${contextPath}/board/listArticles.do?section=${section-1}&pageNum=10"> prev</a> -->
											<li><a href="${contextPath}/service/inqList.do?id=${log_id}&section=${section-1}&pageNum=${currentPage}"> prev</a></li>
										</c:if>
										<c:choose>
											<c:when test="${pageNum==page}">
												<li><a class="selPage" href="${contextPath}/service/inqList.do?id=${log_id}&section=${section}&pageNum=${page}">${(section-1)*10+page}</a></li>
												<c:set var="currentPage" value="${pageNum}" scope="application"/>
											</c:when>
											<c:otherwise>
												<li><a class="noLine" href="${contextPath}/service/inqList.do?id=${log_id}&section=${section}&pageNum=${page}">${(section-1)*10+page}</a></li>
											</c:otherwise>
										</c:choose>
										<c:if test="${page == 10 && totArticles/100>section}">
											<!-- <a href="${contextPath}/board/listArticles.do?section=${section+1}&pageNum=1"> next</a> -->
											<li><a href="${contextPath}/service/inqList.do?id=${log_id}&section=${section+1}&pageNum=${currentPage}"> next</a></li>
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
												<li><a class="selPage" href="${contextPath}/service/inqList.do?id=${log_id}&section=${section}&pageNum=${page}">${page}</a></li>
											</c:when>
											<c:otherwise>
												<li><a class="noLine" href="${contextPath}/service/inqList.do?id=${log_id}&section=${section}&pageNum=${page}">${page}</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:when>
							</c:choose>
						</c:if>
						</ul>
					</div>
				</div>
			</div>
			<!-- mypage_content -->
		</div>
	</div>
	<!-- content_mypage_e -->
	<jsp:include page="/views/fix/login.jsp"/>
	<!-- footer_s -->
	<jsp:include page="/views/fix/footer.jsp" />
	<!-- footer_e -->
	<script src="${contextPath}/views/js/login.js"></script>
    <script src="${contextPath}/views/js/naver_login.js"></script>
    <script src="${contextPath}/views/js/kakao_login.js"></script>
    <link rel="stylesheet" href="${contextPath}/views/css/login.css">
</body>
</html>