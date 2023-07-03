<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setCharacterEncoding("utf-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
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
<link rel="stylesheet" href="${contextPath}/views/admin/css/view.css">
</head>
<body>
	<div id="box">
    <c:choose>
    	<c:when test="${isAdmLogon == true}">
        <jsp:include page="/views/fix/adminSide.jsp"/>
            <!-- main content -->
			<div class="container">
                <h2>공지사항 > 상세보기</h2>
				<div class="notice_v">
					<div class="notice_d">
						<table class="notice_v_tbl">
							<caption>공지사항 상세화면</caption>
							<tbody>
								<tr class="n_v_tr1">
									<th>제목</th>
									<td>${notice.noticeTitle}</td>
									<th>등록일</th>
									<td>${notice.noticeDate}</td>
								</tr>
								<tr>
									<td class="notice_c" colspan="4" style="word-break: break-all;">
										<div class="min" style="word-break: inherit;">
											${notice.noticeContent}
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="btn_a">
						<button type="button" class="btn list_btn" onclick="location.href='${contextPath}/admin/noticeList.do'">목록</button>
						<button type="button" class="btn update_btn">
							<a href="${contextPath}/admin/modNotice.do?noticeNo=${notice.noticeNo}">수정</a></button>
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