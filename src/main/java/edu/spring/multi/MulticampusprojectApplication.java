package edu.spring.multi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import admin.AdminController;
import admin.AdminDAO;
import gallery.GalleryController;
import gallery.MusicDAO;
import musiclist.MusicListController;
import musiclist.MusicListDAO;
import notice.NoticeController;
import notice.NoticeDAO;
import profile.KakaoProfileController;
import profile.KakaoProfileDAO;
import qna.QnAController;
import qna.QnADAO;
import reply.ReplyDAO;
import user.UserController;
import user.UserDAO;


@ComponentScan(basePackageClasses = ReplyDAO.class)
@MapperScan(basePackageClasses = ReplyDAO.class)
@ComponentScan(basePackageClasses = NoticeController.class)
@MapperScan(basePackageClasses = NoticeDAO.class)
@ComponentScan(basePackageClasses = QnAController.class)
@MapperScan(basePackageClasses = QnADAO.class)


@SpringBootApplication
@ComponentScan (basePackageClasses = GalleryController.class)
@MapperScan(basePackageClasses = MusicDAO.class)
@ComponentScan
@ComponentScan(basePackageClasses = UserController.class)
@ComponentScan(basePackageClasses = AdminController.class)
@ComponentScan(basePackageClasses = MusicListController.class)
@ComponentScan(basePackageClasses = KakaoProfileController.class)
@MapperScan(basePackageClasses = UserDAO.class)
@MapperScan(basePackageClasses = AdminDAO.class)
@MapperScan(basePackageClasses = MusicListDAO.class)
@MapperScan(basePackageClasses = KakaoProfileDAO.class)
public class MulticampusprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MulticampusprojectApplication.class, args);
		System.out.println("boot 정상 실행");
	}

}
