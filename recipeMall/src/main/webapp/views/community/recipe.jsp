<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="recipeList" value="${recipeMap.recipeList}" />
<c:set var="totRecipe" value="${recipeMap.totRecipe}" />
<c:set var="section" value="${recipeMap.section}" />
<c:set var="pageNum" value="${recipeMap.pageNum}" />
<%
String query = request.getQueryString();
if (query != null) {
	int index = query.indexOf("&section=");
	if (index != -1) {
		query = query.substring(0, index);
		System.out.println("잘라낸 쿼리 : " + query);
	}
}
%>
<c:set var="tot100" value="${totArticles % 120}" />

<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>recipeMall</title>
<script src="https://kit.fontawesome.com/54880b83c5.js"
	crossorigin="anonymous"></script>

<link rel="stylesheet" href="${contextPath}/views/css/common.css">
<link rel="stylesheet"
	href="${contextPath}/views/community/css/recipe.css">

<!-- 공통 -->
<script src="${contextPath}/views/js/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/views/js/jquery-ui.min.js"></script>
<script src="${contextPath}/views/js/login.js"></script>
<script src="${contextPath}/views/js/naver_login.js"></script>
<script src="${contextPath}/views/js/kakao_login.js"></script>
<link rel="stylesheet" href="${contextPath}/views/css/login.css">
<script src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>
<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.1.0/kakao.min.js" integrity="sha384-dpu02ieKC6NUeKFoGMOKz6102CLEWi9+5RQjWSV0ikYSFFd8M3Wp2reIcquJOemx"
	crossorigin="anonymous"></script>
