<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<!-- [s]Header_top 영역 -->
		<div class="header_top flex_area_rsb">
			<!--[s]메인메뉴 영역 -->
			<div>
				<ul class="mainmenu flex_area_rsb">
                    <li class="main" title="메인페이지로 이동" onclick="location.href='${contextPath}/main/index.do'"></li>
                    <li><a href="${contextPath}/main/index.do" class="a_community">커뮤니티</a></li>
                    <li><a href="${contextPath}/shopping/shopping_index.do" class="a_shopping">쇼핑</a></li>
                </ul>
			</div>
			<!--[e]메인메뉴 영역 -->

			<!--[s]검색 영역 -->
			<form action="" class="search_form">
				<div class="search_area  flex_area_rsb">
					<input id="" name="" type="search" title="검색어 입력" maxlength="255"
						class="" placeholder=" 통합검색">
					<div class="btn_search">
						<i class="fa-solid fa-magnifying-glass"></i>
					</div>
				</div>
			</form>
			<!--[e]검색 영역-->

			<!--[s]서브 영역 -->
			<div>
				<ul class="submenu">
					<!-- <li><a href="#"><i class="fa-sharp fa-regular fa-cart-shopping"></i></a></li> -->
                    <li><a href="#"><i class="fa-solid fa-cart-plus"></i></a></li>
					<c:if test="${isLogon == true}">
						<li><a href="">${log_id}님</a></li>
	                    <li><a href="${contextPath}/loginProc/logout" class="logout">로그아웃</a></li>
	                    <li><a href="${contextPath}/service/main.do?id=${log_id}" class="">마이페이지</a></li>
					</c:if>
					<c:if test="${isLogon != true}">
	                    <li><a href="" class="login">로그인</a></li>
	                    <li><a href="${contextPath}/user/joinForm.do">회원가입</a></li>
					</c:if>
                    
                    <li><button class="btn_write">글쓰기
                            <i class="fa-solid fa-chevron-down"></i>
                        </button>
                    </li>
				</ul>
			</div>
			<!--[e]서브 영역 -->
			<!--[s]글쓰기 하위메뉴 영역 -->
			<div class="btn_write_sub" hidden>
				<a class="a_btn_write" href="${contextPath}/recipe/insert.do">
					<div class="btn_write_icon">
						<i class="fa-solid fa-book"></i>
					</div>
					<div class="btn_write_txt">
						<div class="btn_write_t">레시피 글쓰기</div>
						<div class="btn_write_c">나만의 레시피를 공유해보세요.</div>
					</div>
				</a>
				<a class="a_btn_write" href="${contextPath}/board/notice_write.do">
					<div class="btn_write_icon">
						<i class="fa-solid fa-clipboard-list"></i>
					</div>
					<div class="btn_write_txt">
						<div class="btn_write_t">자유게시판 글쓰기</div>
						<div class="btn_write_c">레시피몰의 회원들과 자유롭게 이야기를 나눠보세요.</div>
					</div>
				</a>
				<a class="a_btn_write" href="">
					<div class="btn_write_icon">
						<i class="fa-regular fa-thumbs-up"></i>
					</div>
					<div class="btn_write_txt">
						<div class="btn_write_t">상품 리뷰하기</div>
						<div class="btn_write_c">상품 후기를 공유해보세요.</div>
					</div>
				</a>

			</div>
			<!--[e]글쓰기 하위메뉴 영역 -->
		</div>
		<!-- [e]Header_top 영역 -->

		<div class="textline"></div>

		<!-- [s]Header_bottom 영역 -->
		<div class="header_bottom flex_area_rsb">
			<!--[s]카테고리 영역 -->
			<div class="flex_area_rsb">
				<ul class="category">
					<li><a href="${contextPath }/shopping/matarial.do">재료</a></li>
					<li><a href="${contextPath }/shopping/shopping_best.do">베스트</a></li>
					<li><a href="${contextPath }/shopping/season.do">대표상품</a></li>
				</ul>
			</div>
			<!--[e]카테고리 영역 -->

			<!--[s]실시간차트 영역-->
			<div class="rank">
				<ul>
					<li><a href="#">1.레시피</a></li>
				</ul>
			</div>
			<!--[e]실시간차트 영역 -->
		</div>
		<!-- [e]Header_bottom 영역 -->
		<div class="textline cf"></div>
	</header>
</body>
</html>