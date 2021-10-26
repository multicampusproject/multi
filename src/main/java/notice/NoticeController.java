package notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import reply.ReplyServiceImpl;
import reply.ReplyVO;


@Controller
public class NoticeController {
	
	@Autowired
	@Qualifier("noticeservice")
	NoticeServiceImpl noticeservice;
	
	@Autowired
	@Qualifier("replyservice")
	ReplyServiceImpl replyservice;
	
	@RequestMapping("/notice") 
	public ModelAndView notice() {
		ModelAndView mv = new ModelAndView();
		NoticeVO[] noticelist = noticeservice.noticeList();
		mv.addObject("noticelist", noticelist);
		mv.setViewName("notice/notice_board");
		//문의게시판 page로 이동
		return mv;
	}
	
	@RequestMapping("/noticeview")
	public ModelAndView noticeView(int code, Integer number, String flag) { //?code=1 get 방식으로
		//공지사항 데이터 전달
		ModelAndView mv = new ModelAndView();
		NoticeVO vo = noticeservice.noticeOne(code);
		ReplyVO[] replyList = replyservice.noticeReplyList(code);
		if (number != null) {
			mv.addObject("number", (int)number);			
		}
		if (flag!= null) {
			mv.addObject("flag", flag);			
		}
		mv.addObject("vo", vo);
		mv.addObject("replyList", replyList);
		mv.setViewName("notice/notice_view");
		return mv;
	}
	
	
	@RequestMapping("/noticewrite")
	public String noticeWrite() {
		//공지사항 작성 페이지로 이동
		return "notice/notice_write";
	}
	
	@RequestMapping(value="/noticewriteaction", method=RequestMethod.POST)
	public ModelAndView noticeWriteAction(HttpServletRequest request, HttpSession session) {
		//공지사항 작성 페이지로 이동
		ModelAndView mv = new ModelAndView();
		NoticeVO vo = new NoticeVO();
		String email = (String) session.getAttribute("email");
		//email 없이 테스트용 if문
		if (email==null) {
			vo.setMember_email("syouo@naver.com");
		} else {
			vo.setMember_email(email);			
		}
		String notice_content = request.getParameter("notice_content");
		String notice_title = request.getParameter("notice_title");
		vo.setNotice_content(notice_content);
		vo.setNotice_subject(notice_title);
		noticeservice.insertNotice(vo);
		mv.addObject("vo", vo);
		mv.setViewName("redirect:notice");
		return mv;
	}

	
	@RequestMapping("/noticeupdate")
	public ModelAndView noticeUpdate(int code) {
		//qna 수정 페이지로 이동
		ModelAndView mv = new ModelAndView();
		NoticeVO vo = noticeservice.noticeOne(code);
		mv.addObject("vo", vo);
		mv.setViewName("notice/notice_update");
		return mv;
	}
	

	
	
	@RequestMapping("/noticeupdateaction")
	public ModelAndView noticeUpdateAction(HttpServletRequest request) {
		//글수정 action
		ModelAndView mv = new ModelAndView();
		int code = Integer.parseInt(request.getParameter("code"));
		System.out.println(code);
		NoticeVO vo = noticeservice.noticeOne(code);
		String content = request.getParameter("content");
		System.out.println(content);
		String subject = request.getParameter("subject");
		System.out.println(subject);
		vo.setNotice_content(content);
		vo.setNotice_subject(subject);
		vo.setNotice_code((int)code);
		
		noticeservice.updateNotice(vo);
		mv.setViewName("redirect:noticeview?code="+(int)code);
		return mv;
	}
	
	@RequestMapping("/noticedelete")
	public ModelAndView noticeDelete(int code, HttpServletRequest request) {
		//글삭제 action
		ModelAndView mv = new ModelAndView();
		//HttpSession session = request.getSession();
		//if (session.getAttribute("email").equals(vo.member_email)) { //세션에 등록된 이메일과 qnavo의 이메일이 같으면
			//입력된 qna코드의 글 삭제
			noticeservice.deleteNotice(code);
			//mv.addObject("message", "삭제되었습니다.");
		//} else {
			//다시 로그인 요청
			//mv.addObject("message", "다시 로그인해주세요");
		//}
		mv.setViewName("redirect:notice");
		return mv;
		
	}
	
	@RequestMapping(value="/noticeInsertReply", method=RequestMethod.POST)
	public ModelAndView noticeInsertReply(Integer code, HttpServletRequest request) {
		//댓글달기 action
		ModelAndView mv = new ModelAndView();
		ReplyVO vo = new ReplyVO();
		String comment = (String) request.getParameter("comment");
		vo.setReply_content(comment);
		vo.setMember_email("syouo@naver.com");
		vo.setNotice_code((int)code);
		replyservice.insertNoticeReply(vo);
		mv.setViewName("redirect:noticeview?code="+(int)code);
		return mv;
	}
	
	@RequestMapping("/noticeUpdateReply")
	public String noticeUpdateReply(int code, Integer number, HttpServletRequest request) {
		//댓글달기 action
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		return "redirect:noticeview?code="+(int)code + "&number="+ (int)number +"&flag=u";
	}
	
	@RequestMapping("/noticeUpdateReplyAction")
	public String noticeUpdateReplyAction(HttpServletRequest request) {
		//댓글달기 action
		ReplyVO vo = new ReplyVO();
		String flag = request.getParameter("flag");
		int noticeCode = Integer.parseInt(request.getParameter("noticeCode"));
		int replyNumber = Integer.parseInt(request.getParameter("replyNumber"));
		HttpSession session = request.getSession();
		String comment = request.getParameter("comment");
		String email = "syouo@naver.com";
		vo.setReply_number(replyNumber);
		vo.setNotice_code(noticeCode);
		vo.setMember_email(email);	
		vo.setReply_content(comment);
		replyservice.updateNoticeReply(vo);
		return "redirect:noticeview?code="+ noticeCode;
	}
	
	@RequestMapping("/noticeDeleteReply")
	public ModelAndView noticeDeleteReply(int number, Integer code, HttpServletRequest request) {
		//댓글달기 action
		ModelAndView mv = new ModelAndView();
		ReplyVO vo = new ReplyVO();
		vo.setNotice_code((int)code);
		vo.setReply_number(number);
		replyservice.deleteNoticeReply(vo);
		//if (code==null) {
			//System.out.println("코드값이 없습니다.");
		//} else {
			//notice_code 존재하는 경우
		//}
		mv.setViewName("redirect:noticeview?code="+(int)code);
		//mv.addObject("vo",vo);
		//HttpSession session = request.getSession();
		//String email = (String)session.getAttribute("email");
		return mv;
	}
	
}
