<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 게시판 메인</title>
</head>
<script src="jquery-3.2.1.min.js"></script>
<script type="text/javascript">
//로그인 안하고 댓글달면 alert 
var message = '${param.message}'
if (message!="") {
	alert(message);
}	

</script>
<body>
<div class="container">
<div class="mb-5">
<%@ include file="../common/header.jsp" %>
<br><br>
</div>
<br>
<h3 class="text-center section-heading">QnA</h3>
<h6 class="text-center section-subheading text-muted fw-lighter">QnA</h6>
<br>
<br><br>
<table class="table table-hover" style="text-align:center;">
	<tr>
		<th>글번호</th>
		<th style="width:600px">제목</th>
		<th>작성자</th>
		<th>작성일</th>
	</tr>
<c:forEach items="${qnalist}" var="QnAVO">
	<tr>
		<td>${QnAVO.qna_code}</td>
		<td><a href="/qnaview?code=${QnAVO.qna_code}">${QnAVO.qna_subject}</a></td>
		<td>${QnAVO.member_email}</td>
		<td>${QnAVO.qna_regdate}</td>
	</tr>
</c:forEach>
</table>
<br>
<c:if test="${vo.member_email != null}">
<button class="btn btn-primary btn-m" onclick="location.href='/qnawrite'" id="writeBtn">글 작성하기</button>
</c:if>

<div style="text-align:center">
<div>
	<c:if test="${prev}">
	 <span><a href="/qna?num=${startPageNum-1}">&#60;</a></span>
	</c:if>
	
	<c:forEach begin="${startPageNum}" end="${endPageNum}" var="num">
	<span>
 
  	<c:if test="${select != num}">
   		<a href="/qna?num=${num}">${num}</a>
  	</c:if>    
  
 	<c:if test="${select == num}">
   		<b>${num}</b>
  	</c:if>
    
 	</span>
	</c:forEach>
	
	<c:if test="${next}">
	 <span><a href="/qna?num=${endPageNum+1}">next</a></span>
	</c:if>
</div>
</div>

<br><br><br>

</div>
<%@ include file="../common/footer.jsp" %>

</body>
</html>