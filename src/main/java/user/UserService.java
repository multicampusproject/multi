package user;

import java.util.List;

public interface UserService {

	/**
	 * 이메일에 해당하는 회원정보
	 * */
	public UserVO emailCheck(String member_email);
	
	/**
	 * 회원가입 
	 * */
	public void userjoin(UserVO vo);
	
	/**
	 * 회원정보 수정
	 * */
	public void updateInfo(UserVO vo);
	
	/**
	 * 회원탈퇴
	 * */
	public void deleteInfo(String member_email);
	
	/**
	 * 아이디중복체크
	 * */
	public int idCheck(String member_id);
	
	/**
	 * 추천히스토리 검색
	 * */
	public List<HistoryJoinVO> historyByEmail(String member_email);
}
