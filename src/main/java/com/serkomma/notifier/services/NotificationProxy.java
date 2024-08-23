package com.serkomma.notifier.services;

import com.serkomma.notifier.entities.DispatcherNotificationEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
@FeignClient(name="notification",
        url="${dispatcher.address}")
public interface NotificationProxy {
    @PostMapping("/")
    void send_notification(@RequestHeader long chatId,
                           @RequestBody DispatcherNotificationEntity cachedNotificationEntity);
}
