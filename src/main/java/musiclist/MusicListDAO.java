package musiclist;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository 
@Mapper   
public interface MusicListDAO {
	
	public List<MusicListVO> multimusicList();
	
	/**
	 * 음악 insert
	 * */
	public void multimusicInsert(MusicListVO vo);
	
	/**
	 * 음악 삭제
	 * */
	public void multimusicDelete(int music_num);

}



 














