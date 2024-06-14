package com.shopping.mail.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;
import java.util.Collections;
import java.util.LinkedList;
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

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ayalait.utils.Email;
import com.ayalait.utils.ErrorState;
import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.google.gson.Gson;
import com.microsoft.graph.models.Attachment;
import com.microsoft.graph.models.BodyType;
import com.microsoft.graph.models.EmailAddress;
import com.microsoft.graph.models.FileAttachment;
import com.microsoft.graph.models.ItemBody;
import com.microsoft.graph.models.Recipient;
import com.microsoft.graph.models.UserSendMailParameterSet;
import com.microsoft.graph.requests.GraphServiceClient;

import com.microsoft.graph.authentication.IAuthenticationProvider;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;

import java.util.Arrays;




//nuevos
import com.microsoft.graph.authentication.IAuthenticationProvider;
import com.microsoft.graph.requests.GraphServiceClient;
import com.microsoft.graph.requests.MessageCollectionRequestBuilder;
import com.shopping.mail.modelo.ConfigSendMail;
import com.shopping.mail.modelo.ConfigSendMailEntity;
import com.microsoft.graph.models.Recipient;
import com.microsoft.graph.options.QueryOption;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.models.DriveRecipient;
import com.microsoft.graph.options.HeaderOption;
import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.microsoft.graph.requests.*;

@Service
public class SendMailServiceImpl implements SendMailService {
	
	private static  Session session;
    private static String password;
    private static String send;
    private static Transport t;
	 final String username = "info@ayalait.com.uy"; // Tu correo de Outlook 365

	 
	 private static String CLIENT_ID; // El ID de cliente de la app registrada
	 private static  String CLIENT; 

//     String resource = "https://outlook.office365.com/";
     private static String TENANT_ID ; // El ID de tu inquilino (tenant)

    @Autowired
    ConfigSendMail daoSend;


	/*public SendMailServiceImpl() {
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
			CLIENT_ID = properties.getProperty("clienteId");
			CLIENT= properties.getProperty("clienteSecret");
			TENANT_ID=properties.getProperty("tenandId");
			
		}
		
		
		
		
	}*/



	/*@Override
	public ResponseEntity<String> sendMail(Email email) {
		try{
			
				
				//Email email = new Gson().fromJson(request, Email.class);
				MimeMessage message = new MimeMessage(session);
				message.setFileName(email.getName());
				message.setFrom(new InternetAddress(send));
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
			message.setFrom(new InternetAddress(send));
			message.setSender(new InternetAddress(send));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getEmail()));
			message.setSubject(email.getSubject());
			//email.setAdjunto(true);
			//email.setArchivo("C:\\recursos\\prefacturas\\prefactura_54.pdf");
			 // Crear parte para el archivo adjunto
			MimeBodyPart attachmentPart = new MimeBodyPart();
			if(email.isAdjunto()) {
				 
		         File file = new File(email.getArchivo());
		         try {
					attachmentPart.attachFile(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
           
			
			Multipart mp = new MimeMultipart();
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(email.getMessage(), "text/html; charset=utf-8");
			mp.addBodyPart(htmlPart);
			mp.addBodyPart(attachmentPart);
			message.setContent(mp);
			
			
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			return new ResponseEntity<String>("Tu mensaje ha sido enviado.",HttpStatus.OK);
		
		
	}catch (MessagingException me){
                    //Aqui se deberia o mostrar un mensaje de error o en lugar
                    //de no hacer nada con la excepcion, lanzarla para que el modulo
                    //superior la capture y avise al usuario con un popup, por ejemplo.
		ErrorState error= new ErrorState();
		error.setCode(408);
		error.setMenssage(me.getMessage());
		return new ResponseEntity<String>(new Gson().toJson(error),HttpStatus.NOT_ACCEPTABLE);
	} 
		
	}

*/

