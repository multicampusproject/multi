package qna;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import notice.NoticeVO;


@Mapper
@Repository 
public interface QnADAO {
	
	public QnAVO[] qnaList(); 
	
	public QnAVO qnaOne(int code);
	
	public void insertQnA(QnAVO vo);
	
	public void updateQnA(QnAVO vo);
	
	public void deleteQnA(int code);
	
	public int cnt(HashMap<String, Object> map);

	public List<NoticeVO> listPage(HashMap<String, Object> map);
	

}