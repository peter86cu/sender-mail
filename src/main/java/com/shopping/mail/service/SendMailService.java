package com.shopping.mail.service;

import org.springframework.http.ResponseEntity;

import com.ayalait.utils.Email;


public interface SendMailService {
	
	/*ResponseEntity<String> sendMail(Email email);
	
	ResponseEntity<String> sendMailRegistro(String email);*/
	
	ResponseEntity<String> sendMailGraphMailSender(String mail);

}
