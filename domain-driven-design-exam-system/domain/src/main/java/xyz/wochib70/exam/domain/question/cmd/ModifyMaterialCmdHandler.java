package xyz.wochib70.exam.domain.question.cmd;

import org.springframework.stereotype.Component;
import xyz.wochib70.exam.domain.question.MaterialCapableQuestion;
import xyz.wochib70.exam.domain.question.repository.ModifyMaterialRepository;

@Component
public class ModifyMaterialCmdHandler {

    private final ModifyMaterialRepository modifyMaterialRepository;

    public ModifyMaterialCmdHandler(
            ModifyMaterialRepository modifyMaterialRepository
    ) {
        this.modifyMaterialRepository = modifyMaterialRepository;
    }

    public void handle(ModifyMaterialCmd cmd) {
        MaterialCapableQuestion question = this.modifyMaterialRepository.findByIdentifier(cmd.questionId());
        question.updateMaterials(cmd.materials());
        modifyMaterialRepository.save(question);
    }
}
