package com.serkomma.notifier.repositories;

import com.serkomma.notifier.entities.NotificationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends CrudRepository<NotificationEntity, LocalDateTime> {
    List<NotificationEntity> getAllByChatid(long chatId);
}
