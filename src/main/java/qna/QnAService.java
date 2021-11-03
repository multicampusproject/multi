package qna;

import java.util.HashMap;
import java.util.List;
import notice.NoticeVO;

public interface QnAService {
	public QnAVO[] qnaList();
	
	public QnAVO qnaOne(int code);
	
	public void insertQnA(QnAVO vo);
	
	public void updateQnA(QnAVO vo);
	
	public void deleteQnA(int code);
	
	public int cnt(HashMap<String, Object> map);
	
	public List<NoticeVO> listPage(HashMap<String, Object> map);
}
