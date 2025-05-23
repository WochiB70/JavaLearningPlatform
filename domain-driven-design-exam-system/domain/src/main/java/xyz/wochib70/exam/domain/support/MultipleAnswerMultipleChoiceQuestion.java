package xyz.wochib70.exam.domain.support;

import org.springframework.util.Assert;
import xyz.wochib70.exam.common.IdentifierId;
import xyz.wochib70.exam.domain.CandidateAnswer;
import xyz.wochib70.exam.domain.ChooseOption;
import xyz.wochib70.exam.domain.RenderType;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class MultipleAnswerMultipleChoiceQuestion extends MultipleChoiceQuestion {

    public MultipleAnswerMultipleChoiceQuestion(IdentifierId identifierId, RenderType renderType) {
        super(identifierId, renderType);
    }

    @Override
    protected void validate() {
        Assert.isTrue(getCandidateAnswers().isEmpty(), "多选题的答案至少有一个");
        Collection<CandidateAnswer> candidateAnswers = getCandidateAnswers();
        Set<String> chooseIdentifierSet = getChooseOptions().stream().map(ChooseOption::identifier).collect(Collectors.toSet());
        for (CandidateAnswer candidateAnswer : candidateAnswers) {
            if (!chooseIdentifierSet.contains(candidateAnswer.identifier())) {
                throw new IllegalArgumentException("多选题的所有答案都应该存在选项中");
            }
        }
    }
}
