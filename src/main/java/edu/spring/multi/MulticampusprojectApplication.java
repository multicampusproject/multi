package edu.spring.multi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import user.UserController;
import user.UserDAO;

@SpringBootApplication
@ComponentScan(basePackageClasses = UserController.class)
@MapperScan(basePackageClasses = UserDAO.class)
public class MulticampusprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MulticampusprojectApplication.class, args);
		System.out.println("import 완료");
	}

}
