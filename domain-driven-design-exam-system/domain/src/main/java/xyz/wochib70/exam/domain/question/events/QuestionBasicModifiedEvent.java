package xyz.wochib70.exam.domain.question.events;

import lombok.Builder;
import lombok.Getter;
import xyz.wochib70.exam.domain.AbstractAggregateEvent;
import xyz.wochib70.exam.domain.Aggregate;
import xyz.wochib70.exam.domain.question.CalculateScoreType;
import xyz.wochib70.exam.domain.question.RenderType;
import xyz.wochib70.exam.domain.question.ShelvesStatus;
import xyz.wochib70.exam.domain.question.impl.DefaultQuestion;

@Getter
public class QuestionBasicModifiedEvent extends AbstractAggregateEvent {

    private final String aggregateId;

    private final String question;

    private final CalculateScoreType calculateScoreType;

    private final RenderType renderType;

    private final ShelvesStatus shelvesStatus;

    public QuestionBasicModifiedEvent(
            String aggregateId,
            String question,
            CalculateScoreType calculateScoreType,
            RenderType renderType,
            ShelvesStatus shelvesStatus
    ) {
        super(DefaultQuestion.class);
        this.aggregateId = aggregateId;
        this.question = question;
        this.calculateScoreType = calculateScoreType;
        this.renderType = renderType;
        this.shelvesStatus = shelvesStatus;
    }
}
