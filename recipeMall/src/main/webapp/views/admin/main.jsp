<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<% request.setCharacterEncoding("utf-8"); %>
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
				<div class="container main_c">
					<div class="new_today new">
						<div class="c_title">
							오늘 올라온 것
						</div>
						<div class="s_box today">
							<ul>
								<li>새 주문</li>
								<li>배송대기</li>
								<li>답변대기 문의</li>
							</ul>
						</div>
					</div>
					<div class="new_review new">
						<div class="c_title">
							리뷰
						</div>
						<div class="s_box inq">
							<ul>
								<li>리뷰1</li>
								<li>리뷰2</li>
								<li>리뷰3</li>
							</ul>
						</div>
					</div>
					<div class="new_board new">
						<div class="c_title">
							게시판
						</div>
						<div class="s_box post">
							<ul>
								<c:set var="freeList" value="${indexMap.freeList}"/>
								<c:forEach var="free" items="${freeList}">
									<li>
										<div class="inql m_list">
											<div class="inq_title">
												<a href="${contextPath}/admin/userInqAnswer.do?boardNo=${free.boardNo}">${free.boardName}</a>
											</div>
											<div class="inq_info">
												<span>${free.id}</span>
												<span>${free.writeDate}</span>
											</div>
										</div>
										<a></a>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
					<div class="new_inquery new">
						<div class="c_title">
							문의
						</div>
						<div class="s_box inq">
							<ul>
							<c:set var="inqList" value="${indexMap.inqList}"/>
							<c:forEach var="inquery" items="${inqList}">
								<li>
									<div class="inql m_list">
										<div class="inq_title">
											<a href="${contextPath}/admin/userInqAnswer.do?inqNo=${inquery.inqNo}">${inquery.inqTitle}</a>
										</div>
										<div class="inq_info">
											<span>${inquery.id}</span>
											<span>${inquery.inqDate}</span>
										</div>
									</div>
								</li>
							</c:forEach>
							</ul>
						</div>
					</div>
				</div>
	        </div>
    	</c:when>
    	<c:otherwise>
    		<jsp:forward page="/views/admin/adminLogin.jsp"/>
    	</c:otherwise>
    </c:choose>
    </div>
</body>
</html>