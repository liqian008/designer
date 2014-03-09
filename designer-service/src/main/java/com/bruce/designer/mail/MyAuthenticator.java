package com.bruce.designer.mail;

/**
 *Module:       MailInfo.java
 *Description:  邮件授权类
 *Company:      
 *Author:       ptp
 *Date:         Mar , 
 */
import javax.mail.PasswordAuthentication;

public class MyAuthenticator extends javax.mail.Authenticator {
	private String strUser;
	private String strPwd;

	public MyAuthenticator(String user, String password) {
		this.strUser = user;
		this.strPwd = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(strUser, strPwd);
	}
}