package com.example.notification_service.controller;

import com.example.notification_service.model.Notification;
import com.example.notification_service.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Endpoint pour créer une notification et la sauvegarder dans la base de données
    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationService.saveNotification(notification);
    }

    // Endpoint pour récupérer toutes les notifications
    @GetMapping
    public List<Notification> getNotifications() {
        return notificationService.getAllNotifications();
    }

    // Endpoint pour envoyer une notification au service d'authentification
    @PostMapping("/send")
    public String sendNotification(@RequestParam String userId, @RequestParam String message) {
        notificationService.notifyAuthenticationService(userId, message);
        return "Notification envoyée au service Authentification";
    }
}
