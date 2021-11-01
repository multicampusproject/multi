package profile;

public class KakaoHistoryVO {
	int recommend_code;
	String image_name;
	int music_num;
	String member_email;
	String member_name;
	int member_state;
	
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public int getMember_state() {
		return member_state;
	}
	public void setMember_state(int member_state) {
		this.member_state = member_state;
	}
	public int getRecommend_code() {
		return recommend_code;
	}
	public void setRecommend_code(int recommend_code) {
		this.recommend_code = recommend_code;
	}
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}
	public int getMusic_num() {
		return music_num;
	}
	public void setMusic_num(int music_num) {
		this.music_num = music_num;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	
	


}
