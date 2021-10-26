package reply;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
@Qualifier("replyservice")
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired //만들어진 dao 를 자동으로 나에게 주입해줘 - setter 뺌
	ReplyDAO dao;
	
	@Override
	public ReplyVO[] noticeReplyList(int code) {
		return dao.noticeReplyList(code);
	}

	@Override
	public ReplyVO[] qnaReplyList(int code) {
		return dao.qnaReplyList(code);
	}

	@Override
	public void insertNoticeReply(ReplyVO vo) {
		dao.insertNoticeReply(vo);
	}

	@Override
	public void insertQnAReply(ReplyVO vo) {
		dao.insertQnAReply(vo);
	}

	@Override
	public void updateNoticeReply(ReplyVO vo) {
		dao.updateNoticeReply(vo);
	}

	@Override
	public void updateQnAReply(ReplyVO vo) {
		dao.updateQnAReply(vo);
	}

	@Override
	public void deleteNoticeReply(ReplyVO vo) {
		dao.deleteNoticeReply(vo);
	}

	@Override
	public void deleteQnAReply(ReplyVO vo) {
		dao.deleteQnAReply(vo);
	}


	
}
