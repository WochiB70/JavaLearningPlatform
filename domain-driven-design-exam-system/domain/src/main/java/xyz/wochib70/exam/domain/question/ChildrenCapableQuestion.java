package xyz.wochib70.exam.domain.question;

import java.util.Collection;

public interface ChildrenCapableQuestion {

    /**
     * @return 子问题列表
     */
    Collection<? extends ChildrenQuestion> getChildrenQuestions();

    /**
     * @param childrenQuestions 更新子问题列表
     */
    void updateChildrenQuestions(Collection<? extends ChildrenQuestion> childrenQuestions);

    /**
     * @param childrenQuestion 添加子问题列表
     */
    void addChildrenQuestion(ChildrenQuestion... childrenQuestion);

    /**
     * @param childrenQuestionIds 删除子问题列表
     */
    void removeChildrenQuestions(String... childrenQuestionIds);
}
