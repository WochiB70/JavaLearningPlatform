package xyz.wochib70.exam.domain.question.support;

import xyz.wochib70.exam.domain.IdentifierId;
import xyz.wochib70.exam.domain.question.Material;
import xyz.wochib70.exam.domain.question.MaterialCapableQuestion;
import xyz.wochib70.exam.domain.question.RenderType;
import xyz.wochib70.exam.domain.question.ShelvesStatus;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultMaterialCapableQuestion extends AbstractQuestionAggregate implements MaterialCapableQuestion {

    private Collection<Material> materials;

    public DefaultMaterialCapableQuestion(IdentifierId identifierId, RenderType renderType) {
        super(identifierId, renderType);
        this.materials = new ArrayList<>();
    }

    public DefaultMaterialCapableQuestion(IdentifierId identifierId,
                                          RenderType renderType,
                                          ShelvesStatus shelvesStatus,
                                          Collection<Material> materials) {
        super(identifierId, renderType, shelvesStatus);
        this.materials = new ArrayList<>(materials);
    }

    @Override
    public Collection<Material> getMaterials() {
        return Collections.unmodifiableCollection(materials);
    }

    @Override
    public void updateMaterials(Collection<Material> materials) {
        canModify();
        if (materials == null || materials.isEmpty()){
            this.materials = new ArrayList<>();
            return;
        }
        this.checkMaterialUnique(materials);
        this.materials = new ArrayList<>(materials);
    }

    @Override
    public void addMaterials(Material... materials) {
        canModify();
        List<Material> toAddOptions = new ArrayList<>(materials != null ? List.of(materials) : Collections.emptyList());
        toAddOptions.addAll(this.materials);
        checkMaterialUnique(toAddOptions);
        this.materials = toAddOptions;
    }

    @Override
    public void removeMaterials(String... materialIds) {
        canModify();
        Set<String> toDeleteIdentifierSet = new HashSet<>(List.of(materialIds));
        this.materials.removeIf(material -> toDeleteIdentifierSet.contains(material.identifier()));
    }

    @Override
    protected void validate() {
        throw new UnsupportedOperationException(" 资源能力提供能不支持发布功能");
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
