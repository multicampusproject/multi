<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="../resources/static/css/styles.css" rel="stylesheet" type="text/css" />
<meta charset="UTF-8">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="../resources/static/js/scripts.js"></script>
<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
<title>Gallery Result</title>
</head>
<body id='page-top'>
<%@ include file="../common/header.jsp" %>
        <section class="page-section bg-light" id="portfolio">
            <div class="container">
                <div class="text-center">
                    <h2 class="section-heading text-uppercase">Gallery Upload</h2>
                    <h3 class="section-subheading text-muted">사진을 업로드하여 어울리는 음악을 추천받아보세요!</h3>
                </div>
                <div class="text-center">
                <% String image = request.getParameter("image"); %>
                <img src="/galleryimages/<%=image %>"  width=200 height=200> <br>
</div>

<!-- 업로드한 사진 캔버스로 그리기 -->
<!--  
<canvas id ="facecanvas" width=500 height =500 style="border:2px solid purple"></canvas>
<script>
window.onload = function(){
	let facecanvas = document.getElementById("facecanvas");
	let facecontext = facecanvas.getContext("2d");
	let faceimage = new Image();
	faceimage.src = "/galleryimages/<%=image%>";
	faceimage.onload = function(){
		facecontext.drawImage(faceimage,0,0,faceimage.width,faceimage.height)
	}
	
	
}
</script>
-->
<%  
// 사진 cfr 결과 데이터 받아와서 분석
String faceresult = (String)request.getAttribute("faceresult");
JSONObject obj = new JSONObject(faceresult);
JSONArray faces = (JSONArray)obj.get("faces");
String emotionvalue= "";
String agevalue="";
String gendervalue="";
for(int i = 0; i < faces.length(); i++){
	JSONObject oneface = (JSONObject)faces.get(i);
	JSONObject roi = (JSONObject)oneface.get("roi");
	int x = (Integer)roi.get("x");
	int y = (Integer)roi.get("y");
	int width = (Integer)roi.get("width");
	int height = (Integer)roi.get("height");
	//out.println("얼굴 위치는 x  좌표(왼쪽) " + x  + " , y  좌표(아래로) " + y + " 에서 시작하여 "
	//+ "가로 " + width + " 세로 " + height + " 크기를 가집니다.");
	
	//성별 , 나이, 감정, 자세 -confidence   제외 
	JSONObject gender = (JSONObject)oneface.get("gender");
	String genderString = (String)gender.get("value");
	JSONObject age = (JSONObject)oneface.get("age");
	String ageString = (String)age.get("value");	
	JSONObject emotion = (JSONObject)oneface.get("emotion");
	String emotionString = (String)emotion.get("value");
	JSONObject pose = (JSONObject)oneface.get("pose");
	String poseString = (String)pose.get("value");
	if (emotionString.equals("disgust")) {
		emotionString = "angry";
	} 
	emotionvalue = emotionString;
	agevalue = ageString;
	gendervalue = genderString;
}//for end
String[] avalue2 = agevalue.split("~"); // (String)["82", "86"]
String avalue3 = avalue2[0]; //(String)"82"
int avalue4 =(int)Math.round(Integer.parseInt(avalue3)/10)*10  ;
if(avalue4 >=50) {
	 avalue4= 50 ;	 
} else if(avalue4 < 10){
	 avalue4= 10 ;
}
String avalue = String.valueOf(avalue4);
String murl = "";
String mtitle = "";
String martist = "";
//out.println(avalue4+"<br>");
//나이 변환 종료
//System.out.println(agevalue);
//MusicVo로 부터 음악데이터베이스 받아오기
ArrayList musiclist = (ArrayList) request.getAttribute("musiclist");
//받아온 음악리스트와 분석된 감정 음악 추천
for(int i = 0; i< musiclist.size() ;i++){
	String music1 = musiclist.get(i).toString();
	String target = "music_gender";
	int target_num = music1.indexOf(target); 
	String genderresult= music1.substring(target_num,(music1.substring(target_num).indexOf(":")+target_num));
	String genresult = genderresult.substring(13);
	String ageresult = music1.substring(music1.length()-2, music1.length());
	if (musiclist.get(i).toString().contains(emotionvalue)){//감정에 맞는 음악 리스트가 있다면
		if(ageresult.equals(avalue) && (genresult.equals(gendervalue))) {
			String music = musiclist.get(i).toString();
			String[] results = music.split(":"); //음악 문자열로 바꾼후에 , 별로 배열로 저장
			murl = results[3].substring(10);
			mtitle = results[2].substring(12);
			martist = results[0].substring(13);
		}
	}
}

%>
<section class="page-section">
            <div class="container">
                <ul class="timeline">
                    <li>
                        <div class="timeline-image"><img class="rounded-circle img-fluid" src="/assets/img/about/1.jpg" alt="..." /></div>
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                            <br>
                                <h4>이미지 분석 결과</h4>
                            </div>
                            <div class="timeline-body"><p class="text-muted">Age : <%=agevalue %><br> Gender : <%=gendervalue %><br> Emotion : <%=emotionvalue %> </p></div>
                        </div>
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                            	<br>
                                <h4>추천 음악 </h4>
                            </div>
                            <div class="timeline-body"><p class="text-muted">Title : <%=mtitle %> <br> Artist : <%=martist %></p></div>
                        </div>
                    </li>
                    <li class="timeline-pannel">
                          <div class="timeline-image"><img class="rounded-circle img-fluid" src="/assets/img/kakao1.jpg" alt="..." /></div> 
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                            	<br>
                                <h4 class="subheading">Youtube</h4>
                            </div>
                            <div class="timeline-body"><iframe width="300" height="150" src= "https://www.youtube.com/embed/<%=murl %>" title="YouTube video player" frameborder="0" 
allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></div>
                        </div>
                    <!--      <div class="timeline-image"><img class="rounded-circle img-fluid" src="../resources/static/assets/img/kakao1.jpg" alt="..." /></div>-->
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                                <br>
                                <h4>KaKao Talk</h4>
                            </div>
                            <div class="timeline-body"><p class="text-muted">카카오톡 공유하기를<br>통해 자신의 카카오톡에<br>추천음악을 받아보세요!</p>
                                 <button class="btn btn-primary" type="button" onclick="javascript:kakaoShare()">
                                        공유하기
                                    </button>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </section>
<script type="text/JavaScript" src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript">
  // SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해 주세요.
  Kakao.init('fca5649bf1a998d2225b7669329e082e');

  // SDK 초기화 여부를 판단합니다.
  console.log(Kakao.isInitialized());

  function kakaoShare() {
    Kakao.Link.sendDefault({
      objectType: 'feed',
      content: {
        title: '이런 음악 어떄요?',
        description:'Title : <%=mtitle%>\nArtist :<%=martist%>',
        imageUrl: 'https://i1.ytimg.com/vi/<%=murl%>/hqdefault.jpg',
        link: {
          mobileWebUrl: 'https://www.youtube.com/watch?v=<%=murl %>',
          webUrl: 'https://www.youtube.com/watch?v=<%=murl %>',
        },
      },
      buttons: [
        {
          title: '음악 들으러 가기',
          link: {
        	mobileWebUrl: 'https://www.youtube.com/watch?v=<%=murl%>',
            webUrl: 'https://www.youtube.com/watch?v=<%=murl %>',
          },
        },
      ],
      // 카카오톡 미설치 시 카카오톡 설치 경로이동
      installTalk: true,
    })
  }
</script>
<%@ include file="../common/footer.jsp" %>
</body>
</html>


