package qna;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


@Service
@Qualifier("qnaservice")
public class QnAServiceImpl implements QnAService {
	
	@Autowired //만들어진 dao 를 자동으로 나에게 주입해줘 - setter 뺌
	QnADAO dao;
	
	
	@Override
	public QnAVO[] qnaList() {
		return dao.qnaList();
	}
	
	@Override
	public QnAVO[] historyQnAOne(String email) {
		//qna 1사람이 쓴 데이터 가져오기
		return dao.qnaHistory(email); 
	}

	@Override
	public QnAVO qnaOne(int code) {
		return dao.qnaOne(code);
	}

	@Override
	public void insertQnA(QnAVO vo) {
		//qna 입력
		dao.insertQnA(vo);
	}

	@Override
	public void updateQnA(QnAVO vo) {
		//qna 수정
		dao.updateQnA(vo);
	}

	@Override
	public void deleteQnA(int code) {
		dao.deleteQnA(code);
	}

	
}
