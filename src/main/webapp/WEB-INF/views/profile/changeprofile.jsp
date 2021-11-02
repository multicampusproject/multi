<%@page import="java.math.BigDecimal"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>

<html>

<head>
<title>Home</title>
<script src="../resources/static/js/jquery-3.2.1.min.js"></script>
<script src="../resources/static/js/scripts.js"></script>
<link href="../resources/static/css/styles.css" rel="stylesheet" />

</head>
<body>

	<section class="page-section" id="services">
		<div class="container">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">프로필이 바뀌셨나요?</h2>
				<h3 class="section-subheading text-muted">
			비즈니스 등록이 안된 서비스이기 때문에 사용자가 직접 정보를 삭제 후 재등록해야 합니다.
				</h3>
			</div>

			<div class="row text-center">
				<div class="col-md-4">
					<span class="fa-stack fa-4x"> <img
						src="../resources/static/assets/img/change1.JPG" width=100%
						height=100%>
					</span> <span>
						<div>
							<h4 class="my-3">카카오 계정 접속</h4>
							<p class="text-muted">
			<a href="https://accounts.kakao.com/weblogin/account">카카오계정</a> 에 접속하여 상단 계정 이용을 선택한 후 외부 서비스 전체보기를 선택해 주세요.
								</p>
						</div>
					</span>
				</div>
				<div class="col-md-4">
					<span class="fa-stack fa-4x"> <span><img
							src="../resources/static/assets/img/change2.JPG" width=100%
							height=100%></span>
					</span> <span><h4 class="my-3">연결된 서비스 관리</h4></span> <span><p
							class="text-muted">Today's Music 우측에 화살표를 클릭해주세요. </p></span>
				</div>
				<div class="col-md-4">
					<span class="fa-stack fa-4x"> <span><img
							src="../resources/static/assets/img/change3.JPG" width=100%
							height=100%></span>
					</span>
					<h4 class="my-3">연결끊기</h4>
					<p class="text-muted">연결 끊기를 통해 이전 정보를 삭제하고, 다시 로그인 시 서비스 재등록을 해주세요.</p>
				</div>
			</div>
		</div>
	</section>



	<%@ include file="../common/footer.jsp"%>
</body>
</html>