package com.example.notification_service.service;

import com.example.notification_service.model.Notification;
import com.example.notification_service.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    public NotificationService(NotificationRepository notificationRepository, EmailService emailService) {
        this.notificationRepository = notificationRepository;
        this.emailService = emailService;
    }

    /**
     * Save a notification in the database and send an email notification.
     *
     * @param notification the notification to save
     * @return the saved notification
     */
    public Notification saveNotification(Notification notification) {
        // Save the notification to the database
        Notification savedNotification = notificationRepository.save(notification);

        // Send an email notification
        emailService.sendEmail(
                notification.getEmail(),
                "New Notification", // Email subject
                notification.getMessage() // Email message
        );

        return savedNotification;
    }

    /**
     * Retrieve all notifications from the database.
     *
     * @return a list of all notifications
     */
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
}
