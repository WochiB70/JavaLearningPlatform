package xyz.wochib70.exam.common;

import java.time.LocalDateTime;

public interface AggregateEvent {

    LocalDateTime createTime();

    String eventId();

}
