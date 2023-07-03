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
	                <h2>상품 관리 > 등록</h2>
	                <div class="product_insert list">
	                	<form action="${contextPath}/admin/addProduct.do" method="post" enctype="multipart/form-data">
	                		<table class="prod_in_tbl tbl">
	                            <tbody>
	                                <tr>
	                                    <th>상품이름</th>
	                                    <td><input type="text" name="prodName" id="" class="subject"></td>
	                                </tr>
	                                <tr>
	                                    <th>카테고리</th>
	                                    <td><input type="text" name="prodKind" id="" class="subject"></td>
	                                </tr>
	                                <tr>
	                                    <th>가격</th>
	                                    <td><input type="text" name="prodPrice" id="" class="subject">원</td>
	                                </tr>
	                                <tr>
	                                    <th>상품 대표 이미지</th>
	                                    <td><input type="file" name="prodImage" id="" class="subject"></td>
	                                </tr>
	                                <tr>
	                                    <th>포장 당 용량</th>
	                                    <td><input type="text" name="prodContent1" id="" class="subject"></td>
	                                </tr>
	                                <tr>
	                                    <th>출산지</th>
	                                    <td><input type="text" name="prodContent2" id="" class="subject"></td>
	                                </tr>
	                                <tr>
	                                    <th>보관</th>
	                                    <td><input type="text" name="prodContent3" id="" class="subject"></td>
	                                </tr>
	                                <tr>
	                                    <th>상품 이미지1</th>
	                                    <td><input type="file" name="prodContentImage1" id="" class="subject"></td>
	                                </tr>
	                                <tr>
	                                    <th>상품 이미지2</th>
	                                    <td><input type="file" name="prodContentImage2" id="" class="subject"></td>
	                                </tr>
	                                <tr>
	                                    <th>제철</th>
	                                    <td><input type="text" name="prodSeason" id="" class="subject"></td>
	                                </tr>
	                                <tr>
	                                    <th>입고</th>
	                                    <td><input type="text" name="prodRest" id="" class="subject"></td>
	                                </tr>
	                            </tbody>
	                        </table>
	                        <div class="form_btn">
	                           <input type="submit" value="등록">
	                           <input type="button" value="취소" onclick="history.back(-1);">
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