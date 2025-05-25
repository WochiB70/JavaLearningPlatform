package xyz.wochib70.exam.domain;

import java.time.LocalDateTime;

public interface AggregateEvent {

    LocalDateTime createTime();

    String eventId();

}
