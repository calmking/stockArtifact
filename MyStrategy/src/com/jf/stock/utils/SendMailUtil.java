package com.jf.stock.utils;

import java.io.File;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendMailUtil {

	private MimeMessage mimeMsg; // MIME邮件对象

	private Session session; // 邮件会话对象
	private Properties props; // 系统属性
	private boolean needAuth = false; // smtp是否需要认证

	private String username = ""; // smtp认证用户名和密码
	private String password = "";

	private Multipart mp; // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象

	public SendMailUtil() {
		setSmtpHost("127.0.0.1");// 指定邮件服务器
		createMimeMessage();
	}

	public SendMailUtil(String smtp) {
		setSmtpHost(smtp);
		createMimeMessage();
	}

	/**
	 * @param hostName
	 *            String
	 */
	public void setSmtpHost(String hostName) {
		// System.out.println("设置系统属性：mail.smtp.host = "+hostName);
		if (props == null)
			props = System.getProperties(); // 获得系统属性对象

		props.put("mail.smtp.host", hostName); // 设置SMTP主机
	}

	/**
	 * @return boolean
	 */
	public boolean createMimeMessage() {
		try {
			// System.out.println("准备获取邮件会话对象！");
			session = Session.getDefaultInstance(props, null); // 获得邮件会话对象
		} catch (Exception e) {
			System.err.println("获取邮件会话对象时发生错误！" + e);
			return false;
		}

		// System.out.println("准备创建MIME邮件对象！");
		try {
			mimeMsg = new MimeMessage(session); // 创建MIME邮件对象
			mp = new MimeMultipart();

			return true;
		} catch (Exception e) {
			System.err.println("创建MIME邮件对象失败！" + e.getMessage());

			return false;
		}
	}

	/**
	 * @param need
	 *            boolean
	 */
	public void setNeedAuth(boolean need) {
		// System.out.println("设置smtp身份认证：mail.smtp.auth = "+need);
		if (props == null)
			props = System.getProperties();

		if (need) {
			props.put("mail.smtp.auth", "true");
		} else {
			props.put("mail.smtp.auth", "false");
		}
	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public void setNamePass(String name, String pass) {
		username = name;
		password = pass;
	}

	/**
	 * @param mailSubject
	 *            String
	 * @return boolean
	 */
	public boolean setSubject(String mailSubject) {
		// System.out.println("设置邮件主题！");
		try {
			mimeMsg.setSubject(mailSubject);
			return true;
		} catch (Exception e) {
			System.err.println("设置邮件主题发生错误！");
			return false;
		}
	}

	/**
	 * @param mailBody
	 *            String
	 */
	public boolean setBody(String mailBody) {
		try {
			BodyPart bp = new MimeBodyPart();
			bp.setContent(mailBody, "text/html;charset=GB2312");
			mp.addBodyPart(bp);

			return true;
		} catch (Exception e) {
			System.err.println("设置邮件正文时发生错误！" + e);
			return false;
		}
	}
	
	public boolean setAttachment(File file) {
		try {
			BodyPart bp = new MimeBodyPart();
			FileDataSource source = new FileDataSource(file);  
			bp.setDataHandler(new DataHandler(source));  
			bp.setFileName(MimeUtility.encodeWord(file.getName(), "utf-8", null));  
			mp.addBodyPart(bp);
			return true;
		} catch (Exception e) {
			System.err.println("设置邮件正文时发生错误！" + e);
			return false;
		}
	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public boolean addFileAffix(String filename) {

		// System.out.println("增加邮件附件："+filename);

		try {
			BodyPart bp = new MimeBodyPart();
			FileDataSource fileds = new FileDataSource(filename);
			bp.setDataHandler(new DataHandler(fileds));
			bp.setFileName(fileds.getName());

			mp.addBodyPart(bp);

			return true;
		} catch (Exception e) {
			System.err.println("增加邮件附件：" + filename + "发生错误！" + e);
			return false;
		}
	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public boolean setFrom(String from) {
		// System.out.println("设置发信人！");
		try {
			mimeMsg.setFrom(new InternetAddress(from)); // 设置发信人
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public boolean setTo(String to) {
		if (to == null)
			return false;

		try {
			mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress
					.parse(to));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public boolean setCopyTo(String copyto) {
		if (copyto == null)
			return false;
		try {
			mimeMsg.setRecipients(Message.RecipientType.CC,
					(Address[]) InternetAddress.parse(copyto));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public boolean sendout() {
		try {
			mimeMsg.setContent(mp);
			mimeMsg.saveChanges();
			System.out.println("正在发送邮件....");

			Session mailSession = Session.getInstance(props, null);
			Transport transport = mailSession.getTransport("smtp");
			transport.connect((String) props.get("mail.smtp.host"), username,
					password);
			transport.sendMessage(mimeMsg, mimeMsg
					.getRecipients(Message.RecipientType.TO));
			// transport.send(mimeMsg);

			System.out.println("发送邮件成功！");
			transport.close();

			return true;
		} catch (Exception e) {
			System.err.println("邮件发送失败！" + e);
			return false;
		}
	}

	public boolean sendMail(String mailHostName, String fromMail,
			String userName, String password, String toMail,
			String mailSubject, String mailBody) {

		SendMailUtil themail = new SendMailUtil(mailHostName);
		themail.setNeedAuth(true);

		if (themail.setSubject(mailSubject) == false)
			return false;
		if (themail.setBody(mailBody) == false)
			return false;
		if (themail.setTo(toMail) == false)
			return false;
		if (themail.setFrom(fromMail) == false)
			return false;
		// if(themail.addFileAffix("c:\\sqlnet.log") == false) return;
		themail.setNamePass(userName, password);

		if (themail.sendout() == false)
			return false;

		return true;
	}
	
	public boolean sendMail(String mailHostName, String fromMail,
			String userName, String password, String toMail,
			String mailSubject, File file) {

		SendMailUtil themail = new SendMailUtil(mailHostName);
		themail.setNeedAuth(true);

		if (themail.setSubject(mailSubject) == false)
			return false;
		if (themail.setAttachment(file) == false)
			return false;
		if (themail.setTo(toMail) == false)
			return false;
		if (themail.setFrom(fromMail) == false)
			return false;
		// if(themail.addFileAffix("c:\\sqlnet.log") == false) return;
		themail.setNamePass(userName, password);

		if (themail.sendout() == false)
			return false;

		return true;
	}

	public static void main(String[] args) {

		String mailbody = "你好!";
		SendMailUtil themail = new SendMailUtil("email.cindasc.com");
		themail.sendMail("smtp.163.com", "15021080090@163.com", "15021080090@163.com",
				"loveshanshan", "464072212@qq.com", "hello test", new File("d:/myself/stock/strategy1-20141020.txt"));
	}

}
