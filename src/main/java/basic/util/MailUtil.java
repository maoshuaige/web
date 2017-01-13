package basic.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



/**
 * 邮件发送
 * @author jun.zeng
 *
 */
public class MailUtil { 
	
//		private static Logger = 
		
		private String protocol = "smtp" ; //默认为smtp
		private String host = "mail.hand-china.com" ;
		private String username ;  
		private String nick = "汉得校园招聘";  //昵称
		private String password ;
		private String auth = "true";
		private boolean debug = false ;
		
		private InternetAddress[] to;  //接受者 
		private InternetAddress[] cc;  //抄送给
		private InternetAddress[] bcc;  //抄送给
		
		public MailUtil(String username,String password) {
			this.password = password;
			this.username = username;
		}
		
		/**
		 * 发送可带附件的信息
		 * @param multipart
		 * @throws MessagingException
		 */
		public void send(String subject,Multipart multipart) throws MessagingException{
			MimeMessage message = init();
			message.setSubject(subject);
			message.setContent(multipart);
			message.saveChanges();  
			Transport.send(message);
		}
		
		/**
		 * 发送html文本   需要先设置发送人、抄送人、密送人(可选)
		 * @param subject
		 * @param html
		 * @throws MessagingException
		 */
		public void send(String subject,String html) throws MessagingException{
			MimeMessage message = init();
			message.setSubject(subject);
			message.setContent(html, "text/html;charset=UTF-8");
			message.saveChanges();  
			Transport.send(message);
		}
		
		private MimeMessage init(){
			Properties props = new Properties();
			props.put("mail.smtp.auth", auth);
	        props.put("mail.smtp.host",host);
	        // 访问SMTP服务时需要提供的密码
	        props.put("mail.transport.protocol", protocol);
	        Session mailSession = Session.getInstance(props, new MyAuthenticator());
	        mailSession.setDebug(debug);
	        MimeMessage message = new MimeMessage(mailSession);
	        try {
	        	nick = javax.mail.internet.MimeUtility.encodeText(nick);
				message.setFrom(new InternetAddress(nick +" <"+username+">"));
				message.addRecipients(RecipientType.TO, to);	// 发送
				message.addRecipients(RecipientType.CC, cc);    // 抄送
				message.addRecipients(RecipientType.BCC, bcc);  //密送
//				message.setContent(o, type);
			} catch (AddressException e) {
				
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	        return message;
		}
		
		private class MyAuthenticator extends Authenticator{
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return  new PasswordAuthentication(username, password);
			}
		}
		
	    public String getProtocol() {
			return protocol;
		}


		public void setProtocol(String protocol) {
			this.protocol = protocol;
		}


		public String getHost() {
			return host;
		}


		public void setHost(String host) {
			this.host = host;
		}


		public String getUsername() {
			return username;
		}


		public void setUsername(String username) {
			this.username = username;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public String getAuth() {
			return auth;
		}


		public void setAuth(String auth) {
			this.auth = auth;
		}


		public InternetAddress[] getTo() {
			return to;
		}


		public void setTo(InternetAddress[] to) {
			this.to = to;
		}


		public InternetAddress[] getCc() {
			return cc;
		}


		public void setCc(InternetAddress[] cc) {
			this.cc = cc;
		}


		public InternetAddress[] getBcc() {
			return bcc;
		}


		public void setBcc(InternetAddress[] bcc) {
			this.bcc = bcc;
		}

		public boolean isDebug() {
			return debug;
		}

		public void setDebug(boolean debug) {
			this.debug = debug;
		}
		
		

		public String getNick() {
			return nick;
		}

		public void setNick(String nick) {
			this.nick = nick;
		}

		public static void main(String[] args) throws MessagingException {
	       
	    }
	}