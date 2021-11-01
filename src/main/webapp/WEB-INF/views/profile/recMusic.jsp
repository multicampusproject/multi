<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../resources/static/css/styles.css" rel="stylesheet" />
<script src="../resources/static/js/scripts.js"></script>
<%@ include file="../common/header.jsp"%>
</head>
<body>




	<c:forEach items="${list }" var="musicVO">

		<section class="page-section">
			<div class="container">
				<ul class="timeline">
					<li>
						<div class="timeline-image">
							<img class="rounded-circle img-fluid"
								src="../resources/static/assets/img/about/1.jpg" alt="..." />
						</div>
						<div class="timeline-panel">
							<div class="timeline-heading">
								<br>
								<h4>
									${imageAge }<br> ${imageGender }<br> ${imageEmotion }
								
							</div>
							<div class="timeline-body">
								<p class="text-muted"></p>
							</div>
						</div>
						<div class="timeline-panel">
							<div class="timeline-heading">
								<br>
							</div>
							<div class="timeline-body">
								<p class="text-muted">
									title: ${musicVO.music_title }<br> <br>artist:
									${musicVO.music_artist }
								</p>
							</div>
						</div>
					</li>
					<br>
					<li class="timeline-inverted">
						<div class="timeline-image">
							<img class="rounded-circle img-fluid"
								src="../resources/static/assets/img/about/4.jpg" alt="..." />
						</div>
						<div class="timeline-panel">
							<div class="timeline-heading">
								<br>
								<h4 class="subheading">Youtube 바로보기</h4>
							</div>
							<div class="timeline-body">
								<iframe width="400" height="200"
									src="https://www.youtube.com/embed/${musicVO.music_url }"
									title="YouTube video player" frameborder="0"
									allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
									allowfullscreen></iframe>
							</div>
						</div>
						<div class="timeline-image">
							<img class="rounded-circle img-fluid"
								src="https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png"
								alt="..." />
						</div>
						<div class="timeline-panel">
							<div class="timeline-heading">
								<br>
								<h4 class="subheading">카카오톡 공유하기</h4>
							</div>
							</h4>
							<div class="timeline-body">
								<p class="text-muted">
									카카오톡 공유하기를 통해<br>자신의 카카오톡으로 <br>해당정보를 받아보세요!
								</p>
								<!--           <button class="btn btn-primary" type="button" ><a href="javascript:;" id="kakao-link-btn">
                                        공유하기
                                    </button> -->
								<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
								<a href="javascript:;" id="kakao-link-btn"> <img
									src="//developers.kakao.com/tool/resource/static/img/button/kakaolink/kakaolink_btn_medium.png"
									width="50px" />
								</a>




							</div>
						</div>
					</li>
				</ul>
			</div>

		</section>

		<!-- 공유하기 기능 -->
		<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
		<a href="javascript:;" id="kakao-link-btn"> <!-- <img src="//developers.kakao.com/tool/resource/static/img/button/kakaolink/kakaolink_btn_medium.png" width="50px" /> -->
		</a>
		<script type="text/javascript">
			//<![CDATA[
			// // 사용할 앱의 JavaScript 키를 설정해 주세요.
			Kakao.init('fca5649bf1a998d2225b7669329e082e');
			// // 카카오링크 버튼을 생성합니다. 처음 한번만 호출하면 됩니다.
			Kakao.Link
					.createDefaultButton({
						container : '#kakao-link-btn',
						objectType : 'feed',
						content : {
							title : "이음악 추천해요!",
							description : ' title: ${musicVO.music_title }\n artist: ${musicVO.music_artist }',
							imageUrl : 'https://i1.ytimg.com/vi/${musicVO.music_url }/hqdefault.jpg',
							link : {
								mobileWebUrl : 'https://www.youtube.com/embed/${musicVO.music_url }',
								webUrl : 'https://www.youtube.com/watch?v=${musicVO.music_url }'
							}
						},
						buttons : [ {
							title : '유튜브로 들어봐요!',
							link : {
								mobileWebUrl : 'https://www.youtube.com/embed/${musicVO.music_url }',
								webUrl : 'https://www.youtube.com/watch?v=${musicVO.music_url }'
							}
						} ]
					});
			//]]>
		</script>



	</c:forEach>





	<%@ include file="../common/footer.jsp"%>
</body>
</html>







