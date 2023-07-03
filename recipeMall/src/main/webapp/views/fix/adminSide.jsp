<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div id="sidebar">
	<div class="side header">
	    RecipeMall
	</div>
	<div class="move">
    	<a href="${contextPath}/main/index"><i class="fa-solid fa-up-right-from-square"></i>사이트 바로가기</a>
  	</div>
  	<div class="manage">
    	<div class="title">사이트 관리</div>
    		<div class="menu">
        		<ul class="main_menu">
					<li><a href="${contextPath}/admin/userList.do">회원 관리</a></li>
					<li><a href="${contextPath}/admin/product.do">상품 관리</a></li>
					<li><a href="${contextPath}/admin/orderList.do">주문 관리</a></li>
					<li><a href="${contextPath}/views/admin/shipping.jsp">배송 관리</a></li>
					<li><span class="lnb">문의 관리</span>
						<ul class="sub">
							<li><a href="${contextPath}/admin/noticeList.do">공지사항</a></li>
							<li><a href="${contextPath}/admin/faqList.do">자주하는 질문</a></li>
							<li><a href="${contextPath}/admin/prodInqList.do">상품 문의</a></li>
							<li><a href="${contextPath}/admin/userInqList.do">일대일문의</a></li>
						</ul>
					</li>
					<li><span class="lnb">게시판 관리</span>
               			<ul class="sub">
                   			<li><a href="${contextPath}/admin/freeBoardList.do">자유게시판</a></li>
        					<li><a href="${contextPath}/admin/recipeList.do">레시피</a></li>
                 		</ul>
             		</li>
				</ul>
        	</div>
    	</div>
	</div>
	<div id="content">
		<div class="cont header">
			<span>관리자 ${log_adminId}님 </span>
			<a class="home" href="${contextPath}/admin/main.do">메인</a>
			<a class="log" href="${contextPath}/admin/logout.do">로그아웃</a>
		</div>