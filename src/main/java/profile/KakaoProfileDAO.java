package profile;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface KakaoProfileDAO {
	
	public void insertImage(KakaoImageVO vo);
	public List<KakaoMusicVO> selectMusic(KakaoImageVO vo); 
	public void insertHistory(KakaoHistoryVO vo);
	public List<KakaoMusicVO> selectMusic2(KakaoImageVO vo);

}
