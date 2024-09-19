package com.serkomma.notifier.entities;

import com.serkomma.notifier.services.ProcessSteps;
import jakarta.persistence.Entity;
import lombok.*;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class DispatcherNotificationEntity {
    @Id
    private long chatid;
    private String notification;
    private LocalDate date;
    private LocalTime time;
    private ProcessSteps step;
}
