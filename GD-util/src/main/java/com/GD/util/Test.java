package com.GD.util;

public class Test {

	public static void main(String[] args) {
		// �������Ҫ�������ʼ�
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.goodancer.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("test");
		mailInfo.setPassword("test");// ������������
		mailInfo.setFromAddress("test@goodancer.com");
		mailInfo.setToAddress("iversonliang@qq.com");
		mailInfo.setSubject("Test");
		mailInfo.setContent("<html><body><h1>My First Heading</h1><p>My first paragraph.<br/>�ʼ������������</p></body></html>");
		// �������Ҫ�������ʼ�
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendTextMail(mailInfo);// ���������ʽ
//		sms.sendHtmlMail(mailInfo);// ����html��ʽ
		System.out.println("done");
	}
}
