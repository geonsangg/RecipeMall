<%@page import="java.util.Vector"%>
<%@page import="recipeMall.board.FreeBoardDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="recipeMall.board.FreeBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="boardList" value="${boardMap.boardList}"/>
<c:set var="totArticles" value="${boardMap.totArticles}"/>
<c:set var="section" value="${boardMap.section}"/>
<c:set var="pageNum" value="${boardMap.pageNum}"/>
<c:set var="ep" value="${totArticles mod 50}"/>
<c:choose>
   <c:when test="${section > totArticles/50}">
      <c:set var="endPage" value="${(ep%5)==0?ep/5:ep/5+1}"/>
   </c:when>
   <c:otherwise>
      <c:set var="endPage" value="10"/>
   </c:otherwise>
</c:choose>


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
    <script src="https://kit.fontawesome.com/54880b83c5.js" crossorigin="anonymous"></script>
    <script src="${contextPath}/views/js/jquery-3.6.4.min.js"></script>
	<script src="${contextPath}/views/js/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="${contextPath}/views/css/common.css">
    <link rel="stylesheet" href="${contextPath}/viewsl/css/index.css">
    <link rel="stylesheet" href="${contextPath}/views/community/css/free_board.css">
    <link rel="shortcut icon" href="${contextPath}/views/images/dish4.png" /> <!-- 일단 이거 이미지 없음 -->
    
    <style type="text/css">
    	.nolist {
    		height: 20px;
    	}
    </style>
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
	
    <!--[s]자유 게시판 영역-->
    <div class="free_board_area">
        <!--[s] 자유게시판 타이틀-->
        <div class="title_free_board">
            <h1>자유게시판 <i class="fa-regular fa-pen-to-square"></i></h1>
        </div>
        <div class="title_sub">
            <h4>자유롭게 회원들과 소통합시다</h4>
            <h4>*폭언 욕설은 블랙리스트를 당할 수 있습니다.*</h4>
        </div>

        <!--[e] 자유게시판 타이틀-->
        <div class="free_board_search">
            <select name="free_search" id="free_search" class="free_option">
                <option value="제목">제목</option>
                <option value="제목">제목+내용</option>
            </select>
            <div class="free_main_search">
                <input type="text" name="free_main_search_area">
            </div>
            <div class="free_main_btn">
                <button onsubmit="" class="">검색</button>
            </div>
        </div>

        <!--[e] 자유게시판 타이틀-->
        <div class="board_wrap">

            <div class="board_list_wrap">
                <div class="board_list">
                    <div class="top">
                        <div class="num" >번호</div>
                        <div class="title">제목</div>
                        <div class="writer">닉네임</div>
                        <div class="date">작성일</div>
                        <div class="count">조회</div>

                    </div>
				<c:choose>
					<c:when test="${!empty boardList}">
						<c:forEach var="list" items="${boardList}" varStatus="boardNum">
					<!-- <td width="5%">${(pageNum-1)*10+boardNum.count}</td>  -->
		                    <div>
		                        <div class="num">${list.boardNo}</div>
		                        <div class="title"><a href="${contextPath}/free/view.do?boardNo=${list.boardNo}">${list.boardName}</a></div>
		                        <div class="writer">${list.id}</div>
		                        <div class="date">${list.writeDate }</div>
		                        <div class="count">${list.cntView }</div>
		                    </div>
                    </c:forEach>
					</c:when>
					<c:when test="${empty boardList}">
						<div class="nolist">글이 없습니다.</div>
					</c:when>
				</c:choose>
              </div>
				      <div class="center2" align="center">
						<c:if test="${totArticles != 0 }"> <!-- 글이 0개가 아니면  -->
							<c:choose>
								<c:when test="${totArticles > 50}">
									 <c:forEach var="page" begin="1" end="${endPage}" step="1">
						                  <c:if test="${section > 1 && page == 1}">
						                     <a href="${contextPath}/free/free_board.do?section=${section-1}&pageNum=10"><i class="fa-solid fa-caret-left"></i></a>
						                  </c:if>
						                  <c:choose>
						                     <c:when test="${pageNum==page}">
						                        <a class="selPage"  href="${contextPath}/free/free_board.do?section=${section}&pageNum=${page}">${(section-1)*5+page}</a>                     
						                     </c:when>
						                     <c:otherwise>
						                        <a class="noLine" href="${contextPath}/free/free_board.do?section=${section}&pageNum=${page}">${(section-1)*5+page}</a>
						                     </c:otherwise>
						                  </c:choose>
						                  <c:if test="${page == 10 && totArticles/50>section}">
						                     <a href="${contextPath}/free/free_board.do?section=${section+1}&pageNum=1"><i class="fa-solid fa-caret-right"></i></a>
						                  </c:if>
						               </c:forEach>
						 		</c:when>	
								 <c:when test="${totArticles <= 50 }">
									<c:if test="${(totArticles mod 5) == 0}">
				                  		<c:set var="totArticles" value="${totArticles-1}"/>
				               		</c:if>
									<c:forEach var="page" begin="1" end="${totArticles/5+1}" step="1">
										<c:choose>
											<c:when test="${page==pageNum}">
												<a class="selPage" href="${contextPath}/free/free_board.do?secton=${section}&pageNum=${page}">${page}</a>
											</c:when>
											<c:otherwise>
												<a class="noLine" href="${contextPath}/free/free_board.do?secton=${section}&pageNum=${page}">${page}</a>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:when>
							</c:choose>
						</c:if> 
					</div>
            </div>
        </div>
    </div>

    <!--[e]자유 게시판 영역-->
     <!-- [s]footer 영역 -->
	<jsp:include page="/views/fix/footer.jsp" />
	<!-- [e]footer 영역 -->
    
    
     <!--로그인 영역-->
     <div id="modal">
        <div class="loginArea">
            <form action="${contextPath}/user/addUser.do" method="post">
                <fieldset>
                    <legend>입력 칸</legend>
                    <div class="loginbox">
                            <p>
                                <label for="id"></label>
                                <input type="text" id="id" name="id" placeholder="아이디" required>
                            </p><p>
                                <label for="pass"></label>
                                <input type="password" id="pass" name="pass" placeholder="비밀번호" required>
                            </p>
                            <label for="login" class="loggin"><input type="checkbox" name="login" id="login"><span>로그인 상태 유지</span></label>
                            <button type="submit" class="green">로그인</button>    
                            <p class="underBar">또는</p>
                            <a id="naverIdLogin_loginButton" class="naver" href="javascript:void(0)">
                                <span>네이버 로그인</span>
                            </a>
                            <a id="kakao-login-btn" class="kakao_login" href="javascript:loginWithKakao()">
                            <img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="222" height="50"
                                alt="카카오 로그인 버튼" />
                            </a>
                            <p id="token-result"></p>
                            <a href="${contextPath}/RecipeMall/join.jsp">회원가입</a>
                            <a href="#">계정찾기</a>
                            <a href="#">비밀번호 찾기</a>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
    <script src="${contextPath}/views/js/login.js"></script>
    <script src="${contextPath}/views/js/naver_login.js"></script>
    <script src="${contextPath}/views/js/kakao_login.js"></script>
    <link rel="stylesheet" href="${contextPath}/views/css/login.css">
    <script src="${contextPath}/views/js/common.js"></script>
    <script src="${contextPath}/views/js/index.js"></script>
 
</body>
	
</body>
</html>