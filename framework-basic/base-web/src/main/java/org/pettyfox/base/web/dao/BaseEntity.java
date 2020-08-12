package org.pettyfox.base.web.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class BaseEntity<T> extends BaseEntityNotId{
    @TableId(value = "id", type = IdType.AUTO)
    protected T id;

    /**
     * 逻辑删除
     */
    private Boolean flag;
}
