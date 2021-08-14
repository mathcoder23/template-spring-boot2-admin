package org.pettyfox.base.web.dao;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName BaseEntityNotId
 * @Description BaseEntityNotId
 * @author Petty Fox
 * @Date 2020/3/24 12:11
 * @Version 1.0
 */
@Data
public class BaseEntityNotId  implements Serializable {

    private String remark;
    private Date createTime;
    private Date updateTime;
}
