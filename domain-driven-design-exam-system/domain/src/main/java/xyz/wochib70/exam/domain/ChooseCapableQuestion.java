package xyz.wochib70.exam.domain;

import java.util.Collection;

public interface ChooseCapableQuestion {

    /**
     * @return 选项候选列表
     */
    Collection<ChooseOption> getChooseOptions();

    /**
     * @param chooseOptions 修改为的选项列表
     */
    void updateChooseOptions(Collection<ChooseOption> chooseOptions);

    /**
     * @param chooseOptions 添加列表
     */
    void addChooseOption(ChooseOption... chooseOptions);

    /**
     * @param chooseOption 删除列表
     */
    void removeChooseOption(ChooseOption... chooseOption);
}
