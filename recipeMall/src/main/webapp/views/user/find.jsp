<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>recipeMall</title>
<script src="https://kit.fontawesome.com/54880b83c5.js"
	crossorigin="anonymous"></script>
<script src="${contextPath}/views/js/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/views/js/jquery-ui.min.js"></script>
<script src="${contextPath}/views/js/find.js"></script>
<link rel="stylesheet"
	href="${contextPath}/views/shopping/css/shopping_common.css">
<link rel="stylesheet"
	href="${contextPath}/views/css/find.css">
<link rel="shortcut icon"
	href="${contextPath}/views/images/smalllogo.png" />
</head>
<body>
	<!--[s]전체 컨텐츠 영역  -->
	<!--[s]건너뛰기 링크-->
	<a id="skipNav" href="">본문 바로가기</a>
	<!--[e]건너뛰기 링크-->

	<!-- [s]메인 컨텐츠 영역  -->
	<div id="main_content">
		<div class="findArea">
			<img alt="logo" src="${contextPath}/views/images/recipemall-logo-origin.svg" width="500">
			<c:choose>
				<c:when test="${kind == 'id'}">
					<form action="${contextPath}/user/findId.do" method="post">
						<p>
							<label for="name">이름</label>
							<input type="text" id="name" name="name" required>
						</p>
						<p>
							<label for="phone">번호</label>
							<input type="text" id="phone" name="phone" required placeholder="-를 제외하고 입력해주세요">
						</p>
						<button type="submit">아이디 찾기</button>
					</form>
				</c:when>
				<c:when test="${kind == 'pw'}">
					<form action="${contextPath}/user/findPw.do" method="post">
						<p>
							<label for="id">아이디</label>
							<input type="text" id="id" name="id" required>
						</p>
						<p>
							<label for="name">이름</label>
							<input type="text" id="name" name="name" required>
						</p>
						<p>
							<label for="phone">번호</label>
							<input type="text" id="phone" name="phone" required placeholder="-를 제외하고 입력해주세요">
						</p>
						<button type="submit">비밀번호 찾기</button>
					</form>
				</c:when>
				<c:when test="${kind == 'succesId'}">
					<c:choose>
						<c:when test="${empty id}">
							<h2>회원 정보를 찾을 수 없습니다.</h2>
							<a href="${contextPath}/user/find.do?kind=id">다시 입력하기</a>
						</c:when>
						<c:otherwise>
							<h2>회원님의 아이디는 ${id} 입니다.</h2>
							<a href="${contextPath}/main/index.do">홈으로</a>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:when test="${kind == 'succesPw'}">
					<c:choose>
						<c:when test="${empty check}">
							<h2>회원 정보를 찾을 수 없습니다.</h2>
							<a href="${contextPath}/user/find.do?kind=pw">다시 입력하기</a>
						</c:when>
						<c:otherwise>
							<form action="${contextPath}/user/updatePw.do" id="updatePass">
								<p class="pwddd">
								<label for="pw">비밀번호</label>
								<input type="password" name="pw" id="pw1" required>
								</p> 
								<p class="pwddd">
								<label for="pwdd">비밀번호 확인</label>
								<input type="password" id="pw2" required>
								</p>
								<input type="hidden" name="id" value="${id}">
								<button type="button" id="gogoUpdate">비밀번호 변경하기</button>
							</form>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:when test="${kind == 'updatePw'}">
					<h2>비밀번호가 변경되었습니다</h2>
					<a href="${contextPath}/main/index.do">홈으로</a>
				</c:when>
			</c:choose>
		</div>
	</div>
</body>
</html>