package profile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



@Service
public class KakaoProfileCFRServiceImpl implements KakaoProfileCFRService {

	HashMap<String, Object> userInfo = new HashMap<String, Object>();

	// 액세스토큰 획득 메서드
	public String getAccessToken(String code) {
		String accessToken = "";
		String refreshToken = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {

			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=19b62a13fd7d5958d73872912e2a3c34");
			sb.append("&redirect_uri=http://localhost:9001/login2");
			sb.append("&code=" + code);

			bw.write(sb.toString());
			bw.flush();

			int responseCode = conn.getResponseCode();


			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			String result = "";
			while ((line = br.readLine()) != null) {
				result += line;
			}


			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			accessToken = element.getAsJsonObject().get("access_token").getAsString();
			refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();

			br.close();
			bw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}

	// 액세스토큰으로부터 유저 정보 확인
	public HashMap<String, Object> getUserInfo(String accessToken) {

		// HashMap<String, Object> userInfo = new HashMap<String, Object>();

		String reqUrl = "https://kapi.kakao.com/v2/user/me";
		try {

			URL url = new URL(reqUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + accessToken);
			int responseCode = conn.getResponseCode();


			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			

			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}


			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			
			JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
			String profile = properties.getAsJsonObject().get("profile_image").getAsString();
			String email = kakaoAccount.getAsJsonObject().get("email").getAsString();
			String age = kakaoAccount.getAsJsonObject().get("age_range").getAsString();
			String gender = kakaoAccount.getAsJsonObject().get("gender").getAsString();

			userInfo.put("nickname", nickname);
			userInfo.put("email", email);
			userInfo.put("profile", profile);
			userInfo.put("age", age);
			userInfo.put("gender", gender);
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userInfo;
	}
	

	// 로그인 후 유저정보(userinfo) 받아와 profile(url) 을 imageurl에 삽입 후 cfr 정보 출력
	@Override
	public String test(String file) {

		System.out.println("cfr실행");
		
		StringBuffer reqStr = new StringBuffer();
		StringBuffer response = new StringBuffer(); // 응답정보 받아오는것
		String clientId = "m4wcfw0o6q"; // Application Client ID";
		String clientSecret = "cSpYlzWe8M0HF2rYC4e2MpCM2z36IXb3TwUk8IW3"; // Application Client Secret";

		try {
			System.out.println("네이버 접속");
			String paramName = "image"; // 파라미터명은 image로 지정

			String apiURL = "https://naveropenapi.apigw.ntruss.com/vision/v1/face";
			//////////////////////////////////////////
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setUseCaches(false);
			con.setDoOutput(true);
			con.setDoInput(true);
			// multipart request
			String boundary = "---" + System.currentTimeMillis() + "---";
			con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
			con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
			OutputStream outputStream = con.getOutputStream();
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
			String LINE_FEED = "\r\n";

			////
			// 원격서버일 때
			URL imageurl = new URL( (String)(userInfo.get("profile")) );
			//

			URLConnection imagecon = imageurl.openConnection();
			InputStream imagestream = imagecon.getInputStream();

			// file 추가

			String fileName = imageurl.toString();

			System.out.println("cfr 이미지 분석중");
			writer.append("--" + boundary).append(LINE_FEED);
			writer.append("Content-Disposition: form-data; name=\"" + paramName + "\"; filename=\"" + fileName + "\"")
					.append(LINE_FEED);
			writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
			writer.append(LINE_FEED);
			writer.flush();
			// FileInputStream inputStream = new FileInputStream(uploadFile);
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			while ((bytesRead = imagestream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			outputStream.flush();
			imagestream.close();
			writer.append(LINE_FEED).flush();
			writer.append("--" + boundary + "--").append(LINE_FEED);
			writer.close();
			BufferedReader br = null;

			// 응답코드 확인
			int responseCode = con.getResponseCode();
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 오류 발생
				System.out.println("error!!!!!!! responseCode= " + responseCode);
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}
			String inputLine;
			if (br != null) {
				// StringBuffer response = new StringBuffer();
				while ((inputLine = br.readLine()) != null) {
					response.append(inputLine);
				}
				br.close();
			} else {
				System.out.println("error !!!");
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("에러발생");
			return e.toString(); // 에러발생시 string 으로 출력
		}
		System.out.println("cfr종료");
		return response.toString(); // if 블록의 지역변수이므로 전역변수로 선언한다
	}

}
