<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<% request.setCharacterEncoding("utf-8"); %>
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
    <link rel="stylesheet" href="${contextPath}/views/admin/css/inUp.css">
<title>Insert title here</title>
</head>
<body>
	<div id="box">
	<c:choose>
    	<c:when test="${isAdmLogon == true}">
        <jsp:include page="/views/fix/adminSide.jsp"/>
            <!-- main content -->
			<div class="container">
                <h2>자주하는 질문 관리 > 수정</h2>
				<div class="list">
					<form id="frm_notice" action="${contextPath}/admin/updateNotice.do" method="post">
						<input type="hidden" name="adminId" id="" value="${log_adminId}">
						<input type="hidden" name="noticeNo" value="${notice.noticeNo}">
						<table class="notice_in_tbl">
							<tbody>
								<tr>
									<th>제목</th>
									<td><input type="text" name="noticeTitle" value="${notice.noticeTitle}"></td>
								</tr>
								<tr>
									<th>내용</th>
									<td><textarea from="frm_notice" name="noticeContent">${notice.noticeContent}</textarea></td>
								</tr>
							</tbody>
						</table>
						<div class="form_btn">
							<input type="submit" value="수정">
							<input type="button" value="취소" onclick="history.back(-1);">
						</div>
					</form>
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