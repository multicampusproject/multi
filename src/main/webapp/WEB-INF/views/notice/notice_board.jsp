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
<link href="/css/styles.css" rel="stylesheet" />
<script src="../resources/static/js/scripts.js"></script>
</head>

<script>
var message = '${param.message}'

	if (message!="") {
		alert(message);
	}

</script>
<body>
<div class="container">
	<%@ include file="../common/header.jsp" %>
	<br><br>
		<h3 class="text-center section-heading">공지사항</h3>
		<h6 class="text-center section-subheading text-muted fw-lighter">Notice</h6>
	<br><br>
	
	<div align="right">
		<form name="form-control" autocomplete="off">
			<select name="type" class="form-select" style="width:10%; display: inline-block">
				<option selected value="title">제목</option>
				<option value="content">내용</option>
				<option value="writer">작성자</option>
			</select>
			<input type="hidden" name="num" value="1"></input>
			<input type="text" name="keyword" value="" class="form-control" style="width:20%; display: inline-block"></input>
			<input type="submit" onclick="location.href='/notice'" class="btn btn-outline-primary" value="검색"></input>
		</form>
	</div>
	<br>
	
	<table class="table table-hover" style="text-align:center; " id="boardtable">
		<thead>
		<tr>
			<th>글번호</th>
			<th style="width:600px">제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="NoticeVO">
			<tr>
				<td>${NoticeVO.notice_code}</td>
				<td><a href="/noticeview?num=${num}&code=${NoticeVO.notice_code}">${NoticeVO.notice_subject}</a></td>
				<td>${NoticeVO.member_email}</td>
				<td>${NoticeVO.notice_regdate}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<c:if test="${vo.member_state == 1 }">
		<button class="btn btn-primary btn-m" onclick="location.href='/noticewrite'">글 작성하기</button>
	</c:if>
	
	<br><br>
	
	<div style="text-align:center">
		<div>
			<c:if test="${prev == true}">
			 	<span><a href="/notice?num=${startPageNum-1}&type=${type}&keyword=${keyword}">prev</a></span>
			</c:if>
			
			<c:forEach begin="${startPageNum}" end="${endPageNum}" var="num">
				<span>
			 
			  	<c:if test="${select != num}">
			   		<a href="/notice?num=${num}&type=${type}&keyword=${keyword}">${num}</a>
			  	</c:if>    
			  
			 	<c:if test="${select == num}">
			   		<b>${num}</b>
			  	</c:if>
			    
			 	</span>
			</c:forEach>
			
			<c:if test="${next  == true}">
			 	<span><a href="/notice?num=${endPageNum+1}&type=${type}&keyword=${keyword}">next</a></span>
			</c:if>
		</div>
	</div>
<%@ include file="../common/footer.jsp" %>
</div>

 
</body>
</html>