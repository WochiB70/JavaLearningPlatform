package xyz.wochib70.exam.domain;

public interface ChooseOption {

    /**
     * @return 选项的唯一标识
     */
    String identifier();


    /**
     * @return 展示数据
     */
    String getLabel();
}
