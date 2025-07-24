package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.NotificationRequest;
import com.example.demo.Model.Notification;
import com.example.demo.Service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<Notification> create(@RequestBody NotificationRequest req) {
        Notification created = notificationService.createNotification(req);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/receiver/{receiverId}")
    public ResponseEntity<List<Notification>> getByReceiver(@PathVariable Long receiverId) {
        return ResponseEntity.ok(notificationService.getNotificationsByReceiver(receiverId));
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<Notification> markAsRead(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.markAsRead(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
