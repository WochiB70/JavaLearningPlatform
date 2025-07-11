package xyz.wochib70.exam.domain.question;

import xyz.wochib70.exam.domain.IdentifierId;

public interface QuestionAnswer {

    /**
     * 答案的标识符和问题标识符为同一个
     *
     * @return
     */
    IdentifierId identifierId();

    /**
     * @return 答案的类型
     */
    AnswerType getAnswerType();

    /**
     * @param type 答案的类型
     */
    void modifyAnswerType(AnswerType type);


}
