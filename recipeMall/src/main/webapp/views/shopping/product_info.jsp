<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
   isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="productList" value="${productInfoMap.productList}"/>
<c:set var="reviewList" value="${productInfoMap.reviewList}"/>
<c:set var="similarList" value="${productInfoMap.similarList}"/>
<c:set var="productFaqList" value="${productInfoMap.productFaqList}"/>
<%
   request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>recipeMall</title>
    <script src="https://kit.fontawesome.com/54880b83c5.js" crossorigin="anonymous"></script>
    <script src="${contextPath }/views/js/jquery-3.6.4.min.js"></script>
    <script src="${contextPath}/views/js/jquery-ui.min.js"></script>
    <script src="${contextPath}/views/shopping/js/product_info.js"></script>
    <link rel="stylesheet" href="${contextPath}/views/shopping/css/shopping_common.css">
    <link rel="stylesheet" href="${contextPath}/views/shopping/css/product_info.css">
    <link rel="stylesheet" href="${contextPath}/views/css/login.css">
    <link rel="shortcut icon" href="../images/smalllogo.png" />
    <script type="text/javascript">
       function buyProduct(obj) {
          obj.action="${contextPath}/shopping/buyProduct.do";
          let cartCountvalue=$('.option2').text();
          let sentCartCount=$('#sentCartCount');
          sentCartCount.attr("value",cartCountvalue);
          obj.submit();
      }
       function goCart(obj) {
          obj.action="${contextPath}/shopping/goCart.do";
          let cartCountvalue=$('.option2').text();
          let sentCartCount=$('#sentCartCount');
          sentCartCount.attr("value",cartCountvalue);
          obj.submit();
          obj.submit();
      }
       function sendFaq(obj){
    	   let prodContent=$('#prod_content').text();
    	   let contentInput=$('#prodContent');
    	   $(contentInput).attr("value",prodContent);
    	   let prodFaqTitle=$('#prodFaqTitle').value();
    	   let sendprodFaqTitle=$('#sendprodFaqTitle');
    	   $(sendprodFaqTitle).attr("value",prodFaqTitle);
    	   obj.action="${contextPath}/shopping/addFaq.do"
    	   obj.submit();
    	   alert("문의 접수 완료!");
       }
    </script>
