<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>   
<%
   request.setCharacterEncoding("utf-8");
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
    
    <!-- 공통X -->
     <link rel="stylesheet" href="${contextPath}/views/community/css/recipe_insert.css">
    
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
        <h1 class="t_area">
            레시피 등록
            <i class="fa-solid fa-pen-to-square"></i>
        </h1>
        <!-- [s]레시피 등록 내용 영역 -->
        <form action="${contextPath}/recipe/recinsert.do" method="post" enctype="multipart/form-data">
        	<input type="hidden" name="id" value="${log_id}">
            <div class="r_content_area">
                <!-- [s]레시피 소개 영역 -->
                <section class="r_info">
                    <div class="r_txt">레시피 제목</div>
                    <textarea maxlength="80" type="text" name="title" placeholder="예)김치찌개 끓이기" required></textarea>
                    <div class="r_in_photo ">
                        <label for="rec_file">
                            <div class="file_upload_btn">
                                요리 대표사진을 <br>
                               <span class="redtxt">반드시</span>  입력해주세요.<br>
                                <i class="fa-solid fa-image"></i>
                            </div>
                        </label>
                        <input type="file" data-ax-path="file" name="rec_file" id="rec_file" multiple="multiple" required="required">  
                    </div>
                    <div class="r_txt">요리 소개</div>
                    <textarea maxlength="100"  name="intro" placeholder="이 레시피에 관련된 소개를 적어주세요" required></textarea>
                    <div class="r_txt">동영상</div>
                    <textarea maxlength="1000" name="link" id="in_video_link" name="link" class="video cf" placeholder="레시피와 관련된 youtube 동영상이 있을 시 링크를 입력해주세요"></textarea>
                    <div class="r_in_video">
                        동영상 썸네일<br>
                        <i class="fa-solid fa-video"></i>   
                    </div>
                    <div class="r_txt">카테고리</div>
                    <select name="cate" id="r_in_category">
                        <option value="ko">한식</option>
                        <option value="ch">중식</option>
                        <option value="us">양식</option>
                        <option value="ja">일식</option>
                        <option value="bun">분식</option>
                        <option value="ba">베이커리</option>
                    </select>
                    <div class="r_txt cf">요리정보</div>
                    <div class="r_in_num">
                        <span class="r_info_txt">인원</span>
                        <select name="serv" id="r_in_num">
                            <option value="num1">1인분</option>
                            <option value="num2">2인분</option>
                            <option value="num3">3인분</option>
                            <option value="num4">4인분 이상</option>
                        </select>
                    </div>
                    <div class="r_in_time">
                        <span class="r_info_txt">시간</span>
	                    <select name="time" id="r_in_time">
	                        <option value="5m">5분</option>
	                        <option value="10m">10분</option>
	                        <option value="20m">20분</option>
	                        <option value="30m">30분</option>
	                        <option value="1h">1시간</option>
	                        <option value="2h">2시간 이상</option>
	                    </select>
                    </div>
                    <div class="r_in_lev">
                        <span class="r_info_txt">난이도</span>
                        <select name="diff" id="r_in_lev">
                        <option value="lev1">아무나</option>
                        <option value="lev2">초급</option>
                        <option value="lev3">중급</option>
                        <option value="lev4">고수</option>
                    </select>
                    </div>
                </section>
                <div class="textline"></div>
                <!-- [e]레시피 소개 영역 -->

                <!-- [s]재료 영역 -->
                <section class="r_ing_container ">
                    <div class="r_ing flex_area_rsb">
                        <div class="r_txt">재료등록</div>
                        <div class="ing_category">
                            <input name="matetype" type="text" placeholder="재료" required><br>
                            <button type="button" class="btn_ing_del_all">
                                <i class="fa-solid fa-xmark"></i>
                                묶음삭제
                            </button>
                        </div>
                        <div class="r_ing_content">
                            <div class="ing_name_amount">
                                <div class="ing_input_area">
                                    <input name="mate_name" type="text" placeholder="예)돼지고기"  required>
                                    <input name="mate_amount" type="text" placeholder="예)300g"    required>  
                                </div>
                                
                                <div class="ing_input_area">
                                    <input  name="mate_name" type="text" placeholder="예)양배추"    required>
                                    <input  name="mate_amount" type="text" placeholder="예)1/2개"    required>  
                                    <div class="btn_del">
                                        <button type="button">
                                            <i class="fa-solid fa-circle-xmark"></i>
                                        </button>
                                    </div>
                                </div>
                                
                             <div class="ing_input_area">
                                    <input  name="mate_name" type="text" placeholder="예)참기름" required>
                                    <input  name="mate_amount" type="text" placeholder="예)1T" required>
                                    <div class="btn_del">
                                        <button type="button">
                                            <i class="fa-solid fa-circle-xmark"></i>
                                        </button>
                                    </div>
                             </div>  
                                <button type="button" name="btn_add" class="btn_add_s">
                                    <i class="fa-solid fa-circle-plus"></i>
                                    추가
                                </button>
                            </div>
                        </div>
                        
                    </div>
                    <div class="r_ing_ex_area">
                        <div class="textline "></div>
                        <p class="r_in_ex">※양념, 양념장, 소스, 드레싱, 토핑, 시럽, 육수, 밑간 등으로 구분해서 작성해주세요.</p>
                        <button type="button" class="btn_add">재료/양념 묶음 추가</button>
                    </div>
                </section>
                <div class="textline"></div>
                <!-- [e]재료 영역 -->
                
                <!-- [s]순서 영역 -->
                <section class="r_seq">
                    <div class="seq_content_area flex_area_rsb">
                        <div class="r_txt ">요리순서</div>
                        <h2>Step<span class="stepNum">1</span></h2>
                        <textarea  maxlength="200" name="step_ex" id="step_ex" cols="30" rows="10" placeholder="예)소고기는 기름기를 떼어내고 적당한 크기로 썰어주세요." required></textarea>
                        <div class="seq_step_pho seq_step_pho1">
                            <label for="rec_file_step1">
                                <div class="file_upload_btn">
                                    <i class="fa-solid fa-plus"></i>
                                </div> 
                            </label>
                            <input type="file" data-ax-path="file" name="rec_file_step1" id="rec_file_step1" multiple="multiple"> 
                        </div>
                    </div>
                    <div class="btn_add_area">
                        <button type="button" class="btn_add">순서 추가</button>
                    </div>
                    
                    
                </section>
                <div class="textline"></div>
                <!-- [e]순서 영역 -->

                <!-- [s]기타 영역 -->
                <section class="r_ect flex_area_rsb">
                    <div class="r_txt">요리팁</div>
                    <textarea maxlength="200" name="tip" id="" cols="30" rows="10" placeholder="예)고기요리에는 소금보다 설탕을 먼저 넣어야 단맛이 겉돌지 않고 육질이 부드러워요"></textarea>
                </section>
                <div class="textline"></div>
                <!--[e]기타 영역 -->
            
            </div>
        
        
        <!-- [e]레시피 등록 내용 영역 -->

        <!-- [s]버튼 영역 -->
        <section class="btn_r_insert">
            <input class="btn_in" type="submit" value="게시하기">
            <input class="btn_can" type="reset" value="취소">
        </section>
        <!-- [e]버튼 영역 -->
        </form>
    </div>
    <!-- [e]main_content 영역 -->
    <!-- [s]footer 영역 -->
	<jsp:include page="/views/fix/footer.jsp" />
	<!-- [e]footer 영역 -->
    <!--로그인 영역-->
    <jsp:include page="/views/fix/login.jsp" />
    <!--로그인 영역-->
    <script src="${contextPath}/views/js/common.js"></script>
    <script src="${contextPath}/views/community/js/insert.js"></script>
</body>
</html>