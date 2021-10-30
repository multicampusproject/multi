package edu.spring.multi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//<<<<<<< HEAD
//=======

import user.UserController;
import user.UserDAO;
//>>>>>>> branch 'master' of https://github.com/multicampusproject/multi.git

import notice.NoticeController;
import notice.NoticeDAO;
import qna.QnAController;
import qna.QnADAO;
import reply.ReplyDAO;

@ComponentScan(basePackageClasses = ReplyDAO.class)
@MapperScan(basePackageClasses = ReplyDAO.class)
@ComponentScan(basePackageClasses = NoticeController.class)
@MapperScan(basePackageClasses = NoticeDAO.class)
@ComponentScan(basePackageClasses = QnAController.class)
@MapperScan(basePackageClasses = QnADAO.class)
@SpringBootApplication
@ComponentScan(basePackageClasses = UserController.class)
@MapperScan(basePackageClasses = UserDAO.class)
public class MulticampusprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MulticampusprojectApplication.class, args);
		System.out.println("boot 정상 실행");
	}

}
