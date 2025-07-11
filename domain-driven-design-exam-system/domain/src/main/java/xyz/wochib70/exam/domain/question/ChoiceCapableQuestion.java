package xyz.wochib70.exam.domain.question;

import xyz.wochib70.exam.domain.IdentifierId;

import java.util.Collection;

public interface ChoiceCapableQuestion {

    /**
     * @return 选项候选列表
     */
    Collection<ChoiceOption> getChoiceOptions();

    /**
     * @param choiceOption 修改为的选项列表
     */
    void updateChoiceOptions(ChoiceOption choiceOption);

    /**
     * @param label 添加选项的标签
     */
    void addChoiceOption(String label);

    /**
     * @param id 删除选项的ID
     */
    void removeChoiceOption(IdentifierId id);
}
