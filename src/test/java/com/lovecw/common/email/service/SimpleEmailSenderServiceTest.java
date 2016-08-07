package com.lovecw.common.email.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lovecw.common.email.exception.EmailException;

public class SimpleEmailSenderServiceTest {

	SimpleEmailService emailSenderService = null;
	ClassPathXmlApplicationContext applicationContext = null;

	@Before
	public void before() {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-email.xml");
		applicationContext.start();

		emailSenderService = applicationContext.getBean(SimpleEmailService.class);

	}

	@Test
	public void send() {
		try {
			boolean send = emailSenderService.send("915827225@qq.com", null, "lovecws", "测试数据");
			System.out.println(send);
		} catch (EmailException e) {
			e.printStackTrace();
		} finally {
			applicationContext.close();
		}
	}
	
	@Test
	public void receive(){
		try {
			emailSenderService.receiveAll();
		} catch (EmailException e) {
			e.printStackTrace();
		}finally {
			applicationContext.close();
		}
	}
}
