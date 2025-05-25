package xyz.wochib70.exam.domain.question.support;

import xyz.wochib70.exam.domain.IdentifierId;
import xyz.wochib70.exam.domain.question.ChildrenCapableQuestion;
import xyz.wochib70.exam.domain.question.ChildrenQuestion;
import xyz.wochib70.exam.domain.question.RenderType;
import xyz.wochib70.exam.domain.question.ShelvesStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DefaultChildrenCapableQuestion extends AbstractQuestionAggregate implements ChildrenCapableQuestion {

    private Collection<ChildrenQuestion> childrenQuestions;

    public DefaultChildrenCapableQuestion(IdentifierId identifierId, RenderType renderType) {
        super(identifierId, renderType);
    }

    public DefaultChildrenCapableQuestion(IdentifierId identifierId,
                                          RenderType renderType,
                                          ShelvesStatus shelvesStatus,
                                          Collection<ChildrenQuestion> childrenQuestions) {
        super(identifierId, renderType, shelvesStatus);
        this.childrenQuestions = new ArrayList<>(childrenQuestions);
    }

    @Override
    public Collection<? extends ChildrenQuestion> getChildrenQuestions() {
        return Collections.unmodifiableCollection(childrenQuestions);
    }

    @Override
    public void updateChildrenQuestions(Collection<? extends ChildrenQuestion> childrenQuestions) {
        canModify();
    }

    @Override
    public void addChildrenQuestion(ChildrenQuestion... childrenQuestion) {
        canModify();
    }

    @Override
    public void removeChildrenQuestions(String... childrenQuestionIds) {
        canModify();
    }

    @Override
    protected void validate() {
        throw new UnsupportedOperationException("子问题管理能力提供能不支持发布校验功能");
    }
}
