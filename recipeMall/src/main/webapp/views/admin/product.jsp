<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setCharacterEncoding("utf-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="prodList" value="${productMap.prodList}"/>
<c:set var="totProducts" value="${productMap.totProducts}"/>
<c:set var="section" value="${productMap.section}"/>
<c:set var="pageNum" value="${productMap.pageNum}"/>
<c:set var="ep" value="${(totProducts mod 100)}"/>
<c:choose>
	<c:when test="${section > totProducts/100}">
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
    <title>RecipeMall-관리자</title>
    <script src="https://kit.fontawesome.com/fd4a2f2bab.js" crossorigin="anonymous"></script>
    <script src="${contextPath}/views/js/jquery-3.6.4.min.js"></script>
    <script src="${contextPath}/views/js/jquery-ui.min.js"></script>
	<!-- <script src="js/admin.js"></script> -->
    <link rel="stylesheet" href="${contextPath}/views/admin/css/admin.css">
    <link rel="stylesheet" href="${contextPath}/views/admin/css/select.css">
</head>
<body>
    <div id="box">
    <c:choose>
    	<c:when test="${isAdmLogon == true}">
        <jsp:include page="/views/fix/adminSide.jsp"/>
            <!-- main content -->
			<div class="container">
                <h2>상품 목록</h2>
                <div class="prod_list list">
                    <div class="prod_search search">
                        <form class="" action="" method="get">
                            <select name="" id="">
                                <option value="">제목</option>
                                <option value="">내용</option>
                                <option value="">제목 내용</option>
                            </select>
                            <input type="search">
                            <input type="submit" name="" id="" value="검색">
                        </form>
                    </div>
                    <table class="prod_tbl tbl">
                        <caption>상품 목록</caption>
                        <tbody>
                            <tr>
                                <th>카테고리</th>
                                <th>이름</th>
                                <th>가격</th>
                                <th>재고</th>
                                <!-- <th>등록일</th> -->
                                <th>수정</th>
                                <th>삭제</th>
                            </tr>
                       	<c:forEach var="prod" items="${prodList}">
                            <tr>
                                <td>
                               	<c:choose>
                               		<c:when test="${prod.prodKind == 1}">채소/과일</c:when>
                               		<c:when test="${prod.prodKind == 2}">정육</c:when>
                               		<c:when test="${prod.prodKind == 3}">해산물</c:when>
                               		<c:when test="${prod.prodKind == 4}">면/양념</c:when>
                               		<c:when test="${prod.prodKind == 5}">베이커리</c:when>
                               		<c:when test="${prod.prodKind == 7}">기타</c:when>
                               	</c:choose>
                                </td>
                                <td><a href="">${prod.prodName}</a></td>
                                <td>${prod.prodPrice}원</td>
                                <td>${prod.prodRest}</td>
                                <td><a href="${contextPath}/admin/productForm.do?prodCode=${prod.prodCode}">수정</a></td>
                                <td><a href="${contextPath}/admin/delProduct.do?prodCode=${prod.prodCode}">삭제</a></td>
                            </tr>
                       	</c:forEach>
                        </tbody>
                    </table>
                    <div class="cud">
                        <ul class="mi">
                            <li><a href="${contextPath}/admin/productForm.do">등록</a></li>
                        </ul>
                    </div>
                    <div class="paging">
                        <c:set var="lastPage" value="${(totProducts mod 100)}"/>
						<c:if test="${totProducts != 0}">
							<c:choose>
								<c:when test="${totProducts > 100}">
									<c:forEach var="page" begin="1" end="${endPage}" step="1">
										<c:if test="${section > 1 && page == 1}">
											<!-- <a href="${contextPath}/board/listArticles.do?section=${section-1}&pageNum=10"> prev</a> -->
											<a href="${contextPath}/admin/product.do?section=${section-1}&pageNum=${currentPage}"> prev</a>
										</c:if>
										<c:choose>
											<c:when test="${pageNum==page}">
												<a class="selPage" href="${contextPath}/admin/product.do?section=${section}&pageNum=${page}">${(section-1)*10+page}</a>
												<c:set var="currentPage" value="${pageNum}" scope="application"/>
											</c:when>
											<c:otherwise>
												<a class="noLine" href="${contextPath}/admin/product.do?section=${section}&pageNum=${page}">${(section-1)*10+page}</a>
											</c:otherwise>
										</c:choose>
										<c:if test="${page == 10 && totProducts/100>section}">
											<!-- <a href="${contextPath}/board/listArticles.do?section=${section+1}&pageNum=1"> next</a> -->
											<a href="${contextPath}/admin/product.do?section=${section+1}&pageNum=1"> next</a>
										</c:if>
									</c:forEach>
								</c:when>
								<c:when test="${totProducts <= 100}">
									<c:if test="${(totProducts mod 10) == 0}">
										<c:set var="totProducts" value="${totProducts-1}"/>
									</c:if>
									<c:forEach var="page" begin="1" end="${totProducts/10+1}" step="1">
										<c:choose>
											<c:when test="${page == pageNum}">
												<a class="selPage" href="${contextPath}/admin/product.do?section=${section}&pageNum=${page}">${page}</a>
											</c:when>
											<c:otherwise>
												<a class="noLine" href="${contextPath}/admin/product.do?section=${section}&pageNum=${page}">${page}</a>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:when>
							</c:choose>
						</c:if>
                    </div>
                </div>
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