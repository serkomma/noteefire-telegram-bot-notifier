package com.serkomma.notifier.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Table(name = "notifications")
public class NotificationEntity implements Serializable {
    @Id
    private LocalDateTime dateTime;
    private long chatid;
    private String notification;
}
