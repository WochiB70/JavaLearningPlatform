package xyz.wochib70.exam.domain.question;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import xyz.wochib70.exam.domain.DomainIdGenerator;
import xyz.wochib70.exam.domain.question.impl.DefaultQuestion;

@RequiredArgsConstructor
@Component
public class QuestionFactory {

    private final DomainIdGenerator domainIdGenerator;

    public QuestionAggregate createQuestion() {
        return new DefaultQuestion(domainIdGenerator.nextAggregateId());
    }
}
