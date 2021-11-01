package edu.spring.multi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //현재클래스가 설정 모든 결과를   xml 파일 설정으로 
public class WebConfig implements WebMvcConfigurer {
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/galleryimages/**")
        .addResourceLocations("file:///C:/Users/HOME/Desktop/images/");
        //.addResourceLocations("file:///c:/kdigital2/images/");
    }
}

//  http://localhost:port/galleryimages/파일명
