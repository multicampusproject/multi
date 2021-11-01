package musiclist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import musiclist.MusicListVO;

@Controller
public class MusicListController {
	
	@Autowired
	//@Qualifier("service")
	MusicListService service;
	
	
	
	/**
	 * 음악리스트
	 * */
	@RequestMapping("/musiclist")
	public ModelAndView musiclist() {
	List<MusicListVO> list = service.multimusicList();
	ModelAndView mv = new ModelAndView();
	
	mv.addObject("aa", "까꿍");
	mv.addObject("musiclist", list);
	mv.setViewName("/musiclist/musiclist");
	return mv;
	
	}
	
	/**
	 * 음악 insert form
	 * */
	@RequestMapping("/insertform")
	public String insertform() {
		return "/musiclist/insert";
	}
	
	/**
	 * 음악 insert
	 * */
	@RequestMapping("/musicinsert")
	public String musicInsert(MusicListVO vo) {
		System.out.println(vo.getMusic_age());
		System.out.println(vo.getMusic_artist());
		System.out.println(vo.getMusic_emotion());
		System.out.println(vo.getMusic_gender());
		System.out.println(vo.getMusic_title());
		System.out.println(vo.getMusic_url());
		
		service.multimusicInsert(vo);
		return "redirect:/musiclist";
	}

	/**
	 * 음악 삭제
	 * */
	@RequestMapping("/deletemusic/{music_num}")
	public String musicDelete(@PathVariable("music_num") int music_num) {
		System.out.println(music_num);
		service.multimusicDelete(music_num);
		return "redirect:/musiclist";
	}
	

}

