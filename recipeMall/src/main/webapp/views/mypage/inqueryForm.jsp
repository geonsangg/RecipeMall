<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("utf-8");
session=request.getSession(false);
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
<script src="${contextPath}/views/js/common.js"></script>
<script src="${contextPath}/views/mypage/js/mypage.js"></script>

<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage.css">
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage_common.css">
<link rel="stylesheet" href="${contextPath}/views/mypage/css/mypage_6.css">
<link rel="shortcut icon" href="${contextPath}/views/images/smalllogo.png" />
<script type="text/javascript">
$(function () {
	$('#cate').change(function () {
		$('#inqCate').val($(this).val());
	});
});
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
				<h3 class="menu_title">고객센터</h3>
				<div class="cs_center">
					<h4>일대일 문의</h4>
					<div class="one_inquery">
						<form action="${contextPath}/service/addInq.do" method="post" enctype="multipart/form-data">
							<input type="hidden" name="id" value="${log_id}">
							<table class="inquery_tbl">
								<caption>일대일 문의</caption>
								<tbody>
									<tr>
										<th>분류</th>
										<td>
											<select id="cate">
												<option value="">선택</option>
												<option value="1">쇼핑</option>
												<option value="2">커뮤니티</option>
											</select>
											<input type="hidden" id="inqCate" name="inqCate" value="">
										</td>
									</tr>
									<tr>
										<th>제목</th>
										<td><input type="text" name="inq_title" id="inq_title"></td>
									</tr>
									<!-- <tr>
										<th>이메일</th>
										<td><input type="email" name="email" id="email">
											<input type="checkbox" name="email_comm_ok"
											id="email_comm_ok"> 답변 받기</td>
									</tr> -->
									<tr>
										<th>내용</th>
										<td><textarea name="inq_content" id="inq_content"
												class="inq_txta" cols="90" rows="20"></textarea></td>
									</tr>
									<!-- <tr class="file_area">
										<th>파일첨부</th>
										<td>
											<label for="inq_file">
												<div class="file_upload_btn">파일 첨부</div>
											</label>
											<input type="file" data-ax-path="file" name="inq_file" id="inq_file" multiple="multiple">
											<div class="file_list"></div>
										</td>
									</tr> -->
								</tbody>
							</table>
							<div class="subm_btn">
								<input type="submit" class="inq_btn btn_c" value="문의하기">
								<input type="button" class="inq_btn cancel" value="취소">
								<!-- <button class="cancel inq_btn">취소</button> -->
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- mypage_content -->
		</div>
	</div>
	<!-- content_mypage_e -->

	<!-- footer_s -->
	<jsp:include page="/views/fix/footer.jsp" />
	<!-- footer_e -->
</body>
</html>