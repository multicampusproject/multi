package qna;

import java.util.ArrayList;
import java.util.List;

import notice.NoticeVO;

public interface QnAService {
	public QnAVO[] qnaList();
	
	public QnAVO[] historyQnAOne(String email);
	
	public QnAVO qnaOne(int code);
	
	public void insertQnA(QnAVO vo);
	
	public void updateQnA(QnAVO vo);
	
	public void deleteQnA(int code);
	
	public int cnt();
	
	public List<QnAVO> listPage(int displayPost, int postNum);
}
