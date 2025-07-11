package xyz.wochib70.exam.domain.question.impl;

import lombok.Setter;
import xyz.wochib70.exam.domain.AbstratcAggregate;
import xyz.wochib70.exam.domain.IdentifierId;
import xyz.wochib70.exam.domain.question.CandidateAnswer;
import xyz.wochib70.exam.domain.question.CandidateAnswerCapableQuestion;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultCandidateAnswerCapableQuestion extends AbstratcAggregate implements CandidateAnswerCapableQuestion {

    @Setter
    private Collection<CandidateAnswer> answers;

    public DefaultCandidateAnswerCapableQuestion(IdentifierId identifierId) {
        super(identifierId);
    }

    @Override
    public Collection<CandidateAnswer> getCandidateAnswers() {
        return Collections.unmodifiableCollection(answers);
    }

    @Override
    public void updateCandidateAnswers(Collection<CandidateAnswer> candidateAnswers) {
        if (candidateAnswers == null || candidateAnswers.isEmpty()) {
            answers = new ArrayList<>();
            return;
        }
        this.checkCandidateAnswersUnique(candidateAnswers);
        this.answers = new ArrayList<>(candidateAnswers);
    }

    @Override
    public void addCandidateAnswer(CandidateAnswer... candidateAnswer) {
        List<CandidateAnswer> toAddAnswers = candidateAnswer != null ? List.of(candidateAnswer) : Collections.emptyList();
        ArrayList<CandidateAnswer> newAnswers = new ArrayList<>(this.answers);
        newAnswers.addAll(toAddAnswers);
        this.checkCandidateAnswersUnique(newAnswers);
        this.answers = newAnswers;
    }

    @Override
    public void removeCandidateAnswer(CandidateAnswer... candidateAnswer) {
        List<CandidateAnswer> toDeleteAnswers = candidateAnswer != null ? List.of(candidateAnswer) : Collections.emptyList();
        Set<String> toDeleteIdentifierSet = toDeleteAnswers.stream().map(CandidateAnswer::identifier).collect(Collectors.toSet());
        this.answers.removeIf(answer -> toDeleteIdentifierSet.contains(answer.identifier()));
    }

    private void checkCandidateAnswersUnique(Collection<CandidateAnswer> candidateAnswers) {
        Map<String, Long> duplicates = candidateAnswers.stream()
                .collect(Collectors.groupingBy(
                        CandidateAnswer::identifier,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        if (!duplicates.isEmpty()) {
            throw new IllegalArgumentException("Duplicates answer found: " + duplicates);
        }
    }
}
