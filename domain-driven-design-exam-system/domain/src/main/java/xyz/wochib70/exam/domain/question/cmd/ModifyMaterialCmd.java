package xyz.wochib70.exam.domain.question.cmd;

import xyz.wochib70.exam.domain.question.Material;

import java.util.Collection;

public record ModifyMaterialCmd(
        String questionId,
        Collection<Material> materials
) {
}
