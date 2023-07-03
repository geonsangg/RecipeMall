<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="matarialList" value="${matarialMap.matarialList}" />
<c:set var="totmatarial" value="${matarialMap.totmatarial}" />
<c:set var="section" value="${matarialMap.section}" />
<c:set var="pageNum" value="${matarialMap.pageNum}" />
<c:set var="tot100" value="${totmatarial % 90}" />
<c:set var="recipeList" value="${matarialMap.recipeList}"/>
<c:choose>
	<c:when test="${section > totmatarial/90}">
		<c:set var="endValue"
			value="${(tot100%9) == 0? tot100/9 : tot100/9+1}" />
	</c:when>
	<c:otherwise>
		<c:set var="endValue" value="10" />
	</c:otherwise>
</c:choose>
<%
request.setCharacterEncoding("utf-8");
%>
<%
String query = request.getQueryString();
if (query != null) {
	int index = query.indexOf('&');
	if (index != -1) {
		query = query.substring(0, index);
	}
}
%>
<!DOCTYPE html>

<html>
<head>
<script src="https://kit.fontawesome.com/54880b83c5.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="${contextPath}/views/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="${contextPath}/views/js/jquery-ui.min.js"></script>
<link rel="stylesheet" href="${contextPath}/views/shopping/css/shopping_common.css">
<link rel="stylesheet" href="${contextPath}/views/shopping/css/matarial.css">
<link rel="shortcut icon" href="${contextPath}/views/images/smalllogo.png" />
<script type="text/javascript" src="${contextPath}/views/js/login.js"></script>
<script type="text/javascript" src="${contextPath}/views/js/naver_login.js"></script>
<script type="text/javascript" src="${contextPath}/views/js/kakao_login.js"></script>
<link rel="stylesheet" href="${contextPath}/views/css/login.css">
<script type="text/javascript" src="${contextPath}/views/shopping/js/matarial.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>
<script type="text/javascript"
	src="https://t1.kakaocdn.net/kakao_js_sdk/2.1.0/kakao.min.js"
	integrity="sha384-dpu02ieKC6NUeKFoGMOKz6102CLEWi9+5RQjWSV0ikYSFFd8M3Wp2reIcquJOemx"
	crossorigin="anonymous"></script>
