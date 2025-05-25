package xyz.wochib70.exam.domain.question;

import java.util.Collection;

public interface CandidateChooseOption {

    /**
     * @return 选项的唯一标识
     */
    String getIdentifier();

    /**
     * @return 所有的选项
     */
    Collection<? extends ChoiceOption> getChooseOptions();

    /**
     * @param chooseOptions 更新为新的选项列表
     */
    void updateChooseOptions(Collection<? extends ChoiceOption> chooseOptions);

    /**
     * @param chooseOption 添加选项列表
     */
    void addChooseOption(ChoiceOption... chooseOption);

    /**
     * @param chooseOption 删除选项列表
     */
    void removeChooseOption(ChoiceOption... chooseOption);
}
