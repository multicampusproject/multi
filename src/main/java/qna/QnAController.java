package qna;

import java.util.List;

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
import user.UserVO;


@Controller
public class QnAController {
	
	@Autowired
	@Qualifier("qnaservice")
	QnAServiceImpl qnaservice;
	
	@Autowired
	@Qualifier("replyservice")
	ReplyServiceImpl replyservice;
	
	@RequestMapping("/qna") 
	public ModelAndView qna(HttpServletRequest request, int num) {
		ModelAndView mv = new ModelAndView();
		//qnalist 가져오기
		QnAVO[] qnalist = qnaservice.qnaList();
		mv.addObject("qnalist", qnalist);
		int cnt = qnaservice.cnt();
		//한 페이지에 출력할 게시물 갯수
		int postNum = 10;
		//하단 페이징 번호 ([게시물 총 갯수 / 한 페이지에 출력할 갯수]의 올림)
		int pageNum = (int)Math.ceil((double)cnt/postNum);
		//출력할 게시물
		int displayPost = (num-1)*postNum;
		//num은 페이지 번호
		List list = null;
		list = qnaservice.listPage(displayPost,postNum);
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
		
		//세션정보 가져오기
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("vo");
		//모델에 세션정보 넣어주기
		mv.addObject("vo", vo);
		mv.setViewName("qna/qna_board");
		//문의게시판 page로 이동
		return mv;
	}
	
	@RequestMapping("/qnaview")
	public ModelAndView qnaView(int code, Integer number, String flag, HttpServletRequest request) { //?code=1 get 방식으로
		//공지사항 데이터 전달
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("vo");	
		QnAVO qvo = qnaservice.qnaOne(code);
		ReplyVO[] replyList = replyservice.qnaReplyList(code);
		if (number != null) {
			mv.addObject("number", (int)number);			
		}
		if (flag!= null) {
			mv.addObject("flag", flag);			
		}
		mv.addObject("qvo", qvo);
		mv.addObject("vo", vo);
		mv.addObject("replyList", replyList);
		mv.setViewName("qna/qna_view");
		return mv;
	}
	
	
	@RequestMapping("/qnawrite")
	public ModelAndView qnaWrite(HttpSession session) {
		//공지사항 작성 페이지로 이동
		ModelAndView mv = new ModelAndView();
		QnAVO qvo = new QnAVO();
		//session 에서 회원 vo 가져오기
		UserVO vo = (UserVO)session.getAttribute("vo");
		if (vo==null) {
			//session 이 없으면 로그인창으로 보내주기
			mv.setViewName("redirect:index");
			mv.addObject("message", "로그인 후 작성 가능합니다.");
		} else {
			mv.setViewName("qna/qna_write");
		}
		return mv;

	}
	
	@RequestMapping(value="/qnawriteaction", method=RequestMethod.POST)
	public ModelAndView qnaWriteAction(HttpServletRequest request, HttpSession session) {
		//공지사항 작성 페이지로 이동
		ModelAndView mv = new ModelAndView();
		QnAVO qvo = new QnAVO();
		//session 에서 회원 vo 가져오기
		UserVO vo = (UserVO)session.getAttribute("vo");
		//email 없이 테스트용 if문
		if (vo==null) {
			//session 이 없으면 로그인창으로 보내주기
			mv.setViewName("redirect:testmain");
			mv.addObject("message", "로그인 후 작성 가능합니다.");
		} else {			
			String qna_content = request.getParameter("qna_content");
			String qna_title = request.getParameter("qna_title");
			String email = vo.getMember_email();
			qvo.setMember_email(email);			
			qvo.setQna_content(qna_content);
			qvo.setQna_subject(qna_title);
			qnaservice.insertQnA(qvo);
			mv.addObject("vo", qvo);
			mv.setViewName("redirect:qna");
			mv.addObject("message", "게시글이 등록되었습니다.");
		}
		return mv;
	}

	
	@RequestMapping("/qnaupdate")
	public ModelAndView qnaUpdate(int code) {
		//qna 수정 페이지로 이동
		ModelAndView mv = new ModelAndView();
		QnAVO qvo = qnaservice.qnaOne(code);
		mv.addObject("qvo", qvo);
		mv.setViewName("qna/qna_update");
		return mv;
	}
	

	
	
	@RequestMapping("/qnaupdateaction")
	public ModelAndView qnaUpdateAction(HttpServletRequest request) {
		//글수정 action
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("vo");
		int code = Integer.parseInt(request.getParameter("code"));
		String content = request.getParameter("content");
		String subject = request.getParameter("subject");
		QnAVO qvo = qnaservice.qnaOne(code);
		if (vo == null) {
			//세션 정보가 없으면 로그인 창으로 보내기
			mv.addObject("message", "로그인 정보가 없습니다.");
			mv.setViewName("redirect:index");
		} else {
			String email = vo.getMember_email();
			if(email.equals(qvo.member_email)) {
				System.out.println(subject);
				qvo.setQna_content(content);
				qvo.setQna_subject(subject);
				qvo.setQna_code((int)code);				
				qnaservice.updateQnA(qvo);
				mv.setViewName("redirect:qnaview?code="+(int)code);
				mv.addObject("message", "게시글이 수정되었습니다.");
			} else {
				mv.addObject("message", "작성자가 일치하지 않습니다.");
			}
		}

		return mv;
	}
	
