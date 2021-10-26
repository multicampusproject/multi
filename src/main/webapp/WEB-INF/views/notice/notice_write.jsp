<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글쓰기 화면</title>
</head>
<body>
<form action="/noticewriteaction" method="POST">
	<table style="text-align:center; border: 1px solid #dddddd">
		<thead>
			<tr>
				<th colspan="2" style="background-color: #eeeeee; text-align:center; width:1000px">게시판 글쓰기 양식</th>
			</tr>
		</thead>	
		</tbody>	
			<tr>
				<td><input type="text" class="form-control" placeholder="글 제목" name="notice_title" maxlength="50" style="width:1000px"></td>
			</tr>
			<tr>
				<td><textarea class="form-control" placeholder="글 내용" name="notice_content" maxlength="2048" style="height:350px; width:1000px "></textarea></td>
			</tr>
		</tbody>	
	</table>
	<input type="submit" class="btn" value="글쓰기">
</form>
</body>
</html>