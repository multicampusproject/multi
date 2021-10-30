package gallery;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("musicservice")
public class MusicServiceImpl implements MusicService {
	@Autowired
	MusicDAO dao ;

	@Override
	public ArrayList<MusicVO> getMusicList() {
		return dao.musiclist();
	}



}
