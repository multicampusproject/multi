package reply;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface ReplyDAO { 

	
	public ReplyVO getNoticeReply(ReplyVO vo);
	
	public ReplyVO getQnAReply(ReplyVO vo);
	
	public ReplyVO[] noticeReplyList(int code);
	
	public ReplyVO[] qnaReplyList(int code); 
	
	public void insertNoticeReply(ReplyVO vo);
	
	public void insertQnAReply(ReplyVO vo);
	
	public void updateNoticeReply(ReplyVO vo);
	
	public void updateQnAReply(ReplyVO vo);
	
	public void deleteNoticeReply(ReplyVO vo);
	
	public void deleteQnAReply(ReplyVO vo);
	
	
}