package user;

public class HistoryJoinVO {

	int recommend_code;
	String member_email, image_name, music_emotion, music_artist, music_title, music_url;
	int cnt, music_num;
	String image_age,image_gender;

	public String getImage_age() {
		return image_age;
	}

	public void setImage_age(String image_age) {
		this.image_age = image_age;
	}

	public String getImage_gender() {
		return image_gender;
	}

	public void setImage_gender(String image_gender) {
		this.image_gender = image_gender;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public String getImage_name() {
		return image_name;
	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	public String getMusic_artist() {
		return music_artist;
	}

	public void setMusic_artist(String music_artist) {
		this.music_artist = music_artist;
	}

	public String getMusic_title() {
		return music_title;
	}

	public void setMusic_title(String music_title) {
		this.music_title = music_title;
	}

	public String getMusic_url() {
		return music_url;
	}

	public void setMusic_url(String music_url) {
		this.music_url = music_url;
	}

	public int getRecommend_code() {
		return recommend_code;
	}

	public void setRecommend_code(int recommend_code) {
		this.recommend_code = recommend_code;
	}

	public String getMusic_emotion() {
		return music_emotion;
	}

	public void setMusic_emotion(String music_emotion) {
		this.music_emotion = music_emotion;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getMusic_num() {
		return music_num;
	}

	public void setMusic_num(int music_num) {
		this.music_num = music_num;
	}

	
	
	
}
