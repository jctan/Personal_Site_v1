package mail;

import java.util.*;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import java.io.IOException;
import java.io.PrintWriter;  
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SendMail {

	public static boolean run(String addressedTo, String subject, String subjectline, String content){
		
		String fromAddress = "your_google_email";
		String recipients = addressedTo.trim();
		String contentType = "text/plain";		
		
		String smtpHost = "smtp.gmail.com";
		int smtpPort = 587;
		String username = "your_google_email";
		String password = "your_password";
		
		try
		{
			Properties props = System.getProperties();
			props.put("mail.smtp.starttls.enable", "true");
			Session session = Session.getDefaultInstance(props);

			MimeMessage message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(fromAddress));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(recipients, false));
			
			 message.setSubject(subject);
			 message.setSubject(subjectline);
			 message.setContent(content, contentType);
			 message.setSentDate(new Date());
			 
			 Transport transport = session.getTransport("smtp");
			 transport.connect(smtpHost, smtpPort, username, password);
			 transport.sendMessage(message, message.getAllRecipients());
			 transport.close();

			 return true;		 
		} catch (MessagingException messagingException)
		{
			System.out.print(messagingException);
			return false;

		}catch (Exception e)
		{
			System.out.print(e);
			return false;
		}
		
	}

}
