<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
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
<title>RecipeMall-마이페이지</title>
<script src="https://kit.fontawesome.com/54880b83c5.js" crossorigin="anonymous"></script>
<script src="${contextPath}/views/js/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/views/js/jquery-ui.min.js"></script>
<script src="${contextPath}/views/mypage/js/mypage_3_2.js"></script>
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage.css">
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage_common.css">
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage_3_2.css">
<link rel="shortcut icon" href="${contextPath}/views/images/smalllogo.png" />
</head>
<body>
   <!--[s]전체 컨텐츠 영역  -->
   <!--[s]건너뛰기 링크-->
   <a id="skipNav" href="">본문 바로가기</a>
   <!--[e]건너뛰기 링크-->
   <!-- [s]Header 영역 -->
   <jsp:include page="/views/fix/mypageHeader.jsp" />
   <!-- [e]Header 영역 -->

   <!-- [s]main_content 영역 -->
   <div id="main_content" class="mypage_box">
      <!--[s]content_mypage_e -->
      <div id="mypage">
         <h2 class="mypage_title">
            <a href="${contextPath}/main/mypage.do">마이페이지</a>
         </h2>
         <!-- sidebar -->
         <jsp:include page="/views/fix/mypageSidebar.jsp" />
         <!-- sidebar -->
           <!-- mypage_content -->
            <div class="mypage_content">
                <h3>상품/상품리뷰</h3>
                <hr>
            </div>
            <div class="mypage_product_area">
             <c:forEach var="review" items="${reviewList}" varStatus="status">
                <c:choose>
                   <c:when test="${empty review.reviewContent}">
                      <div class="mypage_product_review">
                           <div class="mypage_product_view flex_area_rsb">
                               <div class="img_area">
                                   <img src="${contextPath}/views/shopping/productImages/${review.prodImage}" alt="이미지" width="100%" height="100%">
                               </div>
                               <h4>${review.prodName}</h4>   <!-- prodName을 가져와야함 -->
                               <p><em>배송완료</em> 2023/05/02</p>
                               <div class="btn_product_area">
                                   <!-- <button class="btn_product product_reorder"><a href="#">재주문</a></button> -->
                                   <button class="btn_product product_view"><a href="${contextPath}/views/shopping/product_info.do?prodCode=${review.prodCode}">상품보러가기</a></button>
                                   <button class="btn_product product_open" data-review-id="${status.index + 1}">리뷰쓰기</button>
                               </div>
                           </div>
                           <form class="mypage_product_review_view" id="review_form_${status.index + 1}" action="${contextPath}/mypage/review/updateReview.do" enctype="multipart/form-data">
                               <div class="star-rating">
                                   <span>별점과 리뷰를 작성해 주세요</span>
                                   <input type="radio" id="rating-${status.index + 1}-5-stars" name="reviewStar" value="5"/>
                                   <label for="rating-${status.index + 1}-5-stars" class="star pr-4">★</label>
                                   <input type="radio" id="rating-${status.index + 1}-4-stars" name="reviewStar" value="4"/>
                                   <label for="rating-${status.index + 1}-4-stars" class="star">★</label>
                                   <input type="radio" id="rating-${status.index + 1}-3-stars" name="reviewStar" value="3"/>
                                   <label for="rating-${status.index + 1}-3-stars" class="star">★</label>
                                   <input type="radio" id="rating-${status.index + 1}-2-stars" name="reviewStar" value="2"/>
                                   <label for="rating-${status.index + 1}-2-stars" class="star">★</label>
                                   <input type="radio" id="rating-${status.index + 1}-1-star" name="reviewStar" value="1" />
                                   <label for="rating-${status.index + 1}-1-star" class="star">★</label>
                               </div>
                               <div class="review_form">
                                   <input type="text" class="mypage_product_review_write" maxlength="200" placeholder="내용을 입력해주세요.(200자 제한)" name="reviewContent" value="${empty review.reviewContent ? '' : review.reviewContent}">
                                   <%-- <input type="hidden" name="nextPage" value="${contextPath}/mypage/review/insertReview.do"> --%>
                                   <%-- <input type="hidden" name="reviewCode" value="${status.index + 1}"> --%>
                                   <input type="hidden" name="id" value="${log_id}">
                                   <input type="hidden" name="prodCode" value="${review.prodCode }">
                                   <input type="hidden" name="orderCode" value="${review.orderCode }">
                                   <input class="btn_product_review_insert" type="submit" value="등록하기">
                               </div>
                             </form>
                         </div>
                   </c:when>
                   <c:when test="${!empty review.reviewContent}">
                      <div class="mypage_product_review">
                           <div class="mypage_product_view flex_area_rsb">
                               <div class="img_area">
                                   <img src="${contextPath}/views/shopping/productImages/${review.prodImage}" alt="이미지">
                               </div>
                               <h4>${review.prodName}</h4>   <!-- prodName을 가져와야함 -->
                               <p><em>배송완료</em> 2023/05/02</p>
                               <div class="btn_product_area">
                                   <!-- <button class="btn_product product_reorder"><a href="#">재주문</a></button> -->
                                   <button class="btn_product product_view"><a href="${contextPath}/views/shopping/product_info.do?prodCode=${review.prodCode}">상품보러가기</a></button>
                                   <button class="btn_product product_open" data-review-id="${status.index + 1}">리뷰확인</button>
                               </div>
                           </div>
                           <form class="mypage_product_review_view" id="review_form_${status.index + 1}" action="${contextPath}/mypage/review/updateReview.do" enctype="multipart/form-data">
                               <div class="star-rating">
                                   <!-- <span>별점과 리뷰를 작성해 주세요</span> -->
                                   <c:forEach var="star" begin="1" end="${review.reviewStar}" varStatus="status">
                                   		★ 
                                   </c:forEach>
                                   <%-- <input type="radio" id="rating-${status.index + 1}-5-stars" name="reviewStar" value="5"/>
                                   <label for="rating-${status.index + 1}-5-stars" class="star pr-4">★</label>
                                   <input type="radio" id="rating-${status.index + 1}-4-stars" name="reviewStar" value="4"/>
                                   <label for="rating-${status.index + 1}-4-stars" class="star">★</label>
                                   <input type="radio" id="rating-${status.index + 1}-3-stars" name="reviewStar" value="3"/>
                                   <label for="rating-${status.index + 1}-3-stars" class="star">★</label>
                                   <input type="radio" id="rating-${status.index + 1}-2-stars" name="reviewStar" value="2"/>
                                   <label for="rating-${status.index + 1}-2-stars" class="star">★</label>
                                   <input type="radio" id="rating-${status.index + 1}-1-star" name="reviewStar" value="1" />
                                   <label for="rating-${status.index + 1}-1-star" class="star">★</label> --%>
                               </div>
                                 <div class="review_form">
                                     <input type="text" class="mypage_product_review_write" maxlength="200" disabled name="reviewContent" value="${review.reviewContent}">
                                     <input type="hidden" name="nextPage" value="${contextPath}/mypage/review/insertReview.do">
                                     <input type="hidden" name="reviewCode" value="${status.index + 1}">
                                     <!-- <input class="btn_product_review_insert" type="submit" value="내가 쓴 리뷰 확인하기"> -->
                                 </div>
                             </form>
                         </div>
                   </c:when>
                   <c:when test="${review.orderCode == null}">
                         <table>
                            <tr colspan="4">
                               <td>구매 목록이 없습니다.</td>
                            </tr>
                         </table>
                   </c:when>
                </c:choose>
                </c:forEach>
            </div>
            <!-- mypage_content -->
        </div>
    </div>
    <!-- content_mypage_e -->
   <!-- [s]footer 영역 -->
   <jsp:include page="/views/fix/footer.jsp" />
   <!-- [e]footer 영역 -->
   <!--로그인 영역-->
   <jsp:include page="/views/fix/login.jsp"/>
   <!--로그인 영역-->
   <script src="${contextPath}/views/js/login.js"></script>
   <script src="${contextPath}/views/js/naver_login.js"></script>
   <script src="${contextPath}/views/js/kakao_login.js"></script>
   <link rel="stylesheet" href="${contextPath}/views/css/login.css">
   <link rel="stylesheet" href="${contextPath}/views/js/common.js">
</body>
</html>