package xyz.wochib70.exam.domain.question.events;

import xyz.wochib70.exam.domain.AbstractAggregateEvent;
import xyz.wochib70.exam.domain.IdentifierId;

public class QuestionHasShelvesOffEvent  extends AbstractAggregateEvent {

    private final IdentifierId questionIdentifier;

    public QuestionHasShelvesOffEvent(String eventId, IdentifierId questionIdentifier) {
        super(eventId);
        this.questionIdentifier = questionIdentifier;
    }

    public IdentifierId getQuestionIdentifier() {
        return questionIdentifier;
    }
}
