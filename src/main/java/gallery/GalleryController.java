package gallery;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//<context>cpn... base=p..="upload"
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GalleryController {
	@Autowired

	@RequestMapping(value="/index")
	public String Main() {
		return "/index";
	}
	@RequestMapping(value="/index#portfolio")
	public String Main1() {
		return "/upload/uploadform";
	}
	
	//비회원 업로드 페이지 올리면 해당 파일 서버컴퓨터에 저장
	@RequestMapping(value="/galleryupload", method=RequestMethod.GET)
	public String uploadForm() {
		return "/upload/uploadform";
	}
	String filename = "";
	@RequestMapping(value="/galleryupload", method=RequestMethod.POST)
	public String uploadResult(@ModelAttribute("vo") UploadVO vo) throws IOException{
		MultipartFile multi1 = vo.getFile1();
		String filename1 = multi1.getOriginalFilename();
		String savePath = "C:/Users/HOME/Desktop/images/";
		String ext1 = filename1.substring(filename1.lastIndexOf("."));
		filename1 = getUuid()+ext1;
		File file1 = new File(savePath + filename1);
		multi1.transferTo(file1);
		vo.setFilename1(filename1);
		System.out.println(vo.getFilename1());
		return "/upload/faceinput";
	}
	
	public static String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
	}

	@Autowired
	Gallerycfr gallerycfr;

	// 업로드한 이미지 띄운 후에 맞으면 input
	@RequestMapping("/faceinput")
	public ModelAndView faceinput() {
		File f = new File("C:/Users/HOME/Desktop/images/");
		String[] namelist = f.list();
		System.out.println(filename);
		ModelAndView mv = new ModelAndView();
		mv.addObject("filename", filename);
		mv.setViewName("/upload/faceinput");
		return mv;
	}
	
	@Autowired
	MusicService musicservice;
	
	// faceinput 결과를 face로 전송 , 음악 DB도 같이 전송
	@RequestMapping("/face")
	public ModelAndView face(String image){
		System.out.println("시작");
		ModelAndView mv = new ModelAndView();
		ArrayList<MusicVO> list = musicservice.getMusicList();
		mv.addObject("musiclist",list);
		String jsonModel = gallerycfr.test(image);
		mv.addObject("image", filename);
		mv.addObject("faceresult", jsonModel);
		mv.setViewName("/upload/face");
		return mv;
		
	}

}
