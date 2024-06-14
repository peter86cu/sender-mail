package com.shopping.mail.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ayalait.response.ResponseResultado;
import com.ayalait.utils.Email;
import com.google.gson.Gson;
import com.shopping.mail.service.SendMailService;



@RestController
public class SendMailController {

	@Autowired
	SendMailService service;
	
	/*@PostMapping(value = "sendMail", produces = MediaType.APPLICATION_JSON_VALUE)	
	public void enviarMail(@RequestParam("name") String name, @RequestParam("subject") String subject,
			@RequestParam("email") String email,@RequestParam("message") String message,	HttpServletResponse responseHttp) throws IOException {
		Email datos = new Email();
		datos.setEmail(email);
		datos.setMessage(message);
		datos.setName(name);
		datos.setSubject(subject);
		ResponseEntity<String> result= service.sendMail( datos);
		ResponseResultado response = new ResponseResultado();
		
			response.setCode(result.getStatusCode().value());
			response.setResultado(result.getBody());
			
			String json = new Gson().toJson(response);
			responseHttp.setContentType("application/json");
			responseHttp.setCharacterEncoding("UTF-8");
			responseHttp.getWriter().write(json);
		
	}
	*/
	
	@PostMapping(value = "sendMailBody", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> enviarMailBody(@RequestBody String datos,	HttpServletResponse responseHttps) {
		
		return service.sendMailGraphMailSender( datos);
		
	}
	
}
