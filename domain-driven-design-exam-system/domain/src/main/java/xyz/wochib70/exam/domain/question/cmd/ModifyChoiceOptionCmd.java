package xyz.wochib70.exam.domain.question.cmd;

import xyz.wochib70.exam.domain.question.ChoiceOption;

public record ModifyChoiceOptionCmd(
        String questionId,
        ChoiceOption  choiceOption
) {
}
