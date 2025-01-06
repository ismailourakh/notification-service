package com.example.notification_service.service;

import com.example.notification_service.dto.NotificationRequest;
import com.example.notification_service.model.Notification;
import com.example.notification_service.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final RestTemplate restTemplate;

    public NotificationService(NotificationRepository notificationRepository, RestTemplate restTemplate) {
        this.notificationRepository = notificationRepository;
        this.restTemplate = restTemplate;
    }

    // Sauvegarde une notification dans la base de données
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    // Récupère toutes les notifications
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // Appelle le service d'authentification pour notifier
    public void notifyAuthenticationService(String userId, String message) {
        String authServiceUrl = "http://localhost:8091/api/auth/notifications"; // URL du service Authentification

        NotificationRequest request = new NotificationRequest(userId, message);

        restTemplate.postForObject(authServiceUrl, request, Void.class);

        System.out.println("Notification envoyée au service Authentification : " + request);
    }
}
