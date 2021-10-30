package edu.spring.multi;

import gallery.GalleryController;
import gallery.MusicDAO;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import admin.AdminController;
import admin.AdminDAO;
import musiclist.MusicListController;
import musiclist.MusicListDAO;
import user.UserController;
import user.UserDAO;


@SpringBootApplication
@ComponentScan (basePackageClasses = GalleryController.class)
@MapperScan(basePackageClasses = MusicDAO.class)
@ComponentScan
@ComponentScan(basePackageClasses = UserController.class)
@ComponentScan(basePackageClasses = AdminController.class)
@ComponentScan(basePackageClasses = MusicListController.class)
@MapperScan(basePackageClasses = UserDAO.class)
@MapperScan(basePackageClasses = AdminDAO.class)
@MapperScan(basePackageClasses = MusicListDAO.class)
public class MulticampusprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MulticampusprojectApplication.class, args);
		System.out.println("multicampus서비스 시작");
		System.out.println("import 완료");
	}

}
