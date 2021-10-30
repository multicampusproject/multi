package edu.spring.multi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import gallery.GalleryController;
import gallery.MusicDAO;

@SpringBootApplication
@ComponentScan (basePackageClasses = GalleryController.class)
@MapperScan(basePackageClasses = MusicDAO.class)
@ComponentScan
public class MulticampusprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MulticampusprojectApplication.class, args);
		System.out.println("multicampus서비스 시작");
	}

}
