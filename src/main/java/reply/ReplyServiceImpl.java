package reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
@Qualifier("replyservice")
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired 
	ReplyDAO dao;
	
	
	@Override
	public ReplyVO getNoticeReply(ReplyVO vo) {
		return dao.getNoticeReply(vo);
	}

	@Override
	public ReplyVO getQnAReply(ReplyVO vo) {
		return dao.getQnAReply(vo);
	}

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
