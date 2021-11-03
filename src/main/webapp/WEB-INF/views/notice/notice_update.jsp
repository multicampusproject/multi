<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="notice.NoticeVO"%>
<%@ page import="notice.NoticeDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정화면</title>
<link href="/css/styles.css" rel="stylesheet" />
<script src="jquery-3.2.1.min.js"></script>

</head>
<body>
<div class="container">
	<div class="col">
		<%@ include file="../common/header.jsp" %>
		<div class="container">
			<div>
				<form action="/noticeupdateaction?num=${num}" method="post">
					<table class="table table-bordered" style="text-align:center" >
						<tr>
							<th style="width:100px">글 번호</th>
							<th>${nvo.notice_code}</th>
						</tr>
						<tr>
							<th style="width:100px">글 제목</th>
							<th><input class="col-sm" type="text" id="subject" name="subject" value="${nvo.notice_subject}" style="width:100%; text-align:center"/></th>
						</tr>
						<tr>
							<td>작성자</td>
							<td>${nvo.member_email}</td>
						</tr>
						<tr>
							<td>작성일</td>
							<td>${nvo.notice_regdate}</td>
						</tr>
						<tr>
							<td style="height:400px; vertical-align:middle" >글 내용</td>
							<td><textarea class="col-sm" id="content" name="content" style="height:400px; width:100%; text-align:center">${nvo.notice_content}</textarea></td>
						</tr>
					</table>
					<input type="hidden" id="code" name="code" value="${nvo.notice_code }">
					<c:if test="${vo.member_state == 1 }">
						<input class="btn btn-primary" type="submit" id ="updateBtn" value="수정하기">
					</c:if>
					<input class="btn btn-light" id = "cancelBtn" type="button" onclick="location.href='noticeview?num=${num}&code=${nvo.notice_code }'" value="취소"/>
					<input class="btn btn-outline-primary" id = "listBtn" type="button" onclick="location.href='notice?num=${num}'; return false;" value="목록으로 이동">
				</form>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp" %>
</div>
</body>
</html>