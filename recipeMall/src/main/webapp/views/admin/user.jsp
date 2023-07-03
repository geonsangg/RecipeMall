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
            <!-- main content -->
			<div class="container">
                <h2>회원 관리</h2>
                <div class="user_list list">
                    <div class="user_search search">
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
                    <table class="user_tbl tbl">
                        <caption>자주하는 질문 목록</caption>
                        <tbody>
                            <tr>
                                <th>번호</th>
                                <th>아이디</th>
                                <th>닉네임</th>
                                <th>주소</th>
                                <th></th>
                                <th>가입일</th>
                                <th><input type="checkbox" name="ud_check" id=""></th>
                            </tr>
                    <c:forEach var="user" items="${userList}">
                            <tr>
                                <td></td>
                                <td><a href="">${user.id}</a></td>
                                <td>${user.nikName}</td>
                                <td>${user.userAddr}</td>
                                <td>${user.userAddr}</td>
                                <td>${user.joinDate }</td>
                                <td><input type="checkbox" name="ud_check" id=""></td>
                            </tr>                    
                    </c:forEach>
                        </tbody>
                    </table>
                    <div class="cud">
                        <ul class="mi">
                            <li><a href="">삭제</a></li>
                        </ul>
                    </div>
                    <div class="paging">
                        1 2 3
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