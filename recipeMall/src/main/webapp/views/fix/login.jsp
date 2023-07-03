<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div id="modal">
        <div class="loginArea">
            <form name="frm_login" action="${contextPath}/loginProc/login" method="post">
                <fieldset>
                    <legend>입력 칸</legend>
                    <div class="loginbox">
                        <p>
                            <label for="id"></label>
                            <input type="text" id="id" name="id" placeholder="아이디" required>
                        </p>
                        <p>
                            <label for="pass"></label>
                            <input type="password" id="pass" name="pass" placeholder="비밀번호" required>
                        </p>
                        <label for="login" class="loggin"><input type="checkbox" name="login" id="login"><span>로그인 상태 유지</span></label>
                        <div class="login_check"></div>
                        <button type="submit" class="green" >로그인</button>    
                        <p class="underBar">또는</p>
                        <a id="naverIdLogin_loginButton" class="naver" href="javascript:void(0)">
                            <span>네이버 로그인</span>
                        </a>
                        <a id="kakao-login-btn" class="kakao_login" href="javascript:loginWithKakao()">
                        <img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="222" height="50"
                            alt="카카오 로그인 버튼" />
                        </a>
                        <input type="hidden" id="token" name="token" value="">
                        <p id="token-result"></p>
                        <a href="${contextPath}/user/joinForm.do">회원가입</a>
                        <a href="${contextPath}/user/find.do?kind=id">아이디찾기</a>
                        <a href="${contextPath}/user/find.do?kind=pw">비밀번호 찾기</a>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>