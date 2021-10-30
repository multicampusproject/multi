<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<link href="../resources/static/css/styles.css" rel="stylesheet" type="text/css" />
<meta charset="UTF-8">
<title>Gallery input</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="../resources/static/js/scripts.js"></script>
<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</head>
<body id='page-top'>
<%@ include file="../common/header.jsp" %>
 <section class="page-section bg-light" id="portfolio">
            <div class="container">
                <div class="text-center">
                    <h2 class="section-heading text-uppercase">Gallery Upload</h2>
                    <h3 class="section-subheading text-muted">사진을 업로드하여 어울리는 음악을 추천받아보세요!</h3>
                </div>
                </div>
          <div class="container">
                <div class="text-center">
				<h3 class="section-subheading text-muted">업로드 하신 사진이 맞다면 Next 버튼을 눌러주세요.</h3>
				<a class="btn btn-primary btn-M text-uppercase" href="/face?image=${vo.filename1 }">Next</a><br>
				<img src="/galleryimages/${vo.filename1 }"  width=1000 height=1000> <br>
</div>
</div>
</section>
<%@ include file="../common/footer.jsp" %>
</body>
</html>