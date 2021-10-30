package notice;

import java.util.ArrayList;
import java.util.List;

public interface NoticeService {
	public NoticeVO[] noticeList();
	
	public NoticeVO[] historyNoticeOne(String email);
	
	public NoticeVO noticeOne(int code);
	
	public void insertNotice(NoticeVO vo);
	
	public void updateNotice(NoticeVO vo);
	
	public void deleteNotice(int code);
	
	public int cnt();
	
	public List<NoticeVO> listPage(int displayPost, int postNum);

}
