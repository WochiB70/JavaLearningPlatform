package xyz.wochib70.exam.domain.question.impl;

import lombok.Setter;
import xyz.wochib70.exam.domain.AbstratcAggregate;
import xyz.wochib70.exam.domain.IdentifierId;
import xyz.wochib70.exam.domain.question.ChildrenCapableQuestion;
import xyz.wochib70.exam.domain.question.ChildrenQuestion;

import java.util.Collection;
import java.util.Collections;

public class DefaultChildrenCapableQuestion extends AbstratcAggregate implements ChildrenCapableQuestion {

    @Setter
    private Collection<ChildrenQuestion> childrenQuestions;

    public DefaultChildrenCapableQuestion(IdentifierId identifierId) {
        super(identifierId);
    }


    @Override
    public Collection<? extends ChildrenQuestion> getChildrenQuestions() {
        return Collections.unmodifiableCollection(childrenQuestions);
    }

    @Override
    public void updateChildrenQuestions(Collection<? extends ChildrenQuestion> childrenQuestions) {
    }

    @Override
    public void addChildrenQuestion(ChildrenQuestion... childrenQuestion) {
    }

    @Override
    public void removeChildrenQuestions(String... childrenQuestionIds) {
    }

}
