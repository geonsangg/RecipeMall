<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% request.setCharacterEncoding("utf-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="totOrders" value="${orderMap.totOrders}"/>
<c:set var="section" value="${orderMap.section}"/>
<c:set var="pageNum" value="${orderMap.pageNum}"/>
<c:set var="ep" value="${(totOrders mod 100)}"/>
<c:choose>
	<c:when test="${section > totOrders/100}">
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
	                <h2>주문 목록</h2>
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
	                    <table class="order_tbl tbl">
	                        <caption>주문 목록</caption>
	                        <tbody>
	                            <tr>
	                                <th>주문일</th>
	                                <th>이름</th>
	                                <th>주문코드</th>
	                                <th>상품이름</th>
	                                <th>상품개수</th>
	                                <th>총 주문금액</th>
	                                <th>주문상태</th>
	                                <th>결제상태</th>
	                            </tr>
                            	<c:forEach var="list" items="${orderMap.orderList}" >
									<tr>
	                                	<td>${list.orderDate}</td>
	                               		<td>${list.name}</td>
	                                	<td>${list.orderCode}</td>
	                                	<td>${list.prodName}</td>
	                                	<td>${list.orderAmount}</td>
	                                	<td>${list.allOrderPrice}</td>
	                                	<td>${list.orderStatus}</td>
	                                	<td>${list.paymentStatus}</td>
									</tr>
	                            </c:forEach>
	                        </tbody>
	                    </table>
	                    <%-- <div class="cud">
	                        <ul class="mi">
	                            <li><a href="${contextPath}/admin/productForm.do">등록</a></li>
	                        </ul>
	                    </div> --%>
	                    <div class="paging">
	                        <c:set var="lastPage" value="${(totOrders mod 100)}"/>
							<c:if test="${totOrders != 0}">
								<c:choose>
									<c:when test="${totOrders > 100}">
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
											<c:if test="${page == 10 && totOrders/100>section}">
												<!-- <a href="${contextPath}/board/listArticles.do?section=${section+1}&pageNum=1"> next</a> -->
												<a href="${contextPath}/admin/product.do?section=${section+1}&pageNum=1"> next</a>
											</c:if>
										</c:forEach>
									</c:when>
									<c:when test="${totOrders <= 100}">
										<c:if test="${(totOrders mod 10) == 0}">
											<c:set var="totProducts" value="${totOrders-1}"/>
										</c:if>
										<c:forEach var="page" begin="1" end="${totOrders/10+1}" step="1">
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