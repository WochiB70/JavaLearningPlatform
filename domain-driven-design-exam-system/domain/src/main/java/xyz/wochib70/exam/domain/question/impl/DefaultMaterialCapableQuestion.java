package xyz.wochib70.exam.domain.question.impl;

import lombok.Setter;
import xyz.wochib70.exam.domain.AbstratcAggregate;
import xyz.wochib70.exam.domain.IdentifierId;
import xyz.wochib70.exam.domain.question.Material;
import xyz.wochib70.exam.domain.question.MaterialCapableQuestion;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultMaterialCapableQuestion extends AbstratcAggregate implements MaterialCapableQuestion {

    @Setter
    private Collection<Material> materials;

    public DefaultMaterialCapableQuestion(IdentifierId identifierId) {
        super(identifierId);
    }

    @Override
    public Collection<Material> getMaterials() {
        return Collections.unmodifiableCollection(materials);
    }

    @Override
    public void updateMaterials(Collection<Material> materials) {
        if (materials == null || materials.isEmpty()) {
            this.materials = new ArrayList<>();
            return;
        }
        this.checkMaterialUnique(materials);
        this.materials = new ArrayList<>(materials);
    }

    @Override
    public void addMaterials(Material... materials) {
        List<Material> toAddOptions = new ArrayList<>(materials != null ? List.of(materials) : Collections.emptyList());
        toAddOptions.addAll(this.materials);
        checkMaterialUnique(toAddOptions);
        this.materials = toAddOptions;
    }

    @Override
    public void removeMaterials(String... materialIds) {
        Set<String> toDeleteIdentifierSet = new HashSet<>(List.of(materialIds));
        this.materials.removeIf(material -> toDeleteIdentifierSet.contains(material.identifier()));
    }

    public void checkMaterialUnique(Collection<Material> newMaterials) {
        Map<String, Long> duplicates = newMaterials.stream()
                .collect(Collectors.groupingBy(
                        Material::identifier,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        if (!duplicates.isEmpty()) {
            throw new IllegalArgumentException("Duplicates material found: " + duplicates);
        }
    }
}
