<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script type="text/javascript">

//로그인 안하고 댓글달면 alert 
var message = '${param.message}'

if (message!="") {
	alert(message);
}

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
</head>
<body>
<h1>메인페이지</h1>
<%-- <a href="javascript:void(0)"  onclick="kakaoLogin();">카카오 로그인</a><br>
<a href="${pageContext.request.contextPath}/userlogout">로그아웃</a><br>
<a href="${pageContext.request.contextPath}/mypage">마이페이지</a> --%>
<c:choose>
	<c:when test="${isLogOn==true and not empty vo}">
		로그인성공 <br>
		${vo.member_name}님 로그인 중<br>
		${vo.member_email }<br>
		${vo.member_id }<br>
		<a href="${pageContext.request.contextPath}/myinfo">내 정보확인</a><br>
		<a href="${pageContext.request.contextPath}/deleteinfo" onclick="return confirm('정말 탈퇴하시겠습니까?');">회원탈퇴</a><br>
		<a href="${pageContext.request.contextPath}/myhistory">내 히스토리</a><br>
		<a href="${pageContext.request.contextPath}/userlogout">로그아웃</a><br>
		
	</c:when>
	<c:otherwise>
		<a href="javascript:void(0)"  onclick="kakaoLogin();">카카오 로그인</a><br>
	</c:otherwise>
</c:choose>
<button onclick ="location.href='notice'">공지사항</button>
<button onclick ="location.href='qna'">QnA</button>

</body>
</html>