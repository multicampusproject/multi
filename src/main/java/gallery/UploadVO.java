package gallery;

import org.springframework.web.multipart.MultipartFile;

public class UploadVO {
//전송자이름, 설명, 파일 2개 전송--> 4개 INPUT  JSP
	String name;
	String description;
	MultipartFile file1;
	String filename1;
	public String getFilename1() {
		return filename1;
	}
	public void setFilename1(String filename1) {
		this.filename1 = filename1;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
