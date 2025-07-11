package xyz.wochib70.exam.domain;

import java.time.LocalDateTime;

public interface AggregateEvent {

    LocalDateTime createTime();

    IdentifierId eventId();

    Class<? extends AggregateEvent> eventClass();

    Class<? extends Aggregate> aggregateClass();
}
