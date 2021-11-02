<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
       <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Gallery Upload</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="../resources/static/assets/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)--> 
		<link href="../resources/static/css/styles.css" rel="stylesheet" type="text/css" />

<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#submit{
	border:1px solid #ebebeb;
	background-color: #fdfdfd;
	color:#999;
	padding 5px;
	border-top-left-radius: 5px;
	border-bottom-right-radius: 5px;
	border-bottom-left-radius: 5px;
	border-top-right-radius: 5px;
}
#submit:hover{
	color:white;
	background-color:#FFDC46;
}
.filebox input[type="file"] {
  position: absolute;
  width: 0;
  height: 0;
  padding: 0;
  overflow: hidden;
  border: 0;
}

.filebox label {
  display: inline-block;
  padding: 5px;
  color: #999;
  vertical-align: middle;
  background-color: #fdfdfd;
  cursor: pointer;
  border: 1px solid #ebebeb;
  border-top-left-radius: 5px;
  border-bottom-right-radius: 5px;
  border-bottom-left-radius: 5px;
  border-top-right-radius: 5px;
}
.filebox label:hover{
	color:white;
	background-color:#FFDC46;
}

/* named upload */
.filebox .upload-name {
  display: inline-block;
  height: 35px;
  font-size:15px; 
  padding: 0 10px;
  vertical-align: middle;
  background-color: #f5f5f5;
  border: 1px solid #ebebeb;
  border-radius: 5px;

}
</style>
</head>

<body id='page-top'>
<%@ include file="../common/header.jsp" %>
 <section class="page-section bg-light" id="portfolio">
            <div class="container">
                <div class="text-center">
                    <h2 class="section-heading text-uppercase">Gallery Upload</h2>
                    <h3 class="section-subheading text-muted">사진을 업로드하여 어울리는 음악을 추천받아보세요! <br>아래 이미지를 클릭하면 더 자세한 정보를 확인할 수 있습니다.</h3>
                </div>
                <div class="row justify-content-center">
                    <div class="col-lg-4 col-sm-6 mb-4">
                        <!-- Portfolio item 1-->
                        <div class="portfolio-item">
                            <a class="portfolio-link" data-bs-toggle="modal" href="#portfolioModal1">
                                <div class="portfolio-hover">
                                    <div class="portfolio-hover-content"><i class="fas fa-plus fa-3x"></i></div>
                                </div>
                                <img class="img-fluid" src="/assets/img/portfolio/5.jpg" alt="..." />
                            </a>
                            <div class="portfolio-caption">
                                <div class="portfolio-caption-heading">사진 업로드</div>
                                	<form action="/galleryupload"  method="post" enctype="multipart/form-data">
									<div class="filebox"> 
  									<label for="file">사진 선택</label> 
  									<input id="file" name="file1" required type="file">  
									<input class="upload-name" value="사진 경로">
									</div>
									<input id='submit' type=submit value="사진 업로드">
								</form>
                                <div class="portfolio-caption-subheading text-muted"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Portfolio Modals-->
        <div class="portfolio-modal modal fade" id="portfolioModal1" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="close-modal" data-bs-dismiss="modal"><img src="/assets/img/close-icon.svg" alt="Close modal" /></div>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-8">
                                <div class="modal-body">
                                    <!-- Project details-->
                                    <h2 class="text-uppercase">Gallery Upload</h2>
                                    <p class="item-intro text-muted">갤러리의 사진을 업로드하여 어울리는 음악을 추천받아보세요.</p>
                                    <img class="img-fluid d-block mx-auto" src="/assets/img/portfolio/5.jpg" alt="..." />
                                    <p>카카오톡 프로필 사진 뿐 만 아니라 갤러리의 다른 사진을 업로드하여 사진과 어울리는 음악을 추천받으세요. 얼굴이 드러나있는 사진을 업로드하셔야 더욱 정확한 분석을 통한 음악을 추천해드립니다.이 서비스는 카카오 로그인이 필요하지 않습니다. </p>
                                    <ul class="list-inline">
                                        <li>
                                            <strong>분석도구 :</strong>
                                            Naver CFR Service
                                        </li>
                                    </ul>
                                    <button class="btn btn-primary btn-xl text-uppercase" data-bs-dismiss="modal" type="button">
                                        <i class="fas fa-times me-1"></i>
                                        Close
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
<script>
$(document).ready(function(){ 
	  var fileTarget = $('#file'); 
	  fileTarget.on('change', function(){ // 값이 변경되면
	      var cur=$(".filebox input[type='file']").val();
	    $(".upload-name").val(cur);
	  }); 
	}); 
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
<script src="../resources/static/js/scripts.js"></script>
<%@ include file="../common/footer.jsp" %>
</body>
</html>