package cn.soa;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KeypersonManageApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(KeypersonManageApplication.class, args);
	}

	@Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	        // 注意这里要指向原先用main方法执行的Application启动类
	        return builder.sources(KeypersonManageApplication.class); 
	 }
}

