package com.project.board;

import com.project.board.global.session.SessionListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpSessionListener;

@SpringBootApplication
public class BoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}
	@Bean
	public CommonsMultipartResolver commonsMultipartResolver(){
		return new CommonsMultipartResolver();
	}
	@Bean
	public HttpSessionListener httpSessionListener(){
		return new SessionListener();
	}

}
