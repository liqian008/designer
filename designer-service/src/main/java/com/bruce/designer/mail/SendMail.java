package com.bruce.designer.mail;

import java.security.Security;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	// private static final Log log = LogFactory.getLog(SendMail.class);

	/**
	 * Title: sendMailOfValidate Description:发送邮件的方法,Authenticator类验证
	 */
	private void sendSSLMail() {
		MailInfo mailInfo = new MailInfo();
		String mailAddress = "admin@jinwanr.com.cn";
		mailInfo.setHost("smtp.exmail.qq.com");
		mailInfo.setUsername("admin@jinwanr.com.cn");
		mailInfo.setPassword("vv234808");
		mailInfo.setTitle("title");
		mailInfo.setContent("content");
		mailInfo.setFrom(mailAddress);
		mailInfo.setTo(mailAddress);

		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

		Properties props = System.getProperties();
		props.put("mail.smtp.host", mailInfo.getHost());// 设置邮件服务器的域名或IP
		props.put("mail.smtp.auth", "true");// 授权邮件,mail.smtp.auth必须设置为true
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");

		String password = mailInfo.getPassword();// 密码

		// 传入发件人的用户名和密码,构造MyAuthenticator对象
		MyAuthenticator myauth = new MyAuthenticator(mailInfo.getUsername(), password);
		// 传入props、myauth对象,构造邮件授权的session对象
		Session session = Session.getDefaultInstance(props, myauth);
		// 将Session对象作为MimeMessage构造方法的参数传入构造message对象
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(mailInfo.getFrom()));// 发件人

			// 对多个收件人的情况进行处理,配置文件SendMail.xml中每个收件人之间必须用逗号隔开的
			if (mailInfo.getTo() != null && !"".equals(mailInfo.getTo())) {
				String to[] = mailInfo.getTo().split(",");
				for (int i = 0; i < to.length; i++) {
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));// 收件人
				}
			}
			message.setSubject(mailInfo.getTitle());// 主题
			message.setText(mailInfo.getContent());// 内容

			Transport.send(message);// 调用发送邮件的方法
			System.out.println("邮件发送成功");
			// log.debug("邮件发送成功");
		} catch (Exception e) {
			System.out.println("邮件发送失败");
		}
	}

	/**
	 * 为了方便直接用main方法测试
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		SendMail mail = new SendMail();
		mail.sendSSLMail();
	}
}