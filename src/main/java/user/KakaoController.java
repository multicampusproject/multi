package user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
public class KakaoController {

	HashMap<String, Object> userInfo = new HashMap<String, Object>();
	 String access_Token = "";
	
	@Autowired
	@Qualifier("userservice")
	UserService service;
	
	/**
	 * 메인
	 * */
	@RequestMapping("/index")
	public String index(HttpSession session) {
		UserVO vo = (UserVO)session.getAttribute("vo");
		if(vo != null) {
			UserVO dbvo = service.emailCheck(vo.getMember_email());
			session.setAttribute("isLogOn", true);
			session.setAttribute("vo", dbvo);
			//session.setAttribute("imageurl", userInfo.get("profile").toString());
			session.setAttribute("userId", userInfo.get("email"));
			session.setAttribute("userName", userInfo.get("nickname"));
			session.setAttribute("userAge", userInfo.get("age"));
			session.setAttribute("userProfile", userInfo.get("profile"));
			session.setAttribute("userGender", userInfo.get("gender"));
			session.setAttribute("accessToken", access_Token);
			System.out.println("인덱스실행중");
			
			
			
		}

		
		
		return "index";
	}
	
	
	@RequestMapping(value = "/login/getKakaoAuthUrl")
	public @ResponseBody String getKakaoAuthUrl(
			HttpServletRequest request) throws Exception {
		String reqUrl = 
				"https://kauth.kakao.com/oauth/authorize"
				//+ "?client_id=9f408ed91ceb3fd32f37ca846fc220f7"
				+ "?client_id=19b62a13fd7d5958d73872912e2a3c34"
				+ "&redirect_uri=http://localhost:9001/loginresult"
				+ "&response_type=code";
		
		return reqUrl;
	}
	
	// 카카오 연동정보 조회
	//@RequestMapping(value = "/login/oauth_kakao")
	@RequestMapping(value = "/loginresult")
	public String oauthKakao(
			@RequestParam(value = "code", required = false) String code
			, Model model, HttpServletRequest request) throws Exception {

		System.out.println("#########" + code);
        String access_Token = getAccessToken(code);
        System.out.println("###access_Token#### : " + access_Token);
        
        
        userInfo = getUserInfo(access_Token);
        System.out.println("###access_Token#### : " + access_Token);
        System.out.println("###userInfo#### : " + userInfo.get("email"));
        System.out.println("###profile_image#### : " + userInfo.get("profile"));
        System.out.println(userInfo.get("nickname"));
        System.out.println(userInfo.get("age"));
        System.out.println(userInfo.get("gender"));
        System.out.println("연동조회 완료");


        //JSONObject kakaoInfo =  new JSONObject(userInfo);
        //model.addAttribute("kakaoInfo", kakaoInfo);
        model.addAttribute("email", userInfo.get("email"));
        
        String email = userInfo.get("email").toString();
        String imageurl = userInfo.get("profile").toString();
        System.out.println(email + "   연동조회 email");
        
        
        UserVO dbvo = service.emailCheck(email);
        
    	//System.out.println("------------------------------------------------");
    	//System.out.println(email);
    	//System.out.println(dbvo.getEmail());
    	if(dbvo == null) {//db에 정보없다 -> 회원가입 페이지로 이동
    		model.addAttribute("member_email", email);
    		return "/user/joinform"; //회원가입 페이지
    		
    	}else {//db에 정보 있다 -> 로그인하고 메인으로 이동
    		//로그인 처리하기
    		HttpSession session = request.getSession();
    		session= request.getSession();
    		session.setAttribute("isLogOn", true);
    		session.setAttribute("vo", dbvo);
    		//프로필url 세션에 저장
    		//session.setAttribute("imageurl", imageurl);
			session.setAttribute("userId", userInfo.get("email"));
			session.setAttribute("userName", userInfo.get("nickname"));
			session.setAttribute("userAge", userInfo.get("age"));
			session.setAttribute("userProfile", userInfo.get("profile"));
			session.setAttribute("userGender", userInfo.get("gender"));
			session.setAttribute("accessToken", access_Token);
    		System.out.println("세션 저장완료");
    		
    		
    		
    		//model.addAttribute("message", "로그인 되었습니다.");
    		return "/index"; //메인페이지로 이동
    	}
        //return "/user/test"; //본인 원하는 경로 설정
	}
	
    //토큰발급
	public String getAccessToken (String authorize_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //  URL연결은 입출력에 사용 될 수 있고, POST 혹은 PUT 요청을 하려면 setDoOutput을 true로 설정해야함.
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //	POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            //sb.append("&client_id=9f408ed91ceb3fd32f37ca846fc220f7");  //본인이 발급받은 key
            sb.append("&client_id=19b62a13fd7d5958d73872912e2a3c34");  //본인이 발급받은 key
            sb.append("&redirect_uri=http://localhost:9001/loginresult");     // 본인이 설정해 놓은 경로
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            //    결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            
            
            
            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return access_Token;
    }
	
    //유저정보조회
    public HashMap<String, Object> getUserInfo (String access_Token) {

        //    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            //    요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            
            /* 수현님 userinfo
            //JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            //String profile_image = properties.getAsJsonObject().get("profile_image").getAsString();
            String email = kakao_account.getAsJsonObject().get("email").getAsString();
            //String profile = kakao_account.getAsJsonObject().get("profile").getAsString();
            //JsonArray profile = kakao_account.getAsJsonObject().get("profile").getAsJsonArray();
            //String profile_image_url = profile.getAsJsonObject().get("profile_image_url").getAsString();
            
            
            JsonObject profile = kakao_account.getAsJsonObject().get("profile").getAsJsonObject();
            String image_url = profile.getAsJsonObject().get("profile_image_url").getAsString();

            System.out.println("**** 프로필 url *** : "+image_url);
            
            userInfo.put("accessToken", access_Token);
            //userInfo.put("profile_image", profile_image);
            userInfo.put("email", email);
            userInfo.put("profile", image_url);
            */
            
            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			System.out.println("element: "+element);
			JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
			JsonObject profile2 = kakaoAccount.getAsJsonObject().get("profile").getAsJsonObject();

			/*
			if(properties.getAsJsonObject().get("profile_image").getAsString().equals("") ) {
				String profile = properties.getAsJsonObject().get("http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg").getAsString();
				userInfo.put("profile", profile);
			} else {
				String profile = properties.getAsJsonObject().get("profile_image").getAsString();
				userInfo.put("profile", profile);
			}
			*/

			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
			String profile = profile2.getAsJsonObject().get("profile_image_url").getAsString();
			String email = kakaoAccount.getAsJsonObject().get("email").getAsString();
			String age = kakaoAccount.getAsJsonObject().get("age_range").getAsString();
			String gender = kakaoAccount.getAsJsonObject().get("gender").getAsString();

			userInfo.put("nickname", nickname);
			userInfo.put("email", email);
			userInfo.put("profile", profile);
			userInfo.put("age", age);
			userInfo.put("gender", gender);
	

            
            
            
            

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return userInfo;
    }
 }