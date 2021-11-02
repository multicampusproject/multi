<%@page import="java.math.BigDecimal"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>

<html>

<head>
<title>error 페이지</title>
<script src="/jquery-3.2.1.min.js"></script>
<link href="../resources/static/css/styles.css" rel="stylesheet" />

</head>
<body>
<script type="text/javascript">
window.onload = function() {
  alert("분석에 실패했습니다.");	}

  </script>

	<section class="page-section" id="services">
		<div class="container">
			<div class="text-center">
				<h2 class="section-heading text-uppercase" style="color:red" >Error!!!</h2>
				<h3 class="section-subheading text-muted">
				${error }
				</h3>
			</div>

			<div class="text-center">
				<div class="col-md-6">
					<span class="fa-stack fa-4x">
					 <% String image = request.getParameter("image"); %>
					 <img src="/galleryimages/<%=image %>"  width=100% height=100%>
					</span> 
						
				</div>
					<div class="row justify-content-center">
               
							<h4 class="my-3">업로드한 사진에 "얼굴"이 나올수 있도록 해주세요!</h4>
							<p class="text-muted">CFR이 업로드한 사진에서 얼굴을 감지하지 못했습니다. <br>
							<a href="${pageContext.request.contextPath}/galleryupload">갤러리 음악 추천 받기</a>로 다시 업로드 해주세요.</p>
						</div>
				
			</div>
		</div>
	</section>



	<%@ include file="../common/footer.jsp"%>

	</body>
</html>