package user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	@Autowired
	@Qualifier("userservice")
	UserService service;

	
	/**
	 * 아이디중복체크
	 * */
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	@ResponseBody
	public int idCheck(@RequestParam("userid") String userid) {
		System.out.println("아이디중복체크 전달받은 아이디 = "+userid);
		int cnt = service.idCheck(userid);
		System.out.println("결과 = "+cnt);
		return cnt;
	}
	
	
	/**
	 * 회원가입
	 * */
	@RequestMapping("/userjoin")
	public String userjoin(String email, String userid, String name) {
		UserVO vo = new UserVO();
		vo.setMember_email(email);
		vo.setMember_id(userid);
		vo.setMember_name(name);
		System.out.println("===="+email+"====");
		System.out.println("===="+userid+"====");
		System.out.println("===="+name+"====");
		service.userjoin(vo);
		
		return "/index";//회원가입 후 메인페이지로 이동
	}
	
	/**
	 * 로그아웃
	 * */
	@RequestMapping("/userlogout")
	public ModelAndView userlogout(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		session= request.getSession();
		session.invalidate();

		mv.addObject("message", "로그아웃 되었습니다.");
		mv.setViewName("/index");
		return mv;

	}
	
	
	/**
	 * 내 정보 확인페이지
	 * */
	@RequestMapping("/myinfo")
	public ModelAndView myinfo(HttpServletRequest request) {
		//로그인정보 가져오기
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("vo");
		UserVO dbvo = service.emailCheck(vo.getMember_email());
		ModelAndView mv = new ModelAndView();
		mv.addObject("dbvo", dbvo);
		mv.setViewName("/user/myinfo");
		
		return mv;
	}
	
	/**
	 * 회원정보 수정
	 * */
	@RequestMapping("/updatemyinfo")
	public String updateInfo(UserVO vo) {
		System.out.println("이메일"+vo.getMember_email());
		System.out.println("name"+vo.getMember_name());
		service.updateInfo(vo);
		return "redirect:/myinfo";
	}
	
	
	/**
	 * 회원탈퇴
	 * */
	@RequestMapping("/deleteinfo")
	public String deleteInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session= request.getSession();
		UserVO vo = (UserVO)session.getAttribute("vo");
		System.out.println(vo.getMember_email());
		service.deleteInfo(vo.getMember_email());
		session.invalidate();
		return "redirect:/index";
	}
	
	/**
	 * 내 히스토리
	 * */
	@RequestMapping("/myhistory")
	public ModelAndView myhistory(HttpServletRequest request) {
		//로그인정보 가져오기
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("vo");
		System.out.println("****************");
		System.out.println(vo.getMember_email());
		
		List<HistoryJoinVO> list = service.historyByEmail(vo.getMember_email());
		System.out.println("개수 : "+list.size());
		for(HistoryJoinVO h :list) {
			System.out.println(h.getMember_email());
			System.out.println(h.getImage_name());
			System.out.println(h.getMusic_artist());
			System.out.println(h.getMusic_title());
			System.out.println(h.getMusic_url());
			System.out.println(h.getMusic_emotion());
			System.out.println(h.getImage_gender());
			System.out.println(h.getImage_age());
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", list);
		mv.setViewName("/user/myhistory");
		return mv;
	}
	
}
