package reply;

import java.util.ArrayList;

public interface ReplyService {
	public ReplyVO[] noticeReplyList(int code);
	
	public ReplyVO[] qnaReplyList(int code); 
	
	public void insertNoticeReply(ReplyVO vo);
	
	public void insertQnAReply(ReplyVO vo);
	
	public void updateNoticeReply(ReplyVO vo);
	
	public void updateQnAReply(ReplyVO vo);
	
	public void deleteNoticeReply(ReplyVO vo);
	
	public void deleteQnAReply(ReplyVO vo);
	
	
}
