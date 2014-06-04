package com.GD.email;

import java.io.UnsupportedEncodingException;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// 这个类主要是设置邮件
		MailInfo mailInfo = new MailInfo();
		mailInfo.setUserName("test");
		mailInfo.setPassword("test");// 您的邮箱密码
		mailInfo.setFromAddress("test@goodancer.com");
		mailInfo.setToAddress("test@goodancer.com");
		mailInfo.setSubject("TestSubject");
		mailInfo.setContent("<html><body><h1>My First Heading</h1><p>My first paragraph.<br/>邮件服务器搞好了</p></body></html>");
		// 这个类主要来发送邮件
		MailSender.sendTextMail(mailInfo);// 发送文体格式
//		sms.sendHtmlMail(mailInfo);// 发送html格式
		System.out.println("done");
	}
}
