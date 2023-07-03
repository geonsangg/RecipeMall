<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("utf-8");
session.getAttribute("log_id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>RecipeMall-마이페이지</title>
<script src="https://kit.fontawesome.com/54880b83c5.js"
	crossorigin="anonymous"></script>
<script src="${contextPath}/views/js/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/views/js/jquery-ui.min.js"></script>
<script src="${contextPath}/views/js/common.js"></script>
<script src="${contextPath}/views/mypage/js/mypage_2_4.js"></script>
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage.css">
<link rel="stylesheet"
	href="${contextPath}/views/mypage/css/mypage_common.css">
<link rel="stylesheet"
	href="${contextPath}/views/mypage/css/mypage_2_4.css">
<script type="text/javascript">
	function chkPay() {
		let cartCountInputs = document.querySelectorAll(".count");
		let changeCartCounts = document.querySelectorAll("#changeCartCount");

		for (let i = 0; i < cartCountInputs.length; i++) {
			changeCartCounts[i].value = cartCountInputs[i].value;
		}

		document.getElementById('prodSubmitForm').submit();
	};
</script>
</head>
<body>
	<!--[s]건너뛰기 링크-->
	<a id="skipNav" href="">본문 바로가기</a>
	<!--[e]건너뛰기 링크-->
	<!-- [s]Header 영역 -->
	<jsp:include page="/views/fix/mypageHeader.jsp" />
	<!-- [e]Header 영역 -->
	<!-- content_mypage_s -->
	<div id="main_content" class="mypage_box">
		<div id="mypage">
			<h2 class="mypage_title">
				<a href="${contextPath}/main/mypage.do">마이페이지</a>
			</h2>
			<!-- sidebar -->
			<jsp:include page="/views/fix/mypageSidebar.jsp" />
			<!-- sidebar -->
			<!-- mypage_content -->
			<div class="mypage_content">
				<!-- mypage_content_장바구니 시작-->
				<h3>장바구니</h3>
				<div class="mypage_marketbag">
					<div class="cart_cont_all">
						<b>선택한 상품</b>
						<p>
							( <span class="product_choose_count">0</span> )
						</p>
					</div>
					<div class="cart_cont_list">
						<table>
							<thead>
								<tr>
									<th><input type="checkbox" name="product_chk_all"
										id="product_chk_all">전체선택</th>
									<th>상품</th>
									<th>수량</th>
									<th>상품금액</th>
									<th>삭제</th>
								</tr>
							</thead>
							<c:choose>
								<c:when test="${empty cartList}">
									<tr>
										<td colspan="5" align="center">등록된 상품이 없습니다.</td>
									</tr>
								</c:when>
								<c:when test="${!empty cartList}">
									<form action="${contextPath }/mypage/updateCart.do" id="prodSubmitForm" method="post">
										<c:forEach var="cartList" items="${cartList}">
											<tbody>
												<tr>
													<td><input type="checkbox" name="prodName" id="proudct_chk" value="${cartList.prodName }"></td>
													<td><img src="${contextPath}/views/shopping/productImages/${cartList.prodImage}" alt="" id="product_img">
														<p class="product_market_name">${cartList.prodName }</p></td>
													<td><p class="product_count_minus">-</p>
													<span class="product_sangpum_count"><input type="text" maxlength="3" value="${cartList.cartCount }" class="count" id="cartCountInput"></span>
														<p class="product_count_plus">+</p>
													</td>
													<td><span class="product_sangpum_price">${cartList.prodPrice}</span></td>
													<td><a href="${contextPath}/mypage/delCart.do?id=${log_id}&prodCode=${cartList.prodCode}">삭제</a></td>
												</tr>
											</tbody>
											<input type="hidden" value="${cartList.prodCode }" name="prodCode">
											<input type="hidden" value="${cartList.cartCount}" name="cartCount" id="changeCartCount">
											<input type="hidden" value="${cartList.prodPrice }" name="prodPrice">
											<input type="hidden" value="${cartList.prodImage }" name="prodImage">
											<input type="hidden" name="id" value="${log_id}">
										</c:forEach>
									</form>
								</c:when>
							</c:choose>
						</table>
					</div>
				</div>
				<div class="btn_left_box">
					<a href="#"><em>&lt; 쇼핑계속하기</em></a>
				</div>
				<div class="price_sum">
					<div class="price_sum_count">
						<ul>
							<li>총 <span class="product_choose_count">0</span> 개의 상품금액
							</li>
							<li><strong id="total_goods_price">0</strong> 원</li>
						</ul>
						<span><img
							src="${contextPath }/views/images/order_price_plus.png" alt="더하기"></span>
						<ul>
							<li>배송비</li>
							<li><strong id="delivery_price">2,500</strong> 원</li>
						</ul>
						<span><img
							src="${contextPath }/views/images/order_price_total2.png" alt="합"></span>
						<ul>
							<li>합계</li>
							<li><strong id="total_price">0</strong> 원</li>
						</ul>
					</div>
				</div>
				<div class="btn_right_Pbox">
					<button type="button" class="btn_order_choice" onclick="chkPay()">상품주문</button>
				</div>
				<!-- mypage_content_장바구니 끝-->
			</div>
			<!-- mypage_content -->
		</div>
	</div>
	<!-- content_mypage_e -->

	<!-- [s]footer 영역 -->
	<jsp:include page="/views/fix/footer.jsp" />
	<!-- [e]footer 영역 -->
</body>
</html>