package com.example.model;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.example.Config.MQConfig;

import lombok.Getter;

@Component
@Getter
public class MessageListener {
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail(String toEmail,
			  String body,
			  String subject) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("vennela24gowda@gmail.com");
		message.setTo(toEmail);
		message.setText(body);	
		message.setSubject(subject);
		mailSender.send(message);
	}

	@RabbitListener(queues = MQConfig.QUEUE)
	public void consumeMessageFromQueue(EmailBody email) {
		sendEmail(email.getToMail(), email.getBody(), email.getSubject());
		//System.out.println("Confirmation email sent successfully to "+email.getToMail());
	}
}
/*
 * public void sendEmail(String toEmail, String body, String subject) {
 * SimpleMailMessage message = new SimpleMailMessage();
 * message.setFrom("vennelareddycs2000@gmail.com"); message.setTo(toEmail);
 * message.setText(body); message.setSubject(subject); mailSender.send(message);
 * }
 * 
 * @PostMapping("/send-mail") public String sendMail(@RequestBody EmailBody
 * emailBody) {
 * 
 * sendEmail(emailBody.getToMail(), emailBody.getBody(),
 * emailBody.getSubject());
 * 
 * return "Confirmation email sent successfully to "+emailBody.getToMail(); }
 */