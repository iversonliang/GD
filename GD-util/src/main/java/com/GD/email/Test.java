package com.GD.email;

import java.io.UnsupportedEncodingException;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// 这个类主要是设置邮件
		MailInfo mailInfo = new MailInfo();
		mailInfo.setUserName("mail");
		mailInfo.setPassword("goodancer_mail");// 您的邮箱密码
		mailInfo.setFromAddress("mail@goodancer.com");
		mailInfo.setToAddress("iversonlicjk@gmail.com");
		mailInfo.setSubject("优舞网测试邮件");
		mailInfo.setContent("<html><body><h1>My First Heading</h1><p>My first paragraph.<br/>优舞网内侧邮件</p></body></html>");
		// 这个类主要来发送邮件
//		MailSender.sendTextMail(mailInfo);// 发送文体格式
		MailSender.sendHtmlMail(mailInfo);// 发送html格式
		System.out.println("done");
	}
}
