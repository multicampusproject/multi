package gallery;

//@Component
public class MusicVO {
//employees 테이블 컬럼 변수 = 레코드 1개 객체
	int music_num,music_copyright,music_age;
	String music_artist,music_emotion,music_gender,music_title,music_url;
	public int getMusic_num() {
		return music_num;
	}
	public void setMusic_num(int music_num) {
		this.music_num = music_num;
	}
	public int getMusic_copyright() {
		return music_copyright;
	}
	public void setMusic_copyright(int music_copyright) {
		this.music_copyright = music_copyright;
	}
	public int getMusic_age() {
		return music_age;
	}
	public void setMusic_age(int music_age) {
		this.music_age = music_age;
	}
	public String getMusic_artist() {
		return music_artist;
	}
	public void setMusic_artist(String music_artist) {
		this.music_artist = music_artist;
	}
	public String getMusic_emotion() {
		return music_emotion;
	}
	public void setMusic_emotion(String music_emotion) {
		this.music_emotion = music_emotion;
	}
	public String getMusic_gender() {
		return music_gender;
	}
	public void setMusic_gender(String music_gender) {
		this.music_gender = music_gender;
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
	@Override
	public String toString() {
		return "music_artist=" + music_artist + ":"+ "music_emotion=" + music_emotion + ":"+"music_title=" + music_title + ":"+"music_url=" + music_url+ ":"+"music_gender=" + music_gender+ ":"+"music_age=" + music_age;
	}
}
