package xyz.wochib70.exam.common;

import org.springframework.util.Assert;

import java.util.*;

public abstract class AbstratcAggregate implements Aggregate {

    private final IdentifierId identifierId;

    private final Deque<AggregateEvent> events;

    public AbstratcAggregate(IdentifierId identifierId) {
        Assert.notNull(identifierId, "identifierId must not be null");
        this.identifierId = identifierId;
        this.events = new ArrayDeque<>();
    }

    @Override
    public IdentifierId identiferId() {
        return identifierId;
    }

    @Override
    public Collection<? super AggregateEvent> getEvents() {
        return Collections.unmodifiableCollection(events);
    }

    @Override
    public void publishEvent(AggregateEvent event) {
        events.addLast(event);
    }

}
