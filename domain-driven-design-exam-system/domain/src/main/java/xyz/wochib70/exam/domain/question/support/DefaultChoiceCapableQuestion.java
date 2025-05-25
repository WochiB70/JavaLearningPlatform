package xyz.wochib70.exam.domain.question.support;

import org.springframework.util.CollectionUtils;
import xyz.wochib70.exam.domain.IdentifierId;
import xyz.wochib70.exam.domain.question.ChoiceOption;
import xyz.wochib70.exam.domain.question.ChooseCapableQuestion;
import xyz.wochib70.exam.domain.question.RenderType;
import xyz.wochib70.exam.domain.question.ShelvesStatus;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultChoiceCapableQuestion extends AbstractQuestionAggregate implements ChooseCapableQuestion {

    private List<ChoiceOption> choices = new ArrayList<>();
    public DefaultChoiceCapableQuestion(IdentifierId identifierId, RenderType renderType) {
        super(identifierId, renderType);
    }

    public DefaultChoiceCapableQuestion(IdentifierId identifierId,
                                        RenderType renderType,
                                        ShelvesStatus shelvesStatus,
                                        Collection<ChoiceOption> chooseOptions) {
        super(identifierId, renderType, shelvesStatus);
        this.choices = new ArrayList<>(chooseOptions);
    }

    @Override
    public Collection<ChoiceOption> getChooseOptions() {
        return Collections.unmodifiableCollection(choices);
    }

    @Override
    public void updateChooseOptions(Collection<ChoiceOption> candidateChooseOptions) {
        checkModify();
        if (CollectionUtils.isEmpty(candidateChooseOptions)) {
            choices = new ArrayList<>();
            return;
        }
        this.checkChooseOptionsUnique(candidateChooseOptions);
        this.choices = new ArrayList<>(candidateChooseOptions);
    }

    @Override
    public void addChooseOption(ChoiceOption... candidateChooseOption) {
        checkModify();
        List<ChoiceOption> toAddOptions = candidateChooseOption != null ? List.of(candidateChooseOption) : Collections.emptyList();
        ArrayList<ChoiceOption> newOptions = new ArrayList<>(this.choices);
        newOptions.addAll(toAddOptions);
        this.checkChooseOptionsUnique(newOptions);
    }

    @Override
    public void removeChooseOption(ChoiceOption... chooseOption) {
        checkModify();
        List<ChoiceOption> toDeleteOptions = chooseOption != null ? List.of(chooseOption) : Collections.emptyList();
        Set<String> toDeleteIndentifierSet = toDeleteOptions.stream().map(ChoiceOption::identifier).collect(Collectors.toSet());
        this.choices.removeIf(choose -> toDeleteIndentifierSet.contains(choose.identifier()));
    }


    @Override
    protected void validate() {
        throw new UnsupportedOperationException("选项能力提供能不支持发布功能");
    }

    private void checkChooseOptionsUnique(Collection<ChoiceOption> chooseOptions) {
        Map<String, Long> duplicates = chooseOptions.stream()
                .collect(Collectors.groupingBy(
                        ChoiceOption::identifier,
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