<title>recipeMall</title>
</head>
<body>
	<!--[s]전체 컨텐츠 영역  -->
	<!--[s]건너뛰기 링크-->
	<a id="skipNav" href="">본문 바로가기</a>
	<!--[e]건너뛰기 링크-->

	<!-- [s]광고배너 영역 -->
	<div class="adbanner">
		<p>
			<a href="#">지금 가입하면 무료배송!
				<button class="banner_btn" onclick="close_div()">
					<i class="fa-solid fa-x"></i>
				</button>
			</a>
		</p>
	</div>
	<!--[e]광고배너 영역 -->


	<!-- [s]Header 영역 -->
	<jsp:include page="/views/fix/shopHeader.jsp"/>
	<!-- [e]Header 영역 -->

	<!-- [s]메인 컨텐츠 영역  -->
	<div id="main_content">
		<div class="matarial_main">
			<!-- [s]메인 카테고리 영역 -->
			<div class="main_category">
				<h2>카테고리</h2>
				<ul>
					<li><a href="${contextPath}/shopping/matarial.do?prodKind=1">채소/과일</a></li>
					<li><a href="${contextPath}/shopping/matarial.do?prodKind=2">정육</a></li>
					<li><a href="${contextPath}/shopping/matarial.do?prodKind=3">해산물</a></li>
					<li><a href="${contextPath}/shopping/matarial.do?prodKind=4">면/양념</a></li>
					<li><a href="${contextPath}/shopping/matarial.do?prodKind=5">베이커리</a></li>
					<li>기타</li>
					<li><a href="${contextPath}/shopping/matarial.do?prodKind=6">견과류</a></li>
					<li><a href="${contextPath}/shopping/matarial.do?prodKind=7">유제품</a></li>
				</ul>
			</div>
			<!-- [e]메인 카테고리 영역 -->

			<!-- [s]메인 오늘의 레시피 영역 -->
			<div class="today_recipe">
                <h2>오늘의 레시피</h2>
                <ul>
                    <c:forEach var="list" begin="0" end="2" items="${recipeList}">
                       <li>
                           <a href="${contextPath}/recipe/specific.do?recipeNo=${list.recipeNo}">
                               <img src="${contextPath}/download.do?imgName=${list.imgVO.imgName}&recipeNo=${list.recipeNo}" alt="레시피 베스트 이미지" width="100%" height="100%">
                           </a>
                           <a href="${contextPath}/recipe/specific.do?recipeNo=${list.recipeNo}">${list.recipeTitle}</a>
                       </li>   
                   </c:forEach>
                </ul>
            </div>
			<!-- [e]메인 오늘의 레시피 영역 -->

			<!-- [s]재료 검색 영역-->
			<div class="form_matarial">
				<form action="${contextPath}/shopping/matarial.do" method="get">
					<input type="text" name="prodName" placeholder="재료검색">
					<div class="btn_search mat_search">
						<i class="fa-solid fa-magnifying-glass"></i>
					</div>
				</form>
			</div>
			<!-- [e]재료 검색 영역 -->

			<!-- [s]재료 컨텐츠 영역 -->
			<div class="matarial_content">
				<ul>
					<c:choose>
						<c:when test="${empty matarialList}">
							<li>검색된 재료가없습니다.</li>
						</c:when>
						<c:when test="${!empty matarialList}">
							<c:forEach var="matarial" items="${matarialList}">
								<li><a
									href="${contextPath}/shopping/product_info.do?prodCode=${matarial.prodCode}">
										<img src="${contextPath}/views/shopping/productImages/${matarial.prodImage}"
										alt="이미지">
								</a> <a
									href="${contextPath}/shopping/product_info.do?prodCode=${matarial.prodCode}">${matarial.prodName}</a>
									<a
									href="${contextPath}/shopping/product_info.do?prodCode=${matarial.prodCode}">${matarial.prodPrice}</a>
									<a
									href="${contextPath}/shopping/product_info.do?prodCode=${matarial.prodCode}">★${matarial.avgStar}
										리뷰${matarial.reviewCnt}</a></li>
							</c:forEach>
						</c:when>
					</c:choose>
				</ul>
			</div>
			<!-- [e]재료 컨텐츠 영역 -->

			<!-- 페이지 넘기기 영역 -->
			<div class="page_number">
				<c:if test="${totmatarial != 0}">
					<c:choose>
						<c:when test="${totmatarial > 90}">
							<c:forEach var="page" begin="1" end="${endValue}" step="1">
								<c:if test="${section > 1 && page == 1}">
									<!-- 1번 앞에만 생성되게 하기위해 -->
									<a
										href="${contextPath}/shopping/matarial.do?<%=query%>section=${section-1}&pageNum=${(section-1)*9}">
										&lt;</a>
								</c:if>
								<c:choose>
									<c:when test="${page == pageNum}">
										<a class="selPage"
											href="${contextPath}/shopping/matarial.do?<%=query%>&section=${section}&pageNum=${page}">${(section-1)*9+page}</a>
									</c:when>
									<c:otherwise>
										<a class="noLine"
											href="${contextPath}/shopping/matarial.do?<%=query%>&section=${section}&pageNum=${page}">${(section-1)*9+page}</a>
									</c:otherwise>
								</c:choose>
								<c:if test="${page==10 && totmatarial/90 > section}">
									<a
										href="${contextPath}/shopping/matarial.do?section=${section+1}&pageNum=1">
										&gt;</a>
								</c:if>
							</c:forEach>
						</c:when>
						<c:when test="${totmatarial <= 90}">
							<c:if test="${(totmatarial % 9) == 0}">
								<c:set var="totmatarial" value="${totmatarial-1}" />
							</c:if>
							<c:forEach var="page" begin="1" end="${totmatarial/9+1}" step="1">
								<c:choose>
									<c:when test="${page == pageNum}">
										<a class="selPage"
											href="${contextPath}/shopping/matarial.do?<%=query%>&section=${section}&pageNum=${page}">${page}</a>

									</c:when>
									<c:otherwise>
										<a class="noLine"
											href="${contextPath}/shopping/matarial.do?<%=query%>&section=${section}&pageNum=${page}">${page}</a>

									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:when>
					</c:choose>
				</c:if>
			</div>
		</div>
	</div>
	<!-- [e]메인 컨텐츠 영역  -->

	<!-- [s]footer 영역 -->
	<jsp:include page="/views/fix/footer.jsp"/>
	<!-- [e]footer 영역 -->
	<jsp:include page="/views/fix/login.jsp"/>
</body>
<script type="text/javascript"
	src="${contextPath}/views/shopping/js/shopping_common.js"></script>
</html>