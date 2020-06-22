package com.eface.comm.base.web.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @ClassName BaseEntityLogicDelete
 * @Description 基础逻辑删除实体
 * @Author eface
 * @Date 2020/3/25 17:29
 * @Version 1.0
 */
@Data
public class BaseEntityLogicDelete extends BaseEntityNotId {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using= ToStringSerializer.class)
    protected Long id;

    /**
     * 逻辑删除
     */
    private Integer flag;
}
