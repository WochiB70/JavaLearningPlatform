package xyz.wochib70.exam.domain.question;

import java.util.Collection;

/**
 * 具备候选答案的问题
 *
 * @author wochib70
 */
public interface CandidateAnswerCapableQuestion {

    /**
     * 获取答案候选列表
     *
     * @return 答案候选列表
     */
    Collection<CandidateAnswer> getCandidateAnswers();

    /**
     * 更新答案候选列表
     *
     * @param candidateAnswers 更新的答案候选列表
     */
    void updateCandidateAnswers(Collection<CandidateAnswer> candidateAnswers);

    /**
     * 添加候选答案
     *
     * @param candidateAnswer 添加的候选答案
     */
    void addCandidateAnswer(CandidateAnswer... candidateAnswer);

    /**
     * @param candidateAnswer 删除候选答案
     */
    void removeCandidateAnswer(CandidateAnswer... candidateAnswer);
}
