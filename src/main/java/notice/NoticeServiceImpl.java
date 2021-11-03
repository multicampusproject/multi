package notice;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
@Qualifier("noticeservice")
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	NoticeDAO dao;
	
	
	@Override
	public NoticeVO[] noticeList() {
		return dao.noticeList();
	}

	@Override
	public NoticeVO noticeOne(int code) {
		return dao.noticeOne(code);
	}

	@Override
	public void insertNotice(NoticeVO vo) {
		dao.insertNotice(vo);
	}

	@Override
	public void updateNotice(NoticeVO vo) {
		dao.updateNotice(vo);
	}

	@Override
	public void deleteNotice(int code) {
		dao.deleteNotice(code);
	}

	@Override
	public int cnt(HashMap<String, Object> map) {
		return dao.cnt(map);
	}

	@Override
	public List<NoticeVO> listPage(int displayPost, int postNum) {
		return dao.listPage(displayPost, postNum);
	}

	public List<NoticeVO> listPage(HashMap<String, Object> map) {
		return dao.listPage(map);
	}

	
	
	
}
