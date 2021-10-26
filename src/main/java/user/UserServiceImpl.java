package user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userservice")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO dao;
	
	@Override
	public UserVO emailCheck(String member_email) {
		System.out.println("%%%%%%%%%%%%"+member_email);
		return dao.emailCheck(member_email);
	}

	@Override
	public void userjoin(UserVO uservo) {
		if(dao.emailCheck(uservo.getMember_email()) == null) {
			dao.userjoin(uservo);
		}
	}

	@Override
	public void updateInfo(UserVO vo) {
		dao.updateInfo(vo);
	}

	@Override
	public void deleteInfo(String member_email) {
		dao.deleteInfo(member_email);
	}

	@Override
	public int idCheck(String member_id) {
		return dao.idCheck(member_id);
	}

	@Override
	public List<HistoryJoinVO> historyByEmail(String member_email) {
		return dao.historyByEmail(member_email);
	}

}
