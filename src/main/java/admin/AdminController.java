package admin;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import user.HistoryJoinVO;
import user.UserVO;

@Controller
public class AdminController {

	@Autowired
	@Qualifier("adminservice")
	AdminService service;
	
	
	
	/**
	 * 1.관리자메인#
	 * 2.회원리스트#
	 * 3.회원상세관리#
	 * 4.음악리스트#
	 * 5.음악추가#
	 * 6.음악삭제#
	 * 7.분석감정통계#
	 * 8.추천음악통계#
	 * */
	
	/**
	 * 관리자메인
	 * */
	@RequestMapping("/ad")
	public String main() {
		return "/admin/main";
	}
	
	/**
	 * 회원리스트
	 * */
	@RequestMapping("/aduserlist")
	public ModelAndView selectAll() {
		List<UserVO> list = service.selectAll();
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", list);
		mv.setViewName("/admin/list");
		return mv;
	}
	
	/**
	 * 회원상세관리
	 * */
	@RequestMapping("/aduserdetail/{member_id}")
	public ModelAndView detail(@PathVariable("member_id") String member_id) {
		System.out.println(member_id);
		UserVO user = service.selectById(member_id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		mv.setViewName("/admin/userdetail");
		return mv;
	}
	
	/**
	 * 회원상태수정 - 일반회원, 관리자 상태 변경
	 * */
	@RequestMapping("/updatestate/{member_id}/{member_state}")
	public ModelAndView updateState(@PathVariable("member_id") String member_id ,@PathVariable("member_state") int member_state) {
		System.out.println("**"+member_id);
		System.out.println(member_state);
		if(member_state == 0) {//일반회원으로 변경
			service.updateState0(member_id);
		}else if(member_state == 1) {//관리자로 변경
			service.updateState1(member_id);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/aduserlist");
		return mv;
	}
	
	/**
	 * 감정분석통계 - 어떤 감정이 많이 나오는지
	 * */
	@RequestMapping("/analysisemotion")
	public String countByEmotion(Model model) {
		List<HistoryJoinVO> list = service.countByEmotion();
		System.out.println("개수"+list.size());
		for(HistoryJoinVO vo : list) {
			System.out.println(vo.getMusic_emotion());
			System.out.println(vo.getCnt());
		}
		model.addAttribute("list", list);
		
		return "/admin/emotion";
	}
	
	/**
	 * 추천음악통계
	 * */
	@RequestMapping("/analysismusic")
	public String countByMusic(Model model) {
		List<HistoryJoinVO> list = service.countByMusic();
		System.out.println("개수"+list.size());
		for(HistoryJoinVO vo : list) {
			System.out.println(vo.getMusic_num());
			System.out.println(vo.getCnt());
			System.out.println(vo.getMusic_artist()+" - "+vo.getMusic_title());
		}
		model.addAttribute("list", list);
		
		return "/admin/music";
	}
	
}
