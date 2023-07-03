<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("utf-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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
<script src="${contextPath}/views/js/find-doro-name.js"></script>
<link rel="stylesheet" href="${contextPath}/views/css/join.css">
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage.css">
<link rel="stylesheet" href="${contextPath}/views/css/common.css">
<link rel="shortcut icon" href="${contextPath}/views/images/smalllogo.png" />
<script type="text/javascript">
/* 아이디 중복 확인 */
function fn_idExisted() {
	let _id=$('#user_id').val();
	
	if(_id == "") {
		alert('아이디를 입력해주세요.');
		return;
	} else {
		$.ajax({
			type: "post",
			async: true,
			dataType: "text",
			url: "${contextPath}/user/idExisted.do",
			data: {id:_id},
			success: function(data, textStatus) {
				if(data=="false") {
					$('.id_ck').html('아이디를 사용할 수 있습니다.');
				} else {
					$('.id_ck').html('입력된 아이디는 사용할 수 없습니다.').css('color','red');
				}
			},
			error: function(data, textStatus, error) {
				alert('오류가 발생했습니다 => '+error);
			}
		});
	}
}
</script>
</head>
<body>
    <!-- content_mypage_s -->
    <div id="main_content" class="mypage_box">
        <div id="mypage">
            
            <!-- mypage_content -->
            <div class="mypage_content">
                <h3 class="subtitle">회원가입</h3>
                <form id="join_form" action="${contextPath}/user/addUser.do" method="post">
					<div class="data_input">
						<p>
							<label for="user_name">이름</label>
							<input type="text" id="user_name" name="user_name" required>
						</p>
						<p>
							<label for="user_id">아이디</label>
							<input type="text" id="user_id" name="user_id" required>
							<input type="button" class="btn_userjoin" value="중복 확인" onclick="fn_idExisted()">
						</p>
						<p class="id_ck"></p>
						<p>
							<label for="user_pwd">비밀번호</label>
							<p class="pwd_placeholder">소문자, 대문자, 특수문자 포함 8자리 이상 입력</p>
							<input type="password" id="user_pwd" name="user_pwd" required minlength="8">
						</p>
						<p>
							<label for="user_pwd_check">비밀번호 확인</label>
							<input type="password" id="user_pwd_check" name="user_pwd_check" required minlength="8">
						</p>
						<p class="pw_e">비밀번호가 일치합니다.</p>
						<p class="pw_ne">비밀번호가 일치하지 않습니다. 다시 입력해주세요.</p>
						<p>
							<label for="user_nickname">닉네임</label>
							<input type="text" id="user_nickname" name="user_nickname" required>
						</p>
						<p>
							<label for="user_phone">휴대폰 번호</label>
							<input type="text" id="user_phone" name="user_phone" placeholder="- 없이 입력 예)01012345678" minlength="9" maxlength="11" required>
							<input type="button" class="btn_userjoin" value="인증번호 받기" onclick="">
						</p>
						<p>
							<label for="user_addr1">주소</label>
							<input type="text" id="zip-code" name="zip-code">
							<input type="button" class="btn_useraddr" value="우편번호 검색" onclick="execDaumPostcode()">
							<input type="text" id="address" class="user_addr" name="user_addr1" placeholder="주소">
							<input type="text" id="detailAddress" class="user_addr" name="user_addr2" placeholder="세부주소쓰기 ex)123동 456호">
							<input type="hidden" id="sample6_extraAddress" placeholder="참고항목">
						</p>
						<p>
							<label for="user_recommend">추천인</label>
							<input type="text" id="user_recommend" name="user_recommend">
							<p class="recommend_p">추천인 입력시 <span>500포인트</span> 지급!!</p>
						</p>
					</div>
	                <div id="agree_check">
	                    <p>약관동의</p>
	                    <label for="all_check">전체 동의</label>
	                    <input type="checkbox" name="all_check" id="all_check">
	                    <div class="agree checkdiv1">
	                        <label for="check1">동의하기</label>
	                        <input type="checkbox" name="check1" class="agreechk" required>
	                        <div class="treaty_area">
	                            <p class="treaty">개인정보보호법에 따라 레시피몰에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 개인정보의 보유 및 이용기간, 동의 거부권 및 동의 거부 시 불이익에 관한 사항을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다.
	                                1. 수집하는 개인정보
	                                이용자는 회원가입을 하지 않아도 정보 검색, 뉴스 보기 등 대부분의 레시피몰 서비스를 회원과 동일하게 이용할 수 있습니다. 이용자가 메일, 캘린더, 카페, 블로그 등과 같이 개인화 혹은 회원제 서비스를 이용하기 위해 회원가입을 할 경우, 레시피몰는 서비스 이용을 위해 필요한 최소한의 개인정보를 수집합니다.
	                                
	                                회원가입 시점에 레시피몰가 이용자로부터 수집하는 개인정보는 아래와 같습니다.
	                                - 회원 가입 시 필수항목으로 아이디, 비밀번호, 이름, 생년월일, 성별, 휴대전화번호를, 선택항목으로 본인확인 이메일주소를 수집합니다.
	                                단, 비밀번호 없이 회원 가입 시에는 필수항목으로 아이디, 이름, 생년월일, 휴대전화번호를, 선택항목으로 비밀번호를 수집합니다.
	                                만약 이용자가 입력하는 생년월일이 만14세 미만 아동일 경우에는 법정대리인 정보(법정대리인의 이름, 생년월일, 성별, 중복가입확인정보(DI), 휴대전화번호)를 추가로 수집합니다.
	                                - 단체아이디로 회원가입 시 단체아이디, 비밀번호, 단체이름, 이메일주소, 휴대전화번호를 필수항목으로 수집합니다. 그리고 단체 대표자명을 선택항목으로 수집합니다.
	                                서비스 이용 과정에서 이용자로부터 수집하는 개인정보는 아래와 같습니다.
	                                - 회원정보 또는 개별 서비스에서 프로필 정보(별명, 프로필 사진)를 설정할 수 있습니다. 회원정보에 별명을 입력하지 않은 경우에는 마스킹 처리된 아이디가 별명으로 자동 입력됩니다.
	                                
	                                - 레시피몰 내의 개별 서비스 이용, 이벤트 응모 및 경품 신청 과정에서 해당 서비스의 이용자에 한해 추가 개인정보 수집이 발생할 수 있습니다. 추가로 개인정보를 수집할 경우에는 해당 개인정보 수집 시점에서 이용자에게 ‘수집하는 개인정보 항목, 개인정보의 수집 및 이용목적, 개인정보의 보관기간’에 대해 안내 드리고 동의를 받습니다.
	                                서비스 이용 과정에서 IP 주소, 쿠키, 서비스 이용 기록, 기기정보, 위치정보가 생성되어 수집될 수 있습니다. 또한 이미지 및 음성을 이용한 검색 서비스 등에서 이미지나 음성이 수집될 수 있습니다.
	                                구체적으로 1) 서비스 이용 과정에서 이용자에 관한 정보를 자동화된 방법으로 생성하여 이를 저장(수집)하거나,
	                                2) 이용자 기기의 고유한 정보를 원래의 값을 확인하지 못 하도록 안전하게 변환하여 수집합니다. 서비스 이용 과정에서 위치정보가 수집될 수 있으며,
	                                레시피몰에서 제공하는 위치기반 서비스에 대해서는 '레시피몰 위치기반서비스 이용약관'에서 자세하게 규정하고 있습니다.
	                                이와 같이 수집된 정보는 개인정보와의 연계 여부 등에 따라 개인정보에 해당할 수 있고, 개인정보에 해당하지 않을 수도 있습니다.
	                                
	                                2. 수집한 개인정보의 이용
	                                레시피몰 및 레시피몰 관련 제반 서비스(모바일 웹/앱 포함)의 회원관리, 서비스 개발・제공 및 향상, 안전한 인터넷 이용환경 구축 등 아래의 목적으로만 개인정보를 이용합니다.
	                                
	                                - 회원 가입 의사의 확인, 연령 확인 및 법정대리인 동의 진행, 이용자 및 법정대리인의 본인 확인, 이용자 식별, 회원탈퇴 의사의 확인 등 회원관리를 위하여 개인정보를 이용합니다.
	                                - 콘텐츠 등 기존 서비스 제공(광고 포함)에 더하여, 인구통계학적 분석, 서비스 방문 및 이용기록의 분석, 개인정보 및 관심에 기반한 이용자간 관계의 형성, 지인 및 관심사 등에 기반한 맞춤형 서비스 제공 등 신규 서비스 요소의 발굴 및 기존 서비스 개선 등을 위하여 개인정보를 이용합니다.
	                                - 법령 및 레시피몰 이용약관을 위반하는 회원에 대한 이용 제한 조치, 부정 이용 행위를 포함하여 서비스의 원활한 운영에 지장을 주는 행위에 대한 방지 및 제재, 계정도용 및 부정거래 방지, 약관 개정 등의 고지사항 전달, 분쟁조정을 위한 기록 보존, 민원처리 등 이용자 보호 및 서비스 운영을 위하여 개인정보를 이용합니다.
	                                - 유료 서비스 제공에 따르는 본인인증, 구매 및 요금 결제, 상품 및 서비스의 배송을 위하여 개인정보를 이용합니다.
	                                - 이벤트 정보 및 참여기회 제공, 광고성 정보 제공 등 마케팅 및 프로모션 목적으로 개인정보를 이용합니다.
	                                - 서비스 이용기록과 접속 빈도 분석, 서비스 이용에 대한 통계, 서비스 분석 및 통계에 따른 맞춤 서비스 제공 및 광고 게재 등에 개인정보를 이용합니다.
	                                - 보안, 프라이버시, 안전 측면에서 이용자가 안심하고 이용할 수 있는 서비스 이용환경 구축을 위해 개인정보를 이용합니다.
	                                3. 개인정보의 보관기간
	                                회사는 원칙적으로 이용자의 개인정보를 회원 탈퇴 시 지체없이 파기하고 있습니다.
	                                단, 이용자에게 개인정보 보관기간에 대해 별도의 동의를 얻은 경우, 또는 법령에서 일정 기간 정보보관 의무를 부과하는 경우에는 해당 기간 동안 개인정보를 안전하게 보관합니다.
	                                
	                                이용자에게 개인정보 보관기간에 대해 회원가입 시 또는 서비스 가입 시 동의를 얻은 경우는 아래와 같습니다.
	                                - 부정 가입 및 이용 방지
	                                부정 이용자의 가입인증 휴대전화번호 또는 DI (만14세 미만의 경우 법정대리인DI) : 탈퇴일로부터 6개월 보관
	                                탈퇴한 이용자의 휴대전화번호(복호화가 불가능한 일방향 암호화(해시처리)) : 탈퇴일로부터 6개월 보관
	                                - QR코드 복구 요청 대응
	                                QR코드 등록 정보:삭제 시점으로부터6개월 보관
	                                - 스마트플레이스 분쟁 조정 및 고객문의 대응
	                                휴대전화번호:등록/수정/삭제 요청 시로부터 최대1년
	                                - 레시피몰 플러스 멤버십 서비스 혜택 중복 제공 방지
	                                암호화처리(해시처리)한DI :혜택 제공 종료일로부터6개월 보관
	                                - 지식iN eXpert 재가입 신청 및 부정 이용 방지
	                                eXpert 서비스 및 eXpert 센터 가입 등록정보 : 신청일로부터 6개월(등록 거절 시, 거절 의사 표시한 날로부터 30일) 보관
	                                전자상거래 등에서의 소비자 보호에 관한 법률, 전자문서 및 전자거래 기본법, 통신비밀보호법 등 법령에서 일정기간 정보의 보관을 규정하는 경우는 아래와 같습니다. 레시피몰는 이 기간 동안 법령의 규정에 따라 개인정보를 보관하며, 본 정보를 다른 목적으로는 절대 이용하지 않습니다.
	                                - 전자상거래 등에서 소비자 보호에 관한 법률
	                                계약 또는 청약철회 등에 관한 기록: 5년 보관
	                                대금결제 및 재화 등의 공급에 관한 기록: 5년 보관
	                                소비자의 불만 또는 분쟁처리에 관한 기록: 3년 보관
	                                - 전자문서 및 전자거래 기본법
	                                공인전자주소를 통한 전자문서 유통에 관한 기록 : 10년 보관
	                                - 전자서명 인증 업무
	                                인증서와 인증 업무에 관한 기록: 인증서 효력 상실일로부터 10년 보관
	                                - 통신비밀보호법
	                                로그인 기록: 3개월
	                                
	                                참고로 레시피몰는 ‘개인정보 유효기간제’에 따라 1년간 서비스를 이용하지 않은 회원의 개인정보를 별도로 분리 보관하여 관리하고 있습니다.
	                                
	                                4. 개인정보 수집 및 이용 동의를 거부할 권리
	                                이용자는 개인정보의 수집 및 이용 동의를 거부할 권리가 있습니다. 회원가입 시 수집하는 최소한의 개인정보, 즉, 필수 항목에 대한 수집 및 이용 동의를 거부하실 경우, 회원가입이 어려울 수 있습니다.</p>
	                        </div>
	                    </div>
	                    <div class="agree checkdiv2">
	                        <label for="check2">동의하기</label>
	                        <input type="checkbox" name="check2" class="agreechk">
	                        <div class="treaty_area">
	                            <p class="treaty">위치기반서비스 이용약관에 동의하시면, 위치를 활용한 광고 정보 수신 등을 포함하는 레시피몰 위치기반 서비스를 이용할 수 있습니다.
	                                제 1 조 (목적)
	                                이 약관은 레시피몰 주식회사 (이하 “회사”)가 제공하는 위치기반서비스와 관련하여 회사와 개인위치정보주체와의 권리, 의무 및 책임사항, 기타 필요한 사항을 규정함을 목적으로 합니다.
	                                
	                                제 2 조 (약관 외 준칙)
	                                이 약관에 명시되지 않은 사항은 위치정보의 보호 및 이용 등에 관한 법률, 개인정보보호법, 정보통신망 이용촉진 및 정보보호 등에 관한 법률, 전기통신기본법, 전기통신사업법 등 관계법령과 회사의 이용약관 및 개인정보처리방침, 회사가 별도로 정한 지침 등에 의합니다.
	                                
	                                제 3 조 (서비스 내용 및 요금)
	                                ① 회사는 위치정보사업자로부터 위치정보를 전달받아 아래와 같은 위치기반서비스를 제공합니다.
	                                
	                                1. GeoTagging 서비스: 게시물 또는 이용자가 저장하는 콘텐츠에 포함된 개인위치정보주체 또는 이동성 있는 기기의 위치정보가 게시물과 함께 저장됩니다. 저장된 위치정보는 별도의 활용없이 보관되거나, 또는 장소를 기반으로 콘텐츠를 분류하거나 검색할 수 있는 기능이 제공될 수도 있습니다.
	                                2. 위치정보를 활용한 검색결과 및 콘텐츠 제공 : 정보 검색을 요청하거나 개인위치정보주체 또는 이동성 있는 기기의 위치정보를 제공 시 본 위치정보를 이용한 검색결과, 주변결과(맛집, 주변업체, 교통수단 등), 번역결과를 제시합니다.
	                                3. 이용자 위치를 활용한 광고정보 제공: 검색결과 또는 기타 서비스 이용 과정에서 개인위치정보주체 또는 이동성 있는 기기의 위치를 이용하여 광고소재를 제시합니다.
	                                4. 이용자 보호 및 부정 이용 방지: 개인위치정보주체 또는 이동성 있는 기기의 위치를 이용하여 권한없는 자의 비정상적인 서비스 이용 시도 등을 차단합니다.
	                                5. 길 안내 등 생활편의 서비스 제공: 교통정보와 길 안내 등 최적의 경로를 지도로 제공하며, 주변 시설물 찾기, 뉴스/날씨 등 생활정보, 긴급구조 서비스, 주소 자동 입력 등 다양한 운전 및 생활 편의 서비스를 제공합니다.
	                                ② 제1항 위치기반서비스의 이용요금은 무료입니다.
	                                제 4 조 (개인위치정보주체의 권리)
	                                ① 개인위치정보주체는 개인위치정보 수집 범위 및 이용약관의 내용 중 일부 또는 개인위치정보의 이용ㆍ제공 목적, 제공받는 자의 범위 및 위치기반서비스의 일부에 대하여 동의를 유보할 수 있습니다.
	                                ② 개인위치정보주체는 개인위치정보의 수집ㆍ이용ㆍ제공에 대한 동의의 전부 또는 일부를 철회할 수 있습니다.
	                                ③ 개인위치정보주체는 언제든지 개인위치정보의 수집ㆍ이용ㆍ제공의 일시적인 중지를 요구할 수 있습니다. 이 경우 회사는 요구를 거절하지 아니하며, 이를 위한 기술적 수단을 갖추고 있습니다
	                                ④ 개인위치정보주체는 회사에 대하여 아래 자료의 열람 또는 고지를 요구할 수 있고, 당해 자료에 오류가 있는 경우에는 그 정정을 요구할 수 있습니다. 이 경우 회사는 정당한 이유 없이 요구를 거절하지 아니합니다.
	                                1. 개인위치정보주체에 대한 위치정보 수집ㆍ이용ㆍ제공사실 확인자료
	                                2. 개인위치정보주체의 개인위치정보가 위치정보의 보호 및 이용 등에 관한 법률 또는 다른 법령의 규정에 의하여 제3자에게 제공된 이유 및 내용
	                                ⑤ 회사는 개인위치정보주체가 동의의 전부 또는 일부를 철회한 경우에는 지체 없이 수집된 개인위치정보 및 위치정보 수집ㆍ이용ㆍ제공사실 확인자료를 파기합니다.단, 동의의 일부를 철회하는 경우에는 철회하는 부분의 개인위치정보 및 위치정보 수집ㆍ이용ㆍ제공사실 확인자료에 한합니다.
	                                ⑥ 개인위치정보주체는 제1항 내지 제4항의 권리행사를 위하여 이 약관 제13조의 연락처를 이용하여 회사에 요구할 수 있습니다.
	                                제 5 조 (법정대리인의 권리)
	                                ① 회사는 만14세 미만 아동으로부터 개인위치정보를 수집ㆍ이용 또는 제공하고자 하는 경우에는 만14세 미만 아동과 그 법정대리인의 동의를 받아야 합니다.
	                                ② 법정대리인은 만14세 미만 아동의 개인위치정보를 수집ㆍ이용ㆍ제공에 동의하는 경우 동의유보권, 동의철회권 및 일시중지권, 열람ㆍ고지요구권을 행사할 수 있습니다.
	                                제 6 조 (위치정보 이용·제공사실 확인자료 보유근거 및 보유기간)
	                                회사는 위치정보의 보호 및 이용 등에 관한 법률 제16조 제2항에 근거하여 개인위치정보주체에 대한 위치정보 수집·이용·제공사실 확인자료를 위치정보시스템에 자동으로 기록하며, 6개월 이상 보관합니다.
	                                제 7 조 (서비스의 변경 및 중지)
	                                ① 회사는 위치기반서비스사업자의 정책변경 등과 같이 회사의 제반 사정 또는 법률상의 장애 등으로 서비스를 유지할 수 없는 경우, 서비스의 전부 또는 일부를 제한, 변경하거나 중지할 수 있습니다.
	                                ② 제1항에 의한 서비스 중단의 경우에는 회사는 사전에 인터넷 등에 공지하거나 개인위치정보주체에게 통지합니다.
	                                제 8 조 (개인위치정보 제3자 제공 시 즉시 통보)
	                                ① 회사는 개인위치정보주체의 동의 없이 당해 개인위치정보주체의 개인위치정보를 제3자에게 제공하지 아니하며, 제3자 제공 서비스를 제공하는 경우에는 제공받는 자 및 제공목적을 사전에 개인위치정보주체에게 고지하고 동의를 받습니다.
	                                ② 회사는 개인위치정보를 개인위치정보주체가 지정하는 제3자에게 제공하는 경우에는 개인위치정보를 수집한 당해 통신단말장치로 매회 개인위치정보주체에게 제공받는 자, 제공일시 및 제공목적을 즉시 통보합니다.
	                                ③ 다만, 아래에 해당하는 경우에는 개인위치정보주체가 미리 특정하여 지정한 통신단말장치 또는 전자우편주소 등으로 통보합니다.
	                                
	                                1.개인위치정보를 수집한 당해 통신단말장치가 문자, 음성 또는 영상의 수신기능을 갖추지 아니한 경우
	                                2.개인위치정보주체가 개인위치정보를 수집한 당해 통신단말장치 외의 통신단말장치 또는 전자우편주소 등으로 통보할 것을 미리 요청한 경우
	                                제 9 조 (8세 이하의 아동 등의 보호의무자의 권리)
	                                ① 회사는 아래의 경우에 해당하는 자(이하 “8세 이하의 아동 등"이라 함)의 보호의무자가 8세 이하의 아동 등의 생명 또는 신체보호를 위하여 개인위치정보의 이용 또는 제공에 동의하는 경우에는 본인의 동의가 있는 것으로 봅니다.
	                                
	                                1. 8세 이하의 아동
	                                2. 피성년후견인
	                                3. 장애인복지법 제2조제2항제2호의 규정에 의한 정신적 장애를 가진 자로서 장애인고용촉진 및 직업재활법 제2조제2호의 규정에 의한 중증장애인에 해당하는 자(장애인복지법 제32조의 규정에 의하여 장애인등록을 한 자에 한정)
	                                ② 8세 이하의 아동 등의 생명 또는 신체의 보호를 위하여 개인위치정보의 이용 또는 제공에 동의를 하고자 하는 보호의무자는 서면동의서에 보호의무자임을 증명하는 서면을 첨부하여 회사에 제출하여야 합니다.
	                                ③ 보호의무자는 8세 이하의 아동 등의 개인위치정보 이용 또는 제공에 동의하는 경우 개인위치정보주체 권리의 전부를 행사할 수 있습니다
	                                제 10 조 (개인위치정보의 보유목적 및 이용기간)
	                                ① 회사는 위치기반서비스를 제공하기 위해 필요한 최소한의 기간 동안 개인위치정보를 보유 및 이용합니다.
	                                ② 회사는 대부분의 위치기반서비스에서 개인위치정보를 일회성 또는 임시로 이용 후 지체없이 파기합니다. 단, ‘GeoTagging’ 서비스와 같이 이용자가 게시물, 콘텐츠와 함께 개인위치정보를 레시피몰 서비스에 게시 또는 보관하는 경우, 해당 게시물, 콘텐츠의 보관기간 동안 개인위치정보가 함께 보관됩니다.
	                                제 11 조 (손해배상)
	                                개인위치정보주체는 회사의 위치정보의 보호 및 이용 등에 관한 법률 제15조 내지 26조의 규정을 위반한 행위로 손해를 입은 경우에 회사에 대하여 손해배상을 청구할 수 있습니다. 이 경우 회사는 고의 또는 과실이 없음을 입증하지 아니하면 책임을 면할 수 없습니다.
	                                제 12 조 (분쟁의 조정)
	                                ① 회사는 위치정보와 관련된 분쟁에 대하여 개인위치정보주체와 협의가 이루어지지 아니하거나 협의를 할 수 없는 경우에는 방송통신위원회에 재정을 신청할 수 있습니다.
	                                ② 회사 또는 개인위치정보주체는 위치정보와 관련된 분쟁에 대해 당사자간 협의가 이루어지지 아니하거나 협의를 할 수 없는 경우에는 개인정보보호법에 따라 개인정보분쟁조정위원회에 조정을 신청할 수 있습니다.
	                                제 13 조 (사업자 정보 및 위치정보 관리책임자)
	                                ① 회사의 상호, 주소 및 연락처는 다음과 같습니다.
	                                
	                                - 상호: 레시피몰주식회사
	                                - 주소: 경기도 성남시 분당구 정자일로 95, NAVER 1784 (우)13561
	                                - 전화번호: 1588-3820
	                                ② 회사는 다음과 같이 위치정보 관리책임자를 지정하여 이용자들이 서비스 이용과정에서 발생한 민원사항 처리를 비롯하여 개인위치정보주체의 권리 보호를 위해 힘쓰고 있습니다.
	                                
	                                - 위치정보 관리책임자 : 이진규 CPO / DPO (개인정보 보호책임자 겸직)
	                                - 메일 : 문의하기
	                                부칙
	                                제1조 시행일
	                                2022년 4월 27일부터 시행되던 종전의 약관은 본 약관으로 대체하며, 본 약관은 2022년 5월 18일부터 적용됩니다.</p>    
	                        </div>
	                    </div>
	                </div>
	                <button type="submit" class="btn_join">회원가입 하기</button>
	                <button class="btn_cancel"><a href="${contextPath}/main/index.do">취소</a></button>
                </form>
            </div>
            <!-- mypage_content -->
        </div>
    </div>
    <!-- content_mypage_e -->
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</body>
</html>