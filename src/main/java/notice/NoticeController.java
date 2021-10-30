package notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import reply.ReplyServiceImpl;
import reply.ReplyVO;
import user.UserVO;


@Controller
public class NoticeController {
	
	@Autowired
	@Qualifier("noticeservice")
	NoticeServiceImpl noticeservice;
	
	@Autowired
	@Qualifier("replyservice")
	ReplyServiceImpl replyservice;
	
	@RequestMapping("/notice") 
	public ModelAndView notice(HttpServletRequest request, @RequestParam("num")int num ){
		ModelAndView mv = new ModelAndView();
		//noticelist 가져오기
		NoticeVO[] noticelist = noticeservice.noticeList();
		
		int cnt = noticeservice.cnt();
		//한 페이지에 출력할 게시물 갯수
		int postNum = 10;
		//하단 페이징 번호 ([게시물 총 갯수 / 한 페이지에 출력할 갯수]의 올림)
		int pageNum = (int)Math.ceil((double)cnt/postNum);
		//출력할 게시물
		int displayPost = (num-1)*postNum;
		//num은 페이지 번호
		List list = null;
		list = noticeservice.listPage(displayPost,postNum);
		mv.addObject("list", list);
		mv.addObject("pageNum", pageNum);
		
		// 한번에 표시할 페이징 번호의 갯수
		int pageNum_cnt = 10;

		// 표시되는 페이지 번호 중 마지막 번호
		int endPageNum = (int)(Math.ceil((double)num / (double)pageNum_cnt) * pageNum_cnt);

		// 표시되는 페이지 번호 중 첫번째 번호
		int startPageNum = endPageNum - (pageNum_cnt - 1);
		
		// 마지막 번호 재계산
		int endPageNum_tmp = (int)(Math.ceil((double)cnt / (double)pageNum_cnt));
		 
		if(endPageNum > endPageNum_tmp) {
		 endPageNum = endPageNum_tmp;
		}
		
		boolean prev = startPageNum == 1? false : true;
		boolean next = endPageNum * pageNum_cnt >= cnt? false : true;
		
		//시작 및 끝 번호
		mv.addObject("startPageNum", startPageNum);
		mv.addObject("endPageNum", endPageNum);
		
		//이전 및 다음
		mv.addObject("prev", prev);
		mv.addObject("next", next);
		
		mv.addObject("noticelist", noticelist);
		mv.setViewName("notice/notice_board");
		//세션정보 가져오기
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("vo");
		//모델에 세션정보 넣어주기
		mv.addObject("vo", vo);
		//문의게시판 page로 이동
		return mv;
	}
	
	
	@RequestMapping("/noticeview")
	public ModelAndView noticeView(int code, Integer number, String flag, String message, HttpServletRequest request) { //?code=1 get 방식으로
		//공지사항 데이터 전달
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("vo");		
		NoticeVO nvo = noticeservice.noticeOne(code);
		ReplyVO[] replyList = replyservice.noticeReplyList(code);
		if (number != null) {
			mv.addObject("number", (int)number);			
		}
		if (flag!= null) {
			mv.addObject("flag", flag);			
		}
		mv.addObject("nvo", nvo);
		mv.addObject("vo", vo);
		mv.addObject("replyList", replyList);
		mv.addObject("message", message);
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
		NoticeVO nvo = new NoticeVO();
		//session 에서 회원 vo 가져오기
		UserVO vo = (UserVO)session.getAttribute("vo");
		//vo의 이메일 가져오기
		//email 없이 테스트용 if문
		if (vo==null) {
			//session 이 없으면 로그인창으로 보내주기
			mv.setViewName("redirect:index");
			mv.addObject("message", "로그인 정보가 없습니다.");
		} else {
		
			String email = vo.getMember_email();
			String notice_content = request.getParameter("notice_content");
			String notice_title = request.getParameter("notice_title");
			nvo.setMember_email(email);			
			nvo.setNotice_content(notice_content);
			nvo.setNotice_subject(notice_title);
			noticeservice.insertNotice(nvo);
			mv.setViewName("redirect:notice");
			mv.addObject("nvo", nvo);
			mv.addObject("message", "게시글이 등록되었습니다.");

		}
		return mv;
	}

	
	@RequestMapping("/noticeupdate")
	public ModelAndView noticeUpdate(int code) {
		//qna 수정 페이지로 이동
		ModelAndView mv = new ModelAndView();
		NoticeVO nvo = noticeservice.noticeOne(code);
		mv.addObject("nvo", nvo);
		mv.setViewName("notice/notice_update");
		
		return mv;
	}
	

	
	
	@RequestMapping("/noticeupdateaction")
	public ModelAndView noticeUpdateAction(HttpServletRequest request, HttpServletResponse response) {
		//글수정 action
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("vo");
		if (vo == null) {
			//세션 정보가 없으면 로그인 창으로 보내기
			mv.setViewName("redirect:index");
			mv.addObject("message", "로그인 정보가 없습니다.");
		} else {
			if (vo.getMember_state()==1) { //세션 정보가 관리자라면
				int code = Integer.parseInt(request.getParameter("code"));
				NoticeVO nvo = noticeservice.noticeOne(code);
				String email = vo.getMember_email();
				System.out.println(code);
				String content = request.getParameter("content");
				System.out.println(content);
				String subject = request.getParameter("subject");
				System.out.println(subject);
				nvo.setNotice_content(content);
				nvo.setNotice_subject(subject);
				nvo.setNotice_code((int)code);
				nvo.setMember_email(email);
				noticeservice.updateNotice(nvo);
				mv.setViewName("redirect:noticeview?code="+(int)code);
				mv.addObject("message", "게시글이 수정되었습니다.");
			} else {
				mv.addObject("message", "로그인 정보가 없습니다.");
			}
		}
		return mv;
	}
	
