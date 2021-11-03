package user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper //@mapper-scan
@Repository//componenet-scan
public interface UserDAO {

	public UserVO emailCheck(String member_email);
	public void userjoin(UserVO vo);
	public void updateInfo(UserVO vo);
	public void deleteInfo(String member_email);
	public int idCheck(String member_id);
	public List<HistoryJoinVO> historyByEmail(String member_email);
	
	
}