</head>
<body>
   <!--[s]전체 컨텐츠 영역  -->
    <!--[s]건너뛰기 링크-->
    <a id="skipNav" href="">본문 바로가기</a>
    <!--[e]건너뛰기 링크-->


    <!-- [s]광고배너 영역 -->
    <div class="adbanner">
        <p><a href="#">지금 가입하면 무료배송!<button class="banner_btn" onclick="close_div()"><i
                        class="fa-solid fa-x"></i></button></a></p>
    </div>
    <!--[e]광고배너 영역 -->


    <!-- [s]Header 영역 -->
    <jsp:include page="/views/fix/shopHeader.jsp"/>
    <!-- [e]Header 영역 -->

        <!-- [s]main_content 영역 -->
        <div id="main_content">
            <!--[s]상품 이름,상품 옵션 선택 영역-->
            <div class="product_area">
                   <div class="product_image">
                       <img src="${contextPath}/views/shopping/productImages/${productList.prodImage}" alt="상품 이미지">
                   </div>
                   <div class="product">
                       <div class="product_name">
                           <h2>${productList.prodName}</h2>
                           <p>★ ${productList.avgStar} 리뷰 ${productList.reviewCnt}</p>
                           <p><span>${productList.prodPrice}</span>원</p>
                       </div>
                       <div class="product_option">
                           <h2>옵션 선택</h2>
                           
                           <div class="product_date">
                               <p>발송일</p> 
                               <select class="product_date_select">
                                   <option class="wavewave">구매 후 바로</option>
                                   <option value="3일 후">3일 후</option>
                                   <option value="5일 후">5일 후</option>
                                   <option value="7일 후">7일 후</option>
                               </select>
                           </div>
                           <div class="product_count">
                               <p>수량</p>
                               <p class="product_count_minus">-</p>
                               <input type="text" maxlength="3" value="1" class="count" >
                               <p class="product_count_plus">+</p>
                           </div>
                           <div class="product_selected">
                               <p>선택한 옵션</p>
                               <p class="selected">발송일 : <span class="option1">구매 후 바로</span> 수량:  <span class="option2" name="prodContent2">1</span> </p>
                           </div>
                       </div>
                       <div class="product_button">
                         <form action="">
                             <input type="hidden" value="${productList.prodName}" name="prodName">
                             <input type="hidden" value="${productList.prodImage}" name="prodImage">
                             <input type="hidden" value="${productList.prodPrice}" name="prodPrice">
                             <input type="hidden" value="${productList.prodCode}" name="prodCode">
                             <input type="hidden" value="" name="cartCount" id="sentCartCount">
                             <input type="hidden" value="${log_id}" name="id">
                              <button class="product_buy" onclick="buyProduct(this.form)">
                                 구매하기
                              </button>
                              <button class="product_cart" onclick="goCart(this.form)">
                                 장바구니
                              </button>
                         </form>     
                       </div>
                   </div>
               </div>
               <!--[e]상품 이름,상품 옵션 선택 영역-->
               <!--[s]비슷한 상품 영역-->
               <div class="product_similar">
                   <h2>비슷한 상품</h2>
                   <div class="product_similar_area">
                           <div class="image_area">
                               <c:forEach var="list" items="${similarList}" varStatus="status">
                                   		<img src="${contextPath}/views/shopping/productImages/${list.prodImage}" alt="">
                               	</c:forEach>
                           </div>
                   </div>
               </div>
           
            <!--[e]비슷한 상품 영역-->
            <!--[s]상품 정보 영역-->
            <div class="product_info_area">
                <div class="product_info_title">
                    <ul>
                        <li>상세 정보</li>
                        <li>리뷰</li>
                        <li>상품문의</li>
                        <li>배송/교환/반품안내</li>
                    </ul>
                </div>
               <div class="product_info product_info1">
                    <div class="product_info_top_area">
                        <h2 class="product_info_name">상세 정보</h2>
                    </div>

                    <ul>
                        <li>총 중량: ${productList.prodContent1}<br> </li>
                        <li>원산지: ${productList.prodContent2}<br> </li>
                        <li>보관: ${productList.prodContent3}<br> </li>
                    </ul>
                    <div class="prodContentImage">
	                    <img alt="" src="${contextPath}/views/shopping/productImages/${productList.prodContentImage1}">
	                    <img alt="" src="${contextPath}/views/shopping/productImages/${productList.prodContentImage2}">
                    </div>
                </div>
                <div class="product_info product_info2">
                    <div class="product_info_top_area flex_area_rsb">
                        <h2 class="product_info_name">리뷰</h2>
                        <span class="cnt_review_tot_star">
                        </span>
                        <form action="${contextPath}/mypage/review/viewReview.do">
                        	<button type="submit">리뷰쓰기</button>
                        </form>
                    </div>
                   
                    <div class="product_reviews">
                        <c:forEach var="list" items="${reviewList}">
                           <ul class="product_review ">
                               <li class="review_id_star_area">
                                   <span class="review_id">작성자:${list.id}</span>
                                   <span class="review_writedate">작성일:${list.reviewDate}</span><br>
                                   <span class="review_star"><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-regular fa-star"></i></span>
                               </li>
                               <div class="product_review_content">
                                   <li class="review_content_area">
                                       <p class="review_title">${list.reviewTitle }</p>
                                       <p class="review_content">${list.reviewContent}</p>
                                   </li>
                               </div>
                           </ul>
                        </c:forEach>
                    </div>
                </div>
                <div class="product_info product_info3" >
                    <div class="product_info_top_area flex_area_rsb">
                        <h2 class="product_info_name">상품문의</h2>
                        
                        <button type="button">문의하기</button>
                    </div>
                    <!-- 처음엔  hidden 
                        문의하기 버튼을 누르면 로그인 된상태면 hidden된게 나오게
                        로그아웃 상태면 로그인창이 뜨게 만들기 -->
                    <form action="${contextPath}/shopping/addFaq.do" method="post">
                        <div class="write_qna_area">
                            <p class="write_qna_title">상품 및 옵션</p>
                                  <input class="wite_qna_p_name" type="text" value="${productList.prodName}" disabled>
                                  <input class="wite_qna_p_name" type="text" value="문의 제목" id="prodFaqTitle" name="prodFaqTitle">
                            <p class="write_qna_title">문의 내용</p>
                            <textarea name="content" id="prod_content" cols="30" rows="10" placeholder="문의 내용을 입력하세요"></textarea>
                            <div class="qna_ex">
                                <p>- 문의내용에 대한 답변은 마이페이지 > 상품문의 또는 '상품 상세페이지'에서 확인 가능합니다.</p> 
                                <p>- 배송, 결제, 교환/반품 문의는 고객센터로 문의 바랍니다.</p> 
                                <p>- 상품과 관련 없거나 부적합한 내용을 기재하는 경우, 사전 고지 없이 삭제 또는 차단될 수 있습니다.</p> 

                            </div>
                            <input type="hidden" name="prodCode" value="${productList.prodCode}">
                            <input type="hidden" name="prodName" value="${productList.prodName}">
                            <input type="hidden" id="tempId" name="id" value="${log_id}">
                            <button id="faq_button" onclick="sendFaq(this.form)">작성완료</button>
                        </div>
                    </form>
                    <c:forEach  var="list" items="${productFaqList }">
                       <article>
                       <c:if test="${list.hasComent }==0">
                             <p class="ck_answer">답변대기</p>
                       </c:if>
                        <c:if test="${list.hasComent }==1">
                             <p class="ck_answer">답변완료</p>
                       </c:if>
                           <p class="inq_top_area">
                               <span>${prodList.prodName } |</span>
                               <span>${list.id }</span>
                
                           </p>
                           <div class="product_info_q">
                               <span class="txt_q">Q.</span><h4>재출고 날짜가 언제인가요?</h4>
                               <p>유기농 고랭지 배추 재출고 날짜가 궁금해 문의 드립니다.</p>
                           </div>
                           <div class="product_info_a" >
                               <span class="txt_a">A.</span>
                               <h4>해당상품은 입고 되는 즉시 재출고 됩니다. 감사합니다.</h4>
                           </div>
                       </article>
                    </c:forEach>
                </div>
                <div class="product_info product_info4" >
                    <h3>교환 반품정보</h3>
                    <ul>
                        <li><strong>반품배송비(편도) : </strong>2,500원 (최초 배송비 미결제시 6,000원 부과)</li>
                        <li><strong>교환배송비(왕복) : </strong>5,000원</li>
                        <li><strong>보내실곳 : </strong>서울 종로구 (주)이젠아카데미</li>
                        <li>단, 교환/반품 비용은 상품 및 교환/반품 사유에 따라 변경될 수 있으므로 교환/반품 신청 화면 확인 부탁드립니다.</li>
                    </ul>
                    <h3>교환/반품 사유에 따른 요청 가능 기간</h3>
                    <ul>
                        <li>구매자 단순 변심 : 상품 수령 후 7일 이내(구매자 반품 배송비 부담)</li>
                        <li>표시/광고와 상이, 계약 내용과 다르게 이행된 경우 :
                            상품 수령 후 3개월 이내 혹은 표시/광고와 다른 사실을 안 날로부터 30일 이내(판매자 반품 배송비 부담)</li>
                    </ul>
                    <h3>교환/반품 불가한 경우</h3>
                    <ul>
                        <li>1.교환/반품 요청이 기간이 지난 경우</li>
                        <li>2.소비자의 책임 있는 사유로 상품 등이 분실/파손/훼손된 경우 (단, 확인을 위한 포장 훼손 제외)</li>
                        <li>3.소비자의 사용/소비에 의해 상품 등의 가치가 현저히 감소한 경우 (예 : 식품, 화장품, 향수, 음반)</li>
                        <li>4.제품을 설치 또는 장착하였거나 개통한 경우 (예 : 전자제품, 컴퓨터, 휴대폰 등)</li>
                        <li>5.시간의 경과에 의해 재판매가 곤란할 정도로 상품 등의 가치가 현저히 감소한 경우 (신선식품과 같이 유통기한이 정해져 있는 상품)</li>
                        <li>6.복제가 가능한 상품 등의 포장을 훼손한 경우 (CD/DVD/GAME/BOOK의 경우 포장 개봉 시)</li>
                        <li>7.주문제작 상품 중 상품제작에 들어간 경우 (주문접수 후 개별생산, 맞춤 제작 등)</li>
                    </ul>
                    <h3>거래 조건에 대한 정보</h3>
                    <ul>
                        <li>1.소화물 택배의 배송은 발송일로부터 1~2 영업일이 소요되나,
                            지역/대형 화물/설치/예약/발송지체 등의 특이사항에 따라 배송기간은 달라질 수 있습니다.</li>
                        <li>2.‘전자상거래등에서의소비자보호에관한법률’이 정하는 바에 따라 소비자의 청약철회 후 판매자가 재화 등을 반환 받은 날로부터
                            3영업일 이내에 지급받은 대금의 환급을 정당한 사유없이 지연하는 때에는 소비자는 지연기간에 대해서 전상법 시행령으로 정하는 이율을
                            곱하여 산정한 지연이자(지연배상금)을 신청할 수 있습니다.</li>
                    </ul>
                </div>
            </div>
            <!--[e]상품 정보 영역-->
        </div>
        
    <!-- [e]main_content 영역 -->
    <!-- [s]footer 영역 -->
    <jsp:include page="/views/fix/footer.jsp"/>
    <!-- [e]footer 영역 -->
    <!--로그인 영역-->
	<jsp:include page="/views/fix/login.jsp"/>     
    <!--로그인 영역-->
    <script src="${contextPath}/views/js/login.js"></script>
    <script src="${contextPath}/views/js/naver_login.js"></script>
    <script src="${contextPath}/views/js/kakao_login.js"></script>
    
        <!-- [e]footer 영역 -->
        <script type="text/javascript" src="${contextPath}/views/shopping/js/shopping_common.js"></script>

    <!--[e]전체 컨텐츠 영역  -->
</body>
</html>