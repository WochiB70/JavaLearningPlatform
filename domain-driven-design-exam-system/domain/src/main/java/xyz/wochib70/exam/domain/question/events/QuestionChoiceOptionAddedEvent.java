package xyz.wochib70.exam.domain.question.events;

import lombok.Builder;
import lombok.Getter;
import xyz.wochib70.exam.domain.AbstractAggregateEvent;
import xyz.wochib70.exam.domain.question.impl.DefaultChoiceCapableQuestion;

@Getter
public class QuestionChoiceOptionAddedEvent extends AbstractAggregateEvent {



    public QuestionChoiceOptionAddedEvent() {
        super(DefaultChoiceCapableQuestion.class);
    }
}