</head>
<body>
	<!--[s]전체 컨텐츠 영역  -->
	<!--[s]건너뛰기 링크-->
	<a id="skipNav" href="">본문 바로가기</a>
	<!--[e]건너뛰기 링크-->

	<!-- [s]Header 영역 -->
	<jsp:include page="/views/fix/indexHeader.jsp" />
	<!-- [e]Header 영역 -->

	<!-- [s]main_content 영역 -->
	<div id="main_content">

		<!-- [s]레시피 바로가기 영역-->
		<section class="direct_recipe">

			<div class="direct_menu_area flex_area_rsb">
				<div class="direct_menu" value="ko" onclick="toCate(this)">
					<img src="${contextPath}/views/images/disah_ko.png" alt="한식">
					<p>한식</p>
				</div>

				<div class="direct_menu" value="ch" onclick="toCate(this)">
					<img src="${contextPath}/views/images/dish_ch.png" alt="중식">
					<p>중식</p>
				</div>
				<div class="direct_menu" value="us" onclick="toCate(this)">
					<img src="${contextPath}/views/images/dish_us.png" alt="양식">
					<p>양식</p>
				</div>
				<div class="direct_menu" value="ja" onclick="toCate(this)">
					<img src="${contextPath}/views/images/dish_ja.png" alt="일식">
					<p>일식</p>
				</div>
				<div class="direct_menu" value="bun" onclick="toCate(this)">
					<img src="${contextPath}/views/images/dish_bun.png" alt="분식">
					<p>분식</p>
				</div>
				<div class="direct_menu" value="ba" onclick="toCate(this)">
					<img src="${contextPath}/views/images/dish_ba.png" alt="베이커리">
					<p>베이커리</p>
				</div>

			</div>
		</section>
		<!-- [e]레시피 바로가기 영역 -->

		<!-- [s]레시피 검색 영역 -->
		<section class="r_search_area ">
			<form action="${contextPath}/recipe/recipe.do">
				<fieldset class="flex_area_rsb">
					<div class="select_searchbox flex_area_rsb">
						<select name="sel_search" id="sel_search">
							<option value="search_title">제목</option>
							<option value="search_id">셰프</option>
						</select>
						<div class="r_input_btn flex_area_rsb">
							<input name="recipeTitle" type="search" title="검색어 입력"
								maxlength="255">
							<div class="r_btn_search">
								<i class="fa-solid fa-magnifying-glass"></i>
							</div>
						</div>
					</div>

				</fieldset>
			</form>
		</section>
		<!-- [e]레시피 검색 영역 -->


		<!-- [s]레시피 리스트 영역 -->
		<section class="r_list">
			<ul class="r_content_area">
				<c:choose>
					<c:when test="${empty recipeList}">
						<div class="norecipe">검색된 레시피가 없습니다</div>
					</c:when>
					<c:when test="${!empty recipeList}">
						<c:forEach var="recipe" items="${recipeList}">
							<li class="r_content"><a
								href="${contextPath}/recipe/specific.do?recipeNo=${recipe.recipeNo}">
									<div class="r_thumb">
										<img
											src="${contextPath}/download.do?imgName=${recipe.imgVO.imgName}&recipeNo=${recipe.recipeNo}"
											width="100%" height="100%">
									</div>
									<div class="r_title_area">
										<p class="r_title">${recipe.recipeTitle}</p>
										<div class="cnt_like_area">
											<span class="cnt_like">${recipe.cntLike}</span> <i
												class="fa-regular fa-heart"></i>
										</div>
										<p class="r_id">${recipe.id}</p>
									</div>
							</a></li>
						</c:forEach>
					</c:when>
				</c:choose>
			</ul>
		</section>

		<!-- [e]레시피 리스트 영역 -->
		<!-- [s]pagnation  -->
		<div class="pagnation_area">
			<c:if test="${totRecipe != 0}">
				<c:choose>
					<c:when test="${totRecipe > 120}">
						<c:forEach var="page" begin="1" end="${endValue}" step="1">
							<c:if test="${section > 1 && page == 1}">
								<!-- 1번 앞에만 생성되게 하기위해 -->
								<a
									href="${contextPath}/recipe/recipe.do?section=${section-1}&pageNum=${(section-1)*12}">
									&lt;</a>
							</c:if>
							<c:choose>
								<c:when test="${page == pageNum}">
									<a class="selPage"
										href="${contextPath}/recipe/recipe.do?<%=query%>&section=${section}&pageNum=${page}">${page}</a>
								</c:when>
								<c:otherwise>
									<a class="noLine"
										href="${contextPath}/recipe/recipe.do?<%=query%>&section=${section}&pageNum=${page}">${page}</a>
								</c:otherwise>
							</c:choose>
							<c:if test="${page==10 && totmatarial/120 > section}">
								<a
									href="${contextPath}/recipe/recipe.do?section=${section+1}&pageNum=1">
									&gt;</a>
							</c:if>
						</c:forEach>
					</c:when>
					<c:when test="${totRecipe <= 120}">
						<c:if test="${(totRecipe % 12) == 0}">
							<c:set var="totRecipe" value="${totRecipe-1}" />
						</c:if>
						<c:forEach var="page" begin="1" end="${totRecipe/12+1}" step="1">
							<c:choose>
								<c:when test="${page == pageNum}">
									<a class="selPage"
										href="${contextPath}/recipe/recipe.do?<%=query%>&section=${section}&pageNum=${page}">${page}</a>
								</c:when>
								<c:otherwise>
									<a class="noLine"
										href="${contextPath}/recipe/recipe.do?<%=query%>&section=${section}&pageNum=${page}">${page}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:when>
				</c:choose>
			</c:if>
		</div>
		<!-- [e]pagnation  -->

	</div>
	<!-- [e]main_content 영역 -->
	<!-- [s]footer 영역 -->
	<jsp:include page="/views/fix/footer.jsp" />
	<!-- [e]footer 영역 -->
	
	<jsp:include page="/views/fix/login.jsp" />

	<script src="${contextPath}/views/js/common.js"></script>
	<script src="${contextPath}/views/community/js/recipe.js"></script>
</body>

</html>