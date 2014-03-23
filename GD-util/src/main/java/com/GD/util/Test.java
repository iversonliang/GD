package com.GD.util;

public class Test {

	public static void main(String[] args) {
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.goodancer.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("test");
		mailInfo.setPassword("test");// 您的邮箱密码
		mailInfo.setFromAddress("test@goodancer.com");
		mailInfo.setToAddress("iversonliang@qq.com");
		mailInfo.setSubject("Test");
		mailInfo.setContent("<html><body><h1>My First Heading</h1><p>My first paragraph.<br/>邮件服务器搞好了</p></body></html>");
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendTextMail(mailInfo);// 发送文体格式
//		sms.sendHtmlMail(mailInfo);// 发送html格式
		System.out.println("done");
	}
}
