<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("utf-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="totReplies" value="${posts.totArticles}"/>
<c:set var="section" value="${posts.section}"/>
<c:set var="pageNum" value="${posts.pageNum}"/>
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
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage.css">
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage_common.css">
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage_4.css">
<link rel="shortcut icon" href="${contextPath}/views/image/smalllogo.png" />
</head>
<body>
	<!--[s]건너뛰기 링크-->
	<a id="skipNav" href="">본문 바로가기</a>
	<!--[e]건너뛰기 링크-->
	<!-- [s]Header 영역 -->
	<!-- [s]Header 영역 -->
	<jsp:include page="/views/fix/mypageHeader.jsp"/>
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
				<h3 class="menu_title">글 관리</h3>
				<div class="post">
					<div class="post_select">
						<ul class="post_s">
							<li>
								<div class="post_class">
									<ul class="post_menu">
										<li class="board_all menu_li active"><a href="#">전체</a></li>
										<li class="board_recipe menu_li"><a class="active_a" href="#">레시피</a></li>
										<li class="board_free menu_li"><a href="#">자유게시판</a></li>
									</ul>
								</div>
							</li>
							<li>
								<div class="post_date_s post_h">
									<ul>
										<li><a href="#">처음</a></li>
										<li><a href="#">최신</a></li>
									</ul>
								</div>
							</li>
							<li>
								<div class="post_cnt post_h">
									<select name="" id="">
										<option value="">10개</option>
										<option value="">30개</option>
									</select>
								</div>
							</li>
							<li><button class="post_view_btn">보기</button></li>
						</ul>
					</div>
					<div class="my_post_list">
						<table class="post_list_tb">
							<thead>
								<tr>
									<th>번호</th>
									<th>게시판</th>
									<th>제목</th>
									<th>작성일</th>
									<th>삭제</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="free" items="${posts.free}" varStatus="status">
									<tr>
										<td class="post_n">${status.count}</td>
										<td class="post_board">
											자유게시판
										</td>
										<td class="post_title"><a href="${contextPath}/free/view.do?boardNo=${free.boardNo}">${free.boardName}</a></td>
										<td class="post_date">${free.writeDate}</td>
										<td class="post_chk">삭제</td>
									</tr>
								</c:forEach>
								<c:forEach var="recipe" items="${posts.recipe}" varStatus="status">
									<tr>
										<td class="post_n">${status.count}</td>
										<td class="post_board">
											레시피
										</td>
										<td class="post_title"><a href="${contextPath}/recipe/specific.do?id=${log_id}&recipeNo=${recipe.recipeNo}">${recipe.recipeTitle}</a></td>
										<td class="post_date">${recipe.writeDate}</td>
										<td class="post_chk">삭제</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- <button class="update_btn">수정</button>
					<button class="delete_btn">삭제</button> -->
					<div class="page">
						<c:set var="lastPage" value="${(totArticles mod 100)}"/>
						<c:if test="${totArticles != 0}">
							<c:choose>
								<c:when test="${totArticles > 100}">
									<c:forEach var="page" begin="1" end="${endPage}" step="1">
										<c:if test="${section > 1 && page == 1}">
											<!-- <a href="${contextPath}/board/listArticles.do?section=${section-1}&pageNum=10"> prev</a> -->
											<a href="${contextPath}/mypage/myPost.do?section=${section-1}&pageNum=${currentPage}"> prev</a>
										</c:if>
										<c:choose>
											<c:when test="${pageNum==page}">
												<a class="selPage" href="${contextPath}/mypage/myPost.do?section=${section}&pageNum=${page}">${(section-1)*10+page}</a>
												<c:set var="currentPage" value="${pageNum}" scope="application"/>
											</c:when>
											<c:otherwise>
												<a class="noLine" href="${contextPath}/mypage/myPost.do?section=${section}&pageNum=${page}">${(section-1)*10+page}</a>
											</c:otherwise>
										</c:choose>
										<c:if test="${page == 10 && totArticles/100>section}">
											<!-- <a href="${contextPath}/board/listArticles.do?section=${section+1}&pageNum=1"> next</a> -->
											<a href="${contextPath}/mypage/myPost.do?section=${section+1}&pageNum=${currentPage}"> next</a>
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
												<a class="selPage" href="${contextPath}/mypage/myPost.do?section=${section}&pageNum=${page}">${page}</a>
											</c:when>
											<c:otherwise>
												<a class="noLine" href="${contextPath}/mypage/myPost.do?section=${section}&pageNum=${page}">${page}</a>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:when>
							</c:choose>
						</c:if>
					</div>
				</div>
			</div>
			<!-- mypage_content -->
		</div>
	</div>
	<!-- content_mypage_e -->

	<!-- footer_s -->
	<jsp:include page="/views/fix/footer.jsp"/>
	<!-- footer_e -->
</body>
</html>