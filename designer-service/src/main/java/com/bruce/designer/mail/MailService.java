package com.bruce.designer.mail;

import java.security.Security;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.bruce.designer.util.ConfigUtil;

@Service
public class MailService {
    /*SMTP配置*/
	private static final String MAIL_SMTP = ConfigUtil.getString("mail_smtp");
	/*邮件人的账户*/
	private static final String MAIL_ADMIN_USERNAME = ConfigUtil.getString("mail_admin_username");
	/*邮件人的密码*/
	private static final String MAIL_ADMIN_PASSWORD = ConfigUtil.getString("mail_admin_password");
	/*欢迎邮件标题*/
	private static final String MAIL_WELCOME_TITLE = ConfigUtil.getString("mail_welcome_title");
	/*欢迎邮件内容*/
	private static final String MAIL_WELCOME_CONTENT = ConfigUtil.getString("mail_welcome_content");
	/*申请设计师邮件标题*/
	private static final String MAIL_APPLY_TITLE = ConfigUtil.getString("mail_apply_title");
	/*申请设计师邮件内容*/
    private static final String MAIL_APPLY_CONTENT = ConfigUtil.getString("mail_apply_content");
    /*审核人的email，多人需用半角逗号,分割*/
    private static final String MAIL_STAFF_EMAIL = ConfigUtil.getString("mail_staff_email");

	// private static final Log log = LogFactory.getLog(SendMail.class);

	/**
	 * 发送欢迎邮件，to用户
	 */
	public void sendWelcomeMail(String userMail){
	    String welcomeTitle = MAIL_WELCOME_TITLE;
	    String welcomeContent = MAIL_WELCOME_CONTENT;
	    sendSSLMail(MAIL_ADMIN_USERNAME, MAIL_ADMIN_PASSWORD, userMail, welcomeTitle, welcomeContent);
	}
	
	/**
	 * 发送设计师申请确认邮件，to审批人
	 */
	public void sendDesignerApplyMail(int userId){
	    String designerApplyTitle = MAIL_APPLY_TITLE;
        String designerApplyContent = MAIL_APPLY_CONTENT;
        //审批人的email，可能有多个，需用,分割
        String staffMail = MAIL_STAFF_EMAIL;
        sendSSLMail(MAIL_ADMIN_USERNAME, MAIL_ADMIN_PASSWORD, staffMail, designerApplyTitle, designerApplyContent);
    }

	/**
	 * 发送设计师申请通过邮件，to设计师
	 */
	public void sendDesignerApprovedMail(int designerId, String designerMail){
	    String designerApprovedTitle = "";
        String designerApprovedContent = "";
        sendSSLMail(MAIL_ADMIN_USERNAME, MAIL_ADMIN_PASSWORD, designerMail, designerApprovedTitle, designerApprovedContent);
	}
	
	/**
	 * 发送邮件的方法,Authenticator类验证
	 * @param mailFrom
	 * @param mailPwd
	 * @param mailTo
	 * @param mailTitle
	 * @param mailContent
	 */
	private void sendSSLMail(String mailFrom, String mailPwd, String mailTo, String mailTitle, String mailContent) {
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

		Properties props = System.getProperties();
		props.put("mail.smtp.host", MAIL_SMTP);// 设置邮件服务器的域名或IP
		props.put("mail.smtp.auth", "true");// 授权邮件,mail.smtp.auth必须设置为true
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");

		// 传入发件人的用户名和密码,构造MyAuthenticator对象
		MyAuthenticator myauth = new MyAuthenticator(mailFrom, mailPwd);
		// 传入props、myauth对象,构造邮件授权的session对象
		Session session = Session.getDefaultInstance(props, myauth);
		// 将Session对象作为MimeMessage构造方法的参数传入构造message对象
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(mailFrom));// 发件人

			// 对多个收件人的情况进行处理,配置文件SendMail.xml中每个收件人之间必须用逗号隔开的
			if (mailTo != null && !"".equals(mailTo)) {
				String to[] = mailTo.split(",");
				for (int i = 0; i < to.length; i++) {
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));// 收件人
				}
			}
			message.setSubject(mailTitle);// 主题
			message.setText(mailContent);// 内容

			Transport.send(message);// 调用发送邮件的方法
//			System.out.println("邮件发送成功");
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
//		SendMail mail = new SendMail();
//		mail.sendSSLMail();
	}
}