	@RequestMapping("/noticedelete")
	public ModelAndView noticeDelete(int code, HttpServletRequest request) {
		//글삭제 action
		ModelAndView mv = new ModelAndView();
		NoticeVO nvo = noticeservice.noticeOne(code);
		String notice_email = nvo.getMember_email();
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("vo");
		if (vo.getMember_email().equals(notice_email)) { //세션에 등록된 이메일과 qnavo의 이메일이 같으면
			//입력된 notice코드의 글 삭제
			noticeservice.deleteNotice(code);
			mv.setViewName("redirect:notice");
			mv.addObject("message", "게시글이 삭제되었습니다.");
		} else {
			//로그인 페이지로 보내기
			mv.setViewName("redirect:index");
			mv.addObject("message", "로그인 정보가 없습니다.");
		}
		return mv;
		
	}
	
	@RequestMapping(value="/noticeInsertReply", method=RequestMethod.POST)
	public ModelAndView noticeInsertReply(Integer code, HttpServletRequest request) {
		//댓글달기 action
		ModelAndView mv = new ModelAndView();
		ReplyVO rvo = new ReplyVO();
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("vo");
		if (vo==null) { //세션 정보 없음
			//로그인 페이지로 보내기
			mv.addObject("message", "로그인 후 댓글을 입력할 수 있습니다.");
			mv.setViewName("redirect:index");
		} else { //세션 있는 경우
			String email = vo.getMember_email();
			String comment = (String) request.getParameter("comment");
			rvo.setReply_content(comment);
			rvo.setMember_email(email);
			rvo.setNotice_code((int)code);
			replyservice.insertNoticeReply(rvo);
			mv.setViewName("redirect:noticeview?code="+(int)code);
			mv.addObject("message", "댓글이 입력되었습니다.");
		}
		return mv;
	}
	
	@RequestMapping("/noticeUpdateReply")
	public ModelAndView noticeUpdateReply(int code, Integer number, HttpServletRequest request) {
		//댓글달기 action
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		ReplyVO rvo = new ReplyVO();
		rvo.setNotice_code(code);
		rvo.setReply_number((int)number);
		ReplyVO rvo2 = replyservice.getNoticeReply(rvo);
		UserVO vo = (UserVO)session.getAttribute("vo");
		String email = vo.getMember_email();
		if (email.equals(rvo2.getMember_email())) { //작성자와 세션email 이 같을때
			mv.setViewName("redirect:noticeview?code="+(int)code + "&number="+ (int)number +"&flag=u");			
			
		} else {
			mv.setViewName("redirect:index");
			mv.addObject("message", "작성자가 일치하지 않습니다.");
		}
		return mv;
	}
	
	@RequestMapping("/noticeUpdateReplyAction")
	public ModelAndView noticeUpdateReplyAction(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		//댓글달기 action
		ReplyVO rvo = new ReplyVO();
		//파라미터 가져오기
		String flag = request.getParameter("flag");
		int noticeCode = Integer.parseInt(request.getParameter("noticeCode"));
		int replyNumber = Integer.parseInt(request.getParameter("replyNumber"));
		String comment = request.getParameter("comment");
		//세션가져오기
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("vo");
		//rvo에 공지사항 글번호와 댓글번호를 넣어준다
		rvo.setReply_number(replyNumber);
		rvo.setNotice_code(noticeCode);
		//기존 rvo 에 저장된 정보 가져오기
		ReplyVO rvo2 = replyservice.getNoticeReply(rvo);
		
		if (vo == null) {//세션이 없으면
			//로그인 페이지로 이동
			mv.addObject("message", "로그인 정보가 없습니다.");
			mv.setViewName("redirect:index");
		} else {
			String email = vo.getMember_email();
			if (email.equals(rvo2.getMember_email())) { //세션 이메일과 댓글 작성자가 같으면
				rvo.setMember_email(email);	
				rvo.setReply_content(comment);
				replyservice.updateNoticeReply(rvo);
				mv.setViewName("redirect:noticeview?code="+ noticeCode);	
				mv.addObject("message", "댓글이 수정되었습니다.");
			} else {
				mv.addObject("message", "작성자가 일치하지 않습니다");
				mv.setViewName("redirect:noticeview?code="+ noticeCode);	
			}
		}
		return mv;
	}
	
	@RequestMapping("/noticeDeleteReply")
	public ModelAndView noticeDeleteReply(int number, Integer code, HttpServletRequest request) {
		//댓글달기 action
		ModelAndView mv = new ModelAndView();
		ReplyVO rvo = new ReplyVO();
		rvo.setNotice_code((int)code);
		rvo.setReply_number(number);
		ReplyVO rvo2 = replyservice.getNoticeReply(rvo);
		//세션정보 가져오기
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("vo");
		if (vo == null) {
			mv.setViewName("redirect:index");
		} else {
			String email = vo.getMember_email();
			if (email.equals(rvo2.getMember_email())) {
				replyservice.deleteNoticeReply(rvo);
				mv.setViewName("redirect:noticeview?code="+(int)code);	
				mv.addObject("message", "댓글이 삭제되었습니다.");
			} else {
				mv.addObject("message", "작성자가 일치하지 않습니다.");
				mv.setViewName("redirect:noticeview?code="+(int)code);	
			}
		}
		return mv;
	}
	


}
