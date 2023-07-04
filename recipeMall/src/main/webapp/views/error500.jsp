<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>recipeMall</title>
<link rel="stylesheet"
	href="${contextPath}/views/shopping/css/shopping_common.css">
<link rel="stylesheet"
	href="${contextPath}/views/css/error404.css">
<link rel="shortcut icon"
	href="${contextPath}/views/images/smalllogo.png" />
</head>
<body>
	<div id="main_content">
		<div class="errorArea">
			<a href="${contextPath}/main/index.do"><img alt="logo" src="${contextPath}/views/images/recipemall-logo-origin.svg" width="300"></a>
			<h2>죄송합니다.<br>
			 서비스 실행 중에 오류가 발생했습니다.</h2>
			<p>잠시 후 다시 시도해주세요.</p>
			<p>관련 문의사항은 <a href="${contextPath}/main/index.do">레시피몰</a>에 알려주시면 친절하게 안내해 드리겠습니다.</p>
			<p>감사합니다.</p>
		</div>
	</div>
</body>
</html>