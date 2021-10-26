package qna;

import java.util.ArrayList;

public interface QnAService {
	public QnAVO[] qnaList();
	
	public QnAVO[] historyQnAOne(String email);
	
	public QnAVO qnaOne(int code);
	
	public void insertQnA(QnAVO vo);
	
	public void updateQnA(QnAVO vo);
	
	public void deleteQnA(int code);
	
	
}
