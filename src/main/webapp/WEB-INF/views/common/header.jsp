<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Today's Music</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="../resources/static/assets/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="../resources/static/css/styles.css" rel="stylesheet" /> 
		

        
    </head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
  <script>
  //카카오로그인
  function kakaoLogin() {

    $.ajax({
        url: '/login/getKakaoAuthUrl',
        type: 'get',
        async: false,
        dataType: 'text',
        success: function (res) {
            location.href = res;
        }
    });

  }

  /*$(document).ready(function() {

      var kakaoInfo = '${kakaoInfo}';

      if(kakaoInfo != ""){
          var data = JSON.parse(kakaoInfo);

          alert("카카오로그인 성공 \n accessToken : " + data['accessToken']);
          alert(
          "user : \n" + "email : "
          + data['email']  
          + "\n nickname : " 
          + data['nickname']);
      }
  });  */

  </script>
    <body id="page-top">
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
            <div class="container">
                <a class="navbar-brand" onclick="location.href='/testmain'"><img src="../resources/static/assets/img/navbar-logo.svg" alt="..." /></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="fas fa-bars ms-1"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
                        <li class="nav-item"><a class="nav-link" href="#services">Services</a></li>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath }/galleryupload">Gallery Upload</a></li>
                        <li class="nav-item"><a class="nav-link" href="#about">About</a></li>
                        <li class="nav-item"><a class="nav-link" href="#team">Team</a></li>
                        <li class="nav-item"><a class="nav-link" href="#contact">Contact</a></li>
                    	
                    	
						<c:choose>
							<c:when test="${isLogOn==true and not empty vo}">
			                    <!-- 로그인 사용자 메뉴 -->
								<li class="nav-item">
								<div class="dropdown">
								  <a class="nav-link" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
								    ${vo.member_name} 님
								    ${vo.member_state } /1이면 관리자 0이면 일반회원임
								  </a>
								
								  <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
								    <li class="nav-item"><a class="dropdown-item" href="${pageContext.request.contextPath}/myinfo">내 정보확인</a></li>
								    <li class="nav-item"><a class="dropdown-item" href="${pageContext.request.contextPath}/myhistory">내 히스토리</a></li>
								    <li class="nav-item"><a class="dropdown-item" href="${pageContext.request.contextPath}/deleteinfo" onclick="return confirm('정말 탈퇴하시겠습니까?');">회원탈퇴</a></li>
								  </ul>
								</div>
								</li>
								<!-- 관리자 메뉴 -->
		                    	<c:if test="${vo.member_state==1}">
			                    	<li class="nav-item">
										<div class="dropdown">
										  <a class="nav-link" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
										    관리자
										  </a>
										
										  <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
										    <li class="nav-item"><a class="dropdown-item" href="${pageContext.request.contextPath}/aduserlist">회원리스트</a></li>
										    <li class="nav-item"><a class="dropdown-item" href="${pageContext.request.contextPath}/musiclist">음악리스트</a></li>
										    <li class="nav-item"><a class="dropdown-item" href="${pageContext.request.contextPath}/analysisemotion">감정분석통계</a></li>
										  	<li class="nav-item"><a class="dropdown-item" href="${pageContext.request.contextPath}/analysismusic">추천음악통계</a></li>
										  </ul>
										</div>
									</li>
		                    	</c:if>

								<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/userlogout">로그아웃</a></li>
							</c:when>
							<c:otherwise>
	                    		<!-- 비로그인 사용자 메뉴 -->
		                        <li class="nav-item"><a class="nav-link" href="javascript:void(0)"  onclick="kakaoLogin();">카카오 로그인</a></li>
							</c:otherwise>
						</c:choose>
                    </ul>
                </div>
            </div>
        </nav>
</body>
</html>