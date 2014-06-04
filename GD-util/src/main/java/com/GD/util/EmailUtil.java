package com.GD.util;

import com.GD.email.MailInfo;

public class EmailUtil {

	public static MailInfo getMailInfo(String address, String code) {
		MailInfo mailInfo = new MailInfo();
		mailInfo.setUserName("test");
		mailInfo.setPassword("test");// 您的邮箱密码
		mailInfo.setFromAddress("test@goodancer.com");
		mailInfo.setToAddress(address);
		mailInfo.setSubject("账户激活");
		mailInfo.setContent("http://goodancer.com/activate.do?code=" + code);
		return mailInfo;
	}
	
}
