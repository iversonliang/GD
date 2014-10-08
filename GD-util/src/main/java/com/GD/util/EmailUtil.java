package com.GD.util;

import com.GD.email.MailInfo;

public class EmailUtil {

	public static MailInfo getRegistMailInfo(String address, String code) {
		MailInfo mailInfo = EmailUtil.getMailAccount();
		mailInfo.setToAddress(address);
		mailInfo.setSubject("账户注册验证");
		mailInfo.setContent("http://goodancer.com/activate.do?code=" + code);
		return mailInfo;
	}
	
	public static MailInfo getInviteCodeMailInfo(String address, String code) {
		MailInfo mailInfo = EmailUtil.getMailAccount();
		mailInfo.setToAddress(address);
		mailInfo.setSubject("优舞网激活码");
		mailInfo.setContent("您的激活码为：" + code);
		return mailInfo;
	}
	
	public static MailInfo getMailAccount() {
		MailInfo mailInfo = new MailInfo();
		mailInfo.setUserName("mail");
		mailInfo.setPassword("goodancer_mail");// 您的邮箱密码
		mailInfo.setFromAddress("mail@goodancer.com");
		return mailInfo;
	}
}
