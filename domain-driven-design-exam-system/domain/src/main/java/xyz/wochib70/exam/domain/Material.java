package xyz.wochib70.exam.domain;

public interface Material {


    String identifier();
    /**
     * @return 返回素材类型
     */
    MaterialType getType();

    /**
     * @return 返回素材资源
     */
    MaterialResource getResource();
}
