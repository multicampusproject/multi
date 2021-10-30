package musiclist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("service")
public class MusicListServiceImpl implements MusicListService {
	
	@Autowired
	MusicListDAO dao;

	public List<MusicListVO> multimusicList() {
		List<MusicListVO> list = dao.multimusicList();
		return list;
	}

	@Override
	public void multimusicInsert(MusicListVO vo) {
		dao.multimusicInsert(vo);
	}

	@Override
	public void multimusicDelete(int music_num) {
		dao.multimusicDelete(music_num);
	}

}

