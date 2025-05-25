package xyz.wochib70.exam.domain.question.support;

import xyz.wochib70.exam.domain.question.ChoiceOption;

public class DefaultChoiceOption implements ChoiceOption {

    private final String identifier;

    private final String label;

    public DefaultChoiceOption(String identifier, String label) {
        this.identifier = identifier;
        this.label = label;
    }

    @Override
    public String identifier() {
        return identifier;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
