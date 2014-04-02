package com.GD.util;

import com.GD.email.MailInfo;

public class EmailUtil {

	public static MailInfo getMailInfo(String address, String code) {
		MailInfo mailInfo = new MailInfo();
		mailInfo.setUserName("test");
		mailInfo.setPassword("test");// ÄúµÄÓÊÏäÃÜÂë
		mailInfo.setFromAddress("test@goodancer.com");
		mailInfo.setToAddress(address);
		mailInfo.setSubject("ÕË»§¼¤»î");
		mailInfo.setContent("http://goodancer.com/activate.do?code=" + code);
		return mailInfo;
	}
	
}
