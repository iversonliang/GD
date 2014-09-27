package com.GD.util;

import com.GD.email.MailInfo;

public class EmailUtil {

	public static MailInfo getRegistMailInfo(String address, String code) {
		MailInfo mailInfo = new MailInfo();
		mailInfo.setUserName("test");
		mailInfo.setPassword("test");// 您的邮箱密码
		mailInfo.setFromAddress("test@goodancer.com");
		mailInfo.setToAddress(address);
		mailInfo.setSubject("账户激活");
		mailInfo.setContent("http://goodancer.com/activate.do?code=" + code);
		return mailInfo;
	}
	
	public static MailInfo getInviteCodeMailInfo(String address, String code) {
		MailInfo mailInfo = new MailInfo();
		mailInfo.setUserName("test");
		mailInfo.setPassword("test");// 您的邮箱密码
		mailInfo.setFromAddress("test@goodancer.com");
		mailInfo.setToAddress(address);
		mailInfo.setSubject("发布视频激活码");
		mailInfo.setContent("您的激活码为：" + code);
		return mailInfo;
	}
}
