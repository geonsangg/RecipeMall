<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="logId" value="${log_id}" scope="page" />

<%
	request.setCharacterEncoding("utf-8");
	session.getAttribute("isLogon");	// 로그인 상태값을 갖고 있는 키
	session.getAttribute("log_id");	// 로그인한 유저 아이디를 갖고 있는 키

%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>recipeMall</title>
    
    
    <!-- 공통 -->
    <script src="https://kit.fontawesome.com/54880b83c5.js" crossorigin="anonymous"></script>
    <link rel="shortcut icon" href="${contextPath}/views/images/dish4.png" />
    <link rel="stylesheet" href="${contextPath}/views/css/common.css">
    <script src="${contextPath}/views/js/jquery-3.6.4.min.js"></script>
    <script src="${contextPath}/views/js/jquery-ui.min.js"></script>
    <script src="${contextPath}/views/js/login.js"></script>
    <script src="${contextPath}/views/js/naver_login.js"></script>
    <script src="${contextPath}/views/js/kakao_login.js"></script>
    <link rel="stylesheet" href="${contextPath}/views/css/login.css">
    <script src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>
    <script src="https://t1.kakaocdn.net/kakao_js_sdk/2.1.0/kakao.min.js"
    integrity="sha384-dpu02ieKC6NUeKFoGMOKz6102CLEWi9+5RQjWSV0ikYSFFd8M3Wp2reIcquJOemx" crossorigin="anonymous"></script>
    
   <!--공통X -->
    <link rel="stylesheet" href="${contextPath}/views/community/css/recipe_spe.css">
    
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
	<jsp:include page="/views/fix/indexHeader.jsp" />
	<!-- [e]Header 영역 -->
    <!-- [s]main_content 영역 -->
    <div id="main_content">
     <!-- [s]레시피 등록 내용 영역 -->
     	
        <div class="r_content_area">
        <!-- [s] 좋아요 영역 -->
            
           <!-- 하트 안눌러져있을 때(0) 하트 색깔없음--><!-- 하트 눌러져있을 때(1) 하트 색깔있음   id로그인한 사람꺼 받아서 hidden한 input 태그에 넣어줘야함--> 
            <c:choose>
               <c:when test="${recipe.likeVO.likeValue == 0}">
                  <form action="${contextPath}/recipe/insertLike.do" class="like_area">
                  	<input type="hidden" name="recipeNo" value="${recipe.recipeNo}">
                  	<input type="hidden" name="cntLike" value="${recipe.cntLike}">
                  	<input type="hidden" name="likeValue" value="${recipe.likeVO.likeValue}">
                  	<input type="hidden" name="id" value="${log_id}">
                  	<button  class= "btn_like"type="submit" value="${recipe.recipeNo}">
                  		<i class="fa-regular fa-heart" ></i> 
                  	</button><br>
                  	<span class="cnt_like ">${recipe.cntLike}</span>
                  </form>
               </c:when>
               <c:when test="${recipe.likeVO.likeValue== 2}">
                  <form action="${contextPath}/recipe/updateLike.do" class="like_area">
                  	<input type="hidden" name="recipeNo" value="${recipe.recipeNo}">
                  	<input type="hidden" name="cntLike" value="${recipe.cntLike}">
                  	<input type="hidden" name="likeValue" value="${recipe.likeVO.likeValue}">
                  	<input type="hidden" name="id" value="${log_id}">
                  	<button  class= "btn_like"type="submit" value="${recipe.recipeNo}">
                  		<i class="fa-regular fa-heart" ></i> 
                  	</button><br>
                  	<span class="cnt_like ">${recipe.cntLike}</span>
                  </form>
               </c:when>
               <c:otherwise>
                  <form action="${contextPath}/recipe/deleteLike.do" class="like_area">
                  	<input type="hidden" name="recipeNo" value="${recipe.recipeNo}">
                  	<input type="hidden" name="cntLike" value="${recipe.cntLike}">
                  	<input type="hidden" name ="likeValue" value="${recipe.likeVO.likeValue}">
                  	<input type="hidden" name="id" value="${log_id}">
                      <button  class= "btn_like"type="submit" value="${recipe.recipeNo}">
                      		<i class="fa-solid fa-heart"></i>
                      </button><br>   
                      <span class="cnt_like ">${recipe.cntLike}</span>
                  </form>
               </c:otherwise>
            </c:choose>
        <!-- [e] 좋아요 영역 -->
            <!-- [s]레시피 소개  영역 -->
            <input type="hidden" name="recipeNo" value="${recipe.recipeNo}">
            <section class="r_info">
                <div class="r_info_img_area">
                	<c:forEach var="img" items="${imgList}">
                		<c:if test="${img.imgCate eq 1}">
                			<img class="r_info_img" src="${contextPath}/download.do?imgName=${img.imgName}&recipeNo=${recipe.recipeNo}">
                		</c:if>
                	</c:forEach>
                </div>
                <div class="r_info_ex_area">
                        <h1 class="r_info_ex_title">${recipe.recipeTitle}</h1>
                        <div class="r_info_ex_in" id="recipe_explain">
                            ${recipe.recipeIntro} 
                        </div>
                        <div class="r_info_sum flex_area_rsb">
                            <span class="r_in_num">
                                <i class="fa-solid fa-user-group"></i><br>
                                <span>
                                <c:choose>
                                	<c:when test="${recipe.infoVO.infoServ eq 'num1'}">1인분</c:when>
                                	<c:when test="${recipe.infoVO.infoServ eq 'num2'}">2인분</c:when>
                                	<c:when test="${recipe.infoVO.infoServ eq 'num3'}">3인분</c:when>
                                	<c:when test="${recipe.infoVO.infoServ eq 'num4'}">4인분 이상</c:when>
                                </c:choose>
                                </span>
                            </span>
                            <span class="r_in_time">
                                
                                <i class="fa-solid fa-clock"></i><br>
                                <span>
                                	<c:choose>
	                                	<c:when test="${recipe.infoVO.infoTime eq '5m'}">5분</c:when>
	                                	<c:when test="${recipe.infoVO.infoTime  eq '10m'}">10분</c:when>
	                                	<c:when test="${recipe.infoVO.infoTime eq '20m'}">20분</c:when>
	                                	<c:when test="${recipe.infoVO.infoTime eq '30m'}">30분</c:when>
                                		<c:when test="${recipe.infoVO.infoTime  eq '1h'}">1시간</c:when>
                                		<c:when test="${recipe.infoVO.infoTime  eq '2h'}">2시간 이상</c:when>
                               		</c:choose>
                                </span>
                            </span>
                            <span class="r_in_lev">
                                <i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><br>
                                <c:choose>
								    <c:when test="${recipe.infoVO.infoDiff eq 'lev1'}">아무나</c:when>
								    <c:when test="${recipe.infoVO.infoDiff eq 'lev2'}">초급</c:when>
								    <c:when test="${recipe.infoVO.infoDiff eq 'lev3'}">중급</c:when>
								    <c:when test="${recipe.infoVO.infoDiff eq 'lev4'}">고수</c:when>
								</c:choose>
                            </span>
                        </div>
                </div>
                <div class="writer_area">
                    <!-- background로 이미지 들어감 -->
                    <span class="writer_img"></span>
                    <span class="writer_id">${recipe.id}</span>
                    <span class="write_date">${recipe.writeDate}</span>
                   
                </div>
               
            </section>
            <div class="textline"></div>
            <!-- [e]레시피 소개 영역 -->
            <!-- [s]재료 영역 -->
            <section class="r_ing_area">
                <!-- recipe ingredient container -->
                <div class="r_ing_con">
                    <div class="r_txt">
                        <i class="fa-solid fa-utensils"></i>
                        재료
                    </div>
                    <div class="r_ings">
						<c:forEach var="matetype" items="${mateTypeList}">
	                    	<ul class="r_ing">
	                            <h3 class="ing_t">[${matetype.matetypeName}]</h3>
	                            <c:forEach var="mate" items="${materialList}">
	                            <c:if test="${matetype.matetypeNo eq mate.matetypeNo}">
                            	<li class="r_ing_c flex_area_rsb">
                           			<span class="r_ing_name">${mate.mateName}</span>
                                	<div class="txt_al_r">
	                                    <span  class="r_ing_amount">${mate.mateAmount}</span>
	                                </div>
                           		</li>
                           		</c:if>
                           		</c:forEach> 
	                        </ul>
                   		</c:forEach>
                    </div>
                </div>
            </section>
            <div class="textline"></div>
            <!-- [e]재료 영역 -->
            
            
            <!-- [s]동영상 영역 -->
            <c:if test="${recipe.videoLink != null}">
	            <section class="r_video">
	                <div class="r_video_container" >
	                    <div class="r_txt">
	                        <i class="fa-solid fa-video"></i>
	                         동영상
	                    </div>
	                    
	                    <div class="video_area">
	                        <iframe width="560" height="315" src="${recipe.videoLink}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
	                    </div>
	                </div>   
	            </section>
	            <div class="textline videoLine"></div>
            </c:if>
            <!-- [e]동영상 영역 -->
            
            <!-- [s]순서 영역 -->
            <section class="r_seq">
                <div class="r_txt">
                    <i class="fa-solid fa-kitchen-set"></i>
                    조리순서
                </div>
                <c:if test="${imgList.size() == 1}">
				            	<div class="noimg_title">
				            	
				            	</div>
				            </c:if>
				<div class="seq_content_area">
				    <div class="seq_content">
				        <c:forEach var="stepEx" items="${stepExList}" varStatus="status">
				            <h2 class="seq_num">Step${status.index + 1}</h2>
				            <span class="seq_txt">${stepEx.stepEx}</span>
				            
				            <c:if  test="${imgList.size() != 1}">
				            	<c:forEach var="img" items="${imgList}">
					                <c:if test="${img.imgCate eq 2 and img.stepNum eq status.index + 1}">
					                    <div class="seq_img">
					                        <img src="${contextPath}/download.do?imgName=${img.imgName}&recipeNo=${recipe.recipeNo}">
					                    </div>
					                </c:if>
				            	</c:forEach>
				            </c:if>
				            <c:if test="${imgList.size() == 1}">
				            	<div class="noimg_step">
				            	
				            	</div>
				            </c:if>
				            
				        </c:forEach>
				    </div>
				</div>
				
               
            </section>
            
            <!-- [e]순서 영역 -->

            <!-- [s]기타 영역 -->
            <c:if test="${recipe.recipeTip eq null}">
            	<div class="null_area"></div>
            </c:if>
            <c:if test="${recipe.recipeTip != null}">
            <div class="textline"></div>
            	<section class="r_ect ">
                <div class="r_txt">
                    <i class="fa-solid fa-triangle-exclamation"></i>
                    팁/주의사항
                </div>
                <p class="r_ect_content">${recipe.recipeTip}</p>            
	            </section>
            </c:if>
            
            <!--[e]기타 영역 -->
        </div>
        <!-- [e]레시피 등록 내용 영역 -->
    </div>
    <!-- [e]main_content 영역 -->
    <!-- [s]footer 영역 -->
	<jsp:include page="/views/fix/footer.jsp" />
	<!-- [e]footer 영역 -->
     <!--로그인 영역-->
    <jsp:include page="/views/fix/login.jsp" />
    <!--로그인 영역-->
    
    <script src="${contextPath}/views/js/common.js"></script>
    <script src="${contextPath}/views/community/js/recipe_spe.js"></script>
</body>
</html>