	@RequestMapping("/qnadelete")
	public ModelAndView qnaDelete(int code, HttpServletRequest request) {
		//글삭제 action
		ModelAndView mv = new ModelAndView();	
		QnAVO qvo = qnaservice.qnaOne(code);
		String qna_email = qvo.getMember_email();
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("vo");
		if (vo.getMember_email().equals(qna_email)) {
			qnaservice.deleteQnA(code);
			mv.setViewName("redirect:qna");
			mv.addObject("message", "게시글이 삭제되었습니다.");
		} else {
			mv.setViewName("redirect:index");
			mv.addObject("message", "로그인 정보가 없습니다.");
		}
		
		return mv;
		
	}
	
	@RequestMapping(value="/qnaInsertReply", method=RequestMethod.POST)
	public ModelAndView qnaInsertReply(Integer code, HttpServletRequest request) {
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
			rvo.setQna_code((int)code);
			replyservice.insertQnAReply(rvo);
			mv.setViewName("redirect:qnaview?code="+(int)code);		
			mv.addObject("message", "댓글이 입력되었습니다.");
		}
		return mv;
	}
	
	@RequestMapping("/qnaUpdateReply")
	public ModelAndView qnaUpdateReply(int code, Integer number, HttpServletRequest request) {
		//댓글달기 action
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		ReplyVO rvo = new ReplyVO();
		rvo.setQna_code(code);
		rvo.setReply_number((int)number);
		ReplyVO rvo2 = replyservice.getQnAReply(rvo);
		UserVO vo = (UserVO)session.getAttribute("vo");
		String email = vo.getMember_email();
		if (email.equals(rvo2.getMember_email())) {
			mv.setViewName("redirect:qnaview?code="+(int)code + "&number="+ (int)number +"&flag=u");			
		} else {
			mv.setViewName("redirect:index");
			mv.addObject("message", "작성자가 일치하지 않습니다.");
		}
		return mv;
	}
	
	
	@RequestMapping("/qnaUpdateReplyAction")
	public ModelAndView qnaUpdateReplyAction(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		//댓글달기 action
		ReplyVO rvo = new ReplyVO();
		//파라미터 가져오기
		String flag = request.getParameter("flag");
		int qnaCode = Integer.parseInt(request.getParameter("qnaCode"));
		int replyNumber = Integer.parseInt(request.getParameter("replyNumber"));
		String comment = request.getParameter("comment");
		//세션가져오기
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("vo");
		//rvo에 공지사항 글번호와 댓글번호를 넣어준다
		rvo.setReply_number(replyNumber);
		rvo.setQna_code(qnaCode);
		//기존 rvo 에 저장된 정보 가져오기
		ReplyVO rvo2 = replyservice.getQnAReply(rvo);
		if (vo == null) {//세션이 없으면
			//로그인 페이지로 이동
			mv.setViewName("redirect:index");
			mv.addObject("message", "로그인 정보가 없습니다.");
		} else {
			String email = vo.getMember_email();
			if (email.equals(rvo2.getMember_email())) {
				rvo.setMember_email(email);	
				rvo.setReply_content(comment);
				replyservice.updateQnAReply(rvo);
				mv.setViewName("redirect:qnaview?code="+ qnaCode);
				mv.addObject("message", "댓글이 수정되었습니다.");
			} else {
				System.out.println("작성자가 일치하지 않습니다.");
				mv.setViewName("redirect:qnaview?code="+ qnaCode);
				
			}
		}
		return mv;
	}
	
	@RequestMapping("/qnaDeleteReply")
	public ModelAndView qnaDeleteReply(int number, Integer code, HttpServletRequest request) {
		//댓글달기 action
		ModelAndView mv = new ModelAndView();
		ReplyVO rvo = new ReplyVO();
		rvo.setQna_code((int)code);
		rvo.setReply_number(number);
		ReplyVO rvo2 = replyservice.getQnAReply(rvo);
		//세션정보 가져오기
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("vo");
		if (vo == null) {
			mv.setViewName("redirect:index");
		} else {
			String email = vo.getMember_email();
			if (email.equals(rvo2.getMember_email())) {
				replyservice.deleteQnAReply(rvo);
				mv.setViewName("redirect:qnaview?code="+(int)code);
				mv.addObject("message", "댓글이 삭제되었습니다.");
			} else {
				System.out.println("작성자가 일치하지 않습니다.");
				mv.setViewName("redirect:qnaview?code="+(int)code);	
				mv.setViewName("redirect:noticeview?code="+(int)code);	
			}
		}	
		return mv;
	}
	
}
