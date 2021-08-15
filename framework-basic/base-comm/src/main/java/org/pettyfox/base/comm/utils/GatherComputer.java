package org.pettyfox.base.comm.utils;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 数学集合计算
 *
 * @author eface
 * @version 1.0
 * @date 2020/4/15 18:18
 */
public class GatherComputer {
    /**
     * 闭区间集合无交集
     *
     * @param list 无序闭区间集合
     * @return 是否存在交集
     */
    public static boolean gatherNonIntersection(List<Integer[]> list) {
        //集合区间起始点从小到大排序
        list.sort(Comparator.comparingInt(o -> o[0]));
        Integer lastStart = null;
        Integer lastEnd = null;
        for (Integer[] g : list) {
            if (null == lastStart) {
                lastStart = g[0];
                lastEnd = g[1];
            } else {
                if (lastEnd >= g[0]) {
                    return true;
                } else {
                    lastEnd = g[1];
                }
            }
        }
        return false;
    }

    /**
     * 新老集合比较
     *
     * @param oldList 老集合列表
     * @param newList 新集合列表
     * @param result  比较结果
     */
    public static <T> void gatherCompare(List<T> oldList, List<T> newList, GatherCompareResult<T> result) {
        if (null == oldList || oldList.isEmpty()) {
            if (null != newList && !newList.isEmpty()) {
                result.onAdds(newList);
            }
            result.finish();
            return;
        }
        if (null == newList || newList.isEmpty()) {
            result.onRemoves(oldList);
            result.finish();
            return;
        }
        List<T> noChangeList = new ArrayList<>();
        List<T> removeList = new ArrayList<>();
        oldList.forEach(o -> {
            if (newList.contains(o)) {
                noChangeList.add(o);
                newList.remove(o);
            } else {
                removeList.add(o);
            }
        });
        List<T> addList = new ArrayList<>(newList);
        if (removeList.size() > 0) {
            result.onRemoves(removeList);
        }
        if (noChangeList.size() > 0) {
            result.onNoChange(noChangeList);
        }
        if (addList.size() > 0) {
            result.onAdds(addList);
        }
        result.finish();


    }

    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        List<String> b = new ArrayList<>();
        a.add("a");
        a.add("b");
        a.add("c");
        b.add("b");
        b.add("c");
        b.add("d");
        GatherComputer.gatherCompare(a,
                b,
                new GatherCompareResult<String>() {
                    @Override
                    public void onAdds(List<String> addList) {
                        System.out.println("onAdds:" + addList.toString());
                    }

                    @Override
                    public void finish() {

                    }

                    @Override
                    public void onNoChange(List<String> addList) {
                        System.out.println("onNoChange:" + addList.toString());
                    }

                    @Override
                    public void onRemoves(List<String> addList) {
                        System.out.println("onRemoves:" + addList.toString());
                    }
                });
    }
}
