package xyz.wochib70.exam.domain.question.events;

import lombok.Getter;
import xyz.wochib70.exam.domain.AbstractAggregateEvent;
import xyz.wochib70.exam.domain.IdentifierId;
import xyz.wochib70.exam.domain.question.impl.DefaultQuestion;

@Getter
public class QuestionHasShelvesOnEvent extends AbstractAggregateEvent {

    private IdentifierId questionIdentifier;

    public QuestionHasShelvesOnEvent() {
        super(DefaultQuestion.class);

    }

    public IdentifierId getQuestionIdentifier() {
        return questionIdentifier;
    }
}
