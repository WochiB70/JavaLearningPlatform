package xyz.wochib70.exam.domain.question;

import xyz.wochib70.exam.domain.Aggregate;

import java.io.Serializable;

public interface QuestionAggregate extends Aggregate, Serializable {

    /**
     * @return 问题（让用户明白这个题是什么意思）
     */
    String getQuestion();

    /**
     * @param question 更新的问题
     */
    void updateQuestion(String question);

    /**
     * @return 得分的计算方式
     */
    CalculateScoreType getScoreType();

    /**
     * @param scoreType 修改得分的计算方式
     */
    void updateScoreType(CalculateScoreType scoreType);

    /**
     * @param renderType 更新渲染模式
     */
    void updateRenderType(RenderType renderType);

    /**
     * 获取渲染类型
     */
    RenderType getRenderType();

    /**
     * 上架问题，标识为可练习和可被组卷
     */
    void shelvesOn();

    /**
     * 下架问题
     */
    void shelvesOff();
}
