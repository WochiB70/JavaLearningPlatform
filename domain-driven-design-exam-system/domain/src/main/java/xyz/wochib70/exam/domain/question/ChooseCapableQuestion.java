package xyz.wochib70.exam.domain.question;

import java.util.Collection;

public interface ChooseCapableQuestion {

    /**
     * @return 选项候选列表
     */
    Collection<ChoiceOption> getChooseOptions();

    /**
     * @param chooseOptions 修改为的选项列表
     */
    void updateChooseOptions(Collection<ChoiceOption> chooseOptions);

    /**
     * @param chooseOptions 添加列表
     */
    void addChooseOption(ChoiceOption... chooseOptions);

    /**
     * @param chooseOption 删除列表
     */
    void removeChooseOption(ChoiceOption... chooseOption);
}
