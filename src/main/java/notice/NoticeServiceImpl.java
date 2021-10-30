package notice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


@Service
@Qualifier("noticeservice")
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired //만들어진 dao 를 자동으로 나에게 주입해줘 - setter 뺌
	NoticeDAO dao;
	
	
	@Override
	public NoticeVO[] noticeList() {
		return dao.noticeList();
	}
	
	@Override
	public NoticeVO[] historyNoticeOne(String email) {
		//notice 1사람이 쓴 데이터 가져오기
		return dao.noticeHistory(email); 
	}

	@Override
	public NoticeVO noticeOne(int code) {
		return dao.noticeOne(code);
	}

	@Override
	public void insertNotice(NoticeVO vo) {
		//qna 입력
		dao.insertNotice(vo);
	}

	@Override
	public void updateNotice(NoticeVO vo) {
		//qna 수정
		dao.updateNotice(vo);
	}

	@Override
	public void deleteNotice(int code) {
		dao.deleteNotice(code);
	}

	@Override
	public int cnt() {
		return dao.cnt();
	}

	@Override
	public List<NoticeVO> listPage(int displayPost, int postNum) {
		return dao.listPage(displayPost, postNum);
	}


	
	
}
