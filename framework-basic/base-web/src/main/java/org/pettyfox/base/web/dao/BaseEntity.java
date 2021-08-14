package org.pettyfox.base.web.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseEntity<T> extends BaseEntityNotId {

    @TableId(value = "id", type = IdType.AUTO)
    protected T id;

}
