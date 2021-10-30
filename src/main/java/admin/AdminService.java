package admin;

import java.util.List;

import user.HistoryJoinVO;
import user.UserVO;

public interface AdminService {

	/**
	 * 회원 list
	 * */
	public List<UserVO> selectAll();
	
	/**
	 * id에 해당하는 회원정보
	 * */
	public UserVO selectById(String member_id);
	
	/**
	 * 관리자로 변경
	 * */
	public void updateState1(String member_id);
	
	/**
	 * 회원으로 변경
	 * */
	public void updateState0(String member_id);
	
	/**
	 * 감정 통계
	 * */
	public List<HistoryJoinVO> countByEmotion();
	
	/**
	 * 음악 통계
	 * */
	public List<HistoryJoinVO> countByMusic();
}