	@Override
	public ResponseEntity<String> sendMailGraphMailSender(String mail) {
				
		try {
            
			String CLIENT_ID="";
			String CLIENT="";
			String TENANT_ID="";
			List<ConfigSendMailEntity> config= daoSend.findAll();
			
			for(ConfigSendMailEntity data: config) {
				if(data.getId()==1)
					CLIENT_ID=data.getClave();
				if(data.getId()==2)
					CLIENT=data.getClave();
				if(data.getId()==3)
					TENANT_ID=data.getClave();
					
			}
			
			//Obtener .class Email
			Email email = new Gson().fromJson(mail, Email.class);
			String encodedFile="";
			File file = null;
			// Leer el archivo y convertirlo a Base64
			/*if(email.isAdjunto()) {
				 
		          file = new File(email.getArchivo());
		         FileInputStream fileInputStream = new FileInputStream(file);
		         byte[] fileBytes = IOUtils.toByteArray(fileInputStream);
		          encodedFile = Base64.getEncoder().encodeToString(fileBytes);
		       
			}*/
			ClientSecretCredential credential = new ClientSecretCredentialBuilder()
				    .clientId(CLIENT_ID)
				    .clientSecret(CLIENT)
				    .tenantId(TENANT_ID)
				    .build();
			
			// Configura el proveedor de autenticación
	        TokenCredentialAuthProvider authProvider = new TokenCredentialAuthProvider(Collections.singletonList("https://graph.microsoft.com/.default"), credential);
	     // Crea un cliente GraphServiceClient
	        GraphServiceClient<?> graphClient = GraphServiceClient.builder()
	            .authenticationProvider(authProvider)
	            .buildClient();

				
			com.microsoft.graph.models.Message message = new com.microsoft.graph.models.Message();
			message.subject = email.getSubject();
			message.body = new ItemBody();
			message.body.contentType = BodyType.HTML;
			message.body.content = email.getMessage();
			
			// Agrega destinatarios al correo electrónico
            Recipient recipient = new Recipient();
            recipient.emailAddress = new EmailAddress();
            recipient.emailAddress.address = email.getEmail();
            message.toRecipients = Collections.singletonList(recipient);
	        
			if(email.isAdjunto()) {
				// Agrega el adjunto
		        FileAttachment attachment = new FileAttachment();
		       /* file = new File(email.getArchivo());
		         FileInputStream fileInputStream = new FileInputStream(fil
		          encodedFile = Base64.getEncoder().encodeToString(email.getArchivo().getBytes());*/
		          
		        attachment.name = email.getNombreArchivo();
		        attachment.contentBytes = email.getArchivo().getBytes();
		        attachment.oDataType = "#microsoft.graph.fileAttachment";
		        List<Attachment> attachments = new LinkedList<>();
		        attachments.add(attachment);


		        AttachmentCollectionResponse response = new AttachmentCollectionResponse();
		        response.value = attachments;

		        AttachmentCollectionRequestBuilder builder = new AttachmentCollectionRequestBuilder(
		        		 "https://graph.microsoft.com/v1.0/users/" + username + "/messages",	
		        		 graphClient,
		            /* Options */ null
		        );
		        
		        AttachmentCollectionPage attachmentPage = new AttachmentCollectionPage(response, builder);
	            message.attachments = attachmentPage;
	            
		        }
			

	        
			// Crea un cliente GraphServiceClient
			 // Configura los parámetros para enviar el correo
	        UserSendMailParameterSet parameters = UserSendMailParameterSet
	            .newBuilder()
	            .withMessage(message)
	            .withSaveToSentItems(false)
	            .build();
	        
	        // Enviar el correo electrónico
	        graphClient
	            .users(username)
	            .sendMail(parameters)
	            .buildRequest()
	            .post();
	        System.out.println("Correo electrónico enviado correctamente.");
			
            // Construir el JSON del mensaje con el archivo adjunto
          /*  String payload = "{"
                + "\"message\": {"
                + "\"subject\": \""+email.getSubject()+"\","
                + "\"body\": {"
                + "\"contentType\": \"Text\","
                + "\"content\": \""+email.getMessage()+"\""
                + "},"
                + "\"toRecipients\": [{"
                + "\"emailAddress\": {"
                + "\"address\": \""+email.getEmail()+"\""
                + "}}],"
                + "\"attachments\": [{"
                + "\"@odata.type\": \"#microsoft.graph.fileAttachment\","
                + "\"name\": \"" + file.getName() + "\","
                + "\"contentType\": \"text/plain\","
                + "\"contentBytes\": \"" + encodedFile + "\""
                + "}]"
                + "}"
                + "}";
            String accessToken = GraphMailSender.getAccessToken();*/
           
           // return new ResponseEntity<String>( GraphMailSender.sendMail(accessToken, username,payload),HttpStatus.OK);

	        return new ResponseEntity<String>( "Correo electrónico enviado correctamente.",HttpStatus.OK);
	}catch (Exception e) {
        e.printStackTrace();
    }
		return null;
	}
	
	

}
