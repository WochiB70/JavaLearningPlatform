package xyz.wochib70.exam.domain;

import java.util.Collection;

public interface Aggregate {


    IdentifierId identifierId();

    /**
     * @return 领域内发生的事件，按照发生顺序
     */
    Collection<? super AggregateEvent> getEvents();

}
