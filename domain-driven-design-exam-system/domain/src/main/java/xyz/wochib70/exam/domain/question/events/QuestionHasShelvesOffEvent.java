package xyz.wochib70.exam.domain.question.events;

import lombok.Getter;
import lombok.Setter;
import xyz.wochib70.exam.domain.AbstractAggregateEvent;
import xyz.wochib70.exam.domain.IdentifierId;
import xyz.wochib70.exam.domain.question.impl.DefaultQuestion;

@Getter
public class QuestionHasShelvesOffEvent extends AbstractAggregateEvent {

    private final IdentifierId questionIdentifier;

    public QuestionHasShelvesOffEvent(
            IdentifierId questionIdentifier
    ) {
        super( DefaultQuestion.class);
        this.questionIdentifier = questionIdentifier;
    }

}
