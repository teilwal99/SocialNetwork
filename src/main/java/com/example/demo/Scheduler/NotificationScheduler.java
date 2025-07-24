package com.example.demo.Scheduler;

import com.example.demo.Model.Notification;
import com.example.demo.Model.User;
import com.example.demo.Repository.NotificationRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class NotificationScheduler {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    public void sendReminderNotification() {
        User user = userRepository.findById(1L).orElse(null);
        if (user != null) {
            Notification notification = new Notification();
            notification.setReceiverId(user.getId()); // assuming you're using receiverId only
            notification.setSender(null); // or set sender if needed
            notification.setType("reminder");
            notification.setData("This is a scheduled reminder!");
            notification.setStatus(true);
            notificationRepository.save(notification);
        }
    }

    public void sendNotificationToAllUsers(String type, String data, Long senderId) {
        User sender = null;
        if (senderId != null) {
            sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        }

        List<User> allUsers = userRepository.findAll();

        for (User user : allUsers) {
            Notification notification = new Notification();
            notification.setType(type);
            notification.setData(data);
            notification.setReceiverId(user.getId()); // Only save receiverId
            notification.setSender(sender);           // Optional
            notification.setStatus(true);
            notificationRepository.save(notification);
        }
    }

   /*  @Scheduled(fixedRate = 86400000) // every 24h = 24 * 60 * 60 * 1000 ms */
    //@Scheduled(cron = "0 0 2 * * *") // Every day at 2:00 AM
    public void autoNotifyAllUsers() {
        sendNotificationToAllUsers("reminder", "Don't forget to check your updates!", null);
    }
}
