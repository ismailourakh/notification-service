package com.example.notification_service;

import com.example.notification_service.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:.env")
public class NotificationServiceApplication implements CommandLineRunner {

	private final EmailService emailService;

	@Autowired
	public NotificationServiceApplication(EmailService emailService) {
		this.emailService = emailService;
	}

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Sending an email example
		String recipient = "meftahahmedreda02@gmail.com";
		String subject = "Test Email";
		String message = "This is a test email from the Notification Service.";

		System.out.println("Envoi de l'e-mail...");
		emailService.sendEmail(recipient, subject, message);
	}
}
