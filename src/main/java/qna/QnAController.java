package qna;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import notice.NoticeVO;
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
	public ModelAndView qna(HttpServletRequest request, @RequestParam("num")Integer num) {
		ModelAndView mv = new ModelAndView();
		if (num==null) {
			num=1;
		}
		//qnalist 가져오기
		//QnAVO[] qnalist = qnaservice.qnaList();
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("type", type);
		hm.put("keyword", keyword);
		int cnt = qnaservice.cnt(hm);
		
		//한 페이지에 출력할 게시물 갯수
		int postNum = 10;
		//하단 페이징 번호 ([게시물 총 갯수 / 한 페이지에 출력할 갯수]의 올림)
		int pageNum = (int)Math.ceil((double)cnt/postNum);
		//출력할 게시물
		int displayPost = (num-1)*postNum;
		//num은 페이지 번호
		mv.addObject("num", num);
		
		List<NoticeVO> list = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("type", type);
		map.put("keyword", keyword);
		map.put("displayPost", displayPost);
		map.put("postNum",  postNum);		
		list = qnaservice.listPage(map);
		mv.addObject("list", list);
		mv.addObject("pageNum", pageNum);
		mv.addObject("type", type);
		mv.addObject("keyword", keyword);
		
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
		//문의게시판 page로 이동
		mv.setViewName("qna/qna_board");
		return mv;
	}
	
	@RequestMapping("/qnaview")
	public ModelAndView qnaView(int code, Integer number, String flag, HttpServletRequest request, Integer num) { //?code=1 get 방식으로
		//num이 null 인 경우 1로 설정
		if (num==null) {
			num=1;
		}
		
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
		mv.addObject("num", num);
		mv.addObject("qvo", qvo);
		mv.addObject("vo", vo);
		mv.addObject("replyList", replyList);
		mv.setViewName("qna/qna_view");
		return mv;
	}
	
	
	@RequestMapping("/qnawrite")
	public ModelAndView qnaWrite(HttpSession session, Integer num) {
		//num이 null 인 경우 1로 설정
		if (num==null) {
			num=1;
		}
		ModelAndView mv = new ModelAndView();
		//session 에서 회원 vo 가져오기
		UserVO vo = (UserVO)session.getAttribute("vo");
		if (vo==null) {
			//session 이 없으면 로그인창으로 보내주기
			mv.setViewName("redirect:index");
			mv.addObject("message", "로그인 후 작성 가능합니다.");
		} else {
			//qna 작성 페이지로 이동
			mv.setViewName("qna/qna_write");
			mv.addObject("num", num);
		}
		return mv;

	}
	
	@RequestMapping("/qnawriteaction")
	public ModelAndView qnaWriteAction(HttpServletRequest request, HttpSession session, Integer num) {
		//공지사항 작성 페이지로 이동
		ModelAndView mv = new ModelAndView();
		QnAVO qvo = new QnAVO();
		//session 에서 회원 vo 가져오기
		UserVO vo = (UserVO)session.getAttribute("vo");
		
		//num이 null 인 경우 1로 설정
		if (num==null) {
			num=1;
		}
		
		//session test
		if (vo==null) {
			//session 이 없으면 로그인창으로 보내주기
			mv.setViewName("redirect:index");
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
			mv.addObject("num",num);
			mv.setViewName("redirect:qna");
			mv.addObject("message", "게시글이 등록되었습니다.");
		}
		return mv;
	}

	
	@RequestMapping("/qnaupdate")
	public ModelAndView qnaUpdate(int code, Integer num) {
		//qna 수정 페이지로 이동
		ModelAndView mv = new ModelAndView();
		QnAVO qvo = qnaservice.qnaOne(code);
		mv.addObject("qvo", qvo);
		mv.addObject("num",num);
		mv.setViewName("qna/qna_update");
		return mv;
	}
	
	
	@RequestMapping("/qnaupdateaction")
	public ModelAndView qnaUpdateAction(HttpServletRequest request, Integer num) {
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
			//세션과 작성자 이메일이 일치하면 수정
			if(email.equals(qvo.member_email)) {
				System.out.println(subject);
				qvo.setQna_content(content);
				qvo.setQna_subject(subject);
				qvo.setQna_code((int)code);				
				qnaservice.updateQnA(qvo);
				mv.setViewName("redirect:qnaview?code="+(int)code);
				mv.addObject("message", "게시글이 수정되었습니다.");
				mv.addObject("num",num);
			} else {
				mv.addObject("message", "작성자가 일치하지 않습니다.");
				mv.addObject("num",num);
			}
		}

		return mv;
	}
	
	@RequestMapping("/qnadelete")
	public ModelAndView qnaDelete(int code, HttpServletRequest request, Integer num) {
		//글삭제 action
		ModelAndView mv = new ModelAndView();	
		QnAVO qvo = qnaservice.qnaOne(code);
		String qna_email = qvo.getMember_email();
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("vo");
		if (vo.getMember_email().equals(qna_email)) {
			//작성자가 일치하면
			qnaservice.deleteQnA(code);
			mv.setViewName("redirect:qna");
			mv.addObject("message", "게시글이 삭제되었습니다.");
			mv.addObject("num",num);
		} else {
			//로그인 페이지로 보내기
			mv.setViewName("redirect:index");
			mv.addObject("message", "로그인 정보가 없습니다.");
		}
		
		return mv;
		
	}
	
	//댓글 ajax
		 @RequestMapping(value="/qna/replyInsert")
		 @ResponseBody
		 public int replyInsert(@RequestParam("code")int code, @RequestParam("replyContent")String replyContent, HttpServletRequest request) throws Exception{
			 ReplyVO rvo = new ReplyVO();
			 rvo.setReply_content(replyContent);
			// rvo.setReply_number(100);
			 HttpSession session = request.getSession();
			 UserVO uvo = (UserVO)session.getAttribute("vo");
			 if (uvo != null) {
				 rvo.setQna_code(code);
				 String email = uvo.getMember_email();			 
				 rvo.setMember_email(email);
				 replyservice.insertQnAReply(rvo);
				 return code;
			 }
			 return -1;
			 
			 //ReplyVO vo = replyservice.getNoticeReply(rvo);
			 //System.out.println(vo.getMember_email());
		    }
		    
	 	@RequestMapping(value="/qna/replyList")
	    @ResponseBody
	    public ReplyVO[] getCommentList(@RequestParam("code")int code) throws Exception{

		 	return replyservice.qnaReplyList(code);
	       
	    }
	 	
	 	@RequestMapping(value="/qna/replyUpdate")
	    @ResponseBody
	    public void replyUpdate(@RequestParam("code")int code, @RequestParam("email")String email, @RequestParam("reply_number")int reply_number, @RequestParam("replyContent")String replyContent) throws Exception{
	 		ReplyVO vo = new ReplyVO();
	 		vo.setMember_email(email);
	 		vo.setReply_number(reply_number);
	 		vo.setQna_code(code);
	 		vo.setReply_content(replyContent);
		 	replyservice.updateQnAReply(vo);
		 	//System.out.println("수정완료");
	       
	    }
		
	 	@RequestMapping(value="/qna/replyDelete")
	    @ResponseBody
	    public void replyDelete(@RequestParam("code")int code, @RequestParam("reply_number")int reply_number) throws Exception{
	 		ReplyVO vo = new ReplyVO();
	 		vo.setReply_number(reply_number);
	 		vo.setQna_code(code);
		 	replyservice.deleteQnAReply(vo);
		 	//System.out.println("삭제완료");
	       
	    }
		
	
}
