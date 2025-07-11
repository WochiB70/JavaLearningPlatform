package xyz.wochib70.exam.domain.question.impl;

import xyz.wochib70.exam.domain.IdentifierId;
import xyz.wochib70.exam.domain.question.ChoiceOption;

public class DefaultChoiceOption implements ChoiceOption {

    private final IdentifierId identifier;

    private final String label;

    public DefaultChoiceOption(IdentifierId identifier, String label) {
        this.identifier = identifier;
        this.label = label;
    }

    @Override
    public IdentifierId identifier() {
        return identifier;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
