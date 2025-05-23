package xyz.wochib70.exam.domain.support;

import org.springframework.util.CollectionUtils;
import xyz.wochib70.exam.common.IdentifierId;
import xyz.wochib70.exam.domain.*;

import java.util.*;
import java.util.stream.Collectors;

public abstract class MultipleChoiceQuestion extends AbstractQuestionAggregate implements CandidateAnswerCapableQuestion, ChooseCapableQuestion {

    private Collection<ChooseOption> choices;

    private Collection<CandidateAnswer> answers;

    public MultipleChoiceQuestion(IdentifierId identifierId, RenderType renderType) {
        super(identifierId, renderType);
        choices = new ArrayList<>();
        answers = new ArrayList<>();
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
    public Collection<ChooseOption> getChooseOptions() {
        return Collections.unmodifiableCollection(choices);
    }

    @Override
    public void updateChooseOptions(Collection<ChooseOption> candidateChooseOptions) {
        checkModify();
        if (CollectionUtils.isEmpty(candidateChooseOptions)) {
            choices = new ArrayList<>();
            return;
        }
        this.checkChooseOptionsUnique(candidateChooseOptions);
        this.choices = new ArrayList<>(candidateChooseOptions);
    }

    @Override
    public void addChooseOption(ChooseOption... candidateChooseOption) {
        checkModify();
        List<ChooseOption> toAddOptions = candidateChooseOption != null ? List.of(candidateChooseOption) : Collections.emptyList();
        ArrayList<ChooseOption> newOptions = new ArrayList<>(this.choices);
        newOptions.addAll(toAddOptions);
        this.checkChooseOptionsUnique(newOptions);
    }

    @Override
    public void removeChooseOption(ChooseOption... chooseOption) {
        checkModify();
        List<ChooseOption> toDeleteOptions = chooseOption != null ? List.of(chooseOption) : Collections.emptyList();
        Set<String> toDeleteIndentifierSet = toDeleteOptions.stream().map(ChooseOption::identifier).collect(Collectors.toSet());
        this.choices.removeIf(choose -> toDeleteIndentifierSet.contains(choose.identifier()));
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

    private void checkChooseOptionsUnique(Collection<ChooseOption> chooseOptions) {
        Map<String, Long> duplicates = chooseOptions.stream()
                .collect(Collectors.groupingBy(
                        ChooseOption::identifier,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        if (!duplicates.isEmpty()) {
            throw new IllegalArgumentException("Duplicates choose option found: " + duplicates);
        }
    }
}
