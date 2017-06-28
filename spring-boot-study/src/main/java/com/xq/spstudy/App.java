package com.xq.spstudy;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
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
//    	SpringApplication.run(App.class, args);	//运行web服务
    	 new SpringApplicationBuilder(App.class).web(false).run(args);
    }
}
