package com.shopping.mail.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ayalait.utils.Email;
import com.google.gson.Gson;


@Service
public class SendMailServiceImpl implements SendMailService {
	
	private static  Session session;
    private static String password;
    private static String send;
    private static Transport t;
    
    


	public SendMailServiceImpl() {
		try {
			conectarServer();
		} catch (IOException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public void conectarServer() throws IOException, MessagingException {
		 Properties properties = new Properties();

		
		URL url = this.getClass().getClassLoader().getResource("application.properties");
		if (url == null) {
			throw new IllegalArgumentException("application.properties" + " is not found 1");
		} else {
			InputStream propertiesStream = url.openStream();
			properties.load(propertiesStream);
			propertiesStream.close();
			send = properties.getProperty("mail.smtp.send");
			password= properties.getProperty("mail.smtp.password");
			session = Session.getDefaultInstance(properties);
			 t = session.getTransport("smtp");
			t.connect(send, password);
		}
		
		
		
		
	}



	@Override
	public ResponseEntity<String> sendMail(Email email) {
		try{
			
				
				//Email email = new Gson().fromJson(request, Email.class);
				MimeMessage message = new MimeMessage(session);
				message.setFileName(email.getName());
				message.setFrom(new InternetAddress("info@ayalait.com"));
				message.setSender(new InternetAddress(email.getEmail()));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(send));
				message.setSubject(email.getSubject());
				//message.setText(email.getMessage());
				message.setContent(email.getMessage(), "text/html; charset=utf-8");
				
				t.sendMessage(message, message.getAllRecipients());
				//t.close();
				return new ResponseEntity<String>("Tu mensaje ha sido enviado.",HttpStatus.OK);
			
			
		}catch (MessagingException me){
                        //Aqui se deberia o mostrar un mensaje de error o en lugar
                        //de no hacer nada con la excepcion, lanzarla para que el modulo
                        //superior la capture y avise al usuario con un popup, por ejemplo.
			return new ResponseEntity<String>(me.getMessage(),HttpStatus.NOT_ACCEPTABLE);
		}
	}



	@Override
	public ResponseEntity<String> sendMailRegistro(String request) {
		try{
			
			if(!t.isConnected())
				t.connect();
			
			Email email = new Gson().fromJson(request, Email.class);
			MimeMessage message = new MimeMessage(session);
			message.setFileName(email.getName());
			message.setFrom(new InternetAddress("info@ayalait.com"));
			message.setSender(new InternetAddress(email.getEmail()));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getEmail()));
			message.setSubject(email.getSubject());
			
			Multipart mp = new MimeMultipart();
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(email.getMessage(), "text/html; charset=utf-8");
			mp.addBodyPart(htmlPart);
			message.setContent(mp);
			
			
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			return new ResponseEntity<String>("Tu mensaje ha sido enviado.",HttpStatus.OK);
		
		
	}catch (MessagingException me){
                    //Aqui se deberia o mostrar un mensaje de error o en lugar
                    //de no hacer nada con la excepcion, lanzarla para que el modulo
                    //superior la capture y avise al usuario con un popup, por ejemplo.
		return new ResponseEntity<String>(me.getMessage(),HttpStatus.NOT_ACCEPTABLE);
	} 
		
	}

}
