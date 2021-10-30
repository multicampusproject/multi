<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge"> <meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>공지사항 게시판 메인</title>
<link href="/resources/static/css/styles.css" rel="stylesheet" />
</head>

<script>
var message = '${param.message}'

	if (message!="") {
		alert(message);
	}
	
</script>
<body>
<div class="container">
<div class="mb-5">
<%@ include file="../common/header.jsp" %>
<br><br><br><br><br><br>

<br>
<h3 class="text-center section-heading">공지사항</h3>
<h6 class="text-center section-subheading text-muted fw-lighter">Notice</h6>
<br>

<table class="table table-hover" style="text-align:center; ">
	<tr>
		<th>글번호</th>
		<th style="width:600px">제목</th>
		<th>작성자</th>
		<th>작성일</th>
	</tr>
	<c:forEach items="${list}" var="NoticeVO">
		<tr>
			<td>${NoticeVO.notice_code}</td>
			<td><a href="/noticeview?code=${NoticeVO.notice_code}">${NoticeVO.notice_subject}</a></td>
			<td>${NoticeVO.member_email}</td>
			<td>${NoticeVO.notice_regdate}</td>
		</tr>
	</c:forEach>
</table>

<c:if test="${vo.member_state == 1 }">
<button class="btn btn-primary btn-m" onclick="location.href='/noticewrite'">글 작성하기</button>
</c:if>


<br>
<div style="text-align:center">
<div>
	<c:if test="${prev}">
	 <span><a href="/notice?num=${startPageNum-1}">&#60;</a></span>
	</c:if>
	
	<c:forEach begin="${startPageNum}" end="${endPageNum}" var="num">
	<span>
 
  	<c:if test="${select != num}">
   		<a href="/notice?num=${num}">${num}</a>
  	</c:if>    
  
 	<c:if test="${select == num}">
   		<b>${num}</b>
  	</c:if>
    
 	</span>
	</c:forEach>
	
	<c:if test="${next}">
	 <span><a href="/notice?num=${endPageNum+1}">next</a></span>
	</c:if>
</div>
</div>
</div>
</div>
<br><br><br>
<%@ include file="../common/footer.jsp" %>

 
</body>
</html>