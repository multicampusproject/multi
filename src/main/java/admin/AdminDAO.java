package admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import user.HistoryJoinVO;
import user.UserVO;

@Mapper
@Repository
public interface AdminDAO {

	public List<UserVO> selectAll();
	public UserVO selectById(String member_id);
	public void updateState1(String member_id);
	public void updateState0(String member_id);
	public List<HistoryJoinVO> countByEmotion();
	public List<HistoryJoinVO> countByMusic();
	
}
