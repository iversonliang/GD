package com.GD.dao.impl;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class TestEmail {

	static final String Charset = "UTF-8";
	static final String Encoding = "B";
	static final String HtmlContentType = "text/html;charset=utf8";

	private static Properties getCommonProperties() {
		Properties props = new Properties();
		// props.setProperty("mail.transport.protocol", "smtp");
		// props.setProperty("mail.smtp.host", "smtp.163.com");
		// props.setProperty("mail.smtp.port", "25");
		// props.setProperty("mail.smtp.auth", "true");
		// props.setProperty("mail.smtp.user", "xxxx@163.com");
		// props.setProperty("mail.smtp.pass", "xxxx");
		// props.setProperty("mail.smtp.from", "xxxx@163.com");
		// props.setProperty("mail.smtp.personal", "����ϵͳ");

		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", "smtp.game.yy.com");
		props.setProperty("mail.smtp.port", "25");
		props.setProperty("mail.smtp.auth", "false");
		return props;
	}

	/**
	 * ��ȡ����Session.
	 * 
	 * @return
	 */
	public static Session getSession(String from, String personal) {
		Properties props = getCommonProperties();
		props.setProperty("mail.smtp.from", from);
		props.setProperty("mail.smtp.personal", personal);
		return Session.getInstance(props);
	}

	/**
	 * ���ͼ򵥵�Text�ʼ�.
	 * 
	 * @param email
	 *            �ռ���
	 * @param subject
	 *            �ʼ�����
	 * @param content
	 *            �ʼ�����
	 * @throws UnsupportedEncodingException
	 * @throws MessagingException
	 */
	public static void sendSimpleEmail(Session session, String email, String subject, String content) throws UnsupportedEncodingException, MessagingException {
		sendSimpleEmail(session, Arrays.asList(email), null, subject, content);
	}

	/**
	 * ���ͼ򵥵�Text�ʼ�.
	 * 
	 * @param session
	 *            �����ʼ��ĻỰ
	 * @param to
	 *            �ռ����б�
	 * @param cc
	 *            �������б�
	 * @param subject
	 *            �ʼ�����
	 * @param content
	 *            �ʼ�����
	 * @throws UnsupportedEncodingException
	 * @throws MessagingException
	 */
	public static void sendSimpleEmail(Session session, List<String> to, List<String> cc, String subject, String content) throws UnsupportedEncodingException, MessagingException {

		if (to == null || to.size() == 0) {
			return;
		}

		Transport port = null;
		try {

			port = session.getTransport();
			port.connect();
			MimeMessage mimeMsg = new MimeMessage(session);
			String from = session.getProperty("mail.smtp.from");
			String personal = session.getProperty("mail.smtp.personal");
			mimeMsg.setFrom(new InternetAddress(from, personal));
			mimeMsg.setSubject(MimeUtility.encodeText(subject, Charset, Encoding));
			mimeMsg.setSentDate(new java.util.Date());
			mimeMsg.setText(content, Charset);

			Set<Address> allAddr = new HashSet<Address>();

			Address[] toAddr = new Address[to.size()];
			for (int i = 0; i < toAddr.length; i++) {
				toAddr[i] = new InternetAddress(to.get(i));
			}
			mimeMsg.setRecipients(Message.RecipientType.TO, toAddr);
			Collections.addAll(allAddr, toAddr);

			Address[] ccAddr = null;
			if (cc != null && cc.size() > 0) {
				ccAddr = new Address[cc.size()];
				for (int i = 0; i < ccAddr.length; i++) {
					ccAddr[i] = new InternetAddress(cc.get(i));
				}
				mimeMsg.setRecipients(Message.RecipientType.CC, ccAddr);

				Collections.addAll(allAddr, ccAddr);
			}

			port.sendMessage(mimeMsg, allAddr.toArray(new Address[0]));

		} finally {
			if (port != null) {
				try {
					port.close();
				} catch (MessagingException e) {
					throw e;
				}
			}
		}

	}

	/**
	 * ����html�ʼ�
	 * 
	 * @param to
	 *            �ռ����б�
	 * @param cc
	 *            �������б�
	 * @param subject
	 *            �ʼ�����
	 * @param html
	 *            �ʼ�����
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public static void sendHtmlEmail(Session session, List<String> to, List<String> cc, String subject, String html) throws MessagingException, UnsupportedEncodingException {

		MimeMultipart multi = new MimeMultipart();
		MimeBodyPart body = new MimeBodyPart();
		body.setContent(html, HtmlContentType);
		multi.addBodyPart(body);

		sendMultiEmail(session, to, cc, subject, multi);

	}

	public static void sendHtmlEmail(Session session, String to, String subject, String html) throws MessagingException, UnsupportedEncodingException {

		MimeMultipart multi = new MimeMultipart();
		MimeBodyPart body = new MimeBodyPart();
		body.setContent(html, HtmlContentType);
		multi.addBodyPart(body);

		sendMultiEmail(session, Arrays.asList(to), null, subject, multi);

	}

	/**
	 * ���ʹ�������Html�ʼ�
	 * 
	 * @param recipient
	 *            �ռ���
	 * @param subject
	 *            �ʼ�����
	 * @param html
	 *            �ʼ�html����
	 * @param attachData
	 *            ��������
	 * @param attachName
	 *            ��������
	 * @throws UnsupportedEncodingException
	 * @throws MessagingException
	 */
	public static void sendHtmlEmailWithAttach(Session session, String recipient, String subject, String html, DataSource attachData, String attachName) throws UnsupportedEncodingException, MessagingException {
		sendHtmlEmailWithAttach(session, Arrays.asList(recipient), null, subject, html, attachData, attachName);
	}

	/**
	 * ���ʹ�������html�ʼ�
	 * 
	 * @param to
	 *            �������б�
	 * @param cc
	 *            �������б�
	 * @param subject
	 *            �ʼ�����
	 * @param html
	 *            �ʼ�html����
	 * @param attachData
	 *            ��������
	 * @param attachName
	 *            ��������
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public static void sendHtmlEmailWithAttach(Session session, List<String> to, List<String> cc, String subject, String html, DataSource attachData, String attachName) throws MessagingException, UnsupportedEncodingException {

		MimeMultipart multi = new MimeMultipart();

		MimeBodyPart body = new MimeBodyPart();
		body.setContent(html, HtmlContentType);
		multi.addBodyPart(body);

		body = new MimeBodyPart();
		body.setDataHandler(new DataHandler(attachData));
		body.setFileName(MimeUtility.encodeText(attachName, Charset, Encoding));
		multi.addBodyPart(body);

		sendMultiEmail(session, to, cc, subject, multi);

	}

	/**
	 * ���ʹ�ͼƬ��html�ʼ�. ע��, ����ռ����б��볭�����б��н���,ĳЩ�ʼ����������������.
	 * 
	 * @param to
	 *            �ռ����б�
	 * @param cc
	 *            �������б�
	 * @param subject
	 *            �ʼ�����
	 * @param html
	 *            �ʼ�html����
	 * @param imgData
	 *            ͼƬ����
	 * @param cid
	 *            ͼƬ����id,��html��ͨ�� <img src="cid:IMG1" ...>��ʽ����ͼƬ
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public static void sendHtmlEmailWithImage(Session session, String recipient, String subject, String html, DataSource imgData, String cid) throws UnsupportedEncodingException, MessagingException {
		MimeMultipart multi = new MimeMultipart("related");

		MimeBodyPart body = new MimeBodyPart();
		body.setContent(html, HtmlContentType);
		multi.addBodyPart(body);

		body = new MimeBodyPart();
		body.setDataHandler(new DataHandler(imgData));
		body.setContentID(cid);
		multi.addBodyPart(body);

		sendMultiEmail(session, Arrays.asList(recipient), null, subject, multi);
	}

	/**
	 * ���ʹ�ͼƬ��html�ʼ�. ע��, ����ռ����б��볭�����б��н���,ĳЩ�ʼ����������������.
	 * 
	 * @param to
	 *            �ռ����б�
	 * @param cc
	 *            �������б�
	 * @param subject
	 *            �ʼ�����
	 * @param html
	 *            �ʼ�html����
	 * @param imgData
	 *            ͼƬ����
	 * @param cid
	 *            ͼƬ����id,��html��ͨ�� <img src="cid:IMG1" ...>��ʽ����ͼƬ
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public static void sendHtmlEmailWithImage(Session session, List<String> to, List<String> cc, String subject, String html, DataSource imgData, String cid) throws MessagingException, UnsupportedEncodingException {

		MimeMultipart multi = new MimeMultipart("related");

		MimeBodyPart body = new MimeBodyPart();
		body.setContent(html, HtmlContentType);
		multi.addBodyPart(body);

		body = new MimeBodyPart();
		body.setDataHandler(new DataHandler(imgData));
		body.setContentID(cid);
		multi.addBodyPart(body);

		sendMultiEmail(session, to, cc, subject, multi);

	}

	public static void sendMultiEmail(Session session, List<String> to, List<String> cc, String subject, Multipart content) throws UnsupportedEncodingException, MessagingException {

		if (to == null || to.size() == 0) {
			return;
		}

		Transport port = null;
		try {

			port = session.getTransport();
			port.connect();

			MimeMessage mimeMsg = new MimeMessage(session);

			mimeMsg.setSentDate(new Date());
			String from = session.getProperty("mail.smtp.from");
			String personal = session.getProperty("mail.smtp.personal");
			mimeMsg.setFrom(new InternetAddress(from, personal));
			mimeMsg.setSubject(MimeUtility.encodeText(subject, Charset, Encoding));
			mimeMsg.setSentDate(new Date());
			mimeMsg.setContent(content);
			mimeMsg.saveChanges();

			Set<Address> allAddr = new HashSet<Address>();

			Address[] toAddr = new Address[to.size()];
			for (int i = 0; i < toAddr.length; i++) {
				toAddr[i] = new InternetAddress(to.get(i));
			}
			mimeMsg.setRecipients(Message.RecipientType.TO, toAddr);
			Collections.addAll(allAddr, toAddr);

			Address[] ccAddr = null;
			if (cc != null && cc.size() > 0) {
				ccAddr = new Address[cc.size()];
				for (int i = 0; i < ccAddr.length; i++) {
					ccAddr[i] = new InternetAddress(cc.get(i));
				}
				mimeMsg.setRecipients(Message.RecipientType.CC, ccAddr);

				Collections.addAll(allAddr, ccAddr);
			}

			port.sendMessage(mimeMsg, allAddr.toArray(new Address[0]));

		} finally {
			try {
				port.close();
			} catch (MessagingException e) {
				throw e;
			}
		}
	}

}

