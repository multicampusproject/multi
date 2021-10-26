<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/sandstone/bootstrap.min.css" integrity="sha384-zEpdAL7W11eTKeoBJK1g79kgl9qjP7g84KfK3AZsuonx38n8ad+f5ZgXtoSDxPOh" crossorigin="anonymous">
</head>
<body  class="pt-5">

<div class="text-center container mt-5">
<main class="form-signin">
  
	<div class="container">
			<table class="table table-hover">
			  <thead>
			    <tr>
			      <th scope="col" colspan="4" >${vo.member_name}님 히스토리</th>
			    </tr>
			  </thead>
			  <tbody>
				<c:choose>
					<c:when test="${empty list}">
						<tr>
						<th colspan="4">
						아직 음악 추천을 받지 않았습니다.
						<a href="#"><p>추천받으러가기</p></a>
						</th>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<th>No.</th>
							<th>이미지 이름</th>
							<th>감정</th>
							<th>추천음악</th>
						</tr>
						<c:forEach items="${list}" var="historyVO" varStatus="status">
							<tr>
								<td>${status.count }</td>
								<td>${historyVO.image_name }</td>
								<td>${historyVO.image_emotion }</td>
								<td>
								<a href="${historyVO.music_url}">
								${historyVO.music_artist} - ${historyVO.music_title }</a></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>			  
			  </tbody>
			</table>
	</div>
</main>
</div>

<div class="b-example-divider"></div>





</body>
</html>