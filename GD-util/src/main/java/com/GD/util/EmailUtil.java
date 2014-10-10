package com.GD.util;

import com.GD.email.MailInfo;

public class EmailUtil {

	public static MailInfo getRegistMailInfo(String address, String code) {
		MailInfo mailInfo = EmailUtil.getMailAccount();
		mailInfo.setToAddress(address);
		mailInfo.setSubject("账户注册验证");
		mailInfo.setContent("http://www.goodancer.com/user/activate.do?code=" + code);
		return mailInfo;
	}
	
	public static MailInfo getInviteCodeMailInfo(String address, String code) {
		MailInfo mailInfo = EmailUtil.getMailAccount();
		mailInfo.setToAddress(address);
		mailInfo.setSubject("优舞网激活码");
		mailInfo.setContent("<html><body><p>您的激活码为：" + code + "， <a href='http://www.goodancer.com/inviteCode/index.do'>立即激活</a></p></body></html>");
		return mailInfo;
	}
	
	public static MailInfo getPasswordResetMailInfo(String address, String code) {
		MailInfo mailInfo = EmailUtil.getMailAccount();
		mailInfo.setToAddress(address);
		mailInfo.setSubject("优舞网密码重置");
		mailInfo.setContent("<html><body><p>请点击以下地址重置您的密码：<a href='http://www.goodancer.com/page/reset.jsp?code=" + code + "'>http://www.goodancer.com/page/reset.jsp?code=" + code + "</a></p></body></html>");
		return mailInfo;
	}
	
	public static MailInfo getMailAccount() {
		MailInfo mailInfo = new MailInfo();
		mailInfo.setUserName("mail");
		mailInfo.setPassword("goodancer_mail");// 您的邮箱密码
		mailInfo.setFromAddress("mail@goodancer.com");
		mailInfo.setPersonal("优舞网");
		return mailInfo;
	}
}
