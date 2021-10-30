package profile;

import java.util.List;

public interface KakaoProfileDBService {
	public void insertImage(KakaoImageVO vo);	
	public List<KakaoMusicVO> selectMusic(KakaoImageVO vo); 
	public void insertHistory(KakaoHistoryVO vo);
	public List<KakaoMusicVO> selectMusic2(KakaoImageVO vo);

}
