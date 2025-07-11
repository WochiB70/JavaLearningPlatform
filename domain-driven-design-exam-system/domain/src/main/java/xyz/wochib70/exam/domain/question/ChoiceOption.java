package xyz.wochib70.exam.domain.question;

import xyz.wochib70.exam.domain.IdentifierId;

public interface ChoiceOption {

    /**
     * @return 选项的唯一标识
     */
    IdentifierId identifier();


    /**
     * @return 展示数据
     */
    String getLabel();
}
