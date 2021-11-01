package gallery;

import org.springframework.web.multipart.MultipartFile;

public class UploadVO {
//전송자이름, 설명, 파일 2개 전송--> 4개 INPUT  JSP
	MultipartFile file1;
	String filename1,member_name,member_state;
	public String getMember_state() {
		return member_state;
	}
	public void setMember_state(String member_state) {
		this.member_state = member_state;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getFilename1() {
		return filename1;
	}
	public void setFilename1(String filename1) {
		this.filename1 = filename1;
	}

	public MultipartFile getFile1() {
		return file1;
	}
	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}

	
	//생성자추가
	//setter/getter
	
	//toString
}
