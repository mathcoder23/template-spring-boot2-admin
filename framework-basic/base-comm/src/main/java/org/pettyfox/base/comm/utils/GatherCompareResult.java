package org.pettyfox.base.comm.utils;

import java.util.List;

/**
 * 集合比较结果
 *
 * @author eface
 * @version 1.0
 * @date 2020/7/2 11:16
 */
public interface GatherCompareResult<T> {


    /**
     * 集合比较后
     * 第一顺序调用
     *
     * @param removeList 删除的,List不会为空
     */
    void onRemoves(List<T> removeList);

    /**
     * 集合比较后
     * 第二顺序调用
     *
     * @param noChangeList 保持不变的,List不会为空
     */
    void onNoChange(List<T> noChangeList);

    /**
     * 集合比较后
     * 第三顺序调用
     *
     * @param addList 新增的,List不会为空
     */
    void onAdds(List<T> addList);

    /**
     * 完成
     * 第四顺序调用
     */
    void finish();
}
