package xyz.wochib70.exam.domain;

import java.util.Collection;

public interface CandidateChooseOption {

    /**
     * @return 选项的唯一标识
     */
    String getIdentifier();

    /**
     * @return 所有的选项
     */
    Collection<? extends ChooseOption> getChooseOptions();

    /**
     * @param chooseOptions 更新为新的选项列表
     */
    void updateChooseOptions(Collection<? extends ChooseOption> chooseOptions);

    /**
     * @param chooseOption 添加选项列表
     */
    void addChooseOption(ChooseOption... chooseOption);

    /**
     * @param chooseOption 删除选项列表
     */
    void removeChooseOption(ChooseOption... chooseOption);
}
