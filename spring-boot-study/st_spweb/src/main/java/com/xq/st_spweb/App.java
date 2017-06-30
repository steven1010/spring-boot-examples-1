package com.xq.st_spweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class App {
	
	@RequestMapping("/")
	public String home(){
		return "This is home page";
	}
	
    public static void main(String[] args) {
    	SpringApplication.run(App.class, args);	//运行web服务
    }
}
