package xyz.wochib70.exam.domain.support;

import xyz.wochib70.exam.common.IdentifierId;
import xyz.wochib70.exam.domain.Material;
import xyz.wochib70.exam.domain.MaterialCapableQuestion;
import xyz.wochib70.exam.domain.RenderType;

import java.util.Collection;
import java.util.List;

public class MultipleAnswerMultipleChoiceMaterialQuestion extends SingleAnswerMultipleChoiceQuestion implements MaterialCapableQuestion {


    public MultipleAnswerMultipleChoiceMaterialQuestion(IdentifierId identifierId, RenderType renderType) {
        super(identifierId, renderType);
    }

    @Override
    public Collection<Material> getMaterials() {
        return List.of();
    }

    @Override
    public void updateMaterials(Collection<Material> materials) {
        this.checkModify();
    }

    @Override
    public void addMaterials(Material... materials) {
        this.checkModify();
    }

    @Override
    public void removeMaterials(Material... materials) {
        this.checkModify();
    }
}
