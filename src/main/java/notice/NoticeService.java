package notice;

import java.util.HashMap;
import java.util.List;

public interface NoticeService {
	public NoticeVO[] noticeList();
	
	public NoticeVO noticeOne(int code);
	
	public void insertNotice(NoticeVO vo);
	
	public void updateNotice(NoticeVO vo);
	
	public void deleteNotice(int code);
	
	public int cnt(HashMap<String, Object> map);
	
	public List<NoticeVO> listPage(int displayPost, int postNum);
	
	public List<NoticeVO> listPage(HashMap<String, Object> map);
}
