<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../resources/static/js/scripts.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/sandstone/bootstrap.min.css" integrity="sha384-zEpdAL7W11eTKeoBJK1g79kgl9qjP7g84KfK3AZsuonx38n8ad+f5ZgXtoSDxPOh" crossorigin="anonymous">
</head>
<body id="page-top">
<%@ include file="../common/header.jsp" %>

<div class="text-center container mt-5">
<main class="form-signin">
  
	<section class="page-section" id="services">
            <div class="container mt-5">
                <div class="text-center">
                    <h2 class="section-heading text-uppercase">음원 추가</h2>
                </div>
                <div class="row pt-3">
                <div class="container pt-5 pb-5">
			    <form action="${pageContext.request.contextPath}/musicinsert" method="post">
			      
			      <div class="form-group">
			        <label for="artist">가수</label>
			        <input type="text" class="form-control" id="music_artist" name="music_artist" placeholder="가수를 입력하세요.">
			      </div>
			      
				  <div class="form-group">
			        <label for="music_emotion">감정 : </label>
			        <input type="radio" name="music_emotion" value="angry" checked>Angry
					<input type="radio" name="music_emotion" value="fear">Fear
					<input type="radio" name="music_emotion" value="laugh">Laugh
					<input type="radio" name="music_emotion" value="neutral">Neutral
					<input type="radio" name="music_emotion" value="sad">Sad
					<input type="radio" name="music_emotion" value="surprise">Surprise
					<input type="radio" name="music_emotion" value="smile">Smile
					<input type="radio" name="music_emotion" value="talking">Talking
			      </div>
			      
			      <div class="form-group">
			        <label for="music_age">나이(주요연령층)</label>
			        <input type="text" class="form-control" id="music_age" name="music_age" placeholder="나이를 입력하세요.">
			      </div>
			      
			      <div class="form-group">
			        <label for="music_gender">성별</label>
			        <input type="radio" name="music_gender" value="male">남자
			        <input type="radio" name="music_gender" value="female">여자
			      </div>
			      
			      <div class="form-group">
			        <label for="music_title">음악 제목</label>
			        <input type="text" class="form-control" id="music_title" name="music_title" placeholder="제목을 입력하세요.">
			      </div>
			      
			      
			      <div class="form-group">
			        <label for=music_url">URL</label>
			        <textarea class="form-control" id="music_url" name="music_url" rows="3"></textarea>
			      </div>
			      
			      
			      
			      <button type="submit" class="btn btn-primary">작성</button>
			    </form>
			    </div>
			    </div>
			    </div>
			    </section>
			    </main>
</div>


<%@ include file="../common/footer.jsp" %>
</body>
</html>