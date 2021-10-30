package musiclist;

import java.util.List;

public interface MusicListService {
	public List<MusicListVO> multimusicList();
	
	/**
	 * 음악 추가
	 * */
	public void multimusicInsert(MusicListVO vo);
	
	/**
	 * 음악 삭제
	 * */
	public void multimusicDelete(int music_num);

}
