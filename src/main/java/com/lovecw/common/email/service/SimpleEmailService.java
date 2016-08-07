package com.lovecw.common.email.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;

import com.lovecw.common.email.bean.SimpleEmail;
import com.lovecw.common.email.exception.EmailException;
import com.lovecw.common.email.receiver.SimpleEmailReceiver;
import com.lovecw.common.email.sender.SimpleEmailSender;

/**
 * 邮件 发送、接收
 * @author ganliang
 */
public class SimpleEmailService implements EmailReceiveService, EmailSendService {

	private static final Logger log = Logger.getLogger(SimpleEmailSender.class);

	// setter 注入
	private String userName;// 用户名
	private String password;// 密码
	private String smtpHostName;//发送邮件主机名称
	private String pop3HostName;//接收邮件主机名称
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSmtpHostName() {
		return smtpHostName;
	}
	public void setSmtpHostName(String smtpHostName) {
		this.smtpHostName = smtpHostName;
	}
	public String getPop3HostName() {
		return pop3HostName;
	}
	public void setPop3HostName(String pop3HostName) {
		this.pop3HostName = pop3HostName;
	}
	
	public boolean send(String recipient,String copyTo, String subject, String content) throws EmailException {
		SimpleEmailSender sms = new SimpleEmailSender(userName, password,smtpHostName);
		boolean sendSuccess = false;
		try {
			sms.send(recipient, copyTo, subject, content);
			log.info("邮件【" + recipient + "】发送成功");
			sendSuccess = true;
		} catch (UnsupportedEncodingException e) {
			log.error("消息异常", e);
			throw new EmailException();
		} catch (AddressException e) {
			log.error("发送邮件地址格式不正确", e);
			throw new EmailException();
		} catch (MessagingException e) {
			log.error("消息异常", e);
			throw new EmailException();
		}
		return sendSuccess;
	}

	public boolean send(String[] recipients,String[] copyTos, String subject, Object content) throws EmailException {
		SimpleEmailSender sms = new SimpleEmailSender(userName, password,smtpHostName);
		boolean sendSuccess = false;
		try {
			sms.send(recipients, copyTos, subject, content);
			log.info("邮件【" + recipients + "】发送成功");
			sendSuccess = true;
		} catch (UnsupportedEncodingException e) {
			log.error("消息异常", e);
			throw new EmailException();
		} catch (AddressException e) {
			log.error("发送邮件地址格式不正确", e);
			throw new EmailException();
		} catch (MessagingException e) {
			log.error("消息异常", e);
			throw new EmailException();
		}
		return sendSuccess;
	}

	public boolean send(SimpleEmail email) throws EmailException {
		SimpleEmailSender sms = new SimpleEmailSender(userName, password,smtpHostName);
		boolean sendSuccess = false;
		try {
			sms.send(email);
			log.info("邮件【" + email.getRecipients() + "】发送成功");
			sendSuccess = true;
		} catch (UnsupportedEncodingException e) {
			log.error("消息异常", e);
			throw new EmailException();
		} catch (AddressException e) {
			log.error("发送邮件地址格式不正确", e);
			throw new EmailException();
		} catch (MessagingException e) {
			log.error("消息异常", e);
			throw new EmailException();
		} 
		return sendSuccess;
	}
 
	public SimpleEmail[] receiveAll() throws EmailException {
		try {
			SimpleEmailReceiver receiver = new SimpleEmailReceiver(pop3HostName, userName, password);
			return receiver.receiveAll();
		} catch (MessagingException e) {
			log.error("消息异常", e);
			throw new EmailException();
		} catch (IOException e) {
			log.error("消息异常", e);
			throw new EmailException();
		}
	}
	
	public SimpleEmail receive(int msgnum) throws EmailException {
		try {
			SimpleEmailReceiver receiver = new SimpleEmailReceiver(pop3HostName, userName, password);
			return receiver.receive(msgnum);
		} catch (MessagingException e) {
			log.error("消息异常", e);
			throw new EmailException();
		} catch (IOException e) {
			log.error("消息异常", e);
			throw new EmailException();
		}
	}
}
