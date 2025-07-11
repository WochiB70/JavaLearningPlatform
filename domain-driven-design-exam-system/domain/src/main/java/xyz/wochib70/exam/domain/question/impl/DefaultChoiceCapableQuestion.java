package xyz.wochib70.exam.domain.question.impl;

import lombok.Setter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import xyz.wochib70.exam.domain.AbstratcAggregate;
import xyz.wochib70.exam.domain.DomainIdRegistry;
import xyz.wochib70.exam.domain.IdentifierId;
import xyz.wochib70.exam.domain.question.ChoiceOption;
import xyz.wochib70.exam.domain.question.ChoiceCapableQuestion;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultChoiceCapableQuestion extends AbstratcAggregate implements ChoiceCapableQuestion {

    @Setter
    private List<ChoiceOption> choices = new ArrayList<>();

    public DefaultChoiceCapableQuestion(IdentifierId identifierId) {
        super(identifierId);
    }


    @Override
    public Collection<ChoiceOption> getChoiceOptions() {
        return Collections.unmodifiableCollection(choices);
    }

    @Override
    public void updateChoiceOptions(ChoiceOption updateChoiceOption) {
//        ChoiceOption find = findChoiceOptiona(updateChoiceOption.identifier());
//        if (!Objects.equals(find, updateChoiceOption)) {
//
//        }
    }

    @Override
    public void addChoiceOption(String label) {
        if (!StringUtils.hasText(label)) {
            throw new IllegalArgumentException("Choice option label cannot be empty");
        }
        DefaultChoiceOption option = new DefaultChoiceOption(
                DomainIdRegistry.nextAggregateId(),
                label
        );
//        publishEvent(new DefaultChoiceOptionAddedEvent(this, option));
    }

    @Override
    public void removeChoiceOption(IdentifierId id) {

    }


    private void checkChooseOptionsUnique(Collection<ChoiceOption> chooseOptions) {
        Map<IdentifierId, Long> duplicates = chooseOptions.stream()
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
