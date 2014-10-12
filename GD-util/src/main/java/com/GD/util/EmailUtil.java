package com.GD.util;

import com.GD.email.MailInfo;

public class EmailUtil {

	public static MailInfo getRegistMailInfo(String address, String username) {
		MailInfo mailInfo = EmailUtil.getMailAccount();
		mailInfo.setToAddress(address);
		mailInfo.setSubject("感谢注册优舞网成为优舞者！");
//		mailInfo.setContent("http://www.goodancer.com/user/activate.do?code=" + code);
		mailInfo.setContent(EmailUtil.getRegisterWelcomeHtml(address, username));
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
	
	private static String getRegisterWelcomeHtml(String email, String username) {
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		sb.append("<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><title>恭喜您，您的优舞网账号已注册成功!</title><meta name=\"Keywords\" content=\"\"/><meta name=\"description\" content=\"\" /></head>");
		sb.append("<body><table width=\"640\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#ffffff\" style=\"font-family:'Microsoft YaHei';\">");
		sb.append("<tbody><tr><td><table width=\"800\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" height=\"40\"></table></td></tr><tr>");
		sb.append("<td><table width=\"800\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#333333\" height=\"48\" style=\"font-family:'Microsoft YaHei';\">");
		sb.append("<tbody><tr><td width=\"74\" height=\"26\" border=\"0\" align=\"left\" valign=\"middle\" style=\"padding-left:20px;\"><a href=\"http://www.goodancer.com\" target=\"_blank\"><img src=\"http://www.goodancer.com/images/logo.gif\" width=\"98\" height=\"50\" border=\"0\"></a></td><td></td></tr></tbody></table></td></tr>");
		sb.append("<tr><td><table width=\"800\" border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" style=\" border:1px solid #edecec; border-top:none; border-bottom:none; padding:0 20px;font-size:13px;color:#333333;\">");
		sb.append("<tbody><tr><td width=\"760\" height=\"56\" border=\"0\" align=\"left\" colspan=\"2\" style=\"font-family:'Microsoft YaHei'; font-size:16px;vertical-align:bottom;\">尊敬的");
		sb.append(email);
		sb.append("：</td></tr><tr><td width=\"760\" height=\"30\" border=\"0\" align=\"left\" colspan=\"2\">&nbsp;</td></tr>");
		sb.append("<tr><td width=\"40\" height=\"32\" border=\"0\" align=\"left\" valign=\"middle\" style=\" width:40px; text-align:left;vertical-align:middle; line-height:32px; float:left;\"><img width=\"32\" height=\"32\" border=\"0\" src=\"http://gtms02.alicdn.com/tps/i2/T1CPucFwxeXXa94Hfd-32-32.png\"></td>");
		sb.append("<td width=\"720\" height=\"32\" border=\"0\" align=\"left\" valign=\"middle\" style=\" width:720px; text-align:left;vertical-align:middle;line-height:32px;font-family:'Microsoft YaHei';\">恭喜您，您的优舞网账号已注册成功!</td></tr>");
		sb.append("<tr><td width=\"720\" height=\"32\" colspan=\"2\" style=\"padding-left:40px;font-family:'Microsoft YaHei';\">您的账号为：").append(username).append("</td></tr>");
		sb.append("<tr><td width=\"720\" height=\"32\" colspan=\"2\" style=\"padding-left:40px;\">&nbsp;</td></tr>");
		sb.append("<tr><td width=\"720\" height=\"32\" colspan=\"2\" style=\"padding-left:40px;font-family:'Microsoft YaHei';\"><a href=\"http://www.goodancer.com\" target=\"_blank\">立即进入优舞网</a></td></tr>");
		sb.append("<tr><td width=\"720\" height=\"32\" colspan=\"2\" style=\"padding-left:40px;\">&nbsp;</td></tr>");
		sb.append("<tr><td width=\"720\" height=\"32\" colspan=\"2\" style=\"padding-left:40px;font-family:'Microsoft YaHei';\"><a href=\"http://www.goodancer.com/user/userInfo.do\" target=\"_blank\">修改个人资料</a></td></tr>");
		sb.append("<tr><td width=\"720\" height=\"32\" colspan=\"2\" style=\"padding-left:40px;font-family:'Microsoft YaHei';\">在这个看脸的社会，当然是要先准备自己的资料，再弄一个完美的头像啦！</td></tr>");
		sb.append("<tr><td width=\"720\" height=\"32\" colspan=\"2\" style=\"padding-left:40px;font-family:'Microsoft YaHei';\"><a href=\"http://www.goodancer.com/opus.do\" target=\"_blank\">国内作品</a></td></tr>");
		sb.append("<tr><td width=\"720\" height=\"32\" colspan=\"2\" style=\"padding-left:40px;font-family:'Microsoft YaHei';\">可能你还没有准备好？先看看其他舞者怎么做到的。</td></tr>");
		sb.append("<tr><td width=\"720\" height=\"32\" colspan=\"2\" style=\"padding-left:40px;font-family:'Microsoft YaHei';\"><a href=\"http://www.goodancer.com/video/contribute.do\" target=\"_blank\">上传作品</a></td></tr>");
		sb.append("<tr><td width=\"720\" height=\"32\" colspan=\"2\" style=\"padding-left:40px;font-family:'Microsoft YaHei';\">万事具备，就等着你的作品啦！</td></tr>");
		sb.append("<tr><td width=\"720\" height=\"32\" colspan=\"2\" style=\"padding-left:40px;\">&nbsp;</td></tr><tr><td width=\"720\" height=\"32\" colspan=\"2\" style=\"padding-left:40px;\">&nbsp;</td></tr>");
		sb.append("<tr><td width=\"720\" height=\"14\" colspan=\"2\" style=\"text-align:right;padding-bottom:16px; border-bottom:1px dashed #e5e5e5;font-family:'Microsoft YaHei';\">优舞网</td></tr>");
	    sb.append("<tr><td width=\"720\" height=\"14\" colspan=\"2\" style=\"text-align:right;padding:8px 0 28px;color:#999999; font-size:12px;font-family:'Microsoft YaHei';\">此为系统邮件请勿回复</td></tr></tbody></table></td></tr></tbody></table></body></html>");
		
		return sb.toString();
	}
}
