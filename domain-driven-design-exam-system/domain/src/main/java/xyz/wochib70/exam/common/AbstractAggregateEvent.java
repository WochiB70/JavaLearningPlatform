package xyz.wochib70.exam.common;

import java.time.LocalDateTime;

public abstract class AbstractAggregateEvent implements AggregateEvent {

    private final String aggregateId;

    private final LocalDateTime createTime;

    public AbstractAggregateEvent(String aggregateId) {
        this.aggregateId = aggregateId;
        this.createTime = LocalDateTime.now();
    }

    @Override
    public LocalDateTime createTime() {
        return createTime;
    }

    @Override
    public String eventId() {
        return aggregateId;
    }
}
