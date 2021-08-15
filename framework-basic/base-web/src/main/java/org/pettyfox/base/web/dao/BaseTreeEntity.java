package org.pettyfox.base.web.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 基础树实体
 *
 * @author pettyfox
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseTreeEntity extends BaseEntitySnowId {

    private String name;
    /**
     * 父级id,-1表示根节点
     */
    private Long parentId;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> parentIds;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> parentNames;

    private Integer pathDepth;

    private Integer orderNum;
}
