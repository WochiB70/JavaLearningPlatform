package xyz.wochib70.exam.domain.support;

import org.springframework.util.Assert;
import xyz.wochib70.exam.common.IdentifierId;
import xyz.wochib70.exam.domain.CandidateAnswer;
import xyz.wochib70.exam.domain.ChooseOption;
import xyz.wochib70.exam.domain.RenderType;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class SingleAnswerMultipleChoiceQuestion extends MultipleChoiceQuestion {

    public SingleAnswerMultipleChoiceQuestion(IdentifierId identifierId, RenderType renderType) {
        super(identifierId, renderType);
    }

    @Override
    protected void validate() {
        Assert.isTrue(getCandidateAnswers().size() == 1, "单选题的答案只能有一个");
        Set<String> candidateAnswerSet = getCandidateAnswers().stream().map(CandidateAnswer::identifier).collect(Collectors.toSet());
        Collection<ChooseOption> chooseOptions = getChooseOptions();
        Assert.isTrue(chooseOptions.stream().anyMatch(chooseOption -> !candidateAnswerSet.contains(chooseOption.identifier())),
                "选择题的答案应该存在选项中");
    }
}
