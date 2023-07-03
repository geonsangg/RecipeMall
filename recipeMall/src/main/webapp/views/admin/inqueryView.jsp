<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("utf-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
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
<link rel="stylesheet" href="${contextPath}/views/admin/css/view.css">
<link rel="shortcut icon" href="${contextPath}/views/images/smalllogo.png" />
</head>
<body>
	<div id="box">
    <c:choose>
    	<c:when test="${isAdmLogon == true}">
        <jsp:include page="/views/fix/adminSide.jsp"/>
            <!-- main content -->
			<div class="container">
                <h2>일대일 문의 관리 > 답변</h2>
                <div class="inq_v">
                    <form class="frm_inqRe" action="${contextPath}/admin/inUserInqReply.do" method="post">
                        <input type="hidden" name="adminId" value="${log_adminId}">
                        <input type="hidden" name="inqNo" value="${inquery.inqNo}">
                        <table class="inq_re_tbl">
                            <tbody>
                                <tr>
                                    <th>제목</th>
                                    <td colspan="5">${inquery.inqTitle}</td>
                                </tr>
                                <tr class="as">
                                    <th>작성자</th>
                                    <td>${inquery.id}</td>
                                    <th>분류</th>
									<td>
									<c:choose>
										<c:when test="${inquery.inqCate == 1}">쇼핑</c:when>
										<c:when test="${inquery.inqCate == 2}">커뮤니티</c:when>
									</c:choose>
									</td>
									<th>등록일</th>
									<td>${inquery.inqDate}</td>
                                </tr>
                                <tr>
                                    <th>내용</th>
                                    <td colspan="5">
                                        <div class="min" style="word-break: inherit;">
                                            ${inquery.inqContent}
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th>답변</th>
                                    <td colspan="5">
                                    	<c:choose>
                                    		<c:when test="${inquery.hasRe == 1}">	<!-- 답변 있을 경우 -->
                                                <div class="min" style="word-break: inherit;">
                                                    ${inquery.inqReply}
                                                </div>
                                    		</c:when>
                                    		<c:when test="${inquery.hasRe == 0}"> <!-- 없을 경우 -->
                                    			<textarea name="inq_reply" rows="" cols=""></textarea>
                                    		</c:when>
                                    	</c:choose>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <div class="form_btn">
							<input type="submit" value="답변">
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