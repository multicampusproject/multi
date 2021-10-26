package qna;

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
public class QnAController {
	
	@Autowired
	@Qualifier("qnaservice")
	QnAServiceImpl qnaservice;
	
	@Autowired
	@Qualifier("replyservice")
	ReplyServiceImpl replyservice;
	
	@RequestMapping("/qna") 
	public ModelAndView qna() {
		ModelAndView mv = new ModelAndView();
		QnAVO[] qnalist = qnaservice.qnaList();
		mv.addObject("qnalist", qnalist);
		mv.setViewName("qna/qna_board");
		//문의게시판 page로 이동
		return mv;
	}
	
	@RequestMapping("/qnaview")
	public ModelAndView qnaView(int code, Integer number, String flag) { //?code=1 get 방식으로
		//공지사항 데이터 전달
		ModelAndView mv = new ModelAndView();
		QnAVO vo = qnaservice.qnaOne(code);
		ReplyVO[] replyList = replyservice.qnaReplyList(code);
		if (number != null) {
			mv.addObject("number", (int)number);			
		}
		if (flag!= null) {
			mv.addObject("flag", flag);			
		}
		mv.addObject("vo", vo);
		mv.addObject("replyList", replyList);
		mv.setViewName("qna/qna_view");
		return mv;
	}
	
	
	@RequestMapping("/qnawrite")
	public String qnaWrite() {
		//공지사항 작성 페이지로 이동
		return "qna/qna_write";
	}
	
	@RequestMapping(value="/qnawriteaction", method=RequestMethod.POST)
	public ModelAndView qnaWriteAction(HttpServletRequest request, HttpSession session) {
		//공지사항 작성 페이지로 이동
		ModelAndView mv = new ModelAndView();
		QnAVO vo = new QnAVO();
		String email = (String) session.getAttribute("email");
		//email 없이 테스트용 if문
		if (email==null) {
			vo.setMember_email("syouo@naver.com");
		} else {
			vo.setMember_email(email);			
		}
		String qna_content = request.getParameter("qna_content");
		String qna_title = request.getParameter("qna_title");
		vo.setQna_content(qna_content);
		vo.setQna_subject(qna_title);
		qnaservice.insertQnA(vo);
		mv.addObject("vo", vo);
		mv.setViewName("redirect:qna");
		return mv;
	}

	
	@RequestMapping("/qnaupdate")
	public ModelAndView qnaUpdate(int code) {
		//qna 수정 페이지로 이동
		ModelAndView mv = new ModelAndView();
		QnAVO vo = qnaservice.qnaOne(code);
		mv.addObject("vo", vo);
		mv.setViewName("qna/qna_update");
		return mv;
	}
	

	
	
	@RequestMapping("/qnaupdateaction")
	public ModelAndView qnaUpdateAction(HttpServletRequest request) {
		//글수정 action
		ModelAndView mv = new ModelAndView();
		int code = Integer.parseInt(request.getParameter("code"));
		System.out.println(code);
		QnAVO vo = qnaservice.qnaOne(code);
		String content = request.getParameter("content");
		System.out.println(content);
		String subject = request.getParameter("subject");
		System.out.println(subject);
		vo.setQna_content(content);
		vo.setQna_subject(subject);
		vo.setQna_code((int)code);
		
		qnaservice.updateQnA(vo);
		mv.setViewName("redirect:qnaview?code="+(int)code);
		return mv;
	}
	
	@RequestMapping("/qnadelete")
	public ModelAndView qnaDelete(int code, HttpServletRequest request) {
		//글삭제 action
		ModelAndView mv = new ModelAndView();
		//HttpSession session = request.getSession();
		//if (session.getAttribute("email").equals(vo.member_email)) { //세션에 등록된 이메일과 qnavo의 이메일이 같으면
			//입력된 qna코드의 글 삭제
		qnaservice.deleteQnA(code);
			//mv.addObject("message", "삭제되었습니다.");
		//} else {
			//다시 로그인 요청
			//mv.addObject("message", "다시 로그인해주세요");
		//}
		mv.setViewName("redirect:qna");
		return mv;
		
	}
	
	@RequestMapping("/qnaInsertReply")
	public ModelAndView qnaInsertReply(int code, HttpServletRequest request) {
		//댓글달기 action
		ModelAndView mv = new ModelAndView();
		ReplyVO vo = new ReplyVO();
		String comment = (String) request.getParameter("comment");
		vo.setReply_content(comment);
		vo.setMember_email("syouo@naver.com");
		vo.setQna_code(code);
		replyservice.insertQnAReply(vo);
		mv.setViewName("redirect:qnaview?code="+(int)code);
		return mv;
	}
	
	@RequestMapping("/qnaUpdateReply")
	public String qnaUpdateReply(int code, Integer number, HttpServletRequest request) {
		//댓글달기 action
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		return "redirect:qnaview?code="+(int)code + "&number="+ (int)number +"&flag=u";
	}
	
	@RequestMapping("/qnaUpdateReplyAction")
	public String qnaUpdateReplyAction(HttpServletRequest request) {
		//댓글달기 action
		ReplyVO vo = new ReplyVO();
		String flag = request.getParameter("flag");
		int qnaCode = Integer.parseInt(request.getParameter("qnaCode"));
		int replyNumber = Integer.parseInt(request.getParameter("replyNumber"));
		HttpSession session = request.getSession();
		String comment = request.getParameter("comment");
		String email = "syouo@naver.com";
		vo.setReply_number(replyNumber);
		vo.setNotice_code(qnaCode);
		vo.setMember_email(email);	
		vo.setReply_content(comment);
		replyservice.updateQnAReply(vo);
		return "redirect:qnaview?code="+ qnaCode;
	}
	
	@RequestMapping("/qnaDeleteReply")
	public ModelAndView qnaDeleteReply(int number, Integer code, HttpServletRequest request) {
		//댓글달기 action
		ModelAndView mv = new ModelAndView();
		ReplyVO vo = new ReplyVO();
		vo.setQna_code((int)code);
		vo.setReply_number(number);
		replyservice.deleteQnAReply(vo);
		//if (code==null) {
			//System.out.println("코드값이 없습니다.");
		//} else {
			//notice_code 존재하는 경우
		//}
		mv.setViewName("redirect:qnaview?code="+(int)code);
		//mv.addObject("vo",vo);
		//HttpSession session = request.getSession();
		//String email = (String)session.getAttribute("email");
		return mv;
	}
	
}
