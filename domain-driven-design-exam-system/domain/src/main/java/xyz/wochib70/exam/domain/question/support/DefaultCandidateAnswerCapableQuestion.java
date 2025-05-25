package xyz.wochib70.exam.domain.question.support;

import xyz.wochib70.exam.domain.IdentifierId;
import xyz.wochib70.exam.domain.question.CandidateAnswer;
import xyz.wochib70.exam.domain.question.CandidateAnswerCapableQuestion;
import xyz.wochib70.exam.domain.question.RenderType;
import xyz.wochib70.exam.domain.question.ShelvesStatus;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultCandidateAnswerCapableQuestion extends AbstractQuestionAggregate implements CandidateAnswerCapableQuestion {

    private Collection<CandidateAnswer> answers;

    public DefaultCandidateAnswerCapableQuestion(IdentifierId identifierId, RenderType renderType) {
        super(identifierId, renderType);
        this.answers = new ArrayList<>();
    }

    public DefaultCandidateAnswerCapableQuestion(IdentifierId identifierId,
                                                 RenderType renderType,
                                                 ShelvesStatus shelvesStatus,
                                                 Collection<CandidateAnswer> candidateAnswers) {
        super(identifierId, renderType, shelvesStatus);
        this.answers = new ArrayList<>(candidateAnswers);
    }


    @Override
    public Collection<CandidateAnswer> getCandidateAnswers() {
        return Collections.unmodifiableCollection(answers);
    }

    @Override
    public void updateCandidateAnswers(Collection<CandidateAnswer> candidateAnswers) {
        checkModify();
        if (candidateAnswers == null || candidateAnswers.isEmpty()) {
            answers = new ArrayList<>();
            return;
        }
        this.checkCandidateAnswersUnique(candidateAnswers);
        this.answers = new ArrayList<>(candidateAnswers);
    }

    @Override
    public void addCandidateAnswer(CandidateAnswer... candidateAnswer) {
        checkModify();
        List<CandidateAnswer> toAddAnswers = candidateAnswer != null ? List.of(candidateAnswer) : Collections.emptyList();
        ArrayList<CandidateAnswer> newAnswers = new ArrayList<>(this.answers);
        newAnswers.addAll(toAddAnswers);
        this.checkCandidateAnswersUnique(newAnswers);
        this.answers = newAnswers;
    }

    @Override
    public void removeCandidateAnswer(CandidateAnswer... candidateAnswer) {
        checkModify();
        List<CandidateAnswer> toDeleteAnswers = candidateAnswer != null ? List.of(candidateAnswer) : Collections.emptyList();
        Set<String> toDeleteIdentifierSet = toDeleteAnswers.stream().map(CandidateAnswer::identifier).collect(Collectors.toSet());
        this.answers.removeIf(answer -> toDeleteIdentifierSet.contains(answer.identifier()));
    }

    @Override
    protected void validate() {
        throw new UnsupportedOperationException("能力提供类中不支持");
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
