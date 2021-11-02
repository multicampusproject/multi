package profile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class KakaoProfileController {

	@Autowired
	KakaoProfileCFRServiceImpl KPCS;

	@Autowired
	@Qualifier("DBservice")
	KakaoProfileDBService DBservice;

	String name = "";

	@RequestMapping("/login2")
	public ModelAndView login(String code, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		// 1번 인증코드 요청 전달

		String accessToken = KPCS.getAccessToken(code);

		// 2번 인증코드로 토큰 전달
		HashMap<String, Object> userInfo = KPCS.getUserInfo(accessToken);

		// 클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
	/*	if (userInfo.get("email") != null) {
			session.setAttribute("userId", userInfo.get("email"));
			session.setAttribute("userName", userInfo.get("nickname"));
			session.setAttribute("userAge", userInfo.get("age"));
			session.setAttribute("userProfile", userInfo.get("profile"));
			session.setAttribute("userGender", userInfo.get("gender"));
			session.setAttribute("accessToken", accessToken);

		}
		*/
			mv.addObject("userId", userInfo.get("email"));
			mv.addObject("userName", userInfo.get("nickname"));
			mv.addObject("userAge", userInfo.get("age"));
			mv.addObject("userProfile", userInfo.get("profile"));
			mv.addObject("userGender", userInfo.get("gender"));

			
		
				System.out.println(userInfo.get("profile") );
				System.out.println(userInfo.get("gender"));
				System.out.println(userInfo.get("nickname"));
				System.out.println("프로필페이지 실행중");
				mv.setViewName("/profile/login");
				return mv;
			
	
	

	}

	// 이미지 분석 controller
	@RequestMapping(value = "/cfr", method = RequestMethod.GET)
	public ModelAndView pf2(String image) throws Exception {

		ModelAndView mv = new ModelAndView();
		String jsonModel = KPCS.test(image); // 얼굴감지 결과 json으로 가져옴
		System.out.println(jsonModel);
		if(jsonModel.contains("\"faceCount\":0") != true ) { //image를 넘겨받는경우
		mv.addObject("pfresult", jsonModel); // 모델에 저장
		mv.setViewName("/profile/cfrImage");
		System.out.println(image + "넘겨받는 이미지");
		System.out.println("이미지분석 컨트롤러 종료1");
		return mv;
		} else { //image를 넘겨받지 못하는 경우
			mv.addObject("error", "잘못된 프로필 사진입니다.");
			mv.setViewName("/profile/errorpage");
			return mv;
		}
	}

	// 이미지 분석 결과 저장 controller2
	@RequestMapping(value = "/saveimage", method = RequestMethod.POST)
	public ModelAndView pfresult(@ModelAttribute("vo") KakaoImageVO vo, HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView();

		// image_name , image_age, image_gender, image_emotion, member_email 객체 생성
		name = vo.getImage_name();
		String age = vo.getImage_age();
		String gender = vo.getImage_gender();
		String emotion = vo.getImage_emotion();
		String email = vo.getMember_email();

		// image_name 중복을 막기위해 뒤에 simpledateformat 추가
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		Date time = new Date();
		name = name + "(" + format.format(time) + ")";

		// 세션 저장
		session.setAttribute("imageName", name);
		session.setAttribute("imageAge", age);
		session.setAttribute("imageGender", gender);
		session.setAttribute("imageEmotion", emotion);
		session.setAttribute("imageEmail", email);

		// vo객체 저장
		vo.setImage_name(name);
		vo.setImage_age(age);
		vo.setImage_gender(gender);
		vo.setImage_emotion(emotion);
		vo.setMember_email(email);

		DBservice.insertImage(vo);

		mv.setViewName("/profile/relogin");
		System.out.println("이미지 저장메소드 종료");
		return mv;
	}

	// 이미지 분석 controller2
	@RequestMapping(value = "/cfr2", method = RequestMethod.POST)
	public ModelAndView pf3(String image) throws Exception {

		ModelAndView mv = new ModelAndView();
		String jsonModel = KPCS.test(image); // 얼굴감지 결과 json으로 가져옴
		mv.addObject("pfresult", jsonModel); // 모델에 저장
		mv.setViewName("/profile/cfrprofile");
		System.out.println("이미지분석 컨트롤러 종료2");
		return mv;
	}

	// kakaoimage - kakaomusic 매칭 결과 출력1
	@RequestMapping(value = "/selectMusic", method = RequestMethod.GET)
	public ModelAndView selectMusic(@ModelAttribute("vo") KakaoImageVO vo, HttpSession session) throws IOException {

		ModelAndView mv = new ModelAndView();
		List<KakaoMusicVO> list = DBservice.selectMusic(vo);
		mv.addObject("list", list);
		session.setAttribute("list", list);
		mv.setViewName("/profile/selectMusic");
		System.out.println("음악추천 종료1");
		return mv;
	}

	// history 저장 메소드
	@RequestMapping(value = "/rec_music", method = RequestMethod.POST)
	public ModelAndView inserthistory(@ModelAttribute("vo") KakaoHistoryVO vo, HttpSession session) throws IOException {

		ModelAndView mv = new ModelAndView();

		int num = vo.getMusic_num();
		String email = vo.getMember_email();

		// 세션 저장
		session.setAttribute("musicNum", num);
		session.setAttribute("musicEmail", email);

		// vo객체 저장
		vo.setImage_name(name);
		vo.setMusic_num(num);
		vo.setMember_email(email);

		DBservice.insertHistory(vo);

		mv.setViewName("/profile/recMusic");
		System.out.println("히스토리저장 메소드 종료");

		return mv;
	}

}
