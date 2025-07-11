package xyz.wochib70.exam.domain;

import java.time.LocalDateTime;

public abstract class AbstractAggregateEvent implements AggregateEvent {

    private final IdentifierId eventId;

    private final LocalDateTime createTime;

    private final Class<? extends Aggregate> aggregateClass;

    private final Class<? extends AggregateEvent> eventClass;

    public AbstractAggregateEvent(
            Class<? extends Aggregate> aggregateClass
    ) {
        this.eventId = DomainIdRegistry.nextEventId();
        this.aggregateClass = aggregateClass;
        this.eventClass = this.getClass();
        this.createTime = LocalDateTime.now();
    }

    @Override
    public LocalDateTime createTime() {
        return createTime;
    }

    @Override
    public IdentifierId eventId() {
        return eventId;
    }

    @Override
    public Class<? extends Aggregate> aggregateClass() {
        return aggregateClass;
    }

    @Override
    public Class<? extends AggregateEvent> eventClass() {
        return eventClass;
    }
}
