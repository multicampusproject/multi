package qna;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import notice.NoticeVO;


@Service
@Qualifier("qnaservice")
public class QnAServiceImpl implements QnAService {
	
	@Autowired
	QnADAO dao;
	
	
	@Override
	public QnAVO[] qnaList() {
		return dao.qnaList();
	}

	@Override
	public QnAVO qnaOne(int code) {
		return dao.qnaOne(code);
	}

	@Override
	public void insertQnA(QnAVO vo) {
		dao.insertQnA(vo);
	}

	@Override
	public void updateQnA(QnAVO vo) {
		dao.updateQnA(vo);
	}

	@Override
	public void deleteQnA(int code) {
		dao.deleteQnA(code);
	}
	
	@Override
	public int cnt(HashMap<String, Object> map) {
		return dao.cnt(map);
	}
	
	@Override
	public List<NoticeVO> listPage(HashMap<String, Object> map) {
		return dao.listPage(map);
	}
	
}
