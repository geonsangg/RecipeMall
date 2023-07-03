<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setCharacterEncoding("utf-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="inqueryList" value="${inqueryMap.inqueryList}"/>
<c:set var="totInqueries" value="${inqueryMap.totInqueries}"/>
<c:set var="section" value="${inqueryMap.section}"/>
<c:set var="pageNum" value="${inqueryMap.pageNum}"/>
<c:set var="ep" value="${(totInqueries mod 100)}"/>
<c:choose>
	<c:when test="${section > totInqueries/100}">
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
    <link rel="stylesheet" href="${contextPath}/views/admin/css/view.css">
    <link rel="stylesheet" href="${contextPath}/views/admin/css/select.css">
</head>
<body>
    <div id="box">
    <c:choose>
    	<c:when test="${isAdmLogon == true}">
        <jsp:include page="/views/fix/adminSide.jsp"/>
            <!-- main content -->
			<div class="container">
                <h2>일대일 문의 관리</h2>
                <div class="userInq_list list">
                    <div class="userInq_search search">
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
                    <table class="userInq_tbl tbl">
                        <caption>자주하는 질문 목록</caption>
                        <tbody>
                            <tr>
                                <th>번호</th>
                                <th>분류</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>등록일</th>
                                <th>답변</th>
                            </tr>
                            <c:choose>
								<c:when test="${empty inqueryList}">
									<tr><td colspan="6">등록된 문의가 없습니다.</td></tr>
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
											<td class="inq_title"><a href="${contextPath}/admin/userInqAnswer.do?&inqNo=${inquery.inqNo}">${inquery.inqTitle}</a></td>
											<td>${inquery.id}</td>
											<td class="inq_date">${inquery.inqDate}</td>
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
                    <div class="paging">
                        <c:set var="lastPage" value="${(totInqueries mod 100)}"/>
						<c:if test="${totInqueries != 0}">
							<c:choose>
								<c:when test="${totInqueries > 100}">
									<c:forEach var="page" begin="1" end="${endPage}" step="1">
										<c:if test="${section > 1 && page == 1}">
											<!-- <a href="${contextPath}/board/listArticles.do?section=${section-1}&pageNum=10"> prev</a> -->
											<a href="${contextPath}/admin/userInqList.do?section=${section-1}&pageNum=${currentPage}"> prev</a>
										</c:if>
										<c:choose>
											<c:when test="${pageNum==page}">
												<a class="selPage" href="${contextPath}/admin/userInqList.do?section=${section}&pageNum=${page}">${(section-1)*10+page}</a>
												<c:set var="currentPage" value="${pageNum}" scope="application"/>
											</c:when>
											<c:otherwise>
												<a class="noLine" href="${contextPath}/admin/userInqList.do?section=${section}&pageNum=${page}">${(section-1)*10+page}</a>
											</c:otherwise>
										</c:choose>
										<c:if test="${page == 10 && totInqueries/100>section}">
											<!-- <a href="${contextPath}/board/listArticles.do?section=${section+1}&pageNum=1"> next</a> -->
											<a href="${contextPath}/admin/userInqList.do?section=${section+1}&pageNum=${currentPage}"> next</a>
										</c:if>
									</c:forEach>
								</c:when>
								<c:when test="${totInqueries <= 100}">
									<c:if test="${(totInqueries mod 10) == 0}">
										<c:set var="totInqueries" value="${totInqueries-1}"/>
									</c:if>
									<c:forEach var="page" begin="1" end="${totInqueries/10+1}" step="1">
										<c:choose>
											<c:when test="${page == pageNum}">
												<a class="selPage" href="${contextPath}/admin/userInqList.do?section=${section}&pageNum=${page}">${page}</a>
											</c:when>
											<c:otherwise>
												<a class="noLine" href="${contextPath}/admin/userInqList.do?section=${section}&pageNum=${page}">${page}</a>
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