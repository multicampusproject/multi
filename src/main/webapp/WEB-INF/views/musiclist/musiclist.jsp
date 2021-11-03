<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Music List</title>
<script>
	
	//alert(1)
	//alert("${aa}")
</script>
<script src="../resources/static/js/scripts.js"></script>
</head>
<body>
<%@ include file="../common/header.jsp" %>

<div class="text-center container mt-5">
<main class="form-signin">
  
	<section class="page-section" id="services">
            <div class="container mt-5">
                <div class="text-center">
                    <h2 class="section-heading text-uppercase">음악 목록</h2>
                </div>
                <div class="row text-center pt-3">
                <div class="container pt-5 pb-5">
                <a href="${pageContext.request.contextPath}/insertform" class="btn btn-outline-primary" >추가하기</a>
				<table class="table table-hover">
				<thead>
			        <tr>
			            <th>No</th>
			            <th>Artist</th>
			            <th>Emotion</th>
			            <th>Age</th>
			            <th>gender</th>
			            <th>title</th>
			            <th>url</th>
			            <th>delete</th>
			        </tr>
			    </thead>
			    <tbody>
			          <c:forEach var="l" items="${musiclist}" varStatus="status">
			              <tr>
			                  <td>${status.count}</td>
			                  <td>${l.music_artist}</td>
			                  <td>${l.music_emotion}</td>
			                  <td>${l.music_age}</td>
			                  <td>${l.music_gender}</td>
			                  <td>${l.music_title}</td>
			                  <td>${l.music_url}</td>
			                  <td><a href="${pageContext.request.contextPath}/deletemusic/${l.music_num}" onclick="return confirm('정말 삭제하시겠습니까? 삭제 시 복구되지 않습니다.');" class="btn btn-primary">음악삭제</a></td>
			              </tr>
			          </c:forEach>
			    </tbody>
			    </table>
		    </div>
		    </div>
		</div>
		</section>
</main>
</div> 
 
<%@ include file="../common/footer.jsp" %>
</body>
</html>
