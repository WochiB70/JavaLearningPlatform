package xyz.wochib70.exam.domain.question.impl;

import lombok.Getter;
import lombok.Setter;
import xyz.wochib70.exam.domain.AbstratcAggregate;
import xyz.wochib70.exam.domain.IdentifierId;
import xyz.wochib70.exam.domain.question.ChildrenQuestion;

public class DefaultChildrenQuestion extends AbstratcAggregate implements ChildrenQuestion {

    @Setter
    @Getter
    private IdentifierId parentId;

    public DefaultChildrenQuestion(IdentifierId identifierId) {
        super(identifierId);
    }
}
