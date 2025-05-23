package xyz.wochib70.exam.domain;

import java.util.Collection;

public interface MaterialCapableQuestion {

    /**
     * @return 返回素材列表
     */
    Collection<Material> getMaterials();

    /**
     * @param materials 更新的素材列表
     */
    void updateMaterials(Collection<Material> materials);

    /**
     * @param materials 需要添加的素材列表
     */
    void addMaterials(Material... materials);

    /**
     * @param materials 需要删除的素材列表
     */
    void removeMaterials(Material... materials);
}
