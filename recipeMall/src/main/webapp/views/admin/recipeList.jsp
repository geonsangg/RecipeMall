<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setCharacterEncoding("utf-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>   
<c:set var="recipeList" value="${recipeMap.recipeList}"/>
<c:set var="totRecipe" value="${recipeMap.totRecipe}"/>
<c:set var="section" value="${recipeMap.section}"/>
<c:set var="pageNum" value="${recipeMap.pageNum}"/>
<c:set var="tot100" value="${totArticles % 120}"/>
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
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RecipeMall-관리자</title>
    <script src="https://kit.fontawesome.com/fd4a2f2bab.js" crossorigin="anonymous"></script>
    <script src="${contextPath}/views/js/jquery-3.6.4.min.js"></script>
    <script src="${contextPath}/views/js/jquery-ui.min.js"></script>
	<!-- <script src="js/admin.js"></script> -->
    <link rel="stylesheet" href="${contextPath}/views/admin/css/admin.css">
    <link rel="stylesheet" href="${contextPath}/views/admin/css/select.css">
    <link rel="stylesheet" href="${contextPath}/views/community/css/recipe.css">
</head>
<body>
    <div id="box">
   	<c:choose>
    	<c:when test="${isAdmLogon == true}">
        <jsp:include page="/views/fix/adminSide.jsp"/>
            <!-- main content -->
			<div class="container">
                <h2>게시판 관리</h2>
                <div class="board_list list">
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
                    <div class="cud">
                        <ul class="mi">
                            <li><a href="">등록</a></li>
                            <li><a href="">수정</a></li>
                            <li><a href="">삭제</a></li>
                        </ul>
                    </div>
                    <div class="paging">
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
                </div>
            </div>
            <!-- main content -->
        </div>
        </c:when>
    	<c:otherwise>
    		<jsp:forward page="/views/admin/adminLogin.jsp"/>
    	</c:otherwise>
    </c:choose>
    </div>
</body>
</html>