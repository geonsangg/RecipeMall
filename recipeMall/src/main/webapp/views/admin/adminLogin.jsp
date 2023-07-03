<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RecipeMall 관리자</title>
<link rel="stylesheet" href="${contextPath}/views/admin/css/admin.css">
<link rel="stylesheet" href="${contextPath}/views/admin/css/login.css">
</head>
<body>
	<div id="content">
        <div id="login_box">
            <div class="logo_img">
                <img src="${contextPath}/views/images/recipemall-logo-origin.svg" alt="logo">
            </div>
            <div class="admin_login">
                <h3>관리자 페이지</h3>
                <form class="frm_alogin" action="${contextPath}/admin/login.do" method="post">
                    <label for="adminId">아이디</label>
                    <input type="text" name="adminId" id="adminId"><br>
                    <label for="adminPw">비밀번호</label>
                    <input type="password" name="adminPw" id="adminPw"><br>
                    <input type="submit" class="btn" value="로그인">
                </form>
            </div>
        </div>
    </div>
</body>
</html>