<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setCharacterEncoding("utf-8"); %>
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
                <h2>공지사항 관리</h2>
                <div class="notice_list list">
                    <div class="notice_search search">
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
                    <table class="notice_tbl tbl">
                        <caption>자주하는 질문 목록</caption>
                        <tbody>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>등록일</th>
                                <th></th>
                            </tr>
                            <c:choose>
								<c:when test="${empty noticeList}">
									<tr>
		                                <td colspan="4">등록된 공지사항이 없습니다.</td>
		                            </tr>
								</c:when>
								<c:when test="${!empty noticeList}">
									<c:forEach var="notice" items="${noticeList}">
										<tr>
			                                <td>${notice.noticeNo}</td>
			                                <td><a href="${contextPath}/admin/noticeView.do?noticeNo=${notice.noticeNo}">${notice.noticeTitle}</a></td>
			                                <td>${notice.adminId }</td>
			                                <td>${notice.noticeDate}</td>
			                                <td><a href="${contextPath}/admin/delNotice.do?noticeNo=${notice.noticeNo}">삭제</a></td>
			                            </tr>
									</c:forEach>
								</c:when>
							</c:choose>
                        </tbody>
                    </table>
                    <div class="cud">
                        <ul class="mi">
                            <li><a href="${contextPath}/admin/noticeForm.do">등록</a></li>
                            <!-- <li><a href="">수정</a></li>
                            <li><a href="">삭제</a></li> -->
                        </ul>
                    </div>
                    <div class="paging">
                        <c:set var="lastPage" value="${(totArticles mod 100)}"/>
						<c:if test="${totArticles != 0}">
							<c:choose>
								<c:when test="${totArticles > 100}">
									<c:forEach var="page" begin="1" end="${endPage}" step="1">
										<c:if test="${section > 1 && page == 1}">
											<!-- <a href="${contextPath}/board/listArticles.do?section=${section-1}&pageNum=10"> prev</a> -->
											<a href="${contextPath}/admin/noticeList.do?section=${section-1}&pageNum=${currentPage}"> prev</a>
										</c:if>
										<c:choose>
											<c:when test="${pageNum==page}">
												<a class="selPage" href="${contextPath}/admin/noticeList.do?section=${section}&pageNum=${page}">${(section-1)*10+page}</a>
												<c:set var="currentPage" value="${pageNum}" scope="application"/>
											</c:when>
											<c:otherwise>
												<a class="noLine" href="${contextPath}/admin/noticeList.do?section=${section}&pageNum=${page}">${(section-1)*10+page}</a>
											</c:otherwise>
										</c:choose>
										<c:if test="${page == 10 && totArticles/100>section}">
											<!-- <a href="${contextPath}/board/listArticles.do?section=${section+1}&pageNum=1"> next</a> -->
											<a href="${contextPath}/admin/noticeList.do?section=${section+1}&pageNum=${currentPage}"> next</a>
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
												<a class="selPage" href="${contextPath}/admin/noticeList.do?section=${section}&pageNum=${page}">${page}</a>
											</c:when>
											<c:otherwise>
												<a class="noLine" href="${contextPath}/admin/noticeList.do?section=${section}&pageNum=${page}">${page}</a>
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