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
<link rel="stylesheet" href="${contextPath}/views/admin/css/view.css">
<link rel="stylesheet" href="${contextPath}/views/admin/css/select.css">
<style type="text/css">
#mod_btn {
	display: none;
}
</style>
<script type="text/javascript">
// 목록 버튼
function fn_backToList(obj) {
	obj.action="${contextPath}/admin/prodInqList.do";
	obj.submit();
}
// 답변 버튼
function fn_answer(obj) {
	obj.action="${contextPath}/admin/answerProdFaq.do";
	obj.submit();
}
// 수정 페이지 이동 버튼
function fn_modForm(obj) {
	document.getElementById("id_prodContent").disabled=false;
	document.getElementById("mod_btn").style.display="block";
	document.getElementById("ans_btn").style.display="none";
}
// 수정하기 버튼
function fn_updateAns(obj) {
	obj.action="${contextPath}/admin/answerProdFaq.do";
	obj.submit();
}
// 취소 버튼
function fn_cancle(obj) {
	obj.action="${contextPath}/admin/prodInqView.do?prodFaqNo=${prodFaq.prodFaqNo}";
	obj.submit();
}
</script>
</head>
<body>
<div id="box">
    <c:choose>
    	<c:when test="${isAdmLogon == true}">
        <jsp:include page="/views/fix/adminSide.jsp"/>
            <!-- main content -->
			<div class="container">
                <h2>상품 문의 관리 > 답변 등록</h2>
                <div class="prodInq_list">
                	<form action="" method="post">
                        <input type="hidden" name="prodFaqNo" value="${prodFaq.prodFaqNo}">
                        <input type="hidden" name="adminId" value="${log_adminId}">
                		<table class="prodFaq_v_tbl tbl">
                			<tbody>
                				<tr>
                                    <th>제목</th>
                                    <td class="pf_title" colspan="3">${prodFaq.prodFaqTitle}</td>
                                </tr>
                                <tr>
                                    <th>작성자</th>
                                    <td>${prodFaq.id}</td>
                                    <th>등록일</th>
                                    <td>${prodFaq.prodFaqDate}</td>
                                </tr>
                                <tr>
                                    <th>상품 이름</th>
                                    <td colspan="3">${prodFaq.prodName}</td>
                                </tr>
                                <tr>
                                    <th>내용</th>
                                    <td class="cn" colspan="3"><div class="min" style="word-break: inherit;">${prodFaq.content}</div></td>
                                </tr>
                                <tr>
                                    <th>답변</th>
                                    <td colspan="3">
                                    <c:if test="${prodFaq.hasComent == 0}">
                                    	<textarea rows="" cols="" name="prodContent">${prodFaq.prodContent}</textarea>
                                    </c:if>
                                    <c:if test="${prodFaq.hasComent == 1}">
                                    	<textarea id="id_prodContent" name="prodContent" disabled>${prodFaq.prodContent}</textarea>
                                    </c:if>
                                    </td>
                                </tr>
                			</tbody>
                		</table>
                        <div id="ans_btn" class="form_btn">
                            <button type="button" class="btn list_btn" onclick="fn_backToList(this.form)">목록</button>
                            <c:choose>
                            	<c:when test="${prodFaq.hasComent == 0}">
		                            <button type="button" class="btn update_btn" onclick="fn_answer(this.form)">답변</button>                            	
                            	</c:when>
                            	<c:when test="${prodFaq.hasComent == 1}">
		                            <button type="button" class="btn update_btn" onclick="fn_modForm(this.form)">수정</button>                            	
                            	</c:when>
                            </c:choose>
                        </div>
                        <div id="mod_btn" class="form_btn">
                            <button type="button" class="btn update_btn" onclick="fn_updateAns(this.form)">수정하기</button>
                            <button type="button" class="btn update_btn" onclick="fn_cancle(this.form)">취소</button>
                        </div>
                	</form>
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