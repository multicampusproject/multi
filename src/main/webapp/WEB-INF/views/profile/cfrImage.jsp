<%@page import="java.math.BigDecimal"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지db 저장</title>
<script src="../resources/static/js/scripts.js"></script>
</head>
<body>

	<!-- 프로필 이미지 불러오는 영역 -->
	<%
	String image = request.getParameter("image");
	%>
	<!-- param image를 < %=image%> 변수에 저장  -->

	<script type="text/javascript">
window.onload = function(){
		var facecanvas = document.getElementById("facecanvas"); 
		var facecontext = facecanvas.getContext("2d"); 
		var faceimage = new Image() ; 
		faceimage.src='<%=image%>
		'
			faceimage.onload = function() {
				// 최대폭을 200 으로 정했다고 가정했을때
				// 최대폭을 넘어가는 경우 canvas 크기를 변경해 줍니다.
				var MAX_WIDTH = 200;
				var MAX_HEIGHT = 200;

				var width = faceimage.width;
				var height = faceimage.height;

				if (width > height) {
					if (width > MAX_WIDTH) {
						height *= MAX_WIDTH / width;
						width = MAX_WIDTH;
					}
				} else {
					if (height > MAX_HEIGHT) {

						width *= MAX_HEIGHT / height;

						height = MAX_HEIGHT;
					}
				}
				faceimage.width = width;
				faceimage.height = height;

				//faceimage에서 얼굴부분만 인식 값
				facecontext.drawImage(faceimage, 0, 0, faceimage.width,
						faceimage.height);
				//facecontext.drawImage(faceimage,0 ,0,400, 500 ); 

				//얼굴부분만 인식하는 작업
	<%String pfresult = (String) request.getAttribute("pfresult");
JSONObject obj = new JSONObject(pfresult);

int MAX_WIDTH = 200;
int MAX_HEIGHT = 200;

//obj.info 이미지 크기 조절
JSONObject info = (JSONObject) obj.get("info");
JSONObject size = (JSONObject) info.get("size");
int infowidth = (Integer) size.get("width");
int infoheight = (Integer) size.get("height");

//obj.faces[i]
JSONArray faces = (JSONArray) obj.get("faces");
for (int i = 0; i < faces.length(); i++) {
	JSONObject oneface = (JSONObject) faces.get(i); //하나의 얼굴에 관한 정보 
	JSONObject roi = (JSONObject) oneface.get("roi");

	int x = (Integer) roi.get("x") * MAX_WIDTH / infowidth;
	int y = (Integer) roi.get("y") * MAX_HEIGHT / infoheight;
	// int width = (Integer)roi.get("width");
	// int height = (Integer)roi.get("height");
	//int width = (Integer)roi.get("width")*infowidth/MAX_WIDTH;
	// int height = (Integer)roi.get("height")*infoheight/MAX_HEIGHT;	

	int width = (Integer) roi.get("width") * MAX_WIDTH / infowidth;
	int height = (Integer) roi.get("height") * MAX_HEIGHT / infoheight;%>
		//javascript로 반복문 속 변수 선언
				var x =
	<%=x%>
		var y =
	<%=y%>
		var width =
	<%=width%>
		var height =
	<%=height%>
		//얼굴 부분에만 사각형 
				facecontext.lineWidth = 2;
				facecontext.strokeStyle = "aqua";
				facecontext.strokeRect(x, y, width, height);
	<%} // for end%>
		} // faceimage end
		}//window end
	</script>


	<%
	String gvalue = "";
	String avalue = "";
	String evalue = "";
	int avalue4 = 0;
	long gconfi2 = 0;
	long aconfi2 = 0;
	long econfi2 = 0;

	String pfresult2 = (String) request.getAttribute("pfresult");
	JSONObject obj2 = new JSONObject(pfresult2);
	JSONArray faces2 = (JSONArray) obj2.get("faces");
	for (int i = 0; i < faces.length(); i++) {
		JSONObject oneface = (JSONObject) faces.get(i); //하나의 얼굴에 관한 정보 

		//성별 gender
		JSONObject gender = (JSONObject) oneface.get("gender");
		gvalue = (String) gender.get("value");
		BigDecimal gconfi = (BigDecimal) gender.get("confidence");
		gconfi2 = Math.round(gconfi.doubleValue() * 100);

		//나이 age
		JSONObject age = (JSONObject) oneface.get("age");

		//나이 범위 숫자 변환작업
		avalue = (String) age.get("value");
		String[] avalue2 = avalue.split("~"); // (String)["82", "86"]
		String avalue3 = avalue2[0]; //(String)"82"
		avalue4 = (int) Math.round(Integer.parseInt(avalue3) / 10) * 10;

		if (avalue4 >= 50) {
			avalue4 = 50;

		} else if (avalue4 < 10) {
			avalue4 = 10;
		}
		//나이 변환 종료 

		BigDecimal aconfi = (BigDecimal) age.get("confidence");
		aconfi2 = Math.round(aconfi.doubleValue() * 100);

		//감정 emotion
		JSONObject emotion = (JSONObject) oneface.get("emotion");
		evalue = (String) emotion.get("value");
		if (evalue.equals("disgust")) {
			evalue = "angry";
		}

		BigDecimal econfi = (BigDecimal) emotion.get("confidence");
		econfi2 = Math.round(econfi.doubleValue() * 100);

	}
	%>



	<form id="imageDB_form" action=/saveimage method=post>
		<!--   	 <form name="imageDB_form" action=/pf2 method=post> -->
		<input type='hidden' name="image_name"
			value='<%=(String) session.getAttribute("userProfile")%>'> <input
			type='hidden' name="image_age" value='<%=(int) avalue4%>'> <input
			type='hidden' name="image_gender" value="<%=gvalue%>"> <input
			type='hidden' name="image_emotion" value="<%=evalue%>"> <input
			type='hidden' name="member_email"
			value='<%=(String) session.getAttribute("userId")%>'>

	</form>

	<!-- db자동 저장 script -->
	<script type="text/javascript">
		this.document.getElementById("imageDB_form").submit();
		//document.imageDB_form.submit();
	</script>






</body>






</body>
</html>