package com.serkomma.notifier.services;

import com.serkomma.notifier.entities.NotificationEntity;
import com.serkomma.notifier.entities.DispatcherNotificationEntity;
import com.serkomma.notifier.repositories.NotificationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationProxy notificationProxy;

    public NotificationService(NotificationRepository notificationRepository, NotificationProxy notificationProxy){
        this.notificationRepository = notificationRepository;
        this.notificationProxy = notificationProxy;
    }

    @PostMapping("/")
    public ResponseEntity<?> saveNotification(
            @RequestHeader long chatId,
            @RequestBody DispatcherNotificationEntity entityRcv){
        NotificationEntity entityBd = NotificationEntity.builder()
                .dateTime(LocalDateTime.of(entityRcv.getDate(), entityRcv.getTime()))
                .chatid(entityRcv.getChatid())
                .notification(entityRcv.getNotification())
                .build();
        notificationRepository.save(entityBd);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    @GetMapping("/")
    public ResponseEntity<?> getNotifications(
            @RequestHeader long chatId){
        return new ResponseEntity<>(notificationRepository.getAllByChatid(chatId), HttpStatus.OK);
    }

    @Scheduled(fixedRate = 1000)  //(cron = "1 * * * * *")                                // every second
    @Async
    public void sendNotification(){
        LocalTime localTime = LocalTime.now();
        var a = LocalDateTime.of(LocalDate.now(),
                LocalTime.of(localTime.getHour(), localTime.getMinute(), localTime.getSecond()));
        var entityBd = notificationRepository.findById(
                LocalDateTime.of(LocalDate.now(),
                        LocalTime.of(localTime.getHour(), localTime.getMinute(), localTime.getSecond()))).orElse(null);
        if (entityBd != null) {
            notificationProxy.send_notification(entityBd.getChatid(), DispatcherNotificationEntity.builder()
                    .chatid(entityBd.getChatid())
                    .notification(entityBd.getNotification())
                    .date(entityBd.getDateTime().toLocalDate())
                    .time(entityBd.getDateTime().toLocalTime())
                    .build());
            System.out.println("Sent!");
        }
    }
}
