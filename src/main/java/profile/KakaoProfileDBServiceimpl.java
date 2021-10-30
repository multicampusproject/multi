package profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DBservice")
public class KakaoProfileDBServiceimpl implements KakaoProfileDBService {
	
	@Autowired
	KakaoProfileDAO dao;
	
	@Override
	public void insertImage(KakaoImageVO vo) {
		dao.insertImage(vo);
		
	}

	@Override
	public List<KakaoMusicVO> selectMusic(KakaoImageVO vo) {
		return dao.selectMusic(vo);
	}
	
	@Override
	public void insertHistory(KakaoHistoryVO vo) {
		dao.insertHistory(vo);
		
	}
	
	@Override
	public List<KakaoMusicVO> selectMusic2(KakaoImageVO vo) {
		return dao.selectMusic2(vo);
	}
	
}
