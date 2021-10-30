package admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.HistoryJoinVO;
import user.UserVO;

@Service("adminservice")
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDAO dao;

	@Override
	public List<UserVO> selectAll() {
		return dao.selectAll();
	}

	@Override
	public UserVO selectById(String member_id) {
		return dao.selectById(member_id);
	}

	@Override
	public void updateState1(String member_id) {
		dao.updateState1(member_id);
	}

	@Override
	public void updateState0(String member_id) {
		dao.updateState0(member_id);
	}

	@Override
	public List<HistoryJoinVO> countByEmotion() {
		return dao.countByEmotion();
	}

	@Override
	public List<HistoryJoinVO> countByMusic() {
		return dao.countByMusic();
	}
}
