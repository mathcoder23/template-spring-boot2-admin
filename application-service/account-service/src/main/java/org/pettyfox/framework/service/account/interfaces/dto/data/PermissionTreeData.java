package org.pettyfox.framework.service.account.interfaces.dto.data;


import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import org.pettyfox.base.web.dto.data.BaseTree;
import org.pettyfox.framework.service.account.doamin.account.po.Permission;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author eface .FW
 * @version 1.0
 * @date 2021/4/15 11:18
 */
public class PermissionTreeData extends BaseTree<Long, Permission> {
    public static List<PermissionTreeData> buildByList(Collection<Permission> list) {
        if (null == list || list.isEmpty()) {
            return null;
        }
        List<PermissionTreeData> resultList = new ArrayList<>();
        List<Tree<Long>> treeList = TreeUtil.build(new ArrayList<>(list), null, new TreeNodeConfig().setWeightKey("orderNum"), (treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.setParentId(treeNode.getParentId());
            tree.setWeight(treeNode.getOrderNum());
            tree.putExtra("obj", treeNode);
            tree.putExtra("label", treeNode.getName());
        });
        treeList.forEach(tree -> {
            resultList.add(eachTree(tree));
        });
        return resultList;
    }

    private static PermissionTreeData eachTree(Tree<Long> tree) {
        PermissionTreeData treeData = new PermissionTreeData();
        treeData.setId(tree.getId());
        treeData.setPId(tree.getParentId());
        treeData.setData((Permission) tree.get("obj"));
        treeData.setLabel((String) tree.get("label"));
        treeData.setChildren(new ArrayList<>());
        if (null != tree.getChildren() && !tree.getChildren().isEmpty()) {
            for (Tree<Long> children : tree.getChildren()) {
                treeData.getChildren().add(eachTree(children));
            }
        }
        return treeData;
    }
}
