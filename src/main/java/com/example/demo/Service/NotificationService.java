package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.NotificationRequest;
import com.example.demo.Model.Notification;
import com.example.demo.Model.User;
import com.example.demo.Repository.NotificationRepository;
import com.example.demo.Repository.UserRepository;

@Service
public class NotificationService {
    
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    public Notification createNotification(NotificationRequest req) {
        System.out.println("Creating notification for receiver: " + req.getReceiverId());

        Notification notification = new Notification();
        notification.setReceiverId(req.getReceiverId());
        notification.setType(req.getType());
        notification.setData(req.getData());

        if (req.getSenderId() != null) {
            User sender = userRepository.findById(req.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));
            notification.setSender(sender);
        }

        return notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsByReceiver(Long receiverId) {
        return notificationRepository.findByReceiverId(receiverId);
    }

    public Notification markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setStatus(false);
        return notificationRepository.save(notification);
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}
