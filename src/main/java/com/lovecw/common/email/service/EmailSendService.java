package com.lovecw.common.email.service;

import com.lovecw.common.email.bean.SimpleEmail;
import com.lovecw.common.email.exception.EmailException;

/**
 * @desc 邮件发送接口
 * @author ganliang
 *
 */
public interface EmailSendService {

	/**
	 * 发送服务
	 * @param mail 发送目的地邮箱
	 * @param copyTos 邮件抄送地
	 * @param subject 邮件的标题 
	 * @param content 邮件的内容
	 * @return
	 * @throws EmailException
	 */
	public boolean send(String recipient,String copyTo, String subject, String content) throws EmailException;
	
	/**
	 * 
	 * @param recipients 邮件发送地
	 * @param copyTos 邮件抄送地
	 * @param subject 邮件的标题 
	 * @param content 邮件的内容
	 * @return
	 * @throws EmailException
	 */
	public boolean send(String[] recipients,String[] copyTos, String subject, Object content) throws EmailException;
	
	/**
	 * 发送邮件
	 * @param email
	 * @return
	 * @throws EmailException
	 */
	public boolean send(SimpleEmail email) throws EmailException;
}
