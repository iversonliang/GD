package com.GD.email;

import java.io.UnsupportedEncodingException;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// �������Ҫ�������ʼ�
		MailInfo mailInfo = new MailInfo();
		mailInfo.setUserName("test");
		mailInfo.setPassword("test");// ������������
		mailInfo.setFromAddress("test@goodancer.com");
		mailInfo.setToAddress("test@goodancer.com");
		mailInfo.setSubject("TestSubject");
		mailInfo.setContent("<html><body><h1>My First Heading</h1><p>My first paragraph.<br/>�ʼ������������</p></body></html>");
		// �������Ҫ�������ʼ�
		MailSender.sendTextMail(mailInfo);// ���������ʽ
//		sms.sendHtmlMail(mailInfo);// ����html��ʽ
		System.out.println("done");
	}
}
