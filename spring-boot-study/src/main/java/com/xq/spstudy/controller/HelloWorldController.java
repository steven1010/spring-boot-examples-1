package com.xq.spstudy.controller;

import org.apache.kafka.common.utils.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xq.spstudy.kafka.KafkaProduce;

@RestController
public class HelloWorldController {
	//返回字符串
    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }
    
    //返回Jason格式的对象
    @RequestMapping("/getUser")
    public User getUser() {
        User user=new User();
        user.setUserName("小明");
        user.setPassWord("xxxx");
        return user;
    }

    
    //自动装载测试
    @Autowired	
    private NeoProperties neoProperties;

    @RequestMapping("/getPro")
    public NeoProperties getPro(){
    	//日志测试
		Logger LOGGER = LoggerFactory.getLogger(this.getClass());
		LOGGER.info("简单邮件已经发送。");
		LOGGER.error("发送简单邮件时发生异常！");
		
    	return neoProperties;
    }
    
    
    @Autowired
    KafkaProduce  ktest;
    //测试发送给kafka
    @RequestMapping("/tks")
    public String tks(){
    	return ktest.kafkaSend();
    }
}


class User {
	private String UserName;
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	
	private String PassWord;
	public String getPassWord() {
		return PassWord;
	}
	public void setPassWord(String passWord) {
		PassWord = passWord;
	}
}

@Component
class NeoProperties {
	@Value("${com.neo.title}")
	private String title;
	
	@Value("${com.neo.description}")
	private String description;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
