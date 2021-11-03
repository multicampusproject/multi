<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="notice.NoticeVO"%>
<%@ page import="notice.NoticeDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 수정화면</title>
<link href="/css/styles.css" rel="stylesheet" />
<script src="jquery-3.2.1.min.js"></script>
<script src="../resources/static/js/scripts.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<div class="container">
	<div class="col">
		<%@ include file="../common/header.jsp" %>
		<div class="container">
			<div>
				<form action="/qnaupdateaction?num=${num}" method="post">
					<table class="table table-bordered" style="text-align:center" style="text-align:center" >
						
						<tr>
							<th style="width:100px">글 번호</th>
							<th>${qvo.qna_code}</th>
						</tr>
						<tr>
							<th style="width:100px">글 제목</th>
							<th><input class="col-sm" type="text" id="subject" style="width:100%; height:100%; text-align:center" name="subject" value="${qvo.qna_subject}" style="text-align:center"/></th>
						</tr>
						<tr>
							<td>작성자</td>
							<td>${qvo.member_email}</td>
						</tr>
						<tr>
							<td>작성일</td>
							<td>${qvo.qna_regdate}</td>
						</tr>
						<tr>
							<td style="height:400px; vertical-align:middle">글 내용</td>
							<td><textarea class="col-sm" id="content" name="content" style="width:100%; height:400px; text-align:center; vertical-align:middle">${qvo.qna_content}</textarea></td>
						</tr>
					</table>
					<input type="hidden" id="code" name="code" value="${qvo.qna_code }">
					<c:if test="${vo.member_email.equals(qvo.member_email) }">
						<input class="btn btn-primary" type="submit" id ="updateBtn" value="수정하기">
						<input class="btn btn-light" id = "cancelBtn" type="button" onclick="location.href='qnaview?num=${num}&code=${qvo.qna_code }'" value="취소"/>
					</c:if>
					<input class="btn btn-outline-primary" id = "listBtn" type="button" onclick="location.href='qna?num=${num}'; return false;" value="목록으로 이동">
				</form>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp" %>
</div>
</body>

</html>