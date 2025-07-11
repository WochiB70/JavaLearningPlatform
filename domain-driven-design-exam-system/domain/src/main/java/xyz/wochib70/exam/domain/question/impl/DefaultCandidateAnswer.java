package xyz.wochib70.exam.domain.question.impl;

import xyz.wochib70.exam.domain.question.CandidateAnswer;

public class DefaultCandidateAnswer implements CandidateAnswer {

    private final String identifier;

    private final String label;

    public DefaultCandidateAnswer(String identifier, String label) {
        this.identifier = identifier;
        this.label = label;
    }


    @Override
    public String identifier() {
        return identifier;
    }

    @Override
    public String label() {
        return label;
    }
}
