package user;

public class HistoryJoinVO {

	int recommend_code;
	String member_email, image_name, image_emotion, music_artist, music_title, music_url;
	
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

	public String getImage_emotion() {
		return image_emotion;
	}

	public void setImage_emotion(String image_emotion) {
		this.image_emotion = image_emotion;
	}
	
	
}
