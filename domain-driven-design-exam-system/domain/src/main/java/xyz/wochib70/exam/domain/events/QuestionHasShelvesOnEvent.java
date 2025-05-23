package xyz.wochib70.exam.domain.events;

import xyz.wochib70.exam.common.AbstractAggregateEvent;
import xyz.wochib70.exam.common.AggregateEvent;
import xyz.wochib70.exam.common.IdentifierId;

public class QuestionHasShelvesOnEvent extends AbstractAggregateEvent {

    private final IdentifierId questionIdentifier;

    public QuestionHasShelvesOnEvent(String eventId, IdentifierId questionIdentifier) {
        super(eventId);
        this.questionIdentifier = questionIdentifier;
    }

    public IdentifierId getQuestionIdentifier() {
        return questionIdentifier;
    }
